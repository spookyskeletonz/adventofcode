package day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Day06 {

  private static final String ROOT = "COM";
  // Part two
  private static final String BEGIN = "YOU";
  private static final String GOAL = "SAN";
  private static final String DEPTH_MARKER = "DEPTH";

  private Map<String, List<String>> graph;
  // set of visited nodes for part two
  private Set<String> visited;

  int solvePartOne(File file) throws FileNotFoundException {
    graph = new HashMap<>();
    Scanner scanner = new Scanner(file);
    // construct directed graph of orbits
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String orbitee = line.split("\\)")[0];
      String orbiter = line.split("\\)")[1];
      if (graph.get(orbitee) != null) {
        graph.get(orbitee).add(orbiter);
      } else {
        List<String> orbiters = new ArrayList<>();
        orbiters.add(orbiter);
        graph.put(orbitee, orbiters);
      }
    }
    // choose arbitrary point and do a depth first traversal, returning the total number of orbits
    return getOrbitCount(0, graph.get(ROOT));
  }

  private int getOrbitCount(int prevCount, List<String> orbiters) {
    if (orbiters == null) {
      return prevCount;
    } else {
      int count = prevCount;
      for (String orbiter : orbiters) {
        count += getOrbitCount(prevCount + 1, graph.get(orbiter));
      }
      return count;
    }
  }

  int solvePartTwo(File file) throws FileNotFoundException {
    graph = new HashMap<>();
    visited = new HashSet<>();
    Scanner scanner = new Scanner(file);
    // construct undirected graph of orbits
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String orbitee = line.split("\\)")[0];
      String orbiter = line.split("\\)")[1];
      // add orbiter to orbitee
      if (graph.get(orbitee) != null) {
        graph.get(orbitee).add(orbiter);
      } else {
        List<String> connections = new ArrayList<>();
        connections.add(orbiter);
        graph.put(orbitee, connections);
      }
      // add orbitee to orbiter
      if (graph.get(orbiter) != null) {
        graph.get(orbiter).add(orbitee);
      } else {
        List<String> connections = new ArrayList<>();
        connections.add(orbitee);
        graph.put(orbiter, connections);
      }
    }
    // perform a BFS between the two nodes
    Queue<String> toVisit = new LinkedList<>();
    int count = 0;
    toVisit.addAll(graph.get(BEGIN));
    // we use DEPTH_MARKER to count depth
    toVisit.add(DEPTH_MARKER);
    visited.add(BEGIN);
    while (!toVisit.isEmpty()) {
      String curr = toVisit.poll();
      visited.add(curr);
      if (curr.equals(GOAL)) break;
      if (curr.equals(DEPTH_MARKER)) {
        /* if we see a depth marker, it means we have hit all nodes for that depth, and so added all the next depths
         * nodes. We should add the next depth marker */
        count++;
        toVisit.add(DEPTH_MARKER);
        continue;
      }
      for (String next : graph.get(curr)) {
        if (!visited.contains(next)) {
          toVisit.add(next);
        }
      }
    }

    // forget about last santa node
    return count - 1;
  }
}
