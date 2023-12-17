package twentytwentythree.day17;

import com.sun.source.doctree.EscapeTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day17 {
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

    private static class State {
        public Coord coord;
        public char direction;
        public int numDirection;

        public int heatLossTotal;
        public HashSet<Coord> visited;

        public State(Coord coord, char direction, int numDirection, int heatLossTotal, HashSet<Coord> visited) {
            this.coord = coord;
            this.direction = direction;
            this.numDirection = numDirection;
            this.heatLossTotal = heatLossTotal;
            this.visited = visited;
        }
    }

    private static List<ArrayList<Integer>> scanGrid(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var grid = new ArrayList<ArrayList<Integer>>();
        while (scanner.hasNextLine()) {
            var row = new ArrayList<Integer>();
            for (var c : scanner.nextLine().split("")) {
                row.add(Integer.parseInt(c));
            }
            grid.add(row);
        }
        return grid;
    }

    public static int solvePartOne(File file) throws FileNotFoundException {
        var grid = scanGrid(file);

        var toVisit = new PriorityQueue<State>(Comparator.comparingInt(s -> s.heatLossTotal));
        toVisit.add(new State(new Coord(0, 1), 'R', 1, grid.get(0).get(1), new HashSet<>()));
        toVisit.add(new State(new Coord(1, 0), 'D', 1, grid.get(1).get(0), new HashSet<>()));
        // cache the lowest heat loss to a given coord from a given direction with number of steps
        var lowestMap = new HashMap<String, Integer>();

        while (!toVisit.isEmpty()) {
            var state = toVisit.poll();
            var row = state.coord.row;
            var col = state.coord.col;
            var coord = new Coord(row, col);
            var lowestKey = row + ":" + col + ":" + state.direction + ":" + state.numDirection;
            if (lowestMap.containsKey(lowestKey) && state.heatLossTotal >= lowestMap.get(lowestKey)) {
                // already found a better path to this coord
                continue;
            }
            if (row == grid.size() - 1 && col == grid.get(row).size() - 1) {
                return state.heatLossTotal;
            }
            lowestMap.put(lowestKey, state.heatLossTotal);
            if (state.visited.contains(coord)) continue;
            var visited = new HashSet<>(state.visited);
            visited.add(new Coord(row, col));
            if (state.direction != 'D' && (state.direction != 'U' || state.numDirection < 3) && row - 1 >= 0) {
                var numDirection = state.direction != 'U' ? 1 : state.numDirection + 1;
                var heatLossTotal = state.heatLossTotal + grid.get(row - 1).get(col);
                toVisit.add(new State(new Coord(row - 1, col), 'U', numDirection, heatLossTotal, visited));
            }
            if (state.direction != 'L' && (state.direction != 'R' || state.numDirection < 3) && col + 1 < grid.get(row).size()) {
                var numDirection = state.direction != 'R' ? 1 : state.numDirection + 1;
                var heatLossTotal = state.heatLossTotal + grid.get(row).get(col + 1);
                toVisit.add(new State(new Coord(row, col + 1), 'R', numDirection, heatLossTotal, visited));
            }
            if (state.direction != 'U' && (state.direction != 'D' || state.numDirection < 3) && row + 1 < grid.size()) {
                var numDirection = state.direction != 'D' ? 1 : state.numDirection + 1;
                var heatLossTotal = state.heatLossTotal + grid.get(row + 1).get(col);
                toVisit.add(new State(new Coord(row + 1, col), 'D', numDirection, heatLossTotal, visited));
            }
            if (state.direction != 'R' && (state.direction != 'L' || state.numDirection < 3) && col - 1 >= 0) {
                var numDirection = state.direction != 'L' ? 1 : state.numDirection + 1;
                var heatLossTotal = state.heatLossTotal + grid.get(row).get(col - 1);
                toVisit.add(new State(new Coord(row, col - 1), 'L', numDirection, heatLossTotal, visited));
            }
        }

        return -1;
    }

    public static int solvePartTwo(File file) throws FileNotFoundException {
        var grid = scanGrid(file);

        var toVisit = new PriorityQueue<State>(Comparator.comparingInt(s -> s.heatLossTotal));
        toVisit.add(new State(new Coord(0, 1), 'R', 1, grid.get(0).get(1), new HashSet<>()));
        toVisit.add(new State(new Coord(1, 0), 'D', 1, grid.get(1).get(0), new HashSet<>()));
        // cache the lowest heat loss to a given coord from a given direction with number of steps
        var lowestMap = new HashMap<String, Integer>();

        while (!toVisit.isEmpty()) {
            var state = toVisit.poll();
            var row = state.coord.row;
            var col = state.coord.col;
            var coord = new Coord(row, col);
            var lowestKey = row + ":" + col + ":" + state.direction + ":" + state.numDirection;
            if (lowestMap.containsKey(lowestKey) && state.heatLossTotal >= lowestMap.get(lowestKey)) {
                // already found a better path to this coord
                continue;
            }
            if (row == grid.size() - 1 && col == grid.get(row).size() - 1) {
                return state.heatLossTotal;
            }
            lowestMap.put(lowestKey, state.heatLossTotal);
            if (state.visited.contains(coord)) continue;
            var visited = new HashSet<>(state.visited);
            visited.add(new Coord(row, col));
            if (state.numDirection < 4) {
                // have to continue moving in this direction
                var numDirection = state.numDirection + 1;
                if (state.direction == 'U') {
                    if (row - 1 < 0) continue;
                    var heatLossTotal = state.heatLossTotal + grid.get(row - 1).get(col);
                    toVisit.add(new State(new Coord(row - 1, col), 'U', numDirection, heatLossTotal, visited));
                } else if (state.direction == 'R') {
                    if (col + 1 > grid.get(row).size() - 1) continue;
                    var heatLossTotal = state.heatLossTotal + grid.get(row).get(col + 1);
                    toVisit.add(new State(new Coord(row, col + 1), 'R', numDirection, heatLossTotal, visited));
                } else if (state.direction == 'D') {
                    if (row + 1 > grid.size() - 1) continue;
                    var heatLossTotal = state.heatLossTotal + grid.get(row + 1).get(col);
                    toVisit.add(new State(new Coord(row + 1, col), 'D', numDirection, heatLossTotal, visited));
                } else if (state.direction == 'L') {
                    if (col - 1 < 0) continue;
                    var heatLossTotal = state.heatLossTotal + grid.get(row).get(col - 1);
                    toVisit.add(new State(new Coord(row, col - 1), 'L', numDirection, heatLossTotal, visited));
                }
            } else {
                if (state.direction != 'D' && (state.direction != 'U' || state.numDirection < 10) && row - 1 >= 0) {
                    var numDirection = state.direction != 'U' ? 1 : state.numDirection + 1;
                    var heatLossTotal = state.heatLossTotal + grid.get(row - 1).get(col);
                    toVisit.add(new State(new Coord(row - 1, col), 'U', numDirection, heatLossTotal, visited));
                }
                if (state.direction != 'L' && (state.direction != 'R' || state.numDirection < 10) && col + 1 < grid.get(row).size()) {
                    var numDirection = state.direction != 'R' ? 1 : state.numDirection + 1;
                    var heatLossTotal = state.heatLossTotal + grid.get(row).get(col + 1);
                    toVisit.add(new State(new Coord(row, col + 1), 'R', numDirection, heatLossTotal, visited));
                }
                if (state.direction != 'U' && (state.direction != 'D' || state.numDirection < 10) && row + 1 < grid.size()) {
                    var numDirection = state.direction != 'D' ? 1 : state.numDirection + 1;
                    var heatLossTotal = state.heatLossTotal + grid.get(row + 1).get(col);
                    toVisit.add(new State(new Coord(row + 1, col), 'D', numDirection, heatLossTotal, visited));
                }
                if (state.direction != 'R' && (state.direction != 'L' || state.numDirection < 10) && col - 1 >= 0) {
                    var numDirection = state.direction != 'L' ? 1 : state.numDirection + 1;
                    var heatLossTotal = state.heatLossTotal + grid.get(row).get(col - 1);
                    toVisit.add(new State(new Coord(row, col - 1), 'L', numDirection, heatLossTotal, visited));
                }
            }
        }

        return -1;
    }
}
