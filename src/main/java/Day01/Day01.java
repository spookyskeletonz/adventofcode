package Day01;

import java.io.*;
import java.util.Scanner;

public class Day01 {
    public int solveFile(File file) throws FileNotFoundException{
      Scanner fileScanner = new Scanner(file);
      int total = 0;
      while (fileScanner.hasNextInt()) {
        int mass = fileScanner.nextInt();
        total += solveInt(mass);
      }

      return total;
    }

    private int solveInt(int mass) {
      return mass/3 - 2;
    }
}
