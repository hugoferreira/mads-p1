package map;

public class Diamond implements Cell{
	
	@Override
	public String print() {
		return "x";
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
