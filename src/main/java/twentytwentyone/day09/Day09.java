package twentytwentyone.day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day09 {
  public class Coord {
    int row;
    int col;

    public Coord(int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Coord coord = (Coord) o;
      return row == coord.row && col == coord.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }
  }

  public class Point {
    int value;
    List<Point> neighbours = new ArrayList<>();
    // For use in part 2 traversal
    boolean visited = false;

    public Point(int value) {
      this.value = value;
    }
  }

  public Map<Coord, Point> buildGraph(Scanner scanner) {
    var row = 0;
    Map<Coord, Point> coordToPoint = new HashMap<>();
    while(scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var column = 0;
      for (var input : line.split("")) {
        var value = Integer.parseInt(input);
        var point = new Point(value);
        var coord = new Coord(row, column);
        coordToPoint.put(coord, point);
        assignNeighbours(coordToPoint, coord);
        column++;
      }
      row++;
    }
    return coordToPoint;
  }

  public void assignNeighbours(Map<Coord, Point> coordToPoint, Coord coord) {
    var row = coord.row;
    var col = coord.col;
    // Input reads left to right, up to down.
    // Therefore, we only need to check and add for neighbours to left and above coord
    // Future neighbours have not been read in yet
    // left
    var leftCoord = new Coord(row, col-1);
    if (coordToPoint.containsKey(leftCoord)) {
      coordToPoint.get(leftCoord).neighbours.add(coordToPoint.get(coord));
      coordToPoint.get(coord).neighbours.add(coordToPoint.get(leftCoord));
    }
    // up
    var upCoord = new Coord(row-1, col);
    if (coordToPoint.containsKey(upCoord)) {
      coordToPoint.get(upCoord).neighbours.add(coordToPoint.get(coord));
      coordToPoint.get(coord).neighbours.add(coordToPoint.get(upCoord));
    }
  }

  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    // Map of coord ([x,y]) => Point
    Map<Coord, Point> coordToPoint = buildGraph(scanner);
    var sumRiskLevel = 0;
    for (var point : coordToPoint.values()) {
      if (point.neighbours.stream().allMatch(p -> point.value < p.value)) {
        sumRiskLevel += point.value + 1;
      }
    }
    return sumRiskLevel;
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    // Map of coord ([x,y]) => Point
    Map<Coord, Point> coordToPoint = buildGraph(scanner);
    // Get starting nodes for basin traversal (low points)
    List<Point> lowPoints = new ArrayList<>();
    for (var point : coordToPoint.values()) {
      if (point.neighbours.stream().allMatch(p -> point.value < p.value)) {
        lowPoints.add(point);
      }
    }
    // Ordered queue from largest to smallest
    PriorityQueue<Integer> basinSizes = new PriorityQueue<>(Collections.reverseOrder());
    for (var point : lowPoints) {
      basinSizes.add(traverseBasinSize(point));
    }
    assert(basinSizes.size() >= 3);
    return(basinSizes.poll() * basinSizes.poll() * basinSizes.poll());
  }

  // Depth first traversal returning size of traversal
  public int traverseBasinSize(Point point) {
    if (point.value == 9 || point.visited) {
      // If we have hit a 9 or point has already been visited then terminate depth first traversal branch
      return 0;
    } else {
      var sum = 1;
      point.visited = true;
      for (var neighbour : point.neighbours) {
        sum += traverseBasinSize(neighbour);
      }
      return sum;
    }
  }
}
