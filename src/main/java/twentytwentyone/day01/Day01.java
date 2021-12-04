package twentytwentyone.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day01 {
  public int solvePartOne(File file) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(file);
    int increaseCount = 0;
    Integer previousValue = null;
    while (fileScanner.hasNextInt()) {
      int x = fileScanner.nextInt();
      if (previousValue != null) {
        if (x > previousValue) increaseCount++;
      }
      previousValue = x;
    }
    return increaseCount;
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(file);
    int increaseCount = 0;
    Integer previousWindowSum = null;
    SlidingWindow window = new SlidingWindow(3);
    while (fileScanner.hasNextInt()) {
      int x = fileScanner.nextInt();
      window.add(x);
      if (window.queue.size() == 3) {
        if (previousWindowSum != null) {
          if (window.sum > previousWindowSum) increaseCount++;
        }
        previousWindowSum = window.sum;
      }
    }
    return increaseCount;
  }

  public static class SlidingWindow {
    Queue<Integer> queue;
    int maxSize;
    Integer sum;

    public SlidingWindow(int size) {
      this.queue = new LinkedList<>();
      this.maxSize = size;
      this.sum = 0;
    }

    public void add(Integer item) {
      if (queue.size() == maxSize) {
        Integer removed = queue.remove();
        sum -= removed;
      }
      queue.add(item);
      sum += item;
    }
  }
}


