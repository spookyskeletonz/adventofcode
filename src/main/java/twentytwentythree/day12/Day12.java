package twentytwentythree.day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day12 {

    public int solvePartOne(File file) throws FileNotFoundException {

        return 0;
    }

    private int solve(String str, List<Integer> arrangements) {
        if (str.isEmpty() && arrangements.isEmpty()) return 1;
        if (str.isEmpty()) return 0;

        if (str.startsWith(".")) {
            return solve(str.substring(1), arrangements);
        }
        if (str.startsWith("?")) {
//            return solve(str.replaceFirst("\\?", "."), arrangements)
//                    + solvePartOne(str.replaceFirst("\\?", "#"), arrangements);
        }
        if (str.startsWith("?")) {

        }
        return 0;
    }
}
