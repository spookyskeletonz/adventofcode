package twentytwentythree.day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class Day04 {
    public static int solvePartOne(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var totalScore = 0;
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();

            var winningNumSMatcher = Pattern.compile(": (.*) \\|").matcher(line);
            if (!winningNumSMatcher.find()) throw new RuntimeException("didn't match on winning nums pattern");
            var winningNumsS = winningNumSMatcher.group(1).split("\\W+");
            var winningSet = new HashSet<Integer>();
            Arrays.stream(winningNumsS).forEach(s -> {
                if (!s.isEmpty()) winningSet.add(Integer.parseInt(s));
            });

            var numSMatcher = Pattern.compile("\\| (.*)$").matcher(line);
            if (!numSMatcher.find()) throw new RuntimeException("didn't match on nums pattern");
            var numsS = numSMatcher.group(1).split("\\W+");

            var numMatches = Arrays.stream(numsS).filter(s -> !s.isEmpty() && winningSet.contains(Integer.parseInt(s))).count();
            if (numMatches != 0) {
                totalScore += (int) Math.pow(2, numMatches - 1);
            }
        }
        return totalScore;
    }

    public static int solvePartTwo(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var cardToMatches = new HashMap<Integer, Integer>();
        var cardNum = 1;
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();

            var winningNumSMatcher = Pattern.compile(": (.*) \\|").matcher(line);
            if (!winningNumSMatcher.find()) throw new RuntimeException("didn't match on winning nums pattern");
            var winningNumsS = winningNumSMatcher.group(1).split("\\W+");
            var winningSet = new HashSet<Integer>();
            Arrays.stream(winningNumsS).forEach(s -> {
                if (!s.isEmpty()) winningSet.add(Integer.parseInt(s));
            });

            var numSMatcher = Pattern.compile("\\| (.*)$").matcher(line);
            if (!numSMatcher.find()) throw new RuntimeException("didn't match on nums pattern");
            var numsS = numSMatcher.group(1).split("\\W+");

            var numMatches = Arrays.stream(numsS).filter(s -> !s.isEmpty() && winningSet.contains(Integer.parseInt(s))).count();
            cardToMatches.put(cardNum, (int) numMatches);
            cardNum++;
        }

        var cardCount = new HashMap<Integer, Integer>();
        // insert original cards into count
        for (var card = 1; card < cardNum; card++) {
            cardCount.put(card, 1);
        }
        // calc copies
        for (var card = 1; card < cardNum; card++) {
            var numCardCopies = cardCount.get(card);
            var numMatches = cardToMatches.get(card);
            for (var i = 1; i <= numMatches; ++i) {
                cardCount.put(card + i, cardCount.get(card + i) + numCardCopies);
            }
        }
        return cardCount.values().stream().reduce(0, Integer::sum);
    }
}
