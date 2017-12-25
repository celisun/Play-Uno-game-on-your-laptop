/**
 * @author sunceli
 * @email sunceli@brandeis.edu
 * @brief Implementatin of class representing a player in game, with name, next/prev player, and its list of cards 
 */

public class Player {
	
	

	private String name;
	private Player nextPlayer=null;
	private Player prevPlayer=null;
	private SinglyLinkedList<UnoCard> hand;     // added 

	
	public Player(String name){
		this.name = name;
		this.hand = new SinglyLinkedList<UnoCard>();
	}
	
	/**
	 * Add a new card to player's hand
	 * @param c
	 * @Runningtime O(1)
	 */
	public void addToHand(UnoCard c){
		this.hand.regularInsert(c);
	}
	
	/**
	 * Delete the uno card at the position from player's hand and return it
	 * @param index 
	 * @return card 
	 * @Runningtime O(n)
	 */
	public UnoCard removeFromHand(int index){
		SinglyLinkedNode<UnoCard> remove = this.hand.remove(index);
		return remove.getData();
	}
	
	/**
	 * Delete the card from hand
	 * @param c
	 * @runningtime O(n)
	 */
	public void removeFromHand(UnoCard c){
		this.hand.remove(c);
	}
	
	
	/**
	 * Return true when your hand has nothing left. 
	 * @return 
	 * @Runningtime O(1)
	 */
	public boolean winner(){
		return this.hand.size()==0 ? true : false;
	}

	
	// O(1)
	public Player getNextPlayer() {
		return nextPlayer;
	}
	// O(1)
	public void setNextPlayer(Player nextPlayer) {
		this.nextPlayer = nextPlayer;
	}
	// O(1)
	public Player getPrevPlayer() {
		return prevPlayer;
	}
	// O(1)
	public void setPrevPlayer(Player prevPlayer) {
		this.prevPlayer = prevPlayer;
	}

	// Player [name= ], [card#= ], [prev/next= ].
	// O(1)
	public String toString() {
		return "Player [name=" + name + "]" + ", [card#=" + this.hand.size() + "], "
				+ "[prev/nex= " + this.prevPlayer.name() + "/" + this.nextPlayer.name() + "].";
	}
	
	// O(1)
	public String name() {
		return this.name;
	}
	
	// O(n)
	public SinglyLinkedList<UnoCard> getHand() {
		return this.hand;
	}
	
}
