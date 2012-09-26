package map;

public class Robot implements Cell{
	
	private int diamonds = 0;
	
	public void addDiamond(){
		diamonds++;
	}
	
	@Override
	public String print() {
		return "R";
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
