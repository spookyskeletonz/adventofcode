package twentytwenty.day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day10 {

  // cache for dp: jolts : (numCombos of adapters from that adapter)
  private Map<Integer, Long> cache;


  public int solvePartOne(File file) throws FileNotFoundException {
    var adapters = new HashSet<Integer>();
    var scanner = new Scanner(file);
    var targetJolts = 3;
    while (scanner.hasNextInt()) {
      var adapter = scanner.nextInt();
      adapters.add(adapter);
      if (adapter + 3 > targetJolts) targetJolts = adapter + 3;
    }
    return findAdapterCombo(adapters, 0, 0, 0, 0, targetJolts);
  }

  public Integer findAdapterCombo(Set<Integer> adapters, int numOneDiff, int numTwoDiff, int numThreeDiff, int jolts, int targetJolts) {
    if (jolts == targetJolts - 3) {
      // last connection to our adapter is always a difference of 3
      return numOneDiff * (numThreeDiff + 1);
    }
    if (adapters.isEmpty() || jolts > targetJolts - 3) {
      return null;
    }
    var adapterOne = jolts + 1;
    var adapterTwo = jolts + 2;
    var adapterThree = jolts + 3;
    if (adapters.contains(adapterOne)) {
      var newAdapters = new HashSet<>(adapters);
      newAdapters.remove(adapterOne);
      var result = findAdapterCombo(newAdapters, numOneDiff + 1, numTwoDiff, numThreeDiff, adapterOne, targetJolts);
      if (result != null) return result;
    }
    if (adapters.contains(adapterTwo)) {
      var newAdapters = new HashSet<>(adapters);
      newAdapters.remove(adapterTwo);
      var result = findAdapterCombo(newAdapters, numOneDiff, numTwoDiff + 1, numThreeDiff, adapterTwo, targetJolts);
      if (result != null) return result;
    }
    if (adapters.contains(adapterThree)) {
      var newAdapters = new HashSet<>(adapters);
      newAdapters.remove(adapterThree);
      var result = findAdapterCombo(newAdapters, numOneDiff, numTwoDiff, numThreeDiff + 1, adapterThree, targetJolts);
      if (result != null) return result;
    }
    return null;
  }

  public long solvePartTwo(File file) throws FileNotFoundException {
    cache = new HashMap<>();
    var adapters = new HashSet<Integer>();
    var scanner = new Scanner(file);
    var targetJolts = 3;
    while (scanner.hasNextInt()) {
      var adapter = scanner.nextInt();
      adapters.add(adapter);
      if (adapter + 3 > targetJolts) targetJolts = adapter + 3;
    }
    return countAdapterCombos(adapters, 0, targetJolts);
  }

  public long countAdapterCombos(Set<Integer> adapters, int jolts, int targetJolts) {
    if (cache.containsKey(jolts)) {
      return cache.get(jolts);
    }
    if (jolts == targetJolts - 3) {
      // found a combo, increment state
      cache.put(jolts, 1L);
      return 1;
    }
    if (adapters.isEmpty() || jolts > targetJolts - 3) {
      cache.put(jolts, 0L);
      return 0;
    }
    var adapterOne = jolts + 1;
    var adapterTwo = jolts + 2;
    var adapterThree = jolts + 3;
    var count = 0L;
    if (adapters.contains(adapterOne)) {
      var newAdapters = new HashSet<>(adapters);
      newAdapters.remove(adapterOne);
      count += countAdapterCombos(newAdapters, adapterOne, targetJolts);
    }
    if (adapters.contains(adapterTwo)) {
      var newAdapters = new HashSet<>(adapters);
      newAdapters.remove(adapterTwo);
      count += countAdapterCombos(newAdapters, adapterTwo, targetJolts);
    }
    if (adapters.contains(adapterThree)) {
      var newAdapters = new HashSet<>(adapters);
      newAdapters.remove(adapterThree);
      count += countAdapterCombos(newAdapters, adapterThree, targetJolts);
    }
    cache.put(jolts, count);
    return count;
  }
}
