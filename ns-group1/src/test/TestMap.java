package test;

import static org.junit.Assert.*;

import map.Map;
import map.Player;

import org.junit.Test;

public class TestMap {

	@Test
	public void testReadMap() {
		Map m = new Map();
		m.readMap("textfile2.txt");
		
		assertEquals(m.getN(),6);
		assertEquals(m.getM(),6);
		assertEquals(m.getMap()[2][2], ' ');
	}
	
	@Test
	public void testMoveObject() {
		
		Map m = new Map();
		m.readMap("textfile2.txt");
		
		m.moveObject(1,4,2,4);
		assertEquals(m.getMap()[4][3], 'R');
		assertEquals(m.getMap()[1][4], ' ');
		
		assertEquals(m.moveObject(1,4,2,4), false);

	}
		
	@Test
	public void testPickUpDiamond() {
		Map m = new Map();
		m.readMap("textfile2.txt");
		
		Player p = new Player(4,3);
		
		assertEquals(p.getDiamonds(), 0);
		
		m.pickUpDiamond(p, 4, 4);
		
		assertEquals(p.getDiamonds(), 1);
	}
	
	@Test
	public void testChangeTerrain() {
		Map m = new Map();
		m.readMap("textfile2.txt");
		
		assertEquals(m.getMap()[2][4], '.');
		m.changeTerrain(2,4, ' ');
		assertEquals(m.getMap()[2][4], ' ');
		
	}
	
	@Test
	public void testCheckRocks() {
		Map m = new Map();
		m.readMap("textfile2.txt");
		
		Player p = new Player(4,3);
		
		assertEquals(m.getMap()[1][3], '*');
		
		m.checkRocks();
		
		assertEquals(m.getMap()[1][3], ' ');
		
		assertEquals(m.getMap()[2][3], '*');
	}
	
	
	@Test
	public void testSlipperyRocks() {
		Map m = new Map();
		m.readMap("textfile3.txt");
		
		m.checkRocks();
		m.printMap();
		assertEquals(m.getMap()[2][3], 'x');
		
	}
	@Test
	public void testPushRocksWithFall() {
		Map m = new Map();
		m.readMap("textfile4.txt");
		
		
		
		assertEquals(m.getMap()[1][2], '*');
		
		m.moveObject(1, 3, 1, 2);
		
		assertEquals(m.getMap()[1][2], 'R');
		
		assertEquals(m.getMap()[2][1], '*');
	
	}
	@Test
	public void testPushRocks() {
		Map m = new Map();
		m.readMap("textfile5.txt");
		
		
		
		assertEquals(m.getMap()[1][2], '*');
		
		m.moveObject(1, 3, 1, 2);
		
		assertEquals(m.getMap()[1][2], 'R');
		
		assertEquals(m.getMap()[1][1], '*');
	
	}
	

}
