package map;

public class Wall implements Cell{
	
	public static char IDENTIFIER = '#';
	
	@Override
	public String print() {
		return "#";
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
