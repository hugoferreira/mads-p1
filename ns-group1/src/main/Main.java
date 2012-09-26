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
		m.readMap("textfile.txt");
		Pair<Integer, Integer> player = m.getPlayer();
		Player p = new Player(player.getSecond(), player.getFirst());
		m.readMap("textfile.txt");
		boolean validMove = true;
		int time = 0;
		while(true)
		{
			
			m.back.add(new Pair<Map, Player>(new Map(m), new Player(p)));
			if(validMove)
					m.checkRocks();
			m.printMap();
			validMove = true;
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
				case Input.UNDO: {
					
					if (m.back.size() >= 2) {
						m.back.pop();
						Pair<Map, Player> redo = m.back.pop();
						redo.getFirst().forward.add(new Pair<Map, Player>(new Map(m), new Player(p)));
						m = redo.getFirst();
						p = redo.getSecond();	
					}else
						validMove = false;
					break;
				}
				case Input.REDO: {
					
					if (m.forward.size() >= 1) {
						Pair<Map, Player> undo = m.forward.pop();
						m = undo.getFirst();
						p = undo.getSecond();
						validMove = false;
					}else
						validMove=false;
					break;
				}
				default:
					validMove = true;
					break;
			}
			
			System.out.println(time++);
			System.out.println(p.getDiamonds());
			
		
		}
	}

}
