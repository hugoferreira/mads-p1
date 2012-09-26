package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import main.Map;
import main.Pos;

import org.junit.Test;

public class TestFileInput {

	@Test
	public void testFile() throws Exception {
		Map testMap = new TestMap();
		Map realMap = new Map("res/map1.txt");
		
		
		for(int i=0; i<testMap.map.length; i++)
			for(int j=0; j<testMap.map[i].length; j++){
				try{
					if (testMap.map[i][j] != realMap.map[i][j]) 
						fail("Not the same");
				}
				catch (Exception e) {
					fail("Exception!");
				}
			}
		
		assertTrue(true);
	}

}
