package twentytwentythree.day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day08 {
    private class Node {
        public final String label;
        public Node left;
        public Node right;

        public Node(String label) {
            this.label = label;
        }
    }

    private Map<String, Node> nodes = new HashMap<>();

    public int solvePartOne(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var instructions = scanner.nextLine().toCharArray();
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            parseLineToMap(line);
        }

        var curr = nodes.get("AAA");
        return getStepCountFromStart(curr, instructions);
    }

    public long solvePartTwo(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var instructions = scanner.nextLine().toCharArray();
        scanner.nextLine();

        // Load starting nodes while parsing
        var currs = new ArrayList<Node>();
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var node = parseLineToMap(line);
            if (node.label.endsWith("A")) currs.add(node);
        }

        var stepCounts = new long[currs.size()];
        for (var i = 0; i < currs.size(); ++i) {
            stepCounts[i] = getStepCountFromStart(currs.get(i), instructions);
        }
        return lcm(stepCounts);
    }

    private int getStepCountFromStart(Node n, char[] instructions) {
        var instIndex = 0;
        var steps = 0;
        while (!n.label.endsWith("Z")) {
            n = switch (instructions[instIndex]) {
                case 'L' -> n.left;
                case 'R' -> n.right;
                default -> throw new RuntimeException();
            };
            steps++;
            instIndex = (instIndex + 1) % instructions.length;
        }
        return steps;
    }

    private Node parseLineToMap(String line) {
        var label = line.split(" = ")[0];
        var leftLabel = line.split(" = ")[1].split(", ")[0].replaceAll("\\(", "");
        var rightLabel = line.split(" = ")[1].split(", ")[1].replaceAll("\\)", "");

        var node = nodes.computeIfAbsent(label, Node::new);
        var leftNode = nodes.computeIfAbsent(leftLabel, Node::new);
        var rightNode = nodes.computeIfAbsent(rightLabel, Node::new);
        node.left = leftNode;
        node.right = rightNode;
        return node;
    }

    private static long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }

    private static long lcm(long[] input) {
        long result = input[0];
        for (int i = 1; i < input.length; i++) result = lcm(result, input[i]);
        return result;
    }
}
