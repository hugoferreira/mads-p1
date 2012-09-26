
public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tab tab = new Tab();
		tab.readFromFile("example2.map");
		Mine mine = new Mine(tab);
		return;
	}
}
