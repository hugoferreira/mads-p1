import static org.junit.Assert.*;

import java.awt.Point;
import java.util.HashMap;

import org.junit.Test;


public class TabTest {

	@Test
	public void testChange() {
		Tab tester = new Tab();
		tester.change(1, 1, '*');
	    assertEquals(tester.getPoint(1, 1), '*');
	}
}
