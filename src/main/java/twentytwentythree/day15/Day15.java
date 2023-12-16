package twentytwentythree.day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day15 {
    public static int solvePartOne(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var line = scanner.nextLine();
        var val = 0;
        for (var string : line.split(",")) {
            val += hash(string);
        }
        return val;
    }

    private static class Lens {
        public String label;
        public int length;
    }

    public static int solvePartTwo(File file) throws FileNotFoundException {
        var boxes = new ArrayList<ArrayList<Lens>>();
        for (var i = 0; i < 256; ++i) {
            boxes.add(new ArrayList<>());
        }

        var scanner = new Scanner(file);
        var line = scanner.nextLine();

        for (var string : line.split(",")) {
            if (string.matches("^.*-$")) {
                var label = string.split("-")[0];
                var hash = hash(label);
                var lenses = boxes.get(hash);
                for (var i = 0; i < lenses.size(); ++i) {
                    var l = lenses.get(i);
                    if (l.label.equals(label)) {
                        // remove and push forward lenses
                        lenses.remove(i);
                        break;
                    }
                }
                boxes.set(hash, lenses);
            } else if (string.matches("^.*=\\d+$")) {
                var label = string.split("=")[0];
                var length = string.split("=")[1];
                var hash = hash(label);
                var lenses = boxes.get(hash);
                var newLens = new Lens();
                newLens.label = label;
                newLens.length = Integer.parseInt(length);
                var foundLens = false;
                for (var i = 0; i < lenses.size(); ++i) {
                    var l = lenses.get(i);
                    if (l.label.equals(label)) {
                        foundLens = true;
                        lenses.set(i, newLens);
                        break;
                    }
                }
                if (!foundLens) {
                    lenses.add(newLens);
                }
                boxes.set(hash, lenses);
            }
        }

        var total = 0;
        for (var boxNum = 0; boxNum < boxes.size(); ++boxNum) {
            var lenses = boxes.get(boxNum);
            for (var lensNum = 0; lensNum < lenses.size(); ++lensNum) {
                var lens = lenses.get(lensNum);
                if (lens != null) {
                    total += (boxNum + 1) * (lensNum + 1) * lens.length;
                }
            }
        }
        return total;
    }

    private static int hash(String string) {
        var val = 0;
        for (var c : string.toCharArray()) {
            val += (int) c;
            val *= 17;
            val %= 256;
        }
        return val;
    }
}
