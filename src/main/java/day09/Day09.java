package day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Day09 {
  // OPCODES
  private static final String ADD = "01";
  private static final String MULTIPLY = "02";
  private static final String INPUT = "03";
  private static final String OUTPUT = "04";
  private static final String JUMP_IF_TRUE = "05";
  private static final String JUMP_IF_FALSE = "06";
  private static final String LESS_THAN = "07";
  private static final String EQUALS = "08";
  private static final String ADJUST_RELATIVE = "09";
  private static final String HALT = "99";

  private Long relativeBase = 0L;

  Long solvePartOne(File file, Queue<Long> inputs) throws FileNotFoundException {
    Map<Long, Long> program = loadProgramFromFile(file);
    return computeProgram(program, inputs);
  }

  private Long computeProgram(Map<Long, Long> program, Queue<Long> inputs) {
    relativeBase = 0L;
    Long output = 0L;
    Long index = 0L;
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
          case "9":
            adjustRelativeBase(program, new Parameter(Mode.POSITION, program.get(index + 1)));
            index += 2;
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
              else if (modes[modes.length - 1] == 2) one = new Parameter(Mode.RELATIVE, program.get(index + 1));
              else one = new Parameter(Mode.POSITION, program.get(index + 1));
            }
            if (modes.length < 2) {
              two = new Parameter(Mode.POSITION, program.get(index + 2));
            } else {
              if (modes[modes.length - 2] == 1) two = new Parameter(Mode.IMMEDIATE, program.get(index + 2));
              else if (modes[modes.length - 2] == 2) two = new Parameter(Mode.RELATIVE, program.get(index + 2));
              else two = new Parameter(Mode.POSITION, program.get(index + 2));
            }
            Long writePos;
            if (modes.length < 3) {
              writePos = program.get(index + 3);
            } else {
              if (modes[modes.length - 3] == 2) writePos = program.get(index + 3) + relativeBase;
              else writePos = program.get(index + 3);
            }
            program = performAdd(program, one, two, writePos);
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
              else if (modes[modes.length - 1] == 2) one = new Parameter(Mode.RELATIVE, program.get(index + 1));
              else one = new Parameter(Mode.POSITION, program.get(index + 1));
            }
            if (modes.length < 2) {
              two = new Parameter(Mode.POSITION, program.get(index + 2));
            } else {
              if (modes[modes.length - 2] == 1) two = new Parameter(Mode.IMMEDIATE, program.get(index + 2));
              else if (modes[modes.length - 2] == 2) two = new Parameter(Mode.RELATIVE, program.get(index + 2));
              else two = new Parameter(Mode.POSITION, program.get(index + 2));
            }
            Long writePos;
            if (modes.length < 3) {
              writePos = program.get(index + 3);
            } else {
              if (modes[modes.length - 3] == 2) writePos = program.get(index + 3) + relativeBase;
              else writePos = program.get(index + 3);
            }
            program = performMultiply(program, one, two, writePos);
            index += 4;
            continue;
          }
          case INPUT: {
            Long writePos;
            if (modes[modes.length - 1] == 2) writePos = program.get(index + 1) + relativeBase;
            else writePos = program.get(index + 1);
            program = performInput(program, writePos, inputs.poll());
            index += 2;
            continue;
          }
          case OUTPUT: {
            Parameter param;
            if (modes[modes.length - 1] == 1) param = new Parameter(Mode.IMMEDIATE, program.get(index + 1));
            else if (modes[modes.length - 1] == 2) param = new Parameter(Mode.RELATIVE, program.get(index + 1));
            // note: because leading zeros are trimmed this case should never be hit
            else param = new Parameter(Mode.POSITION, program.get(index + 1));
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
              else if (modes[modes.length - 1] == 2) one = new Parameter(Mode.RELATIVE, program.get(index + 1));
              else one = new Parameter(Mode.POSITION, program.get(index + 1));
            }
            if (modes.length < 2) {
              two = new Parameter(Mode.POSITION, program.get(index + 2));
            } else {
              if (modes[modes.length - 2] == 1) two = new Parameter(Mode.IMMEDIATE, program.get(index + 2));
              else if (modes[modes.length - 2] == 2) two = new Parameter(Mode.RELATIVE, program.get(index + 2));
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
              else if (modes[modes.length - 1] == 2) one = new Parameter(Mode.RELATIVE, program.get(index + 1));
              else one = new Parameter(Mode.POSITION, program.get(index + 1));
            }
            if (modes.length < 2) {
              two = new Parameter(Mode.POSITION, program.get(index + 2));
            } else {
              if (modes[modes.length - 2] == 1) two = new Parameter(Mode.IMMEDIATE, program.get(index + 2));
              else if (modes[modes.length - 2] == 2) two = new Parameter(Mode.RELATIVE, program.get(index + 2));
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
              else if (modes[modes.length - 1] == 2) one = new Parameter(Mode.RELATIVE, program.get(index + 1));
              else one = new Parameter(Mode.POSITION, program.get(index + 1));
            }
            if (modes.length < 2) {
              two = new Parameter(Mode.POSITION, program.get(index + 2));
            } else {
              if (modes[modes.length - 2] == 1) two = new Parameter(Mode.IMMEDIATE, program.get(index + 2));
              else if (modes[modes.length - 2] == 2) two = new Parameter(Mode.RELATIVE, program.get(index + 2));
              else two = new Parameter(Mode.POSITION, program.get(index + 2));
            }
            Long writePos;
            if (modes.length < 3) {
              writePos = program.get(index + 3);
            } else {
              if (modes[modes.length - 3] == 2) writePos = program.get(index + 3) + relativeBase;
              else writePos = program.get(index + 3);
            }
            program = performLessThan(program, one, two, writePos);
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
              else if (modes[modes.length - 1] == 2) one = new Parameter(Mode.RELATIVE, program.get(index + 1));
              else one = new Parameter(Mode.POSITION, program.get(index + 1));
            }
            if (modes.length < 2) {
              two = new Parameter(Mode.POSITION, program.get(index + 2));
            } else {
              if (modes[modes.length - 2] == 1) two = new Parameter(Mode.IMMEDIATE, program.get(index + 2));
              else if (modes[modes.length - 2] == 2) two = new Parameter(Mode.RELATIVE, program.get(index + 2));
              else two = new Parameter(Mode.POSITION, program.get(index + 2));
            }
            Long writePos;
            if (modes.length < 3) {
              writePos = program.get(index + 3);
            } else {
              if (modes[modes.length - 3] == 2) writePos = program.get(index + 3) + relativeBase;
              else writePos = program.get(index + 3);
            }
            program = performEqual(program, one, two, writePos);
            index += 4;
            continue;
          }
          case ADJUST_RELATIVE: {
            Parameter param;
            if (modes[modes.length - 1] == 1) param = new Parameter(Mode.IMMEDIATE, program.get(index + 1));
            else if (modes[modes.length - 1] == 2) param = new Parameter(Mode.RELATIVE, program.get(index + 1));
            // note: because leading zeros are trimmed this case should never be hit
            else param = new Parameter(Mode.POSITION, program.get(index + 1));
            adjustRelativeBase(program, param);
            index += 2;
            continue;
          }
        }
      }
      throw new RuntimeException("This should not run");
    }

    return output;
  }

  private void adjustRelativeBase(Map<Long, Long> program, Parameter param) {
    Long val1 = getParamVal(program, param);
    relativeBase += val1;
  }

  private Map<Long, Long> performAdd(Map<Long, Long> program, Parameter param1, Parameter param2, Long resultPos) {
    Long val1 = getParamVal(program, param1);
    Long val2 = getParamVal(program, param2);
    program.put(resultPos, val1 + val2);
    return program;
  }

  private Map<Long, Long> performMultiply(Map<Long, Long> program, Parameter param1, Parameter param2, Long resultPos) {
    Long val1 = getParamVal(program, param1);
    Long val2 = getParamVal(program, param2);
    program.put(resultPos, val1 * val2);
    return program;
  }

  private Map<Long, Long> performInput(Map<Long, Long> program, Long writePos, Long input) {
    System.out.println("input: " + input);
    program.put(writePos, input);
    return program;
  }

  private Long performOutput(Map<Long, Long> program, Parameter param) {
    System.out.println("output: " + getParamVal(program, param));
    return getParamVal(program, param);
  }

  private Long performJumpIfTrue(Long index, Map<Long, Long> program, Parameter param1, Parameter param2) {
    Long val1 = getParamVal(program, param1);
    Long val2 = getParamVal(program, param2);
    if (val1 != 0) {
      // return what index should be now
      return val2;
    } else {
      return index + 3;
    }
  }

  private Long performJumpIfFalse(Long index, Map<Long, Long> program, Parameter param1, Parameter param2) {
    Long val1 = getParamVal(program, param1);
    Long val2 = getParamVal(program, param2);
    if (val1 == 0) {
      // return what index should be now
      return val2;
    } else {
      return index + 3;
    }
  }

  private Map<Long, Long> performLessThan(Map<Long, Long> program, Parameter param1, Parameter param2, Long writePos) {
    Long val1 = getParamVal(program, param1);
    Long val2 = getParamVal(program, param2);
    if (val1 < val2) {
      program.put(writePos, 1L);
    } else {
      program.put(writePos, 0L);
    }
    return program;
  }

  private Map<Long, Long> performEqual(Map<Long, Long> program, Parameter param1, Parameter param2, Long writePos) {
    Long val1 = getParamVal(program, param1);
    Long val2 = getParamVal(program, param2);
    if (val1.equals(val2)) {
      program.put(writePos, 1L);
    } else {
      program.put(writePos, 0L);
    }
    return program;
  }

  private Long getParamVal(Map<Long, Long> program, Parameter param) {
    Long res;
    switch (param.mode) {
      case POSITION:
        res = program.get(param.value);
        break;
      case IMMEDIATE:
        res = param.value;
        break;
      case RELATIVE:
        res = program.get(relativeBase + param.value);
        break;
      default:
        throw new RuntimeException("invalid param mode: " + param.mode);
    }
    return res != null ? res : 0L;
  }

  private enum Mode {
    POSITION,
    IMMEDIATE,
    RELATIVE
  }

  private class Parameter {
    Mode mode;
    Long value;

    Parameter(Mode mode, Long value) {
      this.mode = mode;
      this.value = value;
    }
  }

  private Map<Long, Long> loadProgramFromFile(File file) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(file);
    Map<Long, Long> program = new HashMap<>();
    // take input line, split on ',' and convert to map of index to longs
    Long counter = 0L;
    for (String val : fileScanner.nextLine().split(",")) {
      Long longVal = Long.valueOf(val);
      program.put(counter, longVal);
      counter++;
    }
    return program;
  }
}
