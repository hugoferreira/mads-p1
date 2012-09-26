package map;

public class Rock implements Cell{

	@Override
	public String print() {
		return "*";
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
