package map;

public class Diamond implements Cell, Cloneable {
	
	@Override
	public String print() {
		return "x";
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
