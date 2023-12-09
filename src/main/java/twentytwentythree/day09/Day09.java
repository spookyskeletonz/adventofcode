package twentytwentythree.day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day09 {
    private static class Node {
        Node parent;
        Node left;
        Node right;

        Long value;

        public Node(Long value) {
            this.value = value;
        }
    }

    public static long solvePartOne(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var total = 0L;
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var head = createTree(line);

            // traverse right side of tree, get sum
            var sum = 0L;
            while (head != null) {
                sum += head.value;
                head = head.right;
            }
            total += sum;
        }
        return total;
    }

    public static long solvePartTwo(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var total = 0L;
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var head = createTree(line);

            // traverse left side of tree, get diff
            var diff = 0L;
            while (head != null) {
                diff = head.value - diff;
                head = head.left;
            }
            total += diff;
        }
        return total;
    }

    private static Node createTree(String line) {
        var numsS = line.split(" ");
        var nums = Arrays.stream(numsS).filter(s -> !s.isEmpty()).map(Long::parseLong).toList();
        // construct tree
        Queue<Node> row = new LinkedList<>();
        for (var num : nums.reversed()) {
            var node = new Node(num);
            row.add(node);
        }
        Queue<Node> nextRow = new LinkedList<>();
        Node head = null;
        while (!row.isEmpty()) {
            var right = row.poll();
            if (right.value == 0 && row.isEmpty() && nextRow.isEmpty()) {
                // we have reached head of tree
                head = right;
                break;
            }
            var left = row.peek();
            if (left == null) {
                row = nextRow;
                nextRow = new LinkedList<>();
                continue;
            }
            var newNode = new Node(right.value - left.value);
            newNode.left = left;
            newNode.right = right;
            left.parent = newNode;
            right.parent = newNode;
            nextRow.add(newNode);
        }
        return head;
    }
}
