package map;

/** Exception thrown when the robot is destroyed.
 */
@SuppressWarnings("serial")
public class RobotDestroyedException extends Exception {
	private String msg;
	
	public RobotDestroyedException(String msg) {
		this.msg = msg;
	}
	
	public String getMessage() {
		return msg;
	}
}
