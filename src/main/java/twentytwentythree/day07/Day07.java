package twentytwentythree.day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day07 {
    private static class Hand {
        @FunctionalInterface
        interface Scorer {
            int[] score(char[] cards);
        }

        protected char[] cards;
        protected Integer bid;
        protected int[] score;

        public Hand(char[] cards, Integer bid, Scorer scorer) {
            this.cards = cards;
            this.bid = bid;
            this.score = scorer.score(cards);
        }
    }

    public static int solvePartOne(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        return solve(Day07::handScorerPartOne, scanner);
    }

    public static int solvePartTwo(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        return solve(Day07::handScorerPartTwo, scanner);
    }

    private static int[] handScorerPartOne(char[] cards) {
        var score = new int[6];
        // first point of score is based on matches
        var matchMap = new HashMap<Character, Integer>();
        for (var card : cards) {
            if (matchMap.containsKey(card)) matchMap.put(card, matchMap.get(card) + 1);
            else matchMap.put(card, 1);
        }
        var matchCounts = matchMap.values();
        if (matchCounts.size() == 1) {
            // 5 of a kind
            score[0] = 6;
        } else if (matchCounts.contains(4)) {
            // 4 of a kind
            score[0] = 5;
        } else if (matchCounts.contains(3) && matchCounts.contains(2)) {
            // full house
            score[0] = 4;
        } else if (matchCounts.contains(3)) {
            // 3 of a kind
            score[0] = 3;
        } else if (matchCounts.contains(2) && matchCounts.size() == 3) {
            // 2 pair
            score[0] = 2;
        } else if (matchCounts.contains(2)) {
            // 1 pair
            score[0] = 1;
        } else {
            score[0] = 0;
        }

        // other points of score are based on cards from left to right
        for (var i = 0; i < cards.length; ++i) {
            score[i + 1] = switch (cards[i]) {
                case 'A' -> 12;
                case 'K' -> 11;
                case 'Q' -> 10;
                case 'J' -> 9;
                case 'T' -> 8;
                case '9' -> 7;
                case '8' -> 6;
                case '7' -> 5;
                case '6' -> 4;
                case '5' -> 3;
                case '4' -> 2;
                case '3' -> 1;
                case '2' -> 0;
                default -> -1;
            };
        }
        return score;
    }

    private static int[] handScorerPartTwo(char[] cards) {
        var score = new int[6];
        // first point of score is based on matches
        var matchMap = new HashMap<Character, Integer>();
        // jokers are added to best matching count
        var jokerCount = 0;
        var bestMatchingCard = '_';
        for (var card : cards) {
            if (card == 'J') {
                jokerCount++;
                continue;
            }
            if (matchMap.containsKey(card)) matchMap.put(card, matchMap.get(card) + 1);
            else matchMap.put(card, 1);
            if (bestMatchingCard == '_' || matchMap.get(card) > matchMap.get(bestMatchingCard)) {
                bestMatchingCard = card;
            }
        }
        if (matchMap.isEmpty()) {
            // all jokers
            score[0] = 6;
        } else {
            matchMap.put(bestMatchingCard, matchMap.get(bestMatchingCard) + jokerCount);
            var matchCounts = matchMap.values();
            if (matchCounts.size() == 1) {
                // 5 of a kind
                score[0] = 6;
            } else if (matchCounts.contains(4)) {
                // 4 of a kind
                score[0] = 5;
            } else if (matchCounts.contains(3) && matchCounts.contains(2)) {
                // full house
                score[0] = 4;
            } else if (matchCounts.contains(3)) {
                // 3 of a kind
                score[0] = 3;
            } else if (matchCounts.contains(2) && matchCounts.size() == 3) {
                // 2 pair
                score[0] = 2;
            } else if (matchCounts.contains(2)) {
                // 1 pair
                score[0] = 1;
            } else {
                score[0] = 0;
            }
        }

        // other points of score are based on cards from left to right
        for (var i = 0; i < cards.length; ++i) {
            score[i + 1] = switch (cards[i]) {
                case 'A' -> 12;
                case 'K' -> 11;
                case 'Q' -> 10;
                case 'T' -> 9;
                case '9' -> 8;
                case '8' -> 7;
                case '7' -> 6;
                case '6' -> 5;
                case '5' -> 4;
                case '4' -> 3;
                case '3' -> 2;
                case '2' -> 1;
                case 'J' -> 0;
                default -> -1;
            };
        }
        return score;
    }

    private static int solve(Hand.Scorer scorer, Scanner scanner) {
        var rankedHands = new PriorityQueue<>((Hand h1, Hand h2) -> {
            for (var i = 0; i < 6; ++i) {
                if (h1.score[i] != h2.score[i]) {
                    return h1.score[i] - h2.score[i];
                }
            }
            return 0;
        });
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var cards = line.split(" ")[0].trim().toCharArray();
            var bid = Integer.parseInt(line.split(" ")[1].trim());
            var hand = new Hand(cards, bid, scorer);
            rankedHands.add(hand);
        }
        var total = 0;
        var numHands = rankedHands.size();
        for (var i = 0; i < numHands; ++i) {
            var hand = rankedHands.poll();
            total += hand.bid * (i + 1);
        }
        return total;
    }
}
