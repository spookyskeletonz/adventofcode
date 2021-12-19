package twentytwentyone.day17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day17 {

  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var input = scanner.nextLine();
    var targetMinX = Integer.parseInt(input.split(": ")[1].split(", ")[0].replaceAll("x=", "").split("\\.\\.")[0]);
    var targetMaxX = Integer.parseInt(input.split(": ")[1].split(", ")[0].replaceAll("x=", "").split("\\.\\.")[1]);
    var targetMinY = Integer.parseInt(input.split(": ")[1].split(", ")[1].replaceAll("y=", "").split("\\.\\.")[0]);
    var targetMaxY = Integer.parseInt(input.split(": ")[1].split(", ")[1].replaceAll("y=", "").split("\\.\\.")[1]);
    var maxY = Integer.MIN_VALUE;
    // DUMB BRUTE FORCE LMAO
    for (var xVel = -1000; xVel <= 1000; ++xVel) {
      for (var yVel = -1000; yVel <= 1000; ++yVel) {
        if (xVel == 0 && yVel == 0) continue;
        var maxYForVel = findMaxYPositionForVel(xVel, yVel, targetMinX, targetMaxX, targetMinY, targetMaxY);
        if (maxYForVel > maxY) {
          maxY = maxYForVel;
        }
      }
    }
    return maxY;
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var input = scanner.nextLine();
    var targetMinX = Integer.parseInt(input.split(": ")[1].split(", ")[0].replaceAll("x=", "").split("\\.\\.")[0]);
    var targetMaxX = Integer.parseInt(input.split(": ")[1].split(", ")[0].replaceAll("x=", "").split("\\.\\.")[1]);
    var targetMinY = Integer.parseInt(input.split(": ")[1].split(", ")[1].replaceAll("y=", "").split("\\.\\.")[0]);
    var targetMaxY = Integer.parseInt(input.split(": ")[1].split(", ")[1].replaceAll("y=", "").split("\\.\\.")[1]);
    var numHits = 0;
    // DUMB BRUTE FORCE LMAO
    for (var xVel = -1000; xVel <= 1000; ++xVel) {
      for (var yVel = -1000; yVel <= 1000; ++yVel) {
        if (xVel == 0 && yVel == 0) continue;
        if(velHitsTarget(xVel, yVel, targetMinX, targetMaxX, targetMinY, targetMaxY)) numHits++;
      }
    }
    return numHits;
  }

  public boolean velHitsTarget(int xVel, int yVel, int targetMinX, int targetMaxX, int targetMinY, int targetMaxY) {
    var currentY = 0;
    var currentX = 0;
    var currentXVel = xVel;
    var currentYVel = yVel;
    while (true) {
      currentX += currentXVel;
      currentY += currentYVel;
      if (currentXVel > 0) {
        currentXVel--;
      } else if (currentXVel < 0) {
        currentXVel++;
      }
      currentYVel--;
      if (currentY < targetMinY) {
        // This velocity missed target
        return false;
      }
      if (currentXVel >= 0 && currentX > targetMaxX) {
        // This velocity missed target
        return false;
      }
      if (currentXVel <= 0 && currentX < targetMinX) {
        // This velocity missed target
        return false;
      }
      if (currentX <= targetMaxX
          && currentX >= targetMinX
          && currentY <= targetMaxY
          && currentY >= targetMinY) {
        // This velocity hits target
        return true;
      }
    }
  }

  public int findMaxYPositionForVel(int xVel, int yVel, int targetMinX, int targetMaxX, int targetMinY, int targetMaxY)
  {
    var maxY = 0;
    var currentY = 0;
    var currentX = 0;
    var currentXVel = xVel;
    var currentYVel = yVel;
    while (true) {
      currentX += currentXVel;
      currentY += currentYVel;
      if (currentY > maxY) maxY = currentY;
      if (currentXVel > 0) {
        currentXVel--;
      } else if (currentXVel < 0) {
        currentXVel++;
      }
      currentYVel--;
      if (currentY < targetMinY) {
        // This velocity missed target
        return Integer.MIN_VALUE;
      }
      if (currentXVel >= 0 && currentX > targetMaxX) {
        // This velocity missed target
        return Integer.MIN_VALUE;
      }
      if (currentXVel <= 0 && currentX < targetMinX) {
        // This velocity missed target
        return Integer.MIN_VALUE;
      }
      if (currentX <= targetMaxX
          && currentX >= targetMinX
          && currentY <= targetMaxY
          && currentY >= targetMinY) {
        // This velocity hits target
        return maxY;
      }
    }
  }
}
