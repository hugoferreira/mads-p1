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
	
	@Test
	public void testMoveObject() {
		
		Map m = new Map();
		m.readMap("textfile.txt");
		
		m.moveObject(1,4,2,4);
		assertEquals(m.getMap()[2][4], 'R');
		assertEquals(m.getMap()[1][4], ' ');
		
		assertEquals(m.moveObject(1,4,2,4), false);
		
		
	}
		
	@Test
	public void testPickUpDiamond() {
		Map m = new Map();
		m.readMap("textfile.txt");
		
		Player p = new Player(1,2);
		
		assertEquals(p.getDiamonds(), 0);
		
		m.pickUpDiamond(p, 2, 3);
		assertEquals(p.getDiamonds(), 1);
	}

}
