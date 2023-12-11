package twentytwentythree.day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day11 {
    private Set<Integer> expansionRows = new HashSet<>();
    private Set<Integer> expansionColumns = new HashSet<>();
    private long expansionSize;

    private static class Coord {
        public int row;
        public int col;

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

    public long solvePartOne(File file) throws FileNotFoundException {
        return solve(file, 2);
    }

    public long solvePartTwoBasic(File file) throws FileNotFoundException {
        return solve(file, 100);
    }

    public long solvePartTwo(File file) throws FileNotFoundException {
        return solve(file, 1000000);
    }

    private long solve(File file, int expansionSize) throws FileNotFoundException {
        var scanner = new Scanner(file);
        this.expansionSize = expansionSize;
        var grid = new ArrayList<ArrayList<Character>>();
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var row = new ArrayList<Character>();
            for (var c : line.toCharArray()) {
                row.add(c);
            }
            grid.add(row);
        }
        // set expansion rows
        for (var i = 0; i < grid.size(); ++i) {
            var row = grid.get(i);
            if (row.stream().allMatch(c -> c == '.')) {
                expansionRows.add(i);
            }
        }
        // set expansion columns
        var populatedColumns = new HashSet<Integer>();
        for (var row : grid) {
            for (var j = 0; j < row.size(); ++j) {
                if (populatedColumns.contains(j)) continue;
                if (row.get(j) == '#') populatedColumns.add(j);
            }
        }
        for (var i = 0; i < grid.get(0).size(); i++) {
            if (!populatedColumns.contains(i)) expansionColumns.add(i);
        }

        // get set of galaxy coords
        var galaxies = new HashSet<Coord>();
        for (var i = 0; i < grid.size(); ++i) {
            for (var j = 0; j < grid.get(i).size(); ++j) {
                if (grid.get(i).get(j) == '#') galaxies.add(new Coord(i, j));
            }
        }

        // sum lengths of possible paths
        var lengthSum = 0L;
        for (Iterator<Coord> i = galaxies.iterator(); i.hasNext();) {
            var galaxyOne = i.next();
            i.remove();
            for (var galaxyTwo : galaxies) {
                lengthSum += calcLength(galaxyOne, galaxyTwo);
            }
        }
        return lengthSum;
    }

    private long calcLength(Coord one, Coord two) {
        // manhattan dist but handling for intersection with expansion rows and columns
        var expandedRowsCrossed = 0;
        var greaterRow = Math.max(one.row, two.row);
        var lesserRow = Math.min(one.row, two.row);
        for (var i = lesserRow; i <= greaterRow; ++i) {
            if (expansionRows.contains(i)) expandedRowsCrossed++;
        }
        var expandedColsCrossed = 0;
        var greaterCol = Math.max(one.col, two.col);
        var lesserCol = Math.min(one.col, two.col);
        for (var i = lesserCol; i <= greaterCol; ++i) {
            if (expansionColumns.contains(i)) expandedColsCrossed++;
        }
        return Math.abs(one.row - two.row) + Math.abs(one.col - two.col)
                + (expandedRowsCrossed * expansionSize) - expandedRowsCrossed
                + (expandedColsCrossed * expansionSize) - expandedColsCrossed;
    }
}
