package twentytwenty.day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day07 {
  // Consider bags as nodes and rules as edges in a graph
  class Bag {
    String color;
    List<Rule> rules;

    Bag(String colour) {
      this.color = colour;
      this.rules = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Bag bag = (Bag) o;
      return color.equals(bag.color);
    }

    @Override
    public int hashCode() {
      return Objects.hash(color);
    }
  }

  class Rule {
    Bag outerBag;
    int numBags;
    Bag innerBag;

    Rule(Bag outerBag, int numBags, Bag innerBag) {
      this.outerBag = outerBag;
      this.numBags = numBags;
      this.innerBag = innerBag;
    }
  }

  public int solvePartOne(File file) throws FileNotFoundException {
    // For part one we will store a directional graph, where edges are rules,
    // and each bag has the rules linking a containing bag (inner -> outer)
    var scanner = new Scanner(file);
    Map<String, Bag> bags = new HashMap<>();
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var outerBag = getOrCreateBag(bags, line);
      var innerBagStrings = line.split(" contain ")[1].split(", ");
      if (innerBagStrings[0].startsWith("no other bags.")) continue;
      for (var innerBagString : innerBagStrings) {
        var innerBagNum = Integer.parseInt(innerBagString.split(" ")[0]);
        var innerBag = getOrCreateBag(bags, innerBagString.split(" ", 2)[1]);
        // add inner -> outer edge
        innerBag.rules.add(new Rule(outerBag, innerBagNum, innerBag));
      }
    }
    // now BF traverse graph and find number of unique bag types that can eventually contain ours
    Set<Bag> visited = new HashSet<>();
    Bag current = bags.get("shiny gold");
    Queue<Bag> toVisit = new LinkedList<>();
    current.rules.forEach(r -> toVisit.add(r.outerBag));
    while (!toVisit.isEmpty()) {
      current = toVisit.poll();
      visited.add(current);
      current.rules.forEach(r -> {
        if (!visited.contains(r.outerBag)) {
          toVisit.add(r.outerBag);
        }
      });
    }
    return visited.size();
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    // For part two we will store a directional graph, where edges are rules,
    // and each bag has the rules linking its contained bags (outer -> inner)
    var scanner = new Scanner(file);
    Map<String, Bag> bags = new HashMap<>();
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var outerBag = getOrCreateBag(bags, line);
      var innerBagStrings = line.split(" contain ")[1].split(", ");
      if (innerBagStrings[0].startsWith("no other bags.")) continue;
      for (var innerBagString : innerBagStrings) {
        var innerBagNum = Integer.parseInt(innerBagString.split(" ")[0]);
        var innerBag = getOrCreateBag(bags, innerBagString.split(" ", 2)[1]);
        // add outer -> inner edge
        outerBag.rules.add(new Rule(outerBag, innerBagNum, innerBag));
      }
    }
    // now DF traverse rules edges and find number of bags to be contained in a traversal starting at ours
    Bag current = bags.get("shiny gold");
    Stack<Rule> toVisit = new Stack<>();
    int bagCount = 0;
    toVisit.addAll(current.rules);
    while (!toVisit.isEmpty()) {
      var rule = toVisit.pop();
      current = rule.innerBag;
      bagCount += rule.numBags;
      var numCurrent = rule.numBags;
      // If there are multiple of current bag, add rules for each count of current bag
      for (var i = 0; i < numCurrent; ++i) {
        toVisit.addAll(current.rules);
      }
    }
    return bagCount;
  }

  private Bag getOrCreateBag(Map<String, Bag> bags, String bagString) {
    var color = bagString.split(" ")[0] + " " + bagString.split(" ")[1];
    Bag bag;
    // get previously created bag type or make new
    if (bags.containsKey(color)) {
      bag = bags.get(color);
    } else {
      bag = new Bag(color);
      bags.put(color, bag);
    }
    return bag;
  }

}
