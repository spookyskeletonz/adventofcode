package day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

class Day02 {
  /**
   * Opcode 1 adds together numbers read from two positions and stores the result in a third position.
   * The three integers immediately after the opcode tell you these three positions - the first two indicate the
   * positions from which you should read the input values, and the third indicates the position at which the output
   * should be stored.
   *
   * Opcode 2 works exactly like opcode 1, except it multiplies the two inputs instead of adding them.
   *
   * Once you're done processing an opcode, move to the next one by stepping forward 4 positions.
   *
   * Opcode 99 means halt
   */

  // OPCODES
  private static Integer ADD = 1;
  private static Integer MULTIPLY = 2;
  private static Integer HALT = 99;

  int solvePartOne(File file) throws FileNotFoundException {
    List<Integer> program = loadProgramFromFile(file);
    return compute(program);
  }

  int solvePartTwo(File file) throws FileNotFoundException {
    // I don't like this solution. O(n^2)
    int noun = 0;
    int verb = 0;
    List<Integer> original = loadProgramFromFile(file);
    List<Integer> program = loadProgramFromFile(file);
    while (noun < program.size()) {
      while (verb < program.size()) {
        program.set(1, noun);
        program.set(2, verb);
        int res = compute(program);
        if (res == 19690720) {
          return 100 * noun + verb;
        }
        verb++;
        // reset program after failed attempt, need to completely clear and re-add
        program.clear();
        program.addAll(original);
      }
      noun++;
      verb = 0;
    }
    throw new RuntimeException("No possible solution");
  }

  private int compute(List<Integer> program) {
    // variables that we will be using and mutating within the loop
    // index to check for an opcode
    int index = 0;
    // result = operation
    int res;
    // position in program to set to result
    int pos;

    while (index < (program.size() - 4) && !program.get(index).equals(HALT)) {
      pos = program.get(index + 3);
      if (program.get(index).equals(ADD)) {
        res = program.get(program.get(index + 1)) + program.get(program.get(index + 2));
      } else if (program.get(index).equals(MULTIPLY)) {
        res = program.get(program.get(index + 1)) * program.get(program.get(index + 2));
      } else {
        throw new RuntimeException("Invalid input, expecting opcode but got: " + program.get(index));
      }
      program.set(pos, res);
      index += 4;
    }

    return program.get(0);
  }

  private List<Integer> loadProgramFromFile(File file) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(file);
    // take input line, split on ',' and convert to list of Integers
    return Arrays.stream(fileScanner.nextLine().split(","))
            .map(Integer::valueOf)
            .collect(Collectors.toCollection(ArrayList::new));
  }
}
