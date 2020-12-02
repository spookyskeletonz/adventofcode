package twentytwenty.day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day02 {

  public int solvePartOne(File file) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(file);
    int numValid = 0;
    while (fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();
      String[] cols = line.split(" ");
      int min = Integer.parseInt(cols[0].split("-")[0]);
      int max = Integer.parseInt(cols[0].split("-")[1]);
      int character = cols[1].charAt(0);
      int count = 0;
      for (char c : cols[2].toCharArray()) {
        if (c == character) count++;
      }
      if (count <= max && count >= min) {
        numValid++;
      }
    }
    return numValid;
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(file);
    int numValid = 0;
    while (fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();
      String[] cols = line.split(" ");
      int indexOne = Integer.parseInt(cols[0].split("-")[0]) - 1;
      int indexTwo = Integer.parseInt(cols[0].split("-")[1]) - 1;
      int character = cols[1].charAt(0);
      if ((cols[2].charAt(indexOne) == character) ^ (cols[2].charAt(indexTwo) == character)) numValid++;
    }
    return numValid;
  }
}
