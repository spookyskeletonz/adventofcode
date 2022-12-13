package twentytwentytwo.day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day03 {
  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var prioritySum = 0;
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var comp1 = line.substring(0, line.length()/2).chars()
          .mapToObj(i -> (char)i)
          .collect(Collectors.toSet());
      var comp2 = line.substring(line.length()/2).chars()
          .mapToObj(i -> (char)i)
          .collect(Collectors.toSet());
      comp1.retainAll(comp2);
      for (var c : comp1) {
        if (Character.isUpperCase(c)) {
          prioritySum += c - 'A' + 27;
        } else {
          prioritySum += c - 'a' + 1;
        }
      }
    }
    return prioritySum;
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var prioritySum = 0;
    var group = new ArrayList<String>();
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      group.add(line);
      if (group.size() == 3) {
        var comp1 = group.get(0).chars()
            .mapToObj(i -> (char)i)
            .collect(Collectors.toSet());
        comp1.retainAll(group.get(1).chars()
            .mapToObj(i -> (char)i)
            .collect(Collectors.toSet()));
        comp1.retainAll(group.get(2).chars()
            .mapToObj(i -> (char)i)
            .collect(Collectors.toSet()));
        for (var c : comp1) {
          if (Character.isUpperCase(c)) {
            prioritySum += c - 'A' + 27;
          } else {
            prioritySum += c - 'a' + 1;
          }
        }

        group.clear();
      }
    }
    return prioritySum;
  }
}
