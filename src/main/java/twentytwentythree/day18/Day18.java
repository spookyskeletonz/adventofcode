package twentytwentythree.day18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day18 {
    public static int solvePartOne(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        return getArea(scanner);
    }

    public static long solvePartTwo(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        return getAreaTwo(scanner);
    }

    private static Long getAreaTwo(Scanner scanner) {
        // shoelace & picks theorem
        // we want to find the points both inside the shape and the perim
        // picks theorem: Area = pointsInside + perim/2 - 1
        // => pointsInside = area + 1 - perim/2
        // => pointsInside + perim = area + 1 + perim/2
        // using shoelace: pointsInside + perim = shoelace(coords) + perim/2 + 1
        var area = 0L;
        var x = 1L;
        var y = 1L;
        var perim = 0L;
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var hex = line.split(" ")[2].replace("(", "").replace(")", "").replace("#", "");
            var direction = hex.charAt(5);
            var numSteps = HexFormat.fromHexDigitsToLong(hex.substring(0, 5));
            var prevX = x;
            var prevY = y;
            switch (direction) {
                case '3': {
                    y += numSteps;
                    break;
                }
                case '0': {
                    x += numSteps;
                    break;
                }
                case '1': {
                    y -= numSteps;
                    break;
                }
                case '2': {
                    x -= numSteps;
                    break;
                }
            }
            perim += numSteps;
            area += (prevX * y - prevY * x);
        }
        return Math.abs(area) / 2 + perim / 2 + 1;
    }

    private static Integer getArea(Scanner scanner) {
        // shoelace & picks theorem
        // we want to find the points both inside the shape and the perim
        // picks theorem: Area = pointsInside + perim/2 - 1
        // => pointsInside = area + 1 - perim/2
        // => pointsInside + perim = area + 1 + perim/2
        // using shoelace: pointsInside + perim = shoelace(coords) + perim/2 + 1
        var area = 0;
        var x = 1;
        var y = 1;
        var perim = 0;
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var direction = line.split(" ")[0];
            var numSteps = Integer.parseInt(line.split(" ")[1]);
            var prevX = x;
            var prevY = y;
            switch (direction) {
                case "U": {
                    y += numSteps;
                    break;
                }
                case "R": {
                    x += numSteps;
                    break;
                }
                case "D": {
                    y -= numSteps;
                    break;
                }
                case "L": {
                    x -= numSteps;
                    break;
                }
            }
            perim += numSteps;
            area += (prevX * y - prevY * x);
        }
        return Math.abs(area) / 2 + perim / 2 + 1;
    }
}
