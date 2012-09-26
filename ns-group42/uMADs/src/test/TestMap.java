package test;

import main.Map;
import main.Pos;

public class TestMap extends Map{

	public TestMap(){
		map = new  Pos[][]{	
				{Pos.WALL, Pos.WALL,Pos.WALL,Pos.WALL,Pos.WALL,Pos.WALL},
				{Pos.WALL, Pos.EARTH,Pos.EMPTY,Pos.ROCK,Pos.ROBOT,Pos.WALL},
				{Pos.WALL, Pos.EMPTY,Pos.EMPTY,Pos.DIAMOND,Pos.EARTH,Pos.WALL},
				{Pos.WALL, Pos.DIAMOND,Pos.EMPTY,Pos.ROCK,Pos.EMPTY,Pos.WALL},
				{Pos.LIFT_C, Pos.EMPTY,Pos.EMPTY,Pos.EARTH,Pos.DIAMOND,Pos.WALL},
				{Pos.WALL, Pos.WALL,Pos.WALL,Pos.WALL,Pos.WALL,Pos.WALL}
			};
	}
	
	
}
