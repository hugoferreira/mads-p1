package test;

import static org.junit.Assert.*;

import map.Map;
import map.Player;

import org.junit.Test;

public class TestMap {

	@Test
	public void testReadMap() {
		Map m = new Map();
		m.readMap("textfile.txt");
		
		assertEquals(m.getN(),6);
		assertEquals(m.getM(),6);
		assertEquals(m.getMap()[2][2], ' ');
	}
	
	public void testPickUpDiamond() {
		Map m = new Map();
		m.readMap("textfile.txt");
		
		Player p = new Player(1,2);
		
		assertEquals(p.getDiamonds(), 0);
		m.pickUpDiamond(p, 3, 2);
		assertEquals(p.getDiamonds(), 1);
	}

}
