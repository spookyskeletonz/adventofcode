package twentytwentythree.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day01 {
    public static int solvePartOne(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var sum = 0;
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var firstNum = "";
            var lastNum = "";
            for (var s : line.split("")) {
                if (s.matches("\\d")) {
                    if (firstNum.isEmpty()) {
                        firstNum = s;
                        lastNum = s;
                    } else {
                        lastNum = s;
                    }
                }
            }
            sum += Integer.parseInt(firstNum + lastNum);
        }
        return sum;
    }

    public static int solvePartTwo(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var sum = 0;
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var firstNum = "";
            var lastNum = "";
            for (var i = 0; i < line.length(); ++i) {
                if (line.substring(i, Math.min(i + 3, line.length())).matches("one")) {
                    if (firstNum.isEmpty()) {
                        firstNum = "1";
                    }
                    lastNum = "1";
                } else if (line.substring(i, Math.min(i + 3, line.length())).matches("two")) {
                    if (firstNum.isEmpty()) {
                        firstNum = "2";
                    }
                    lastNum = "2";
                } else if (line.substring(i, Math.min(i + 5, line.length())).matches("three")) {
                    if (firstNum.isEmpty()) {
                        firstNum = "3";
                    }
                    lastNum = "3";
                } else if (line.substring(i, Math.min(i + 4, line.length())).matches("four")) {
                    if (firstNum.isEmpty()) {
                        firstNum = "4";
                    }
                    lastNum = "4";
                } else if (line.substring(i, Math.min(i + 4, line.length())).matches("five")) {
                    if (firstNum.isEmpty()) {
                        firstNum = "5";
                    }
                    lastNum = "5";
                } else if (line.substring(i, Math.min(i + 3, line.length())).matches("six")) {
                    if (firstNum.isEmpty()) {
                        firstNum = "6";
                    }
                    lastNum = "6";
                } else if (line.substring(i, Math.min(i + 5, line.length())).matches("seven")) {
                    if (firstNum.isEmpty()) {
                        firstNum = "7";
                    }
                    lastNum = "7";
                } else if (line.substring(i, Math.min(i + 5, line.length())).matches("eight")) {
                    if (firstNum.isEmpty()) {
                        firstNum = "8";
                    }
                    lastNum = "8";
                } else if (line.substring(i, Math.min(i + 4, line.length())).matches("nine")) {
                    if (firstNum.isEmpty()) {
                        firstNum = "9";
                    }
                    lastNum = "9";
                } else if (line.substring(i, i + 1).matches("\\d")) {
                    if (firstNum.isEmpty()) {
                        firstNum = line.substring(i, i + 1);
                    }
                    lastNum = line.substring(i, i + 1);
                }
            }
            sum += Integer.parseInt(firstNum + lastNum);
        }
        return sum;
    }
}
