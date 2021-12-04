package twentytwentyone.day03;

import twentytwentyone.day02.Day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day03 {
  // Class tallying the number of set/unset bits for a position
  public static class PositionValues {
    int numSet = 0;
    int numUnset = 0;
  }

  public int solvePartOne(File file) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(file);
    List<PositionValues> positionValuesList = new ArrayList<>();
    while (fileScanner.hasNextLine()) {
      var line = fileScanner.nextLine();
      if (positionValuesList.isEmpty()) {
        // First line, init list of values
        for (var i = 0; i < line.length(); i++) {
          positionValuesList.add(new PositionValues());
        }
      }
      var position = 0;
      for (var c : line.toCharArray()) {
        if (c == '1') {
          positionValuesList.get(position).numSet++;
        } else {
          positionValuesList.get(position).numUnset++;
        }
        position++;
      }
    }
    // Use tallys at each position to build gamma/epsilon binary strings
    StringBuilder gammaBuilder = new StringBuilder();
    StringBuilder epsilonBuilder = new StringBuilder();
    for (var positionValues : positionValuesList) {
      if (positionValues.numSet > positionValues.numUnset) {
        gammaBuilder.append(1);
        epsilonBuilder.append(0);
      } else {
        gammaBuilder.append(0);
        epsilonBuilder.append(1);
      }
    }
    // convert binary to decimal value
    var gamma = Integer.parseInt(gammaBuilder.toString(), 2);
    var epsilon = Integer.parseInt(epsilonBuilder.toString(), 2);
    return gamma * epsilon;
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(file);
    var binaryValues = new ArrayList<String>();
    while (fileScanner.hasNextLine()) {
      var line = fileScanner.nextLine();
      binaryValues.add(line);
    }
    // List of oxygen values to filter through
    var oxygenValues = new ArrayList<>(binaryValues);
    // Final oxygen value once filtered down
    var finalOxygenValue = 0;
    var position = 0;
    // Filter on each position until one value left
    while (oxygenValues.size() != 1) {
      var numSet = 0;
      var numUnset = 0;
      for (var val : oxygenValues) {
        if (val.toCharArray()[position] == '1') {
          numSet++;
        } else {
          numUnset++;
        }
      }
      var matchingValue = numSet >= numUnset ? '1' : '0';
      var positionToCheck = position;
      oxygenValues.removeIf(v -> oxygenValues.size() != 1 && v.toCharArray()[positionToCheck] != matchingValue);
      if (oxygenValues.size() == 1) {
        finalOxygenValue = Integer.parseInt(oxygenValues.get(0), 2);
        break;
      }
      position++;
    }

    // List of co2 values to filter through
    var co2Values = new ArrayList<>(binaryValues);
    // Final co2 value once filtered down
    var finalCo2Value = 0;
    position = 0;
    // Filter on each position until one value left
    while (co2Values.size() != 1) {
      var numSet = 0;
      var numUnset = 0;
      for (var val : co2Values) {
        if (val.toCharArray()[position] == '1') {
          numSet++;
        } else {
          numUnset++;
        }
      }
      var matchingValue = numSet < numUnset ? '1' : '0';
      var positionToCheck = position;
      co2Values.removeIf(v -> co2Values.size() != 1 && v.toCharArray()[positionToCheck] != matchingValue);
      if (co2Values.size() == 1) {
        finalCo2Value = Integer.parseInt(co2Values.get(0), 2);
        break;
      }
      position++;
    }
    return finalCo2Value * finalOxygenValue;
  }
}
