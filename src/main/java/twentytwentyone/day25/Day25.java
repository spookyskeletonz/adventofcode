package twentytwentyone.day25;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day25 {

  public enum Direction {
    EAST,
    SOUTH
  }

  public class Cucumber {
    Direction direction;

    public Cucumber(Direction direction) {
      this.direction = direction;
    }
  }

  public class Location {
    Cucumber cucumber = null;
  }

  public class Region {
    ArrayList<ArrayList<Location>> locations = new ArrayList<>();
  }

  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var region = new Region();
    while (scanner.hasNextLine()) {
      var newRow = new ArrayList<Location>();
      var line = scanner.nextLine();
      for (var l : line.toCharArray()) {
        var location = new Location();
        switch (l) {
          case '>':
            location.cucumber = new Cucumber(Direction.EAST);
            break;
          case 'v':
            location.cucumber = new Cucumber(Direction.SOUTH);
            break;
          case '.':
            break;
          default:
            throw new RuntimeException();
        }
        newRow.add(location);
      }
      region.locations.add(newRow);
    }
    var hasMoved = true;
    var steps = 0;
    while (hasMoved) {
      hasMoved = regionStepEast(region);
      hasMoved |= regionStepSouth(region);
      steps++;
    }
    return steps;
  }

  public void printRegion(Region region) {
    for (var row : region.locations) {
      var builder = new StringBuilder();
      for (var l : row) {
        if (l.cucumber == null) {
          builder.append('.');
        } else {
          if (l.cucumber.direction == Direction.EAST) {
            builder.append('>');
          } else {
            builder.append('v');
          }
        }
      }
      System.out.println(builder);
    }
    System.out.println();
    System.out.println();
  }

  public boolean regionStepSouth(Region region) {
    var newLocations = copyLocations(region);
    var hasMoved = false;
    var y = 0;
    for (var row : region.locations) {
      var x = 0;
      for (var l : row) {
        if (l.cucumber != null) {
          if (l.cucumber.direction == Direction.SOUTH) {
            var newY = y+1;
            if (newY > region.locations.size() - 1) newY = 0;
            if (region.locations.get(newY).get(x).cucumber == null) {
              hasMoved = true;
              var newLocation = new Location();
              newLocation.cucumber = new Cucumber(Direction.SOUTH);
              // Move cucumber
              newLocations.get(newY).set(x, newLocation);
              // Set current pos to null
              newLocations.get(y).set(x, new Location());
            }
          }
        }
        x++;
      }
      y++;
    }
    region.locations = newLocations;
    return hasMoved;
  }

  public boolean regionStepEast(Region region) {
    var newLocations = copyLocations(region);
    var hasMoved = false;
    var y = 0;
    for (var row : region.locations) {
      var x = 0;
      for (var l : row) {
        if (l.cucumber != null) {
          if (l.cucumber.direction == Direction.EAST) {
            var newX = x+1;
            if (newX > row.size() - 1) newX = 0;
            if (row.get(newX).cucumber == null) {
              hasMoved = true;
              var newLocation = new Location();
              newLocation.cucumber = new Cucumber(Direction.EAST);
              // Move cucumber
              newLocations.get(y).set(newX, newLocation);
              // Set current pos to null
              newLocations.get(y).set(x, new Location());
            }
          }
        }
        x++;
      }
      y++;
    }
    region.locations = newLocations;
    return hasMoved;
  }

  // Deep copy of region locations
  public ArrayList<ArrayList<Location>> copyLocations(Region region) {
    var newLocations = new ArrayList<ArrayList<Location>>();
    for (var row : region.locations) {
      var newRow = new ArrayList<Location>();
      for (var l : row) {
        var newLocation = new Location();
        if (l.cucumber != null) {
          if (l.cucumber.direction == Direction.EAST) {
            newLocation.cucumber = new Cucumber(Direction.EAST);
          } else {
            newLocation.cucumber = new Cucumber(Direction.SOUTH);
          }
        }
        newRow.add(newLocation);
      }
      newLocations.add(newRow);
    }
    return newLocations;
  }
}
