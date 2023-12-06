package twentytwentythree.day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day06 {
    public static int solvePartOne(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var times = Arrays.stream(scanner.nextLine().split(":")[1].split("\\W+"))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toList();
        var distances = Arrays.stream(scanner.nextLine().split(":")[1].split("\\W+"))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toList();
        var multiple = 1;
        for (var i = 0; i < times.size(); ++i) {
            var numWinning = calcNumWinning(times.get(i), distances.get(i));
            multiple *= numWinning;
        }
        return multiple;
    }

    public static long solvePartTwo(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var time = Long.parseLong(Arrays.stream(scanner.nextLine().split(":")[1].split("\\W+"))
                .filter(s -> !s.isEmpty())
                .reduce("", (s1, s2) -> s1 + s2));
        var distance = Long.parseLong(Arrays.stream(scanner.nextLine().split(":")[1].split("\\W+"))
                .filter(s -> !s.isEmpty())
                .reduce("", (s1, s2) -> s1 + s2));
        return calcNumWinning(time, distance);
    }

    public static long calcNumWinning(long time, long distanceToBeat) {
        var numWinning = 0;
        for (var i = 1; i <= time; i++) {
            var dist = calcDistance(i, time);
            if (dist > distanceToBeat) numWinning++;
            // can early return if we start losing
            else if (numWinning > 1 && dist < distanceToBeat) break;
        }
        return numWinning;
    }

    public static long calcDistance(long heldTime, long totalTime) {
        return heldTime * (totalTime - heldTime);
    }
}
