package twentynineteen.day01;

import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

public class Day01Test {
  private final Day01 day = new Day01();

    @Test
    public void testPartOneBasic() throws Exception {
      File file = new File(getClass().getClassLoader().getResource("twentynineteen/day01/testOne.txt").getFile());
      assertEquals(2, day.solveFileForPartOne(file));
    }

    @Test
    public void testPartOneRoundingAndSum() throws Exception {
      File file = new File(getClass().getClassLoader().getResource("twentynineteen/day01/testTwo.txt").getFile());
      assertEquals(656, day.solveFileForPartOne(file));
    }

    @Test
    public void testPartOneLargeInput() throws Exception {
      File file = new File(getClass().getClassLoader().getResource("twentynineteen/day01/input.txt").getFile());
      assertEquals(3297866, day.solveFileForPartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
      File file = new File(getClass().getClassLoader().getResource("twentynineteen/day01/testPartTwoBasic.txt").getFile());
      assertEquals(966, day.solveFileForPartTwo(file));
    }

    @Test
    public void testPartTwoAddition() throws Exception {
      File file = new File(getClass().getClassLoader().getResource("twentynineteen/day01/testPartTwoAddition.txt").getFile());
      assertEquals(51312, day.solveFileForPartTwo(file));
    }

    @Test
    public void testPartTwoLargeInput() throws Exception {
      File file = new File(getClass().getClassLoader().getResource("twentynineteen/day01/input.txt").getFile());
      assertEquals(4943923, day.solveFileForPartTwo(file));
    }
}
