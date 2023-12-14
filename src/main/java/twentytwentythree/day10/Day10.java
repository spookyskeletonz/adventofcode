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

        // convert all non loop to '.'
        for (var i = 0; i < grid.size(); ++i) {
            for (var j = 0; j < grid.get(i).size(); ++j) {
                if (loopCoords.contains(new Coord(i, j))) continue;
                grid.get(i).set(j, '.');
            }
        }

        System.out.println("just loop");
        printGrid(grid);

        // upscale, then flood fill all elements reachable from outside of loop to *
        System.out.println("upscaled");
        var upscaledGrid = upscaleGrid(grid);
        printGrid(upscaledGrid);
        Queue<Coord> toVisit = new LinkedList<Coord>();
        toVisit.add(new Coord(0, 0));
        while (!toVisit.isEmpty()) {
            var curr = toVisit.poll();
            if (visitedPartTwo.contains(curr)) {
                continue;
            }
            visitedPartTwo.add(curr);
            if (curr.row < upscaledGrid.size() && curr.row >= 0 && curr.col < upscaledGrid.get(curr.row).size() && curr.col >= 0) {
                if (upscaledGrid.get(curr.row).get(curr.col) == '.' || upscaledGrid.get(curr.row).get(curr.col) == '/') {
                    upscaledGrid.get(curr.row).set(curr.col, '*');
                    var north = new Coord(curr.row - 1, curr.col);
                    if (!visitedPartTwo.contains(north)) {
                        toVisit.add(north);
                    }
                    var south = new Coord(curr.row + 1, curr.col);
                    if (!visitedPartTwo.contains(south)) {
                        toVisit.add(south);
                    }
                    var east = new Coord(curr.row, curr.col + 1);
                    if (!visitedPartTwo.contains(east)) {
                        toVisit.add(east);
                    }
                    var west = new Coord(curr.row, curr.col - 1);
                    if (!visitedPartTwo.contains(west)) {
                        toVisit.add(west);
                    }
                }
            }
        }
        System.out.println("filled");
        printGrid(upscaledGrid);

        var dotCount = 0;
        for (var i = 0; i < upscaledGrid.size(); ++i) {
            for (var j = 0; j < upscaledGrid.get(i).size(); j++) {
                if (upscaledGrid.get(i).get(j) == '.') dotCount++;
            }
        }
        return dotCount;
    }

    private void printGrid(ArrayList<ArrayList<Character>> grid) {
        for (var row : grid) {
            for (var c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    // NEED TO DEBUG
    private ArrayList<ArrayList<Character>> upscaleGrid(ArrayList<ArrayList<Character>> grid) {
        // create new grid with 1:3 size
        var newGrid = new ArrayList<ArrayList<Character>>(grid.size() * 3);
        for (var row : grid) {
            var newRow = new ArrayList<>(row);
            newRow.addAll(row);
            newRow.addAll(row);
            newGrid.add(newRow);
            newGrid.add(newRow);
            newGrid.add(newRow);
        }
        for (var i = 0; i < grid.size(); ++i) {
            for (var j = 0; j < grid.get(i).size(); ++j) {
                var charsToInsert = switch (grid.get(i).get(j)) {
                    case '.' -> new char[]{
                            '/','/','/',
                            '/','.','/',
                            '/','/','/'};
                    case 'S' -> new char[]{'/','/','/','/','S','/','/','/','/'};
                    case 'F' -> new char[]{'/','F','F','/','F','/','/','F','/'};
                    case '7' -> new char[]{'7','7','/','/','7','/','/','7','/'};
                    case 'J' -> new char[]{'/','J','/','/','J','/','J','J','/'};
                    case 'L' -> new char[]{'/','L','/','/','L','/','/','L','L'};
                    case '-' -> new char[]{'/','/','/','-','-','-','/','/','/'};
                    case '|' -> new char[]{'/','|','/','/','|','/','/','|','/'};
                    default -> throw new RuntimeException();
                };
                var charIndex = 0;
                var rowMin = i * 3;
                var rowMax = i * 3 + 2;
                var colMin = j * 3;
                var colMax = j * 3 + 2;
                for (var i2 = rowMin; i2 <= rowMax; ++i2) {
                    for (var j2 = colMin; j2 <= colMax; ++j2) {
                        newGrid.get(i2).set(j2, charsToInsert[charIndex]);
                        charIndex++;
                    }
                }
            }
        }
        return newGrid;
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
