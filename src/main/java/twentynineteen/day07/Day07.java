package twentynineteen.day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

class Day07 {
  // WILL NEED TO KEEP STATE OF EACH AMPLIFIER.



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
    addAllPhasePerms(new int[]{0,1,2,3,4});
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

  int solvePartTwo(File file) throws FileNotFoundException {
    List<Integer> program = loadProgramFromFile(file);
    allPhasePerms = new HashSet<>();
    addAllPhasePerms(new int[]{5,6,7,8,9});
    int maxSignal = Integer.MIN_VALUE;
    for (int[] perm : allPhasePerms) {
      BlockingQueue<Integer> AInput = new LinkedBlockingDeque<>(Arrays.asList(perm[0], 0));
      List<Integer> AProgram = new ArrayList<>(program);
      Collections.copy(AProgram, program);
      BlockingQueue<Integer> BInput = new LinkedBlockingDeque<>(Arrays.asList(perm[1]));
      List<Integer> BProgram = new ArrayList<>(program);
      Collections.copy(BProgram, program);
      BlockingQueue<Integer> CInput = new LinkedBlockingDeque<>(Arrays.asList(perm[2]));
      List<Integer> CProgram = new ArrayList<>(program);
      Collections.copy(CProgram, program);
      BlockingQueue<Integer> DInput = new LinkedBlockingDeque<>(Arrays.asList(perm[3]));
      List<Integer> DProgram = new ArrayList<>(program);
      Collections.copy(DProgram, program);
      BlockingQueue<Integer> EInput = new LinkedBlockingDeque<>(Arrays.asList(perm[4]));
      List<Integer> EProgram = new ArrayList<>(program);
      Collections.copy(EProgram, program);
      Amplifier A = new Amplifier(AProgram, AInput, BInput);
      Amplifier B = new Amplifier(BProgram, BInput, CInput);
      Amplifier C = new Amplifier(CProgram, CInput, DInput);
      Amplifier D = new Amplifier(DProgram, DInput, EInput);
      Amplifier E = new Amplifier(EProgram, EInput, AInput);
      List<Thread> threadList = new ArrayList<>();
      Thread tA = new Thread(A);
      tA.start();
      threadList.add(tA);
      Thread tB = new Thread(B);
      tB.start();
      threadList.add(tB);
      Thread tC = new Thread(C);
      tC.start();
      threadList.add(tC);
      Thread tD = new Thread(D);
      tD.start();
      threadList.add(tD);
      Thread tE = new Thread(E);
      tE.start();
      threadList.add(tE);
      for (Thread t : threadList) {
        try { t.join(); } catch (Exception e){}
      }
      if (E.finalOutput > maxSignal) {
        maxSignal = E.finalOutput;
      }
    }
    return maxSignal;
  }

  private void addAllPhasePerms(int[] phases) {
    int[] indexes = new int[phases.length];
    for (int i = 0; i < phases.length; i++) {
      indexes[i] = 0;
    }

    allPhasePerms.add(Arrays.copyOf(phases, phases.length));

    int i = 0;
    while (i < phases.length) {
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
    int output = 0;
    int index = 0;
    while (index < (program.size())) {
      String operation = String.valueOf(program.get(index));
      if (operation.equals(HALT)) {
        return output;
      }
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
    System.out.println("input: " + input);
    program.set(writePos, input);
    return program;
  }

  private int performOutput(List<Integer> program, Parameter param) {
    System.out.println("output: " + getParamVal(program, param));
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

  class Amplifier implements Runnable {
    private List<Integer> program;
    private BlockingQueue<Integer> input;
    private BlockingQueue<Integer> output;
    private Integer finalOutput;

    public Amplifier(List<Integer> program, BlockingQueue<Integer> input, BlockingQueue<Integer> output) {
      this.program = program;
      this.input = input;
      this.output = output;
      finalOutput = 0;
    }

    @Override
    public void run() {
      try {
        this.computeProgram();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    private void computeProgram() throws InterruptedException {
      int output = 0;
      int index = 0;
      while (index < (program.size())) {
        String operation = String.valueOf(program.get(index));
        if (operation.equals(HALT)) {
          System.out.println("final output: " + output + ", finished");
          finalOutput = output;
          return;
        }
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
              program = performInput(program, program.get(index + 1), input.take());
              index += 2;
              continue;
            case "4":
              output = this.performOutput(program, new Parameter(Mode.POSITION, program.get(index + 1)));
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
        throw new RuntimeException("Should not throw this");
      }
      System.out.println("final output: " + output + ", finished");
      finalOutput = output;
    }

    private int performOutput(List<Integer> program, Parameter param) throws InterruptedException {
      System.out.println("output: " + getParamVal(program, param));
      output.put(getParamVal(program, param));
      return getParamVal(program, param);
    }
  }
}
