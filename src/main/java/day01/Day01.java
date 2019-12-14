package day01;

import java.io.*;
import java.util.Scanner;

class Day01 {
    int solveFileForPartOne(File file) throws FileNotFoundException {
      Scanner fileScanner = new Scanner(file);
      int total = 0;
      while (fileScanner.hasNextInt()) {
        total += solveInt(fileScanner.nextInt());
      }

      return total;
    }

    int solveFileForPartTwo(File file) throws FileNotFoundException {
      Scanner fileScanner = new Scanner(file);
      int total = 0;
      while (fileScanner.hasNextInt()) {
        total += getFuel(fileScanner.nextInt());
      }
      return total;
    }

    private int solveInt(int mass) {
      return mass/3 - 2;
    }

    private int getFuel(int mass) {
      if (mass <= 0) return 0;
      int fuel = solveInt(mass);
      if (fuel <= 0) return 0;
      return fuel + getFuel(fuel);
    }
}
