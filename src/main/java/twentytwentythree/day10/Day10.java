package twentytwentythree.day10;

import twentytwentyone.day15.Day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day10 {
    private static class Coord {
        int row;
        int col;
        // excluded from equals
        Coord next;

        public Coord(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coord coord = (Coord) o;
            return row == coord.row && col == coord.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    HashSet<Coord> visitedPartTwo = new HashSet<>();
    HashSet<Coord> loopCoords = new HashSet<>();

    public int solvePartOne(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var grid = new ArrayList<ArrayList<Character>>();
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var row = new ArrayList<Character>();
            for (var sym : line.toCharArray()) {
                row.add(sym);
            }
            grid.add(row);
        }

        Coord start = null;
        // find S
        var rowNum = 0;
        while (rowNum < grid.size()) {
            var row = grid.get(rowNum);
            var colNum = 0;
            while (colNum < row.size()) {
                var sym = row.get(colNum);
                if (sym == 'S') {
                    start = new Coord(rowNum, colNum);
                    break;
                }
                colNum++;
            }
            rowNum++;
        }

        // start a breadth first traversal with stepCount until the two paths meet
        var startingCoords = findPathsFromStart(grid, start);
        var path0 = startingCoords.get(0);
        var path1 = startingCoords.get(1);
        var steps = 1;
        while (!path0.equals(path1)) {
            // increment path0
            var path0prev = new Coord(path0.row, path0.col);
            path0 = path0.next;
            path0.next = deriveNext(grid, path0prev, path0);
            // increment path1
            var path1prev = new Coord(path1.row, path1.col);
            path1 = path1.next;
            path1.next = deriveNext(grid, path1prev, path1);
            steps++;
        }
        return steps;
    }

    public int solvePartTwo(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var grid = new ArrayList<ArrayList<Character>>();
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var row = new ArrayList<Character>();
            for (var sym : line.toCharArray()) {
                row.add(sym);
            }
            grid.add(row);
        }

        Coord start = null;
        // find S
        var rowNum = 0;
        while (rowNum < grid.size()) {
            var row = grid.get(rowNum);
            var colNum = 0;
            while (colNum < row.size()) {
                var sym = row.get(colNum);
                if (sym == 'S') {
                    start = new Coord(rowNum, colNum);
                    break;
                }
                colNum++;
            }
            rowNum++;
        }

        // start a breadth first traversal with stepCount until the two paths meet, add loop set
        var startingCoords = findPathsFromStart(grid, start);
        var path0 = startingCoords.get(0);
        var path1 = startingCoords.get(1);
        while (!path0.equals(path1)) {
            loopCoords.add(path0);
            loopCoords.add(path1);
            // increment path0
            var path0prev = new Coord(path0.row, path0.col);
            path0 = path0.next;
            path0.next = deriveNext(grid, path0prev, path0);
            // increment path1
            var path1prev = new Coord(path1.row, path1.col);
            path1 = path1.next;
            path1.next = deriveNext(grid, path1prev, path1);
        }
        loopCoords.add(path0);

        for (var i = 0; i < grid.size(); ++i) {
            for (var j = 0; j < grid.get(i).size(); ++j) {
                if (loopCoords.contains(new Coord(i, j))) continue;
                grid.get(i).set(j, '.');
            }
        }

        // convert all elements reachable from outside of loop to *
        Queue<Coord> toVisit = new LinkedList<Coord>();
        toVisit.add(new Coord(0, 0));
        while (!toVisit.isEmpty()) {
            var curr = toVisit.poll();
            if (visitedPartTwo.contains(curr)) {
                continue;
            }
            visitedPartTwo.add(curr);
            if (!loopCoords.contains(curr)) {
                if (curr.row < grid.size() && curr.row >= 0 && curr.col < grid.get(curr.row).size() && curr.col >= 0) {
                    grid.get(curr.row).set(curr.col, '*');
                    for (var i = curr.row - 1; i <= curr.row + 1; ++i) {
                        for (var j = curr.col - 1; j <= curr.col + 1; ++j) {
                            var coord = new Coord(i, j);
                            if (!visitedPartTwo.contains(coord)) {
                                toVisit.add(coord);
                            }
                        }
                    }
                }
            }
        }

        // FOR BASIC CASE ONLY, DOES NOT HANDLE SQUEEZING BETWEEN PIPES YET. Could do this by upscaling the grid, so parallel pipes have space between them
        var dotCount = 0;
        for (var i = 0; i < grid.size(); ++i) {
            for (var j = 0; j < grid.get(i).size(); j++) {
                if (grid.get(i).get(j) == '.') dotCount++;
            }
        }
        return dotCount;
    }

    private static Coord deriveNext(ArrayList<ArrayList<Character>> grid, Coord prev, Coord curr) {
        var nextSym = grid.get(curr.row).get(curr.col);
        switch (nextSym) {
            case '|': {
                if (curr.row == prev.row + 1) return new Coord(curr.row + 1, curr.col);
                if (curr.row == prev.row - 1) return new Coord(curr.row - 1, curr.col);
                break;
            } case '-': {
                if (curr.col == prev.col + 1) return new Coord(curr.row, curr.col + 1);
                if (curr.col == prev.col - 1) return new Coord(curr.row, curr.col - 1);
                break;
            } case 'L': {
                if (curr.col == prev.col - 1) return new Coord(curr.row - 1, curr.col);
                if (curr.row == prev.row + 1) return new Coord(curr.row, curr.col + 1);
                break;
            } case 'J': {
                if (curr.col == prev.col + 1) return new Coord(curr.row - 1, curr.col);
                if (curr.row == prev.row + 1) return new Coord(curr.row, curr.col - 1);
                break;
            } case '7': {
                if (curr.col == prev.col + 1) return new Coord(curr.row + 1, curr.col);
                if (curr.row == prev.row - 1) return new Coord(curr.row, curr.col - 1);
                break;
            } case 'F': {
                if (curr.col == prev.col - 1) return new Coord(curr.row + 1, curr.col);
                if (curr.row == prev.row - 1) return new Coord(curr.row, curr.col + 1);
                break;
            }
        }
        return null;
    }

    private static List<Coord> findPathsFromStart(ArrayList<ArrayList<Character>> grid, Coord start) {
        var coords = new ArrayList<Coord>();
        for (var row = start.row - 1; row <= start.row + 1; row++) {
            if (row > grid.size() || row < 0) continue;
            for (var col = start.col - 1; col <= start.col + 1; col++) {
                if (col > grid.get(row).size() || col < 0) continue;
                var sym = grid.get(row).get(col);
                switch (sym) {
                    case '|': {
                        if (row == start.row - 1 && col == start.col) {
                            var newCoord = new Coord(row, col);
                            newCoord.next = new Coord(row - 1, col);
                            coords.add(newCoord);
                            continue;
                        } else if (row == start.row + 1 && col == start.col) {
                            var newCoord = new Coord(row, col);
                            newCoord.next = new Coord(row + 1, col);
                            coords.add(newCoord);
                            continue;
                        }
                        break;
                    } case '-': {
                        if (row == start.row && col == start.col - 1) {
                            var newCoord = new Coord(row, col);
                            newCoord.next = new Coord(row, col - 1);
                            coords.add(newCoord);
                            continue;
                        } else if (row == start.row && col == start.col + 1) {
                            var newCoord = new Coord(row, col);
                            newCoord.next = new Coord(row, col + 1);
                            coords.add(newCoord);
                            continue;
                        }
                        break;
                    } case 'L': {
                        if (row == start.row && col == start.col - 1) {
                            var newCoord = new Coord(row, col);
                            newCoord.next = new Coord(row - 1, col);
                            coords.add(newCoord);
                            continue;
                        } else if (row == start.row + 1 && col == start.col) {
                            var newCoord = new Coord(row, col);
                            newCoord.next = new Coord(row, col + 1);
                            coords.add(newCoord);
                            continue;
                        }
                        break;
                    } case 'J': {
                        if (row == start.row && col == start.col + 1) {
                            var newCoord = new Coord(row, col);
                            newCoord.next = new Coord(row - 1, col);
                            coords.add(newCoord);
                            continue;
                        } else if (row == start.row + 1 && col == start.col) {
                            var newCoord = new Coord(row, col);
                            newCoord.next = new Coord(row, col - 1);
                            coords.add(newCoord);
                            continue;
                        }
                        break;
                    } case '7': {
                        if (row == start.row - 1 && col == start.col) {
                            var newCoord = new Coord(row, col);
                            newCoord.next = new Coord(row, col - 1);
                            coords.add(newCoord);
                            continue;
                        } else if (row == start.row && col == start.col + 1) {
                            var newCoord = new Coord(row, col);
                            newCoord.next = new Coord(row + 1, col);
                            coords.add(newCoord);
                            continue;
                        }
                        break;
                    } case 'F': {
                        if (row == start.row && col == start.col - 1) {
                            var newCoord = new Coord(row, col);
                            newCoord.next = new Coord(row + 1, col);
                            coords.add(newCoord);
                            continue;
                        } else if (row == start.row - 1 && col == start.col) {
                            var newCoord = new Coord(row, col);
                            newCoord.next = new Coord(row, col + 1);
                            coords.add(newCoord);
                            continue;
                        }
                        break;
                    } default: {
                        continue;
                    }
                }
            }
        }
        if (coords.size() != 2) {
            throw new RuntimeException();
        }
        return coords;
    }
}
