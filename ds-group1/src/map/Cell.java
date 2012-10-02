package map;

public interface Cell {
	
	public String print();

	public Object clone() throws CloneNotSupportedException;
}
