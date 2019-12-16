package day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day07 {
  // OPCODES
  private static final String ADD = "01";
  private static final String MULTIPLY = "02";
  private static final String OUTPUT = "04";
  private static final String JUMP_IF_TRUE = "05";
  private static final String JUMP_IF_FALSE = "06";
  private static final String LESS_THAN = "07";
  private static final String EQUALS = "08";
  private static final String HALT = "99";

  private Set<int[]> allPhasePerms;

  int solvePartOne(File file) throws FileNotFoundException {
    List<Integer> program = loadProgramFromFile(file);
    allPhasePerms = new HashSet<>();
    addAllPhasePerms(5, new int[]{0,1,2,3,4});
    int maxSignal = Integer.MIN_VALUE;
    for (int[] perm : allPhasePerms) {
      int index = 0;
      Queue<Integer> inputs = new LinkedList<>();
      int output = 0;
      while (index < perm.length) {
        inputs.add(perm[index]);
        inputs.add(output);
        output = computeProgram(program, inputs);
        inputs.clear();
        index++;
      }
      if (output > maxSignal) maxSignal = output;
    }
    return maxSignal;
  }

  private void addAllPhasePerms(int numPhases, int[] phases) {
    int[] indexes = new int[numPhases];
    for (int i = 0; i < numPhases; i++) {
      indexes[i] = 0;
    }

    allPhasePerms.add(Arrays.copyOf(phases, phases.length));

    int i = 0;
    while (i < numPhases) {
      if (indexes[i] < i) {
        swap(phases, i % 2 == 0 ?  0: indexes[i], i);
        allPhasePerms.add(Arrays.copyOf(phases, phases.length));
        indexes[i]++;
        i = 0;
      }
      else {
        indexes[i] = 0;
        i++;
      }
    }
  }

  private void swap(int[] input, int a, int b) {
    int tmp = input[a];
    input[a] = input[b];
    input[b] = tmp;
  }

  private int computeProgram(List<Integer> program, Queue<Integer> inputs) {
    if (inputs.size() != 2) throw new RuntimeException("TOO MANY INPUTS");
    int output = 0;
    int index = 0;
    while (index < (program.size()) && !program.get(index).equals(Integer.parseInt(HALT))) {
      String operation = String.valueOf(program.get(index));
      if (operation.length() == 1) {
        // no param modes
        switch (operation) {
          case "1":
            program = performAdd(program, new Parameter(Mode.POSITION, program.get(index + 1)),
                new Parameter(Mode.POSITION, program.get(index + 2)), program.get(index + 3));
            index += 4;
            continue;
          case "2":
            program = performMultiply(program, new Parameter(Mode.POSITION, program.get(index + 1)),
                new Parameter(Mode.POSITION, program.get(index + 2)), program.get(index + 3));
            index += 4;
            continue;
          case "3":
            program = performInput(program, program.get(index + 1), inputs.poll());
            index += 2;
            continue;
          case "4":
            output = performOutput(program, new Parameter(Mode.POSITION, program.get(index + 1)));
            index += 2;
            continue;
          case "5":
            index = performJumpIfTrue(index, program, new Parameter(Mode.POSITION, program.get(index + 1)),
                new Parameter(Mode.POSITION, program.get(index + 2)));
            continue;
          case "6":
            index = performJumpIfFalse(index, program, new Parameter(Mode.POSITION, program.get(index + 1)),
                new Parameter(Mode.POSITION, program.get(index + 2)));
            continue;
          case "7":
            program = performLessThan(program, new Parameter(Mode.POSITION, program.get(index + 1)),
                new Parameter(Mode.POSITION, program.get(index + 2)), program.get(index + 3));
            index += 4;
            continue;
          case "8":
            program = performEqual(program, new Parameter(Mode.POSITION, program.get(index + 1)),
                new Parameter(Mode.POSITION, program.get(index + 2)), program.get(index + 3));
            index += 4;
            continue;
        }
      } else {
        String opCode = operation.substring(operation.length() - 2);
        int[] modes;
        if (operation.length() > 2) {
          modes =  operation.substring(0, operation.length()-2).chars()
              .map(c -> Integer.parseInt(String.valueOf((char) c)))
              .toArray();
        } else {
          modes = new int[0];
        }
        switch (opCode) {
          case ADD: {
            Parameter one;
            Parameter two;
            if (modes.length == 0) {
              one = new Parameter(Mode.POSITION, program.get(index + 1));
            } else {
              if (modes[modes.length - 1] == 1) one = new Parameter(Mode.IMMEDIATE, program.get(index + 1));
              else one = new Parameter(Mode.POSITION, program.get(index + 1));
            }
            if (modes.length < 2) {
              two = new Parameter(Mode.POSITION, program.get(index + 2));
            } else {
              if (modes[modes.length - 2] == 1) two = new Parameter(Mode.IMMEDIATE, program.get(index + 2));
              else two = new Parameter(Mode.POSITION, program.get(index + 2));
            }
            program = performAdd(program, one, two, program.get(index + 3));
            index += 4;
            continue;
          }
          case MULTIPLY: {
            Parameter one;
            Parameter two;
            if (modes.length == 0) {
              one = new Parameter(Mode.POSITION, program.get(index + 1));
            } else {
              if (modes[modes.length - 1] == 1) one = new Parameter(Mode.IMMEDIATE, program.get(index + 1));
              else one = new Parameter(Mode.POSITION, program.get(index + 1));
            }
            if (modes.length < 2) {
              two = new Parameter(Mode.POSITION, program.get(index + 2));
            } else {
              if (modes[modes.length - 2] == 1) two = new Parameter(Mode.IMMEDIATE, program.get(index + 2));
              else two = new Parameter(Mode.POSITION, program.get(index + 2));
            }
            program = performMultiply(program, one, two, program.get(index + 3));
            index += 4;
            continue;
          }
          case OUTPUT: {
            Parameter param = new Parameter(Mode.IMMEDIATE, program.get(index + 1));
            output = performOutput(program, param);
            index += 2;
            continue;
          }
          case JUMP_IF_TRUE: {
            Parameter one;
            Parameter two;
            if (modes.length == 0) {
              one = new Parameter(Mode.POSITION, program.get(index + 1));
            } else {
              if (modes[modes.length - 1] == 1) one = new Parameter(Mode.IMMEDIATE, program.get(index + 1));
              else one = new Parameter(Mode.POSITION, program.get(index + 1));
            }
            if (modes.length < 2) {
              two = new Parameter(Mode.POSITION, program.get(index + 2));
            } else {
              if (modes[modes.length - 2] == 1) two = new Parameter(Mode.IMMEDIATE, program.get(index + 2));
              else two = new Parameter(Mode.POSITION, program.get(index + 2));
            }
            index = performJumpIfTrue(index, program, one, two);
            continue;
          }
          case JUMP_IF_FALSE: {
            Parameter one;
            Parameter two;
            if (modes.length == 0) {
              one = new Parameter(Mode.POSITION, program.get(index + 1));
            } else {
              if (modes[modes.length - 1] == 1) one = new Parameter(Mode.IMMEDIATE, program.get(index + 1));
              else one = new Parameter(Mode.POSITION, program.get(index + 1));
            }
            if (modes.length < 2) {
              two = new Parameter(Mode.POSITION, program.get(index + 2));
            } else {
              if (modes[modes.length - 2] == 1) two = new Parameter(Mode.IMMEDIATE, program.get(index + 2));
              else two = new Parameter(Mode.POSITION, program.get(index + 2));
            }
            index = performJumpIfFalse(index, program, one, two);
            continue;
          }
          case LESS_THAN: {
            Parameter one;
            Parameter two;
            if (modes.length == 0) {
              one = new Parameter(Mode.POSITION, program.get(index + 1));
            } else {
              if (modes[modes.length - 1] == 1) one = new Parameter(Mode.IMMEDIATE, program.get(index + 1));
              else one = new Parameter(Mode.POSITION, program.get(index + 1));
            }
            if (modes.length < 2) {
              two = new Parameter(Mode.POSITION, program.get(index + 2));
            } else {
              if (modes[modes.length - 2] == 1) two = new Parameter(Mode.IMMEDIATE, program.get(index + 2));
              else two = new Parameter(Mode.POSITION, program.get(index + 2));
            }
            program = performLessThan(program, one, two, program.get(index + 3));
            index += 4;
            continue;
          }
          case EQUALS: {
            Parameter one;
            Parameter two;
            if (modes.length == 0) {
              one = new Parameter(Mode.POSITION, program.get(index + 1));
            } else {
              if (modes[modes.length - 1] == 1) one = new Parameter(Mode.IMMEDIATE, program.get(index + 1));
              else one = new Parameter(Mode.POSITION, program.get(index + 1));
            }
            if (modes.length < 2) {
              two = new Parameter(Mode.POSITION, program.get(index + 2));
            } else {
              if (modes[modes.length - 2] == 1) two = new Parameter(Mode.IMMEDIATE, program.get(index + 2));
              else two = new Parameter(Mode.POSITION, program.get(index + 2));
            }
            program = performEqual(program, one, two, program.get(index + 3));
            index += 4;
            continue;
          }
        }
      }
      throw new RuntimeException("This should not run");
    }

    return output;
  }

  private List<Integer> performAdd(List<Integer> program, Parameter param1, Parameter param2, Integer resultPos) {
    Integer val1 = getParamVal(program, param1);
    Integer val2 = getParamVal(program, param2);
    program.set(resultPos, val1 + val2);
    return program;
  }

  private List<Integer> performMultiply(List<Integer> program, Parameter param1, Parameter param2, Integer resultPos) {
    Integer val1 = getParamVal(program, param1);
    Integer val2 = getParamVal(program, param2);
    program.set(resultPos, val1 * val2);
    return program;
  }

  private List<Integer> performInput(List<Integer> program, Integer writePos, Integer input) {
    program.set(writePos, input);
    return program;
  }

  private int performOutput(List<Integer> program, Parameter param) {
    return getParamVal(program, param);
  }

  private int performJumpIfTrue(Integer index, List<Integer> program, Parameter param1, Parameter param2) {
    Integer val1 = getParamVal(program, param1);
    Integer val2 = getParamVal(program, param2);
    if (val1 != 0) {
      // return what index should be now
      return val2;
    } else {
      return index + 3;
    }
  }

  private int performJumpIfFalse(Integer index, List<Integer> program, Parameter param1, Parameter param2) {
    Integer val1 = getParamVal(program, param1);
    Integer val2 = getParamVal(program, param2);
    if (val1 == 0) {
      // return what index should be now
      return val2;
    } else {
      return index + 3;
    }
  }

  private List<Integer> performLessThan(List<Integer> program, Parameter param1, Parameter param2, Integer writePos) {
    Integer val1 = getParamVal(program, param1);
    Integer val2 = getParamVal(program, param2);
    if (val1 < val2) {
      program.set(writePos, 1);
    } else {
      program.set(writePos, 0);
    }
    return program;
  }

  private List<Integer> performEqual(List<Integer> program, Parameter param1, Parameter param2, Integer writePos) {
    Integer val1 = getParamVal(program, param1);
    Integer val2 = getParamVal(program, param2);
    if (val1.equals(val2)) {
      program.set(writePos, 1);
    } else {
      program.set(writePos, 0);
    }
    return program;
  }

  private Integer getParamVal(List<Integer> program, Parameter param) {
    switch (param.mode) {
      case POSITION:
        return program.get(param.value);
      case IMMEDIATE:
        return param.value;
      default:
        throw new RuntimeException("invalid param mode: " + param.mode);
    }
  }

  private enum Mode {
    POSITION,
    IMMEDIATE
  }

  private class Parameter {
    Mode mode;
    Integer value;

    Parameter(Mode mode, Integer value) {
      this.mode = mode;
      this.value = value;
    }
  }

  private List<Integer> loadProgramFromFile(File file) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(file);
    // take input line, split on ',' and convert to list of Integers
    return Arrays.stream(fileScanner.nextLine().split(","))
        .map(Integer::valueOf)
        .collect(Collectors.toCollection(ArrayList::new));
  }
}
