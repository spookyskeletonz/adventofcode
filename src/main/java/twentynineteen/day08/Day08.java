package twentynineteen.day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Day08 {

  private static final int TRANSPARENT = 2;

  // layered pixels
  private List<Layer> layeredImage;
  private int width;
  private int height;

  static int solvePartOne(int width, int height, File file) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(file).useDelimiter("");
    int layerSize = width * height;
    int minNumZeroes = Integer.MAX_VALUE;
    int index = 0;
    int result = 0;
    int numZeroes = 0;
    int numOnes = 0;
    int numTwos = 0;
    while (fileScanner.hasNextInt()) {
      if (index == layerSize) {
        // we have processed a layer, see if we should update result
        if (numZeroes < minNumZeroes) {
          minNumZeroes = numZeroes;
          result = numOnes * numTwos;
        }
        // reset counters
        numZeroes = 0;
        numOnes = 0;
        numTwos = 0;
        index = 0;
      }
      int pix = fileScanner.nextInt();
      if (pix == 0) numZeroes++;
      else if (pix == 1) numOnes++;
      else if (pix == 2) numTwos++;
      index++;
    }
    // update result for last layer
    if (numZeroes < minNumZeroes) {
      result = numOnes * numTwos;
    }
    return result;
  }

  public int[][] solvePartTwo(int width, int height, File file) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(file).useDelimiter("");
    this.layeredImage = new ArrayList<>();
    this.width = width;
    this.height = height;
    while (fileScanner.hasNextInt()) {
      Layer layer = buildLayer(fileScanner);
      layeredImage.add(layer);
    }
    return flattenImage();
  }

  private Layer buildLayer(Scanner scanner) {
    int limit = width * height;
    int currentWidth = 0;
    int currentHeight = 0;
    Layer layer = new Layer();
    layer.pixels = new int[height][width];
    for (int i = 0; i < limit; ++i) {
      if (currentWidth == width) {
        currentHeight++;
        currentWidth = 0;
      }
      layer.pixels[currentHeight][currentWidth] = scanner.nextInt();
      currentWidth++;
    }
    return layer;
  }

  private int[][] flattenImage() {
    int[][] image = new int[height][width];
    int limit = width * height;
    int currentWidth = 0;
    int currentHeight = 0;
    for (int i = 0; i < limit; ++i) {
      if (currentWidth == width) {
        currentHeight++;
        currentWidth = 0;
      }
      // assumes layers have been built
      image[currentHeight][currentWidth] = choosePixelFromLayers(currentWidth, currentHeight);
      currentWidth++;
    }
    return image;
  }

  private int choosePixelFromLayers(int x, int y) {
    for (Layer layer : layeredImage) {
      int pixel = layer.pixels[y][x];
      // if transparent, continue to next layer
      if (pixel == TRANSPARENT) continue;
      // otherwise return first seen pixel val
      return pixel;
    }
    // if no non transparent found, return transparent
    return TRANSPARENT;
  }

  class Layer {
    // pixels [y][x]
    int[][] pixels;
  }

  static void printImage(int[][] image) {
    for (int[] row : image) {
      for (int pixel : row) {
        if (pixel == 0) System.out.print("■");
        else System.out.print(" ");
      }
      System.out.println();
    }
  }
}
