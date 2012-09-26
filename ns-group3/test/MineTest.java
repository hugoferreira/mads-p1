import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;


public class MineTest {

	@Test
	public void testCheckValid() {
		Boolean opt = Mine.checkValid('u');
	    assertEquals(true, opt);
	}
	
	@Test
	public void testCheckInvalid() {	
		Boolean b = Mine.checkValid('i');
		assertEquals(false, b);
	}
	
	@Test
	public void testValidateMoveDiamond() {
		Mine tester = new Mine("example11.map");
		assertEquals(true,tester.validateMove(new Point(4,4)));
	}
	
	@Test
	public void testValidateMoveEarth() {
		Mine tester = new Mine("example1.map");
		assertEquals(true,tester.validateMove(new Point(5,4)));
	}
}
