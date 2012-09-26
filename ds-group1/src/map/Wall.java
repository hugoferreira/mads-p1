package map;

public class Wall implements Cell{
	
	public static char IDENTIFIER = '#';
	
	@Override
	public String print() {
		return "#";
	}
}
