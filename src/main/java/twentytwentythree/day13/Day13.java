package twentytwentythree.day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day13 {
    public static int solvePartOne(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var grid = new ArrayList<ArrayList<Character>>();
        var vert = 0;
        var horiz = 0;
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            if (line.isEmpty()) {
                var rotatedGrid = rotateGrid(grid);
                vert += findMirror(rotatedGrid);
                horiz += findMirror(grid);
                grid = new ArrayList<>();
            } else {
                var row = new ArrayList<Character>();
                for (var c : line.toCharArray()) {
                    row.add(c);
                }
                grid.add(row);
            }
        }
        var rotatedGrid = rotateGrid(grid);
        vert += findMirror(rotatedGrid);
        horiz += findMirror(grid);

        return vert + 100 * horiz;
    }

    public static int solvePartTwo(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var grid = new ArrayList<ArrayList<Character>>();
        var vert = 0;
        var horiz = 0;
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            if (line.isEmpty()) {
                var rotatedGrid = rotateGrid(grid);
                vert += findMirrorWithSmudge(rotatedGrid);
                horiz += findMirrorWithSmudge(grid);
                grid = new ArrayList<>();
            } else {
                var row = new ArrayList<Character>();
                for (var c : line.toCharArray()) {
                    row.add(c);
                }
                grid.add(row);
            }
        }
        var rotatedGrid = rotateGrid(grid);
        vert += findMirrorWithSmudge(rotatedGrid);
        horiz += findMirrorWithSmudge(grid);

        return vert + 100 * horiz;
    }

    // rotate grid 90 degrees clockwise, so we can more easily compare columns like rows
    private static ArrayList<ArrayList<Character>> rotateGrid(ArrayList<ArrayList<Character>> grid) {
        var newGrid = new ArrayList<ArrayList<Character>>();
        for (var i = 0; i < grid.size(); i++) {
            for (var j = 0; j < grid.get(i).size(); j++) {
                if (j > newGrid.size() - 1) {
                    newGrid.add(new ArrayList<>());
                }
                newGrid.get(j).add(grid.get(i).get(j));
            }
        }
        for (var i = 0; i < newGrid.size(); i++) {
            newGrid.set(i, new ArrayList<>(newGrid.get(i).reversed()));
        }
        return newGrid;
    }

    private static int findMirror(ArrayList<ArrayList<Character>> grid) {
        for (var i = 0; i < grid.size() - 1; ++i) {
            var indexUp = i;
            var indexDown = i + 1;
            var isReflected = true;
            while (indexUp >= 0 && indexDown < grid.size()) {
                var upRow = grid.get(indexUp);
                var downRow = grid.get(indexDown);
                if (!upRow.equals(downRow)) {
                    isReflected = false;
                    break;
                }
                indexUp--;
                indexDown++;
            }
            if (isReflected) {
                return i + 1;
            }
        }
        return 0;
    }

    private static int findMirrorWithSmudge(ArrayList<ArrayList<Character>> grid) {
        for (var i = 0; i < grid.size() - 1; ++i) {
            var indexUp = i;
            var indexDown = i + 1;
            var isReflected = true;
            var numDiff = 0;
            while (indexUp >= 0 && indexDown < grid.size()) {
                var upRow = grid.get(indexUp);
                var downRow = grid.get(indexDown);
                // change to allow tolerance for 1 error off
                for (var j = 0; j < upRow.size(); ++j) {
                    var up = upRow.get(j);
                    var down = downRow.get(j);
                    if (up != down) {
                        numDiff++;
                    }
                }
                if (numDiff > 1) {
                    isReflected = false;
                    break;
                }
                indexUp--;
                indexDown++;
            }
            if (isReflected && numDiff == 1) {
                return i + 1;
            }
        }
        return 0;
    }
}
