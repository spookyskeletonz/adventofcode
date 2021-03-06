package twentynineteen.day07;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class Day07Test {
  private Day07 day = new Day07();

  @Test
  public void testPartOneBasic() throws FileNotFoundException {
    File file = new File(getClass().getClassLoader().getResource("twentynineteen/day07/basic.txt").getFile());
    assertEquals(54321, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws FileNotFoundException {
    File file = new File(getClass().getClassLoader().getResource("twentynineteen/day07/input.txt").getFile());
    assertEquals(273814, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws FileNotFoundException {
    File file = new File(getClass().getClassLoader().getResource("twentynineteen/day07/basicp2.txt").getFile());
    assertEquals(139629729, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws FileNotFoundException {
    File file = new File(getClass().getClassLoader().getResource("twentynineteen/day07/input.txt").getFile());
    assertEquals(34579864, day.solvePartTwo(file));
  }
}