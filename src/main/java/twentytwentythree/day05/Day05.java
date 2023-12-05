package twentytwentythree.day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day05 {
    private record Range(long sourceStart, long destStart, long range) {
    }

    public static long solvePartOne(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var firstLine = scanner.nextLine();
        var seedsS = firstLine.split(":")[1].split("\\W+");
        var seeds = Arrays.stream(seedsS).filter(s -> !s.isEmpty()).map(Long::parseLong).toList();
        scanner.nextLine();
        scanner.nextLine();
        var seedToSoilRanges = getRanges(scanner);
        scanner.nextLine();
        var soilToFertRanges = getRanges(scanner);
        scanner.nextLine();
        var fertToWaterRanges = getRanges(scanner);
        scanner.nextLine();
        var waterToLightRanges = getRanges(scanner);
        scanner.nextLine();
        var lightToTempRanges = getRanges(scanner);
        scanner.nextLine();
        var tempToHumRanges = getRanges(scanner);
        scanner.nextLine();
        var humToLocRanges = getRanges(scanner);

        var minLoc = Long.MAX_VALUE;
        for (var seed : seeds) {
            var soil = getMapping(seed, seedToSoilRanges);
            var fert = getMapping(soil, soilToFertRanges);
            var water = getMapping(fert, fertToWaterRanges);
            var light = getMapping(water, waterToLightRanges);
            var temp = getMapping(light, lightToTempRanges);
            var hum = getMapping(temp, tempToHumRanges);
            var loc = getMapping(hum, humToLocRanges);
            if (loc < minLoc) minLoc = loc;
        }

        return minLoc;
    }

    private record SeedRange(long start, long range){}

    public static long solvePartTwo(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var firstLine = scanner.nextLine();
        var seedsS = firstLine.split(":")[1].split("\\W+");
        var seedNums = Arrays.stream(seedsS).filter(s -> !s.isEmpty()).map(Long::parseLong).toList();
        var seedRanges = new ArrayList<SeedRange>();
        for (var i = 0; i < seedNums.size(); ++i) {
            var start = seedNums.get(i);
            i++;
            var range = seedNums.get(i);
            seedRanges.add(new SeedRange(start, range));
        }
        scanner.nextLine();
        scanner.nextLine();
        var seedToSoilRanges = getRanges(scanner);
        scanner.nextLine();
        var soilToFertRanges = getRanges(scanner);
        scanner.nextLine();
        var fertToWaterRanges = getRanges(scanner);
        scanner.nextLine();
        var waterToLightRanges = getRanges(scanner);
        scanner.nextLine();
        var lightToTempRanges = getRanges(scanner);
        scanner.nextLine();
        var tempToHumRanges = getRanges(scanner);
        scanner.nextLine();
        var humToLocRanges = getRanges(scanner);

        var minLoc = Long.MAX_VALUE;

        // NAIVE solution, running on every seed. Could be possible to optimise
        for (var seedRange : seedRanges) {
            for (var seed = seedRange.start; seed < seedRange.start + seedRange.range; ++seed) {
                var soil = getMapping(seed, seedToSoilRanges);
                var fert = getMapping(soil, soilToFertRanges);
                var water = getMapping(fert, fertToWaterRanges);
                var light = getMapping(water, waterToLightRanges);
                var temp = getMapping(light, lightToTempRanges);
                var hum = getMapping(temp, tempToHumRanges);
                var loc = getMapping(hum, humToLocRanges);
                if (loc < minLoc) minLoc = loc;
            }
            System.out.println("Finished a seed range");
        }

        return minLoc;
    }

    private static long getMapping(long source, List<Range> ranges) {
        var dest = source;
        for (var range : ranges) {
            if (source >= range.sourceStart && source <= range.sourceStart + range.range - 1) {
                dest = range.destStart + (source - range.sourceStart);
                break;
            }
        }
        return dest;
    }

    private static List<Range> getRanges(Scanner scanner) {
        var ranges = new ArrayList<Range>();
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            if (line.isEmpty()) break;
            var vals = Arrays.stream(line.split("\\W+")).filter(s -> !s.isEmpty()).map(Long::parseLong).toList();
            ranges.add(new Range(vals.get(1), vals.get(0), vals.get(2)));
        }
        return ranges;
    }
}
