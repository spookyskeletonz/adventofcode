package twentytwentythree.day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day14 {
    private final Map<ArrayList<ArrayList<Character>>, Integer> lastSeen = new HashMap<>();

    public int solvePartOne(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var grid = new ArrayList<ArrayList<Character>>();
        while (scanner.hasNextLine()) {
            var row = new ArrayList<Character>();
            for (var c : scanner.nextLine().toCharArray()) {
                row.add(c);
            }
            grid.add(row);
        }
        tiltNorth(grid);
        return getLoad(grid);
    }

    public int solvePartTwo(File file, int cycles) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var grid = new ArrayList<ArrayList<Character>>();
        while (scanner.hasNextLine()) {
            var row = new ArrayList<Character>();
            for (var c : scanner.nextLine().toCharArray()) {
                row.add(c);
            }
            grid.add(row);
        }
        int i;
        Integer loopLength = null;
        for (i = 1; i <= cycles; ++i) {
            loopLength = tilt(i, grid);
            if (loopLength != null) {
                break;
            }
        }
        var cyclesToContinue = ((cycles - i) % loopLength) + 1;
        for (var j = 0; j < cyclesToContinue; ++j) {
            lastSeen.clear();
            tilt(j, grid);
        }
        return getLoad(grid);
    }

    private static int getLoad(ArrayList<ArrayList<Character>> grid) {
        var totalLoad = 0;
        for (var col = 0; col < grid.get(0).size(); ++col) {
            for (var row = grid.size() - 1; row >= 0; --row) {
                if (grid.get(row).get(col) == 'O') {
                    totalLoad += grid.size() - row;
                }
            }
        }
        return totalLoad;
    }

    // returns loop length if it finds a loop
    private Integer tilt(int cycles, ArrayList<ArrayList<Character>> grid) {
        if (lastSeen.containsKey(grid)) {
            var last = lastSeen.get(grid);
            // We have found a loop to get here that will repeat itself until we hit the max, so can return on this
            return cycles - last;
        }
        var key = new ArrayList<ArrayList<Character>>();
        for (var row : grid) {
            key.add(new ArrayList<>(row));
        }
        tiltNorth(grid);
        tiltWest(grid);
        tiltSouth(grid);
        tiltEast(grid);
        lastSeen.put(key, cycles);
        return null;
    }

    private void tiltNorth(ArrayList<ArrayList<Character>> grid) {
        for (var col = 0; col < grid.get(0).size(); ++col) {
            var spots = new ArrayList<Integer>();
            var rocks = new ArrayList<Integer>();
            for (var row = 0; row < grid.size(); ++row) {
                var c = grid.get(row).get(col);
                switch (c) {
                    case 'O': {
                        rocks.add(row);
                        spots.add(row);
                        grid.get(row).set(col, '.');
                        break;
                    }
                    case '.': {
                        spots.add(row);
                        break;
                    }
                    case '#': {
                        for (var j = 0; j < rocks.size(); ++j) {
                            var spot = spots.removeFirst();
                            grid.get(spot).set(col, 'O');
                        }
                        spots = new ArrayList<>();
                        rocks = new ArrayList<>();
                        break;
                    }
                }
            }
            for (var j = 0; j < rocks.size(); ++j) {
                var spot = spots.removeFirst();
                grid.get(spot).set(col, 'O');
            }
        }
    }

    private void tiltWest(ArrayList<ArrayList<Character>> grid) {
        for (var row : grid) {
            var spots = new ArrayList<Integer>();
            var rocks = new ArrayList<Integer>();
            for (var i = 0; i < row.size(); ++i) {
                var c = row.get(i);
                switch (c) {
                    case 'O': {
                        rocks.add(i);
                        spots.add(i);
                        row.set(i, '.');
                        break;
                    }
                    case '.': {
                        spots.add(i);
                        break;
                    }
                    case '#': {
                        for (var j = 0; j < rocks.size(); ++j) {
                            var spot = spots.removeFirst();
                            row.set(spot, 'O');
                        }
                        spots = new ArrayList<>();
                        rocks = new ArrayList<>();
                        break;
                    }
                }
            }
            for (var j = 0; j < rocks.size(); ++j) {
                var spot = spots.removeFirst();
                row.set(spot, 'O');
            }
        }
    }

    private void tiltSouth(ArrayList<ArrayList<Character>> grid) {
        for (var col = 0; col < grid.get(0).size(); ++col) {
            var spots = new ArrayList<Integer>();
            var rocks = new ArrayList<Integer>();
            for (var row = grid.size() - 1; row >= 0; --row) {
                var c = grid.get(row).get(col);
                switch (c) {
                    case 'O': {
                        rocks.add(row);
                        spots.add(row);
                        grid.get(row).set(col, '.');
                        break;
                    }
                    case '.': {
                        spots.add(row);
                        break;
                    }
                    case '#': {
                        for (var j = 0; j < rocks.size(); ++j) {
                            var spot = spots.removeFirst();
                            grid.get(spot).set(col, 'O');
                        }
                        spots = new ArrayList<>();
                        rocks = new ArrayList<>();
                        break;
                    }
                }
            }
            for (var j = 0; j < rocks.size(); ++j) {
                var spot = spots.removeFirst();
                grid.get(spot).set(col, 'O');
            }
        }
    }

    private void tiltEast(ArrayList<ArrayList<Character>> grid) {
        for (var row : grid) {
            var spots = new ArrayList<Integer>();
            var rocks = new ArrayList<Integer>();
            for (var i = row.size() - 1; i >= 0; --i) {
                var c = row.get(i);
                switch (c) {
                    case 'O': {
                        rocks.add(i);
                        spots.add(i);
                        row.set(i, '.');
                        break;
                    }
                    case '.': {
                        spots.add(i);
                        break;
                    }
                    case '#': {
                        for (var j = 0; j < rocks.size(); ++j) {
                            var spot = spots.removeFirst();
                            row.set(spot, 'O');
                        }
                        spots = new ArrayList<>();
                        rocks = new ArrayList<>();
                        break;
                    }
                }
            }
            for (var j = 0; j < rocks.size(); ++j) {
                var spot = spots.removeFirst();
                row.set(spot, 'O');
            }
        }
    }

    private void printGrid(ArrayList<ArrayList<Character>> grid) {
        for (var row : grid) {
            for (var c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println();
    }
}
