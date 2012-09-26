import static org.junit.Assert.*;

import org.junit.Test;


public class MineTest {

	@Test
	public void testToString() {
		String mine = "######\n#. *R#\n#  x.#\n#x * #\nL  .x#\n######";

		Mine m = new Mine(mine);
		assertEquals(m.toString(), mine);
	}

	@Test
	public void testGetCell() {
		String mine = "######\n#. *R#\n#  x.#\n#x * #\nL  .x#\n######";

		Mine m = new Mine(mine);
		
		assertEquals(m.getCell(0,0), '#');
		assertEquals(m.getCell(1,1), '.');
		assertEquals(m.getCell(1,3), '*');
		assertEquals(m.getCell(1,4), 'R');
		assertEquals(m.getCell(4,0), 'L');
	}

	@Test
	public void testSetCell() {
		String mine = "######\n#. *R#\n#  x.#\n#x * #\nL  .x#\n######";

		Mine m = new Mine(mine);
		
		assertEquals(Character.toString(m.getCell(1,4)), Character.toString('R'));
		m.setCell(1, 4, ' ');
		assertEquals(Character.toString(m.getCell(1,4)), " ");
	}

	@Test
	public void testMove() {
		String mine = "######\n#.  R#\n#  x.#\n#x * #\nL  .x#\n######";
		Mine m = new Mine(mine);
		
		m.move('l');
		
		assertEquals(m.toString(), "######\n#. R #\n#  x.#\n#x * #\nL  .x#\n######");
	}

	@Test
	public void testUpdateMap() {
		String mine = "###\n#*#\n# #\n###";

		Mine m = new Mine(mine);
		
		assertEquals(Character.toString(m.getCell(1,1)), "*");
		assertEquals(Character.toString(m.getCell(2,1)), " ");
		m.updateMap();
		
		assertEquals(Character.toString(m.getCell(1,1)), " ");
		assertEquals(Character.toString(m.getCell(2,1)), "*");
	}

	@Test
	public void testLevelUp() {
		String mine = "###\n#*#\n# #\n###";
		String mine2 = "######\n#.  R#\n#  x.#\n#x * #\nL  .x#\n######";
		
		Mine m = new Mine(mine);
		Mine m2 = new Mine(mine2);
		
		assertEquals(m.levelUp(), true);
		assertEquals(m2.levelUp(), false);
		
	}

}
