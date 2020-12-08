package twentytwenty.day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day08 {

  interface Instruction {
    // execute instruction given input
    int execute(int input);
  }

  static class NoOp implements Instruction {
    private final int modifier;

    public NoOp(int modifier) {
      this.modifier = modifier;
    }

    @Override
    public int execute(int accumulator) {
      // does nothing
      return accumulator;
    }
  }

  static class Acc implements Instruction {
    private final int modifier;

    public Acc(int modifier) {
      this.modifier = modifier;
    }

    @Override
    public int execute(int accumulator) {
      // given current accum, return result
      return accumulator + modifier;
    }
  }

  static class Jmp implements  Instruction {
    private final int modifier;

    public Jmp(int modifier) {
      this.modifier = modifier;
    }

    @Override
    public int execute(int position) {
      // given current position, returns new position
      return position + modifier;
    }
  }

  public int solvePartOne(File file) throws FileNotFoundException {
    var code = buildCode(file);
    var accum = 0;
    var line = 1;
    Set<Integer> executedLines = new HashSet<>();
    while(true) {
      if (executedLines.contains(line)) break;
      var inst = code.get(line);
      executedLines.add(line);
      if (inst instanceof NoOp) {
        line++;
      } else if (inst instanceof Jmp) {
        line = inst.execute(line);
      } else if (inst instanceof Acc) {
        accum = inst.execute(accum);
        line++;
      } else {
        throw new AssertionError("Unknown inst: " + inst);
      }
    }
    return accum;
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    var code = buildCode(file);
    return executeCode(code, new HashSet<>(), 1, 0, code.size(), false);
  }

  // brute force approach, will try all possible swap branches
  public int executeCode(Map<Integer, Instruction> code, Set<Integer> executedLines, int line, int accum, int numLines, boolean hasSwapped) {
    while(true) {
      // if we have gone past last instruction, we have finished
      if (line > numLines) {
        return accum;
      }
      // we have a loop, solution not possible, exit this branch
      if (executedLines.contains(line)) break;
      var inst = code.get(line);
      if (inst instanceof NoOp) {
        if (!hasSwapped) {
          // try new branch with this instruction swapped
          var newCode = new HashMap<>(code);
          newCode.put(line, new Jmp(((NoOp) inst).modifier));
          // needs a new set to not collide with parent branch
          var newExecuted = new HashSet<>(executedLines);
          var i = executeCode(newCode, newExecuted, line, accum, numLines, true);
          // if i returns as non zero that was a success
          if (i != 0) return i;
        }
        executedLines.add(line);
        line++;
      } else if (inst instanceof Jmp) {
        if (!hasSwapped) {
          // try new branch with this instruction swapped
          var newCode = new HashMap<>(code);
          newCode.put(line, new NoOp(((Jmp) inst).modifier));
          // needs a new set to not collide with parent branch
          var newExecuted = new HashSet<>(executedLines);
          var i = executeCode(newCode, newExecuted, line, accum, numLines, true);
          // if i returns as non zero that was a success
          if (i != 0) return i;
        }
        executedLines.add(line);
        line = inst.execute(line);
      } else if (inst instanceof Acc) {
        accum = inst.execute(accum);
        executedLines.add(line);
        line++;
      } else {
        throw new AssertionError("Unknown inst: " + inst);
      }
    }
    return 0;
  }


  private HashMap<Integer, Instruction> buildCode(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var lineNo = 1;
    var code = new HashMap<Integer, Instruction>();
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var inst = mapLineToInst(line);
      code.put(lineNo, inst);
      lineNo++;
    }
    return code;
  }

  private Instruction mapLineToInst(String line) {
    var opString = line.split(" ")[0];
    var modifierStr = line.split(" ")[1];
    var sign = modifierStr.charAt(0);
    int modifier;
    if (sign == '+') {
      modifier = Integer.parseInt(modifierStr.substring(1));
    } else if (sign == '-') {
      modifier = Integer.parseInt(modifierStr.substring(1)) * -1;
    } else {
      throw new AssertionError("Unknown sign");
    }
    switch (opString) {
      case "nop":
        return new NoOp(modifier);
      case "jmp":
        return new Jmp(modifier);
      case "acc":
        return new Acc(modifier);
      default:
        throw new AssertionError("Unknown opString");
    }
  }
}
