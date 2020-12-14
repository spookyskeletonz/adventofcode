package twentytwenty.day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day13 {

  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var timestamp = Integer.parseInt(scanner.nextLine());
    var busIds = Arrays.stream(scanner.nextLine().split(","))
        .filter(i -> !i.equals("x"))
        .map(Integer::parseInt)
        .collect(Collectors.toList());
    var earliestBus = 0;
    var timeToWait = Integer.MAX_VALUE;
    for (var id : busIds) {
      var timeToNextBus = id - (timestamp % id);
      if (timeToNextBus < timeToWait) {
        timeToWait = timeToNextBus;
        earliestBus = id;
      }
    }
    return timeToWait * earliestBus;
  }

  public long solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    scanner.nextLine();
    var numsString = scanner.nextLine().split(",");
    var count = Arrays.stream(numsString).filter(s -> !s.equals("x")).count();
    var nums = new long[(int) count];
    var rem = new long[(int) count];
    int index = 0;
    int position = 0;
    for (var numString : numsString) {
      if (numString.equals("x")) {
        position++;
        continue;
      }
      nums[index] = Long.parseLong(numString);
      rem[index] = position;
      position++;
      index++;
    }
    return computeMinX(rem, nums);
  }

  // CRT code taken from https://github.com/anujpahade/CRT
  private static long computeMinX(long[] rem, long[] num){
    long product = 1;
    for (int i=0; i<num.length; i++ ) {
      product *= num[i];
    }
    long partialProduct[] = new long[num.length];
    long inverse[] = new long[num.length];
    long sum = 0;
    for (int i=0; i<num.length; i++) {
      partialProduct[i] = product/num[i];//floor division
      inverse[i] = computeInverse(partialProduct[i],num[i]);
      sum += partialProduct[i] * inverse[i] * rem[i];
    }
    // I have no idea what I have done but it works
    return product - sum % product;
  }

  private static long computeInverse(long a, long b) {
    long m = b, t, q;
    long x = 0, y = 1;
    if (b == 1)
      return 0;
    // Apply extended Euclid Algorithm
    while (a > 1)
    {
      // q is quotient
      q = a / b;
      t = b;
      // m is remainder now, process
      // same as euclid's algo
      b = a % b;a = t;
      t = x;
      x = y - q * x;
      y = t;
    }
    // Make x1 positive
    if (y < 0)
      y += m;
    return y;
  }
}
