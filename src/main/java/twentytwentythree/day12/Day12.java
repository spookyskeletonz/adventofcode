package twentytwentythree.day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day12 {
    private class State {
        String str;
        List<Integer> arrangement;

        public State(String str, List<Integer> arrangement) {
            this.str = str;
            this.arrangement = arrangement;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return Objects.equals(str, state.str) && Objects.equals(arrangement, state.arrangement);
        }

        @Override
        public int hashCode() {
            return Objects.hash(str, arrangement);
        }
    }

    private HashMap<State, Long> cache = new HashMap<>();

    public long solvePartOne(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var count = 0L;
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var str = line.split(" ")[0];
            var arrangements = Arrays.stream(line.split(" ")[1].split(",")).map(Integer::parseInt).toList();
            count += solve(str, arrangements);
        }
        return count;
    }

    public long solvePartTwo(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var count = 0L;
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var str = line.split(" ")[0];
            var continuousString = "";
            var arrangements = Arrays.stream(line.split(" ")[1].split(",")).map(Integer::parseInt).toList();
            var continuousArrangements = new ArrayList<Integer>();
            for (var i = 0; i < 5; ++i) {
                continuousString += str;
                if (i != 4) continuousString += '?';
                continuousArrangements.addAll(arrangements);
            }
            count += solve(continuousString, continuousArrangements);
        }
        return count;
    }

    private long solve(String str, List<Integer> arrangements) {
        var key = new State(str, arrangements);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        if (str.isEmpty() && arrangements.isEmpty()) {
            cache.put(key, 1L);
            return 1L;
        }
        if (str.isEmpty()) return 0L;

        var res = 0L;
        if (str.startsWith(".")) {
            res = solve(str.replaceFirst("\\.", ""), arrangements);
        } else if (str.startsWith("?")) {
            res = solve(str.replaceFirst("\\?", "."), arrangements)
                    + solve(str.replaceFirst("\\?", "#"), arrangements);
        } else if (str.startsWith("#")) {
            if (arrangements.isEmpty()) res = 0L;
            else if (str.length() < arrangements.get(0)) res = 0L;
            else if (str.length() == arrangements.get(0)) {
                if (str.matches("^#+[#?]*$")) {
                    var newArrangements = new ArrayList<>(arrangements);
                    newArrangements.remove(0);
                    res = solve("", newArrangements);
                }
            } else {
                if (str.substring(0, arrangements.get(0) + 1).matches("^#+[#?]*\\.$")) {
                    var newArrangements = new ArrayList<>(arrangements);
                    newArrangements.remove(0);
                    res = solve(str.substring(arrangements.get(0) + 1), newArrangements);
                } else if (str.substring(0, arrangements.get(0) + 1).matches("^#+[#?]*\\?+$")) {
                    var newArrangements = new ArrayList<>(arrangements);
                    newArrangements.remove(0);
                    res = solve(str.substring(arrangements.get(0) + 1), newArrangements);
                }
            }
        }
        cache.put(key, res);
        return res;
    }
}
