package test;

import static org.junit.Assert.*;

import map.Map;

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

}
