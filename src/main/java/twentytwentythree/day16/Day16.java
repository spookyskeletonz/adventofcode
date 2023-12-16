package twentytwentythree.day16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day16 {
    private List<ArrayList<Character>> grid = new ArrayList<>();

    private static class State {
        public int row;
        public int col;
        public char dir;

        public State(int row, int col, char dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return row == state.row && col == state.col && dir == state.dir;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col, dir);
        }
    }


    public int solvePartOne(File file) throws FileNotFoundException {
        scanGrid(file);
        return energize(new State(0, 0, 'R'));
    }

    public int solvePartTwo(File file) throws FileNotFoundException {
        scanGrid(file);
        var maxEnergized = Integer.MIN_VALUE;
        for (var i = 0; i < grid.size(); ++i) {
            maxEnergized = Math.max(maxEnergized, energize(new State(i, 0, 'R')));
            maxEnergized = Math.max(maxEnergized, energize(new State(i, grid.get(i).size() - 1, 'L')));
        }
        for (var i = 0; i < grid.get(0).size(); ++i) {
            maxEnergized = Math.max(maxEnergized, energize(new State(0, i, 'D')));
            maxEnergized = Math.max(maxEnergized, energize(new State(grid.size() - 1, i, 'U')));
        }
        return maxEnergized;
    }

    private void scanGrid(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            var chars = scanner.nextLine().toCharArray();
            var row = new ArrayList<Character>();
            for (var c : chars) {
                row.add(c);
            }
            grid.add(row);
        }
    }

    private int energize(State initial) {
        var energized = new HashSet<String>();
        Queue<State> toVisit = new LinkedList<>();
        var visited = new HashSet<String>();
        toVisit.add(initial);
        while (!toVisit.isEmpty()) {
            var state = toVisit.poll();
            var row = state.row;
            var col = state.col;
            var direction = state.dir;
            var key = row + ":" + col + ":" + direction;
            if (visited.contains(key)) continue;
            if (row < 0 || row >= grid.size()) continue;
            if (col < 0 || col >= grid.get(row).size()) continue;
            var curr = grid.get(row).get(col);
            visited.add(key);
            energized.add(row + ":" + col);
            switch (curr) {
                case '.' -> {
                    switch (direction) {
                        case 'U' -> toVisit.add(new State(row - 1, col, direction));
                        case 'R' -> toVisit.add(new State(row, col + 1, direction));
                        case 'D' -> toVisit.add(new State(row + 1, col, direction));
                        case 'L' -> toVisit.add(new State(row, col - 1, direction));
                    }
                }
                case '|' -> {
                    switch (direction) {
                        case 'U' -> toVisit.add(new State(row - 1, col, direction));
                        case 'R', 'L' -> {
                            toVisit.add(new State(row - 1, col, 'U'));
                            toVisit.add(new State(row + 1, col, 'D'));
                        }
                        case 'D' -> toVisit.add(new State(row + 1, col, direction));
                    }
                }
                case '-' -> {
                    switch (direction) {
                        case 'U', 'D' -> {
                            toVisit.add(new State(row, col - 1, 'L'));
                            toVisit.add(new State(row, col + 1, 'R'));
                        }
                        case 'R' -> toVisit.add(new State(row, col + 1, direction));
                        case 'L' -> toVisit.add(new State(row, col - 1, direction));
                    }
                }
                case '/' -> {
                    switch (direction) {
                        case 'U' -> toVisit.add(new State(row, col + 1, 'R'));
                        case 'R' -> toVisit.add(new State(row - 1, col, 'U'));
                        case 'D' -> toVisit.add(new State(row, col - 1, 'L'));
                        case 'L' -> toVisit.add(new State(row + 1, col, 'D'));
                    }
                }
                case '\\' -> {
                    switch (direction) {
                        case 'U' -> toVisit.add(new State(row, col - 1, 'L'));
                        case 'R' -> toVisit.add(new State(row + 1, col, 'D'));
                        case 'D' -> toVisit.add(new State(row, col + 1, 'R'));
                        case 'L' -> toVisit.add(new State(row - 1, col, 'U'));
                    }
                }
            }
        }
        return energized.size();
    }
}
