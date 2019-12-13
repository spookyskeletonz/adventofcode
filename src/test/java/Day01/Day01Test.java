package Day01;

import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by arunavsarkar on 13/12/19.
 */
public class Day01Test {
  private final Day01 day = new Day01();

    @Test
    public void testOne() throws Exception {
      File file = new File(getClass().getClassLoader().getResource("Day01/testOne.txt").getFile());
      assertEquals(2, day.solveFile(file));
    }

    @Test
    public void testRoundingAndSum() throws Exception {
      File file = new File(getClass().getClassLoader().getResource("Day01/testTwo.txt").getFile());
      assertEquals(656, day.solveFile(file));
    }

    @Test
    public void testLargeInput() throws Exception {
      File file = new File(getClass().getClassLoader().getResource("Day01/input.txt").getFile());
      assertEquals(3297866, day.solveFile(file));
    }
}
