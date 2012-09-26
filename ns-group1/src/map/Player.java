package map;

public class Player {
	private int pos_x;
	private int pos_y;
	private int diamonds;
	
	public Player(int x, int y) {
		pos_x = x;
		pos_y = y;
		diamonds = 0;
	}
	

	public Player(Player old) {
		this.pos_x = old.pos_x;
		this.pos_y = old.pos_y;
		this.diamonds = old.diamonds;
	}
	
	public int getPos_x() {
		return pos_x;
	}
	
	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}
	
	public int getPos_y() {
		return pos_y;
	}
	
	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
	}
	
	public int getDiamonds() {
		return diamonds;
	}

	public void setDiamonds(int d) {
		diamonds = d;
	}
	
	public void addDiamond() {
		diamonds++;
	}
}
