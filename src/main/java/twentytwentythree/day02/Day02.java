package twentytwentythree.day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Day02 {
    public static int solvePartOne(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var bluePattern = Pattern.compile("(\\d+) blue");
        var redPattern = Pattern.compile("(\\d+) red");
        var greenPattern = Pattern.compile("(\\d+) green");
        var blueTotal = 14;
        var greenTotal = 13;
        var redTotal = 12;

        var sum = 0;
        var gameNum = 1;
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var isValid = true;
            for (var round : line.split(";")) {
                var blueCount = 0;
                var greenCount = 0;
                var redCount = 0;
                var blueMatcher = bluePattern.matcher(round);
                var greenMatcher = greenPattern.matcher(round);
                var redMatcher = redPattern.matcher(round);
                if (blueMatcher.find()) blueCount = Integer.parseInt(blueMatcher.group(1));
                if (greenMatcher.find()) greenCount = Integer.parseInt(greenMatcher.group(1));
                if (redMatcher.find()) redCount = Integer.parseInt(redMatcher.group(1));
                if (blueCount > blueTotal || greenCount > greenTotal || redCount > redTotal) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                sum += gameNum;
            }
            gameNum++;
        }

        return sum;
    }

    public static int solvePartTwo(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var bluePattern = Pattern.compile("(\\d+) blue");
        var redPattern = Pattern.compile("(\\d+) red");
        var greenPattern = Pattern.compile("(\\d+) green");

        var sum = 0;
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var minBlue = 0;
            var minGreen = 0;
            var minRed = 0;

            for (var round : line.split(";")) {
                var blueCount = 0;
                var greenCount = 0;
                var redCount = 0;
                var blueMatcher = bluePattern.matcher(round);
                var greenMatcher = greenPattern.matcher(round);
                var redMatcher = redPattern.matcher(round);
                if (blueMatcher.find()) blueCount = Integer.parseInt(blueMatcher.group(1));
                if (greenMatcher.find()) greenCount = Integer.parseInt(greenMatcher.group(1));
                if (redMatcher.find()) redCount = Integer.parseInt(redMatcher.group(1));

                if (blueCount > minBlue) {
                    minBlue = blueCount;
                }
                if (redCount > minRed) {
                    minRed = redCount;
                }
                if (greenCount > minGreen) {
                    minGreen = greenCount;
                }
            }

            sum += minBlue * minGreen * minRed;
        }

        return sum;
    }
}
