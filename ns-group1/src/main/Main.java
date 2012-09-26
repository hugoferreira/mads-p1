package main;

import layout.Input;
import map.Map;
import map.Player;
import map.Pair;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Map m = new Map();
		m.readMap("textfile6.txt");
		Pair<Integer, Integer> player = m.getPlayer();
		Player p = new Player(player.getSecond(), player.getFirst());
		m.countDiamonds();
		
		int time = 0;
		while(true)
		{
			m.printMap();
			//m.checkRocks();
			
			if(m.isPlayerDead())
			{
				System.out.println("XAUUUU!");
				System.exit(0);
			}
			
			switch(Input.checkInput()) {
				case Input.LEFT: {
					m.pickUpDiamond(p, p.getPos_y(), p.getPos_x()-1);
					if (m.moveObject(p.getPos_y(), p.getPos_x(), p.getPos_y(), p.getPos_x()-1)) 
						p.setPos_x(p.getPos_x()-1);
					break;
				}
				case Input.RIGHT: {
					m.pickUpDiamond(p, p.getPos_y(), p.getPos_x()+1);
					if (m.moveObject(p.getPos_y(), p.getPos_x(), p.getPos_y(), p.getPos_x()+1)) 
						p.setPos_x(p.getPos_x()+1);
					break;		
				}
				case Input.UP: {
					m.pickUpDiamond(p, p.getPos_y()-1, p.getPos_x());
					if (m.moveObject(p.getPos_y(), p.getPos_x(), p.getPos_y()-1, p.getPos_x())) 
						p.setPos_y(p.getPos_y()-1);
					break;
				}
				case Input.DOWN: {
					m.pickUpDiamond(p, p.getPos_y()+1, p.getPos_x());
					if (m.moveObject(p.getPos_y(), p.getPos_x(), p.getPos_y()+1, p.getPos_x())) 
						p.setPos_y(p.getPos_y()+1);
					break;					
				}
			}
			
			m.checkLiftStatus(p);
			
			if(m.isGameEnd()) {
				System.out.println("GANHOU!");
				System.exit(0);
			}
			
			m.checkRocks();
			
			if(m.isGameEnd()) {
				System.out.println("GANHOU!");
				System.exit(0);
			}
			
			m.cleanEmptySpaceStatus();
			
			/*m.printMap();
			System.out.println(time++);
			System.out.println(p.getDiamonds());*/
		}
	}
}
