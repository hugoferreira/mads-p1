package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import map.Map;
import map.Wall;

import org.junit.Test;

public class MapTest {

	@Test
	public void test() {
		Map map = null;
		try {
			map = new Map("samples/example1.map");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		assertEquals(map.getXY(1, 1).getClass(), Wall.class);  
	
	}

}
