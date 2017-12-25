/**
 * @author sunceli
 * @email sunceli@brandeis.edu
 * @brief Implementatin of data structure of a circular(doubly) linked list of players.
 */

public class PlayerCircle {
	
	
	
	private int size = 0;
	private Player head = null;		//head pointer to first player
	
	
	/**
	 * Add new player p to the circle in alphabetical order.
	 * @param p
	 * @Runningtime O(n)
	 */
	public void addToCircle(Player p){
		if (this.size == 0){		//add directly if first player in circle 
			this.head = p;
			this.size ++;
			
		}else if (this.size == 1){	//add directly with prev/next player linking, if the second player to add
			this.head.setNextPlayer(p);
			this.head.setPrevPlayer(p);
			p.setNextPlayer(this.head);
			p.setPrevPlayer(this.head);
			this.size ++;
			
		}else{   
			Player curr = this.head;
			int i = 0;
			while (curr.name().compareTo(p.name()) < 0 && i < this.size){	// compare name in alphabetical, find correct loc to insert
				curr = curr.getNextPlayer();
				i ++;
			}
			Player prev = curr.getPrevPlayer();
			prev.setNextPlayer(p);
			curr.setPrevPlayer(p);
			p.setPrevPlayer(prev);
			p.setNextPlayer(curr);
			this.size ++;
			
			if (i == 0){	// move head pointer if p added at very front 
				this.head = p;
				
			}
		}
	}
	
	
	/**
	 * Kick out the player from the circle
	 * @param player
	 * @runningtime O(n)
	 */
	public void removePlayer(Player p) {
		Player curr = this.head;
		boolean check = false;
		for (int i=0; i<this.size; i++){
			if (curr.name() == p.name()){
				curr.getPrevPlayer().setNextPlayer(curr.getNextPlayer());
				curr.getNextPlayer().setPrevPlayer(curr.getPrevPlayer());
				this.size --;
				check = true;
				break;
			}
		}
		if (!check) {
			throw new Error ("failed: player to kik out not exist.");
		}
	}
	
	/**
	 * Get the first player in circle.
	 * @return first player
	 * @Runningtime O(1)
	 */
	public Player getFirstPlayer() {
		return this.head;
	}
	
	/**
	 * Get the number of player in the circle.
	 * @return size
	 */
	public int getSize(){
		return this.size;
	}
	
	/**
	 * Print all players in circle.
	 * @return string of players
	 * @Runningtime O(n)
	 */
	public String toString() {
		Player p = this.head;
		String pl = "";
		for (int i=0; i < this.size; i++){
			pl += p.toString()+"\n";
			p = p.getNextPlayer();
		}
		return pl;
	}
}
