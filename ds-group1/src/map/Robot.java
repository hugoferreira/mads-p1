package map;

public class Robot implements Cell{
	
	@Override
	public String print() {
		return "R";
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
