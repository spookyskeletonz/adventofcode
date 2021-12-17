package twentytwentyone.day15;

import twentytwentyone.day09.Day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day15 {
  public class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Coordinate that = (Coordinate) o;
      return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }
  public class Node {
    Coordinate coordinate;
    int risk;
    boolean isEnd = false;
    List<Node> neighbours = new ArrayList<>();

    public Node(int risk, Coordinate coordinate) {
      this.risk = risk;
      this.coordinate = coordinate;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Node node = (Node) o;
      return risk == node.risk && isEnd == node.isEnd && coordinate.equals(node.coordinate);
    }

    @Override
    public int hashCode() {
      return Objects.hash(coordinate, risk, isEnd);
    }
  }


  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var map = new HashMap<Coordinate, Node>();
    var y = 0;
    var maxX = 0;
    while (scanner.hasNextLine()) {
      var risks = scanner.nextLine().split("");
      var x = 0;
      for (var risk : risks) {
        var coord = new Coordinate(x, y);
        var node = new Node(Integer.parseInt(risk), coord);
        map.put(coord, node);
        assignNeighbours(map, coord);
        x++;
      }
      maxX = x;
      y++;
    }
    var end = map.get(new Coordinate(maxX-1, y-1));
    end.isEnd = true;
    var leastRiskMap = traversalFindLeastRiskToCoordinates(map);
    return leastRiskMap.get(end.coordinate);
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var map = new HashMap<Coordinate, Node>();
    var y = 0;
    var maxX = 0;
    while (scanner.hasNextLine()) {
      var risks = scanner.nextLine().split("");
      var x = 0;
      for (var risk : risks) {
        var coord = new Coordinate(x, y);
        var node = new Node(Integer.parseInt(risk), coord);
        map.put(coord, node);
        assignNeighbours(map, coord);
        x++;
      }
      maxX = x;
      y++;
    }
    var expandedMap = expandMap(map, maxX, y);
    var end = expandedMap.get(new Coordinate(maxX*5 - 1, y*5 - 1));
    end.isEnd = true;
    var leastRiskMap = traversalFindLeastRiskToCoordinates(expandedMap);
    return leastRiskMap.get(end.coordinate);
  }

  public Map<Coordinate, Node> expandMap(Map<Coordinate, Node> map, int length, int height) {
    var expandedMap = new HashMap<Coordinate, Node>();
    for (var down = 0; down < 5; down++) {
      for (var right = 0; right < 5; right++) {
        for (var y = 0; y < height; ++y) {
          for (var x = 0; x < length; ++x) {
            var origNode = map.get(new Coordinate(x, y));
            var coord = new Coordinate(x + length*right, y + height*down);
            var risk = origNode.risk + right + down;
            risk = risk > 9 ? risk - 9 : risk;
            var newNode = new Node(risk, coord);
            expandedMap.put(coord, newNode);
            assignNeighbours(expandedMap, coord);
          }
        }
      }
    }
    return expandedMap;
  }

  public Map<Coordinate, Integer> traversalFindLeastRiskToCoordinates(Map<Coordinate, Node> map) {
    // Stores the least risk value path we see for a coordinate
    Map<Coordinate, Integer> leastRiskToGetToNode = new HashMap<>();
    var toVisit = new PriorityQueue<Node>(Comparator.comparingInt(n ->
        leastRiskToGetToNode.getOrDefault(n.coordinate, Integer.MAX_VALUE)));
    leastRiskToGetToNode.put(new Coordinate(0, 0), 0);
    toVisit.add(map.get(new Coordinate(0, 0)));
    var visited = new HashSet<Node>();
    while (!toVisit.isEmpty()) {
      var current = toVisit.poll();
      visited.add(current);
      for (var neighbour : current.neighbours) {
        if (!visited.contains(neighbour)) {
          var riskOfThisPathToNeighbour = leastRiskToGetToNode.get(current.coordinate) + neighbour.risk;
          if (riskOfThisPathToNeighbour < leastRiskToGetToNode.getOrDefault(neighbour.coordinate, Integer.MAX_VALUE)) {
            leastRiskToGetToNode.put(neighbour.coordinate, riskOfThisPathToNeighbour);
            toVisit.add(neighbour);
          }
        }
      }
    }
    return leastRiskToGetToNode;
  }

  public void assignNeighbours(Map<Coordinate, Node> coordToPoint, Coordinate coord) {
    var y = coord.y;
    var x = coord.x;
    // Input reads left to right, up to down.
    // Therefore, we only need to check and add for neighbours to left and above coord
    // Future neighbours have not been read in yet
    // left
    var leftCoord = new Coordinate(x - 1, y);
    if (coordToPoint.containsKey(leftCoord)) {
      coordToPoint.get(leftCoord).neighbours.add(coordToPoint.get(coord));
      coordToPoint.get(coord).neighbours.add(coordToPoint.get(leftCoord));
    }
    // up
    var upCoord = new Coordinate(x, y - 1);
    if (coordToPoint.containsKey(upCoord)) {
      coordToPoint.get(upCoord).neighbours.add(coordToPoint.get(coord));
      coordToPoint.get(coord).neighbours.add(coordToPoint.get(upCoord));
    }
  }
}
