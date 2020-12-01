package twentynineteen.day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

class Day03 {
  int solvePartOne(File file) throws FileNotFoundException {
    Scanner scanner = new Scanner(file);
    List<Path> wireOnePath = parseInputLine(scanner.nextLine());
    List<Path> wireTwoPath = parseInputLine(scanner.nextLine());
    Set<Coordinate> wireOneCoords = plotCoordinates(wireOnePath);
    Set<Coordinate> wireTwoCoords = plotCoordinates(wireTwoPath);
    Set<Coordinate> crosses = new HashSet<>(wireOneCoords);
    crosses.retainAll(wireTwoCoords);
    return distanceToClosestCross(crosses);
  }

  int solvePartTwo(File file) throws FileNotFoundException {
    Scanner scanner = new Scanner(file);
    List<Path> wireOnePath = parseInputLine(scanner.nextLine());
    List<Path> wireTwoPath = parseInputLine(scanner.nextLine());
    Map<Coordinate, Integer> wireOneCoords = plotCoordinatesPartTwo(wireOnePath);
    Map<Coordinate, Integer> wireTwoCoords = plotCoordinatesPartTwo(wireTwoPath);
    Set<Coordinate> crosses = new HashSet<>(wireOneCoords.keySet());
    crosses.retainAll(wireTwoCoords.keySet());
    return minimumCombinedStepsToCross(crosses, wireOneCoords, wireTwoCoords);
  }

  private int minimumCombinedStepsToCross(Set<Coordinate> crosses, Map<Coordinate, Integer> stepsOne,
                                          Map<Coordinate, Integer> stepsTwo) {
    if (crosses.size() == 0) throw new RuntimeException("No crosses found");
    int minSteps = Integer.MAX_VALUE;
    for (Coordinate cross : crosses) {
      int steps = stepsOne.get(cross) + stepsTwo.get(cross);
      if (steps < minSteps) minSteps = steps;
    }
    return minSteps;
  }

  private int distanceToClosestCross(Set<Coordinate> crosses) {
    if (crosses.size() == 0) throw new RuntimeException("No crosses found");
    int minDist = Integer.MAX_VALUE;
    for (Coordinate cross : crosses) {
      int dist = Math.abs(cross.x) + Math.abs(cross.y);
      if (dist < minDist) minDist = dist;
    }
    return minDist;
  }

  private Map<Coordinate, Integer> plotCoordinatesPartTwo(List<Path> paths) {
    int currentX = 0;
    int currentY = 0;
    int numSteps = 0;
    Map<Coordinate, Integer> coordinates = new HashMap<>();
    for (Path path : paths) {
      if (path.direction.equals(Direction.UP)) {
        int travellingY = currentY;
        while (travellingY != currentY + path.distance) {
          numSteps++;
          travellingY++;
          coordinates.put(new Coordinate(currentX, travellingY), numSteps);
        }
        currentY = travellingY;
      } else if (path.direction.equals(Direction.DOWN)) {
        int travellingY = currentY;
        while (travellingY != currentY - path.distance) {
          numSteps++;
          travellingY--;
          coordinates.put(new Coordinate(currentX, travellingY), numSteps);
        }
        currentY = travellingY;
      } else if (path.direction.equals(Direction.LEFT)) {
        int travellingX = currentX;
        while (travellingX != currentX - path.distance) {
          numSteps++;
          travellingX--;
          coordinates.put(new Coordinate(travellingX, currentY), numSteps);
        }
        currentX = travellingX;
      } else if (path.direction.equals(Direction.RIGHT)) {
        int travellingX = currentX;
        while (travellingX != currentX + path.distance) {
          numSteps++;
          travellingX++;
          coordinates.put(new Coordinate(travellingX, currentY), numSteps);
        }
        currentX = travellingX;
      }
    }
    return coordinates;
  }

  private Set<Coordinate> plotCoordinates(List<Path> paths) {
    int currentX = 0;
    int currentY = 0;
    Set<Coordinate> coordinates = new HashSet<>();
    for (Path path : paths) {
      if (path.direction.equals(Direction.UP)) {
        int travellingY = currentY;
        while (travellingY != currentY + path.distance) {
          travellingY++;
          coordinates.add(new Coordinate(currentX, travellingY));
        }
        currentY = travellingY;
      } else if (path.direction.equals(Direction.DOWN)) {
        int travellingY = currentY;
        while (travellingY != currentY - path.distance) {
          travellingY--;
          coordinates.add(new Coordinate(currentX, travellingY));
        }
        currentY = travellingY;
      } else if (path.direction.equals(Direction.LEFT)) {
        int travellingX = currentX;
        while (travellingX != currentX - path.distance) {
          travellingX--;
          coordinates.add(new Coordinate(travellingX, currentY));
        }
        currentX = travellingX;
      } else if (path.direction.equals(Direction.RIGHT)) {
        int travellingX = currentX;
        while (travellingX != currentX + path.distance) {
          travellingX++;
          coordinates.add(new Coordinate(travellingX, currentY));
        }
        currentX = travellingX;
      }
    }
    return coordinates;
  }

  private List<Path> parseInputLine(String line) {
    return Arrays.stream(line.split(","))
            .map(i -> {
              Direction d;
              switch (i.charAt(0)) {
                case 'U':
                  d = Direction.UP;
                  break;
                case 'D':
                  d = Direction.DOWN;
                  break;
                case 'L':
                  d = Direction.LEFT;
                  break;
                case 'R':
                  d = Direction.RIGHT;
                  break;
                default:
                  throw new RuntimeException("Unexpected direction input: " + i.charAt(0));
              }
              return new Path(d, Integer.valueOf(i.substring(1)));
            })
            .collect(Collectors.toList());
  }

  private class Coordinate {
    int x;
    int y;

    Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o == null) {
        return false;
      }
      if (!(o instanceof Coordinate)) {
        return false;
      }
      Coordinate c = (Coordinate) o;
      return c.x == this.x && c.y == this.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  private class Path {
    Direction direction;
    int distance;

    Path(Direction direction, int distance) {
      this.direction = direction;
      this.distance = distance;
    }
  }

  private enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
  }
}
