package Day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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

  private List<Integer> program = new ArrayList<Integer>();

  public int solvePartOne(File file) throws FileNotFoundException {
    loadProgramFromFile(file);
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

  private void loadProgramFromFile(File file) throws FileNotFoundException{
    Scanner fileScanner = new Scanner(file);
    this.program.clear();
    // take input line, split on ',' and convert to list of Integers
    program = Arrays.stream(fileScanner.nextLine().split(","))
            .map(Integer::valueOf)
            .collect(Collectors.toList());
  }
}
