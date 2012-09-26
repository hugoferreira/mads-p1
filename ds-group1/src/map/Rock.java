package map;

public class Rock implements Cell, Cloneable {
	/** Whether this rock was falling last step. */
	private boolean falling = false;
	
	@Override
	public String print() {
		return "*";
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public boolean isFalling() {
		return falling;
	}
	
	public void setFalling(boolean falling) {
		this.falling = falling;
	}

}
