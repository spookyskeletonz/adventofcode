package twentytwentyone.day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day11 {
  public class Octopus {
    int energy;
    boolean flashed = false;

    public Octopus(int energy) {
      this.energy = energy;
    }
  }

  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    List<List<Octopus>> octopusEnergies = new ArrayList<>();
    while (scanner.hasNextLine()) {
      var row = new ArrayList<Octopus>();
      for (var i : scanner.nextLine().toCharArray()) {
        row.add(new Octopus(Character.getNumericValue(i)));
      }
      octopusEnergies.add(row);
    }
    var flashes = 0;
    for (int step = 1; step <= 100; step++) {
      // Increase all energies by 1
      for (var i = 0; i < octopusEnergies.size(); ++i) {
        for (var j = 0; j < octopusEnergies.get(i).size(); ++j) {
          octopusEnergies.get(i).get(j).energy++;
        }
      }
      // Flash octopuses greater than 9 until no flashes left
      var flashOngoing = true;
      while (flashOngoing) {
        flashOngoing = false;
        for (var i = 0; i < octopusEnergies.size(); ++i) {
          for (var j = 0; j < octopusEnergies.get(i).size(); ++j) {
            if (octopusEnergies.get(i).get(j).energy > 9 && !octopusEnergies.get(i).get(j).flashed) {
              flashOctopus(i, j, octopusEnergies);
              flashOngoing = true;
              octopusEnergies.get(i).get(j).flashed = true;
              flashes++;
            }
          }
        }
      }
      // Reset flashed octopuses
      for (var i = 0; i < octopusEnergies.size(); ++i) {
        for (var j = 0; j < octopusEnergies.get(i).size(); ++j) {
          if (octopusEnergies.get(i).get(j).flashed) {
            octopusEnergies.get(i).get(j).flashed = false;
            octopusEnergies.get(i).get(j).energy = 0;
          }
        }
      }
    }

    return flashes;
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    List<List<Octopus>> octopusEnergies = new ArrayList<>();
    while (scanner.hasNextLine()) {
      var row = new ArrayList<Octopus>();
      for (var i : scanner.nextLine().toCharArray()) {
        row.add(new Octopus(Character.getNumericValue(i)));
      }
      octopusEnergies.add(row);
    }
    int step = 1;
    while (true) {
      // Increase all energies by 1
      for (var i = 0; i < octopusEnergies.size(); ++i) {
        for (var j = 0; j < octopusEnergies.get(i).size(); ++j) {
          octopusEnergies.get(i).get(j).energy++;
        }
      }
      // Flash octopuses greater than 9 until no flashes left
      var flashOngoing = true;
      while (flashOngoing) {
        flashOngoing = false;
        for (var i = 0; i < octopusEnergies.size(); ++i) {
          for (var j = 0; j < octopusEnergies.get(i).size(); ++j) {
            if (octopusEnergies.get(i).get(j).energy > 9 && !octopusEnergies.get(i).get(j).flashed) {
              flashOctopus(i, j, octopusEnergies);
              flashOngoing = true;
              octopusEnergies.get(i).get(j).flashed = true;
            }
          }
        }
      }
      // If all octopuses flash on this turn, return this step
      var allFlashed = true;
      for (var i = 0; i < octopusEnergies.size(); ++i) {
        for (var j = 0; j < octopusEnergies.get(i).size(); ++j) {
          if (!octopusEnergies.get(i).get(j).flashed) {
            allFlashed = false;
            break;
          }
        }
        if (!allFlashed) {
          break;
        }
      }
      if (allFlashed) {
        return step;
      }
      // Reset flashed octopuses
      for (var i = 0; i < octopusEnergies.size(); ++i) {
        for (var j = 0; j < octopusEnergies.get(i).size(); ++j) {
          if (octopusEnergies.get(i).get(j).flashed) {
            octopusEnergies.get(i).get(j).flashed = false;
            octopusEnergies.get(i).get(j).energy = 0;
          }
        }
      }
      step++;
    }
  }

  public void flashOctopus(int row, int col, List<List<Octopus>> octopusEnergies) {
    if (row - 1 >= 0) {
      octopusEnergies.get(row-1).get(col).energy++;
    }
    if (row + 1 < octopusEnergies.size()) {
      octopusEnergies.get(row + 1).get(col).energy++;
    }
    if (col - 1 >= 0) {
      octopusEnergies.get(row).get(col - 1).energy++;
    }
    if (col + 1 < octopusEnergies.get(row).size()) {
      octopusEnergies.get(row).get(col + 1).energy++;
    }
    if (row - 1 >= 0 && col - 1 >= 0) {
      octopusEnergies.get(row - 1).get(col - 1).energy++;
    }
    if (row + 1 < octopusEnergies.size() && col - 1 >= 0) {
      octopusEnergies.get(row + 1).get(col - 1).energy++;
    }
    if (row - 1 >= 0 && col + 1 < octopusEnergies.get(row - 1).size()) {
      octopusEnergies.get(row - 1).get(col + 1).energy++;
    }
    if (row + 1 < octopusEnergies.size() && col + 1 < octopusEnergies.get(row + 1).size()) {
      octopusEnergies.get(row + 1).get(col + 1).energy++;
    }
  }
}
