package day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day05 {

  public static void main(String[] args) throws Exception {
    Day05 main = new Day05();
    File file = new File(main.getClass().getClassLoader().getResource("day05/input.txt").getFile());
    main.solvePartOne(file);
  }

  // OPCODES
  private static final String ADD = "01";
  private static final String MULTIPLY = "02";
  private static final String OUTPUT = "04";
  private static final String HALT = "99";

  private void solvePartOne(File file) throws FileNotFoundException {
    List<Integer> program = loadProgramFromFile(file);
    computeProgram(program);
  }

  private void computeProgram(List<Integer> program) {
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
            program = performInput(program, program.get(index + 1));
            index += 2;
            continue;
          case "4":
            performOutput(program, new Parameter(Mode.POSITION, program.get(index + 1)));
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
            performOutput(program, param);
            index += 2;
            continue;
          }
        }
      }
      throw new RuntimeException("This should not run");
    }
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

  private List<Integer> performInput(List<Integer> program, Integer writePos) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Provide input: ");
    Integer input = scanner.nextInt();
    program.set(writePos, input);
    scanner.close();
    return program;
  }

  private void performOutput(List<Integer> program, Parameter param) {
    Integer output = getParamVal(program, param);
    System.out.println("Output: " + output);
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
