package twentytwentythree.day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day03 {
    protected static class Coord {
        int rowNum;
        int colNum;

        public Coord(int rowNum, int colNum) {
            this.rowNum = rowNum;
            this.colNum = colNum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coord coord = (Coord) o;
            return rowNum == coord.rowNum && colNum == coord.colNum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowNum, colNum);
        }
    }

    List<List<Character>> matrix = new ArrayList<>();
    HashSet<Coord> foundNumCoords = new HashSet<>();

    public int solvePartOne(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        matrix = buildMatrix(scanner);
        var sum = 0;
        // Look for symbols
        for (var rowNum = 0; rowNum < matrix.size(); rowNum++) {
            var row = matrix.get(rowNum);
            for (var colNum = 0; colNum < row.size(); colNum++) {
                var item = row.get(colNum);
                if (!Character.isDigit(item) && !item.equals('.')) {
                    var foundNums = findSurroundingNumbers(rowNum, colNum);
                    for (var num : foundNums) {
                        sum += num;
                    }
                }
            }
        }
        return sum;
    }

    public int solvePartTwo(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        matrix = buildMatrix(scanner);
        var sum = 0;
        // Look for gears
        for (var rowNum = 0; rowNum < matrix.size(); rowNum++) {
            var row = matrix.get(rowNum);
            for (var colNum = 0; colNum < row.size(); colNum++) {
                var item = row.get(colNum);
                if (item.equals('*')) {
                    var foundNums = findSurroundingNumbers(rowNum, colNum);
                    if (foundNums.size() == 2) {
                        sum += foundNums.get(0) * foundNums.get(1);
                    }
                }
            }
        }
        return sum;
    }

    private List<Integer> findSurroundingNumbers(int rowNum, int colNum) {
        var foundNums = new ArrayList<Integer>();
        // Look for digits surrounding a symbol that have not been seen already
        for (var i = rowNum - 1; i <= rowNum + 1; ++i) {
            if (i < 0 || i >= matrix.size()) continue;
            var row = matrix.get(i);
            for (var j = colNum - 1; j <= colNum + 1; ++j) {
                if (j < 0 || j >= row.size()) continue;
                if (Character.isDigit(matrix.get(i).get(j))) {
                    var num = maybeBuildNum(i, j);
                    if (num != null) {
                        foundNums.add(num);
                    }
                }
            }
        }
        return foundNums;
    }

    private Integer maybeBuildNum(int i, int startJ) {
        StringBuilder numS = new StringBuilder("" + matrix.get(i).get(startJ));
        if (foundNumCoords.contains(new Coord(i, startJ))) return null;
        foundNumCoords.add(new Coord(i, startJ));
        // span left
        var leftJ = startJ - 1;
        while (leftJ >= 0 && Character.isDigit(matrix.get(i).get(leftJ))) {
            var coord = new Coord(i, leftJ);
            if (foundNumCoords.contains(coord)) return null;
            foundNumCoords.add(coord);
            numS.insert(0, matrix.get(i).get(leftJ));
            leftJ--;
        }
        // span right
        var rightJ = startJ + 1;
        while (rightJ < matrix.get(i).size() && Character.isDigit(matrix.get(i).get(rightJ))) {
            var coord = new Coord(i, rightJ);
            if (foundNumCoords.contains(coord)) return null;
            foundNumCoords.add(coord);
            numS.append(matrix.get(i).get(rightJ));
            rightJ++;
        }
        return Integer.parseInt(numS.toString().toString());
    }

    private List<List<Character>> buildMatrix(Scanner scanner) {
        var matrix = new ArrayList<List<Character>>();
        while (scanner.hasNextLine()) {
            var charArray = scanner.nextLine().toCharArray();
            var row = new ArrayList<Character>();
            for (var c : charArray) row.add(c);
            matrix.add(row);
        }
        return matrix;
    }
}
