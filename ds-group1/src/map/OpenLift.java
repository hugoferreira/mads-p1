package map;

public class OpenLift implements Cell, Cloneable {
	
	@Override
	public String print() {
		return "O";
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
