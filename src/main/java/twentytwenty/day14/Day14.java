package twentytwenty.day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day14 {

  public String leftPad(String binary) {
    StringBuilder binaryBuilder = new StringBuilder(binary);
    while(binaryBuilder.length() != 36) {
      binaryBuilder.insert(0, "0");
    }
    return binaryBuilder.toString();
  }

  public long solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var addressSpace = new HashMap<Long, Long>();
    String mask = "";

    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      if (line.startsWith("mask")) {
        mask = line.split(" = ")[1];
        continue;
      }
      var val = Long.parseLong(line.split(" = ")[1]);
      var address = Long.parseLong(line.substring(line.indexOf('[') + 1, line.indexOf(']')));
      var valBinaryString = leftPad(Long.toBinaryString(val));
      StringBuilder maskedBinaryBuilder = new StringBuilder();
      // For each bit in value string, check against bit in mask
      for (var i = 0; i < valBinaryString.length(); ++i) {
        var valBit = valBinaryString.charAt(i);
        var maskBit = mask.charAt(i);
        if (maskBit == 'X') {
          maskedBinaryBuilder.append(valBit);
        } else {
          maskedBinaryBuilder.append(maskBit);
        }
      }
      // convert result binary string to long
      var resultVal = Long.parseLong(maskedBinaryBuilder.toString(), 2);
      // save to address
      addressSpace.put(address, resultVal);
    }

    // Sum address values
    var sum = 0L;
    for (var i : addressSpace.entrySet()) {
      sum += i.getValue();
    }
    return sum;
  }

  public long solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var addressSpace = new HashMap<Long, Long>();
    String mask = "";

    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      if (line.startsWith("mask")) {
        mask = line.split(" = ")[1];
        continue;
      }
      var val = Long.parseLong(line.split(" = ")[1]);
      var address = Long.parseLong(line.substring(line.indexOf('[') + 1, line.indexOf(']')));
      var addressBinaryString = leftPad(Long.toBinaryString(address));
      StringBuilder maskedBinaryBuilder = new StringBuilder();
      // For each bit in address string, check against bit in mask
      for (var i = 0; i < addressBinaryString.length(); ++i) {
        var addressBit = addressBinaryString.charAt(i);
        var maskBit = mask.charAt(i);
        if (maskBit == 'X') {
          maskedBinaryBuilder.append('X');
        } else if (maskBit == '0') {
          maskedBinaryBuilder.append(addressBit);
        } else {
          maskedBinaryBuilder.append('1');
        }
      }
      // generate all possible perms given masked bits
      var resultAddresses = addressPerms(maskedBinaryBuilder.toString());
      // save to address
      for (var resultAdd : resultAddresses) {
        addressSpace.put(resultAdd, val);
      }
    }

    // Sum address values
    var sum = 0L;
    for (var i : addressSpace.entrySet()) {
      sum += i.getValue();
    }
    return sum;
  }

  public List<Long> addressPerms(String address) {
    List<String> possibleAddresses = possiblePerms(address);
    // Convert binary strings to long values
    return possibleAddresses.stream()
        .map(s -> Long.parseLong(s, 2))
        .collect(Collectors.toList());
  }

  // recursive method to generate all permutations of binary string
  public List<String> possiblePerms(String suffix) {
    if (suffix.isEmpty()) return List.of("");
    var list = new ArrayList<String>();
    var suffixPerms = possiblePerms(suffix.substring(1));
    if (suffix.startsWith("X")) {
      // add all possible permutations, replace X with either 1 or 0
      list.addAll(suffixPerms.stream()
          .map(s -> '1' + s)
          .collect(Collectors.toList()));
      list.addAll(suffixPerms.stream()
          .map(s -> '0' + s)
          .collect(Collectors.toList()));
    } else {
      // add possible permutations for suffix
      list.addAll(suffixPerms.stream()
          .map(s -> suffix.charAt(0) + s)
          .collect(Collectors.toList()));
    }
    return list;
  }
}
