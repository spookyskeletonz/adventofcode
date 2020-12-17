package twentytwenty.day16;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Day16 {

  class Range {
    int min;
    int max;
  }

  class Field {
    String name;
    Range rangeOne;
    Range rangeTwo;

    public Field(String name, int minOne, int maxOne, int minTwo, int maxTwo) {
      this.name = name;
      rangeOne = new Range();
      rangeOne.min = minOne;
      rangeOne.max = maxOne;
      rangeTwo = new Range();
      rangeTwo.min = minTwo;
      rangeTwo.max = maxTwo;
    }
  }

  class Ticket {
    List<Integer> values;
  }

  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var line = scanner.nextLine();
    var fieldList = new ArrayList<Field>();
    // scan all field lines
    while (!line.isEmpty()) {
      fieldList.add(parseFieldLine(line));
      line = scanner.nextLine();
    }
    // Scan our ticket
    line = scanner.nextLine();
    line = scanner.nextLine();
    // Scan nearby tickets while determining invalid
    line = scanner.nextLine();
    line = scanner.nextLine();
    List<Integer> invalidNums = new ArrayList<>();
    while (scanner.hasNextLine()) {
      line = scanner.nextLine();
      Arrays.stream(line.split(","))
          .map(Integer::parseInt)
          .forEach(i -> {
            var invalid = true;
            for (var field : fieldList) {
              if ((i >= field.rangeOne.min && i <= field.rangeOne.max)
               || (i >= field.rangeTwo.min && i <= field.rangeTwo.max)) {
                invalid = false;
                break;
              }
            }
            if (invalid) invalidNums.add(i);
          });
    }
    return invalidNums.stream().reduce(Integer::sum).orElseThrow();
  }

  public List<Integer> solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var line = scanner.nextLine();
    var fieldList = new ArrayList<Field>();
    // scan all field lines
    while (!line.isEmpty()) {
      fieldList.add(parseFieldLine(line));
      line = scanner.nextLine();
    }
    // Scan our ticket
    line = scanner.nextLine();
    line = scanner.nextLine();
    var myTicket = new Ticket();
    myTicket.values = Arrays.stream(line.split(","))
        .map(Integer::parseInt)
        .collect(Collectors.toList());
    // Scan nearby tickets while determining invalid
    line = scanner.nextLine();
    line = scanner.nextLine();
    // Map of field to possible column, initialise to include every
    var fieldMap = new HashMap<String, ArrayList<Integer>>();
    for (var field : fieldList) {
      var list = new ArrayList<Integer>();
      for (var i = 0; i < myTicket.values.size(); ++i) list.add(i);
      fieldMap.put(field.name, list);
    }
    // For each row, remove from the possible columns for a field
    while (scanner.hasNextLine()) {
      line = scanner.nextLine();
      eliminateImpossibleFields(line, fieldList, fieldMap);
    }

    List<Map.Entry<String, ArrayList<Integer>>> departures = fieldMap.entrySet().stream()
        .filter(e -> e.getKey().startsWith("departure"))
        .collect(Collectors.toList());
    var result = new ArrayList<Integer>();
    for (var entry : departures) {
      result.add(myTicket.values.get(entry.getValue().get(0)));
    }
    return result;
  }

  public Field parseFieldLine(String line) {
    var name = line.split(":")[0];
    var minOne = Integer.parseInt(line.split(":")[1].split(" ")[1].split("-")[0]);
    var maxOne = Integer.parseInt(line.split(":")[1].split(" ")[1].split("-")[1]);
    var minTwo = Integer.parseInt(line.split(":")[1].split(" ")[3].split("-")[0]);
    var maxTwo = Integer.parseInt(line.split(":")[1].split(" ")[3].split("-")[1]);
    return new Field(name, minOne, maxOne, minTwo, maxTwo);
  }

  public void eliminateImpossibleFields(String line, List<Field> fieldList, Map<String, ArrayList<Integer>> fieldMap) {
    var index = 0;
    var possibleCols = new HashMap<String, HashSet<Integer>>();
    // build possible columns in this row for fields
    for (var value : line.split(",")) {
      var i = Integer.parseInt(value);
      var invalid = true;
      for (var field : fieldList) {
        if ((i >= field.rangeOne.min && i <= field.rangeOne.max)
            || (i >= field.rangeTwo.min && i <= field.rangeTwo.max)) {
          invalid = false;
          if (possibleCols.containsKey(field.name)) {
            possibleCols.get(field.name).add(index);
          } else {
            var set = new HashSet<Integer>();
            set.add(index);
            possibleCols.put(field.name, set);
          }
        }
      }
      if (invalid) {
        // invalid ticket, disregard
        return;
      }
      index++;
    }
    // remove from global map of possible columns for fields based on this row
    for (var field : fieldList) {
      var possibleColsForField = possibleCols.get(field.name);
      fieldMap.get(field.name).removeIf(x -> !possibleColsForField.contains(x));
    }
  }
}
