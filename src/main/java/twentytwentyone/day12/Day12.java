package twentytwentyone.day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day12 {
  public class Cave {
    String name;
    boolean small;
    List<Cave> neighbours = new ArrayList<>();

    public Cave(String name, boolean small) {
      assert(name != null);
      this.name = name;
      this.small = small;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Cave cave = (Cave) o;
      return small == cave.small && name.equals(cave.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, small);
    }
  }

  public Map<String, Cave> buildNameToCave(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var nameToCave = new HashMap<String, Cave>();
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine().split("-");
      var cave1Name = line[0];
      var cave2Name = line[1];
      Cave cave1;
      if (nameToCave.containsKey(cave1Name)) {
        cave1 = nameToCave.get(cave1Name);
      } else {
        cave1 = new Cave(cave1Name, !cave1Name.toUpperCase(Locale.ROOT).equals(cave1Name));
        nameToCave.put(cave1Name, cave1);
      }
      Cave cave2;
      if (nameToCave.containsKey(cave2Name)) {
        cave2 = nameToCave.get(cave2Name);
      } else {
        cave2 = new Cave(cave2Name, !cave2Name.toUpperCase(Locale.ROOT).equals(cave2Name));
        nameToCave.put(cave2Name, cave2);
      }
      cave1.neighbours.add(cave2);
      cave2.neighbours.add(cave1);
    }
    return nameToCave;
  }

  public int solvePartOne(File file) throws FileNotFoundException {
    var nameToCave = buildNameToCave(file);
    return dfsTraversalOne(new HashSet<>(), nameToCave.get("start"));
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    var nameToCave = buildNameToCave(file);
    var path = new ArrayList<Cave>();
    return dfsTraversalTwo(new HashSet<>(), null, nameToCave.get("start"));
  }

  public int dfsTraversalOne(Set<Cave> visited, Cave current) {
    var visitedNew = new HashSet<>(visited);
    if (current.name.equals("end")) {
      return 1;
    }
    var sumPath = 0;
    if (current.small) {
      visitedNew.add(current);
    }
    for (var neighbour : current.neighbours) {
      if (!visited.contains(neighbour)) {
        sumPath += dfsTraversalOne(visitedNew, neighbour);
      }
    }
    return sumPath;
  }

  public int dfsTraversalTwo(Set<Cave> visitedOnce, Cave visitedTwice, Cave current) {
    var visitedOnceNew = new HashSet<>(visitedOnce);
    if (current.name.equals("end")) {
      return 1;
    }
    var sumPath = 0;
    if (current.small) {
      if (visitedOnceNew.contains(current)) {
        if (current.name.equals("start")) {
          return 0;
        }
        if (visitedTwice == null) {
          // Continue traversals with this cave as visited twice
          for (var neighbour : current.neighbours) {
            sumPath += dfsTraversalTwo(visitedOnceNew, current, neighbour);
          }
        } else {
          // Have already visited a small cave twice on this traversal, exit
          return 0;
        }
      } else {
        // Add cave to visited once and continue traversals
        visitedOnceNew.add(current);
        for (var neighbour : current.neighbours) {
          sumPath += dfsTraversalTwo(visitedOnceNew, visitedTwice, neighbour);
        }
      }
    } else {
      // Continue traversals for big caves
      for (var neighbour : current.neighbours) {
        sumPath += dfsTraversalTwo(visitedOnceNew, visitedTwice, neighbour);
      }
    }

    return sumPath;
  }
}
