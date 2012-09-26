package map;

public class Rock implements Cell{
	/** Whether this rock was falling last step. */
	private boolean falling = false;
	
	@Override
	public String print() {
		return "*";
	}
	
	public boolean isFalling() {
		return falling;
	}
	
	public void setFalling(boolean falling) {
		this.falling = falling;
	}

}
