package Day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

class Day03 {
  int solvePartOne(File file) throws FileNotFoundException {
    Scanner scanner = new Scanner(file);
    List<Path> wireOnePath = parseInputLine(scanner.nextLine());
    List<Path> wireTwoPath = parseInputLine(scanner.nextLine());
    HashSet<Coordinate> wireOneCoords = plotCoordinates(wireOnePath);
    HashSet<Coordinate> wireTwoCoords = plotCoordinates(wireTwoPath);
    HashSet<Coordinate> crosses = new HashSet<>(wireOneCoords);
    crosses.retainAll(wireTwoCoords);
    return distanceToClosestCross(crosses);
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

  private HashSet<Coordinate> plotCoordinates(List<Path> paths) {
    int currentX = 0;
    int currentY = 0;
    HashSet<Coordinate> coordinates = new HashSet<>();
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
