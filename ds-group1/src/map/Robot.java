package map;

public class Robot implements Cell, Cloneable {
	
	private int diamonds = 0;
	private int steps = 0;
	
	public void addDiamond(){
		diamonds++;
	}
	
	public void addStep(){
		steps++;
	}
	
	@Override
	public String print() {
		return "R";
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public int getCurrentScore() {
		return (diamonds * 25 - steps);
	}

	public int getFinalScore() {
		return (diamonds * 25 - steps + 50 * diamonds ) ;
	}
}
