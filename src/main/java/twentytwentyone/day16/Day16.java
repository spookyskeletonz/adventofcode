package twentytwentyone.day16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day16 {

  public static class Packet {
    int version;

    public Packet(int version) {
      this.version = version;
    }

    public int sumVersionsInPacket() {
      return version;
    }

    public long getValue() {
      return 0L;
    }
  }

  public static class LiteralPacket extends Packet {
    long value;

    public LiteralPacket(int version, long value) {
      super(version);
      this.value = value;
    }

    @Override
    public long getValue() {
      return value;
    }
  }

  public static class OperatorPacket extends Packet {
    List<Packet> subPackets = new ArrayList<>();

    public OperatorPacket(int version) {
      super(version);
    }

    @Override
    public int sumVersionsInPacket() {
      return version + subPackets.stream().mapToInt(Packet::sumVersionsInPacket).sum();
    }
  }

  public static class SumPacket extends OperatorPacket {
    public SumPacket(int version) {
      super(version);
    }

    @Override
    public long getValue() {
      return super.subPackets.stream().mapToLong(Packet::getValue).sum();
    }
  }

  public static class ProductPacket extends OperatorPacket {

    public ProductPacket(int version) {
      super(version);
    }

    @Override
    public long getValue() {
      var value = 1l;
      for (var packet : super.subPackets) {
        value *= packet.getValue();
      }
      return value;
    }
  }

  public static class MinimumPacket extends OperatorPacket {

    public MinimumPacket(int version) {
      super(version);
    }

    @Override
    public long getValue() {
      return super.subPackets.stream().mapToLong(Packet::getValue).min().orElse(0L);
    }
  }

  public static class MaximumPacket extends OperatorPacket {

    public MaximumPacket(int version) {
      super(version);
    }

    @Override
    public long getValue() {
      return super.subPackets.stream().mapToLong(Packet::getValue).max().orElse(0L);
    }
  }

  public static class GreaterThanPacket extends OperatorPacket {

    public GreaterThanPacket(int version) {
      super(version);
    }

    @Override
    public long getValue() {
      return super.subPackets.get(0).getValue() > super.subPackets.get(1).getValue() ? 1L : 0L;
    }
  }

  public static class LessThanPacket extends OperatorPacket {

    public LessThanPacket(int version) {
      super(version);
    }

    @Override
    public long getValue() {
      return super.subPackets.get(0).getValue() < super.subPackets.get(1).getValue() ? 1L : 0L;
    }
  }

  public static class EqualToPacket extends OperatorPacket {

    public EqualToPacket(int version) {
      super(version);
    }

    @Override
    public long getValue() {
      return super.subPackets.get(0).getValue() == super.subPackets.get(1).getValue() ? 1L : 0L;
    }
  }

  public class BinaryStringIterator {
    public int pos = 0;
  }

  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var line = scanner.nextLine();
    var binaryString = hexToBin(line);
    var packet = readBinaryStringToPacket(binaryString, new BinaryStringIterator());
    return packet.sumVersionsInPacket();
  }

  public long solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var line = scanner.nextLine();
    var binaryString = hexToBin(line);
    var packet = readBinaryStringToPacket(binaryString, new BinaryStringIterator());
    return packet.getValue();
  }

  private Packet readBinaryStringToPacket(String binaryString, BinaryStringIterator iterator) {
    var version = Integer.parseInt(binaryString.substring(iterator.pos, iterator.pos + 3), 2);
    iterator.pos += 3;
    var packetType = Integer.parseInt(binaryString.substring(iterator.pos, iterator.pos + 3), 2);
    iterator.pos += 3;
    if (packetType == 4) {
      // Literal packet
      var hasNextGroup = true;
      var valueBinaryStringBuilder = new StringBuilder();
      while (hasNextGroup) {
        var groupString = binaryString.substring(iterator.pos, iterator.pos + 5);
        iterator.pos += 5;
        if (!(groupString.toCharArray()[0] == '1')) {
          hasNextGroup = false;
        }
        valueBinaryStringBuilder.append(groupString, 1, 5);
      }
      var value = Long.parseLong(valueBinaryStringBuilder.toString(), 2);
      return new LiteralPacket(version, value);
    } else {
      // Operator packet
      OperatorPacket packet;
      // Initialise as type based on type code
      switch (packetType) {
        case 0:
          packet = new SumPacket(version);
          break;
        case 1:
          packet = new ProductPacket(version);
          break;
        case 2:
          packet = new MinimumPacket(version);
          break;
        case 3:
          packet = new MaximumPacket(version);
          break;
        case 5:
          packet = new GreaterThanPacket(version);
          break;
        case 6:
          packet = new LessThanPacket(version);
          break;
        case 7:
          packet = new EqualToPacket(version);
          break;
        default:
          throw new AssertionError("Unknown packet type: " + packetType);
      }

      var lengthTypeId = binaryString.substring(iterator.pos, iterator.pos + 1);
      iterator.pos++;
      if (lengthTypeId.equals("0")) {
        // Total length mode
        var totalLength = Integer.parseInt(binaryString.substring(iterator.pos, iterator.pos + 15), 2);
        iterator.pos += 15;
        var hasNextSubPacket = true;
        var finalPos = iterator.pos + totalLength;
        while (hasNextSubPacket) {
          var subPacket = readBinaryStringToPacket(binaryString, iterator);
          packet.subPackets.add(subPacket);
          if (iterator.pos == finalPos) {
            hasNextSubPacket = false;
          }
        }
        return packet;
      } else {
        // Number of packets mode
        var numSubPackets = Integer.parseInt(binaryString.substring(iterator.pos, iterator.pos + 11), 2);
        iterator.pos += 11;
        for (var i = 0; i < numSubPackets; ++i) {
          var subPacket = readBinaryStringToPacket(binaryString, iterator);
          packet.subPackets.add(subPacket);
        }
        return packet;
      }
    }
  }

  private String hexToBin(String hex){
    hex = hex.replaceAll("0", "0000");
    hex = hex.replaceAll("1", "0001");
    hex = hex.replaceAll("2", "0010");
    hex = hex.replaceAll("3", "0011");
    hex = hex.replaceAll("4", "0100");
    hex = hex.replaceAll("5", "0101");
    hex = hex.replaceAll("6", "0110");
    hex = hex.replaceAll("7", "0111");
    hex = hex.replaceAll("8", "1000");
    hex = hex.replaceAll("9", "1001");
    hex = hex.replaceAll("A", "1010");
    hex = hex.replaceAll("B", "1011");
    hex = hex.replaceAll("C", "1100");
    hex = hex.replaceAll("D", "1101");
    hex = hex.replaceAll("E", "1110");
    hex = hex.replaceAll("F", "1111");
    return hex;
  }
}
