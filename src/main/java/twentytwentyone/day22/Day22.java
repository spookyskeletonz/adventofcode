package twentytwentyone.day22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day22 {

  public static class Cube {
    int x, y, z;

    public Cube(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Cube cube = (Cube) o;
      return x == cube.x && y == cube.y && z == cube.z;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y, z);
    }
  }

  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var onCubes = new HashSet<Cube>();
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var on = line.split(" ")[0].equals("on");
      var ranges = line.split(" ")[1];
      var xMin = Integer.parseInt(ranges.split(",y=")[0].substring(2).split("\\.\\.")[0]);
      var xMax = Integer.parseInt(ranges.split(",y=")[0].substring(2).split("\\.\\.")[1]);
      var yMin = Integer.parseInt(ranges.split(",y=")[1].split(",z=")[0].split("\\.\\.")[0]);
      var yMax = Integer.parseInt(ranges.split(",y=")[1].split(",z=")[0].split("\\.\\.")[1]);
      var zMin = Integer.parseInt(ranges.split(",z=")[1].split("\\.\\.")[0]);
      var zMax = Integer.parseInt(ranges.split(",z=")[1].split("\\.\\.")[1]);
      if (xMin < -50 ||
          xMax > 50 ||
          yMin < -50 ||
          yMax > 50 ||
          zMin < -50 ||
          zMax > 50) {
        continue;
      }
      for (var x = xMin; x <= xMax; ++x) {
        for (var y = yMin; y <= yMax; ++y) {
          for (var z = zMin; z <= zMax; ++z) {
            var cube = new Cube(x, y, z);
            if (on) {
              onCubes.add(cube);
            } else {
              onCubes.remove(cube);
            }
          }
        }
      }
    }

    return onCubes.size();
  }

  public static class Cuboid {
    int minX, maxX, minY, maxY, minZ, maxZ, value;

    public Cuboid(int minX, int maxX, int minY, int maxY, int minZ, int maxZ, int value) {
      this.minX = minX;
      this.maxX = maxX;
      this.minY = minY;
      this.maxY = maxY;
      this.minZ = minZ;
      this.maxZ = maxZ;
      this.value = value;
    }
  }

  public long solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var cuboids = new ArrayList<Cuboid>();
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var ranges = line.split(" ")[1];
      var xMin = Integer.parseInt(ranges.split(",y=")[0].substring(2).split("\\.\\.")[0]);
      var xMax = Integer.parseInt(ranges.split(",y=")[0].substring(2).split("\\.\\.")[1]);
      var yMin = Integer.parseInt(ranges.split(",y=")[1].split(",z=")[0].split("\\.\\.")[0]);
      var yMax = Integer.parseInt(ranges.split(",y=")[1].split(",z=")[0].split("\\.\\.")[1]);
      var zMin = Integer.parseInt(ranges.split(",z=")[1].split("\\.\\.")[0]);
      var zMax = Integer.parseInt(ranges.split(",z=")[1].split("\\.\\.")[1]);
      var on = line.split(" ")[0].equals("on");
      // create cuboids that subtracts values for intersects with other cuboids
      cuboids = pushIntersectingCuboidsToNegate(cuboids, xMin, xMax, yMin, yMax, zMin, zMax);
      // Add this new cuboid to list if on (don't need to worry about adding off cuboids)
      // Off cuboids only matter for intersections (handled above).
      // On cuboids need to be set to 1. Therefore, the intersect with another on cuboid is negated and then added,
      // totalling 1
      if (on) {
        var cuboid = new Cuboid(xMin, xMax, yMin, yMax, zMin, zMax, 1);
        cuboids.add(cuboid);
      }
    }
    return cuboids.stream()
        .mapToLong(c -> (long) c.value * (c.maxX - c.minX + 1) * (c.maxY - c.minY + 1) * (c.maxZ - c.minZ + 1))
        .sum();
  }

  public ArrayList<Cuboid> pushIntersectingCuboidsToNegate(ArrayList<Cuboid> cuboids, int minX, int maxX, int minY, int maxY,
                                              int minZ, int maxZ) {
    var newCuboids = new ArrayList<>(cuboids);
    for (var cuboid : cuboids) {
      var overlapMinX = Math.max(minX, cuboid.minX);
      var overlapMaxX = Math.min(maxX, cuboid.maxX);
      if (overlapMaxX < overlapMinX) continue;
      var overlapMinY = Math.max(minY, cuboid.minY);
      var overlapMaxY = Math.min(maxY, cuboid.maxY);
      if (overlapMaxY < overlapMinY) continue;
      var overlapMinZ = Math.max(minZ, cuboid.minZ);
      var overlapMaxZ = Math.min(maxZ, cuboid.maxZ);
      if (overlapMaxZ < overlapMinZ) continue;
      // If overlapping cuboid found, push onto cuboids with negating value
      // - If value of cuboid was 1, then add negating value of -1
      // - If value of cuboid was -1, it has already been negated by a previous call
      //   (and there must be another matching cuboid with value 1 that is being negated for this call)
      //   THUS: add a cuboid with value 1 to avoid double negating
      newCuboids.add(new Cuboid(overlapMinX, overlapMaxX, overlapMinY, overlapMaxY, overlapMinZ, overlapMaxZ,
          cuboid.value == 1 ? -1 : 1));
    }
    return newCuboids;
  }
}
