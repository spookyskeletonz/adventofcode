package twentytwenty.day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day12 {

  /**
   * PART ONE
   */

  private final int NORTH = 0;
  private final int EAST = 90;
  private final int SOUTH = 180;
  private final int WEST = 270;

  class ShipCoordinates {
    int x;
    int y;
    // direction ship is facing, using ints defined at top of class
    int direction;

    public ShipCoordinates(int x, int y, int direction) {
      this.x = x;
      this.y = y;
      this.direction = direction;
    }
  }

  private void moveTrueDirection(char direction, int units, ShipCoordinates coords) {
    switch(direction) {
      case 'N':
        coords.y = coords.y + units;
        break;
      case 'E':
        coords.x = coords.x + units;
        break;
      case 'W':
        coords.x = coords.x - units;
        break;
      case 'S':
        coords.y = coords.y - units;
        break;
      default:
        throw new AssertionError();
    }
  }

  private void moveForward(int units, ShipCoordinates coords) {
    switch (coords.direction) {
      case NORTH:
        moveTrueDirection('N', units, coords);
        break;
      case EAST:
        moveTrueDirection('E', units, coords);
        break;
      case SOUTH:
        moveTrueDirection('S', units, coords);
        break;
      case WEST:
        moveTrueDirection('W', units, coords);
        break;
    }
  }

  private void rotate(char rotationalDir, int degrees, ShipCoordinates coords) {
    int newDir;
    if (rotationalDir == 'R') {
      newDir = (coords.direction + degrees) % 360;
    } else if (rotationalDir == 'L') {
      newDir = (coords.direction - degrees + 360) % 360;
    } else {
      throw new AssertionError();
    }
    coords.direction = newDir;
  }

  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    ShipCoordinates coords = new ShipCoordinates(0, 0, EAST);
    while(scanner.hasNextLine()) {
      var line = scanner.nextLine();
      if (line.startsWith("F")) {
        moveForward(Integer.parseInt(line.substring(1)), coords);
      } else if (line.startsWith("R") || line.startsWith("L")) {
        rotate(line.charAt(0), Integer.parseInt(line.substring(1)), coords);
      } else {
        moveTrueDirection(line.charAt(0), Integer.parseInt(line.substring(1)), coords);
      }
    }
    return Math.abs(coords.x) + Math.abs(coords.y);
  }

  /**
   * PART TWO
   */

  class Coordinates {
    int x;
    int y;

    public Coordinates(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  private void moveTowardsWaypoint(int units, Coordinates coords, Coordinates waypoint) {
    coords.x = coords.x + units * waypoint.x;
    coords.y = coords.y + units * waypoint.y;
  }

  private void moveWaypointInDirection(char direction, int units, Coordinates waypoint) {
    switch(direction) {
      case 'N':
        waypoint.y = waypoint.y + units;
        break;
      case 'E':
        waypoint.x = waypoint.x + units;
        break;
      case 'W':
        waypoint.x = waypoint.x - units;
        break;
      case 'S':
        waypoint.y = waypoint.y - units;
        break;
      default:
        throw new AssertionError();
    }
  }

  private void rotateWaypoint(char rotationalDir, int degrees, Coordinates waypoint) {
    // Treat this as a rotation around the origin of a cartesian plane
    // https://lexique.netmath.ca/en/rotation-in-a-cartesian-plane/
    if (rotationalDir == 'R') {
      for (var i = degrees; i > 0; i = i - 90) {
        var x = waypoint.x;
        var y= waypoint.y;
        waypoint.x = y;
        waypoint.y = x * -1;
      }
    } else if (rotationalDir == 'L') {
      for (var i = degrees; i > 0; i = i - 90) {
        var x = waypoint.x;
        var y= waypoint.y;
        waypoint.x = y * -1;
        waypoint.y = x;
      }
    } else {
      throw new AssertionError();
    }
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    Coordinates shipCoordinates = new Coordinates(0, 0);
    Coordinates waypoint = new Coordinates(10, 1);
    while(scanner.hasNextLine()) {
      var line = scanner.nextLine();
      if (line.startsWith("F")) {
        moveTowardsWaypoint(Integer.parseInt(line.substring(1)), shipCoordinates, waypoint);
      } else if (line.startsWith("R") || line.startsWith("L")) {
        rotateWaypoint(line.charAt(0), Integer.parseInt(line.substring(1)), waypoint);
      } else {
        moveWaypointInDirection(line.charAt(0), Integer.parseInt(line.substring(1)), waypoint);
      }
    }
    return Math.abs(shipCoordinates.x) + Math.abs(shipCoordinates.y);
  }

}
