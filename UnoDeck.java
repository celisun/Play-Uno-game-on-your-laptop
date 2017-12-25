/* 
 * @author sunceli
 * @email sunceli@brandeis.edu
 * @brief Class for the Uno Deck
 * The cards have already been created for you in the constructor, you just have to add them
 * to your linked list that 
 */

import java.util.*;
public class UnoDeck {
	private static final String[] REGULAR_COLORS = {"red", "yellow", "blue", "green"};
	//uncomment once you make your deck, then add all the cards in the constructor to your deck
	private SinglyLinkedList<UnoCard> deck; // initialize this in your constructor
	private SinglyLinkedList<UnoCard> discard; // initialize this in your constructor
	private UnoCard lastDiscarded = null;

	//http://play-k.kaserver5.org/Uno.html
	// There are 108 cards in a Uno deck. 
	// There are four suits, Red, Green, Yellow and Blue, 
	// each consisting of one 0 card, two 1 cards, two 2s, 3s, 4s, 5s, 6s, 7s, 8s and 9s; 
	// two Draw Two cards; two Skip cards; and two Reverse cards. 
	// In addition there are four Wild cards and four Wild Draw Four cards.

	
	
	public UnoDeck(){
		//initialize deck and discard.
		deck = new SinglyLinkedList<UnoCard>();
		discard = new SinglyLinkedList<UnoCard>();
		
		// Initialized as having all 108 cards, as described above
		for (String color : REGULAR_COLORS){
			this.deck.randomInsert(new UnoCard(color, 0)); // add one of your color in zero
			for (int i = 0; i<2; i++){
				// add numbers 1-9
				for (int cardNumber = 1; cardNumber<=9; cardNumber++){
					this.deck.randomInsert(new UnoCard(color, cardNumber)); // Add this to deck
				}
				// add 2 of each of the special card for that color
				this.deck.randomInsert(new UnoCard(color, true, false, false)); // add to deck
				this.deck.randomInsert(new UnoCard(color, false, true, false)); // add to deck
				this.deck.randomInsert(new UnoCard(color, false, false, true)); // add to deck
			}
			
		}
		// add 4 wild cards, and 4 draw 4 wild cards
		for (int i = 0; i<4; i++){
			this.deck.randomInsert(new UnoCard(false)); // add to deck
			this.deck.randomInsert(new UnoCard(true)); // add to deck
		}
	}
	
	
	/**
	 * @return last discarded uno card
	 * @Runningtime O(1)
	 */
	public UnoCard getLastDiscarded() {
		return this.lastDiscarded;
	}
	
	
	/**
	 * Draw a card from the first in deck, if deck empty draw from discards.
	 * @return uno card 
	 * @Runningtime O(n)
	 */
	public UnoCard drawCard() {
		if (this.deck.size() != 0){		//if deck still has cards, draw from deck  
			SinglyLinkedNode<UnoCard> curr = this.deck.getHead();
			this.deck.remove(0);
			return curr.getData();
		}else{							//deck empty, draw from discards
			
			if(this.discard.size()== 0){				//discard deck both empty, error.
				throw new Error("failed: no card to draw");
			}
			
			SinglyLinkedNode<UnoCard> curr = this.discard.getHead();
			this.discard.remove(curr.getData());
			return curr.getData();
		}
	}
	
	
	/**
	 * Add card to discard pile, and set it as the last discarded card. 
	 * Throw error if invalid card is placed on the deck.
	 * @param c
	 * @Runningtime O(n)
	 */
	public void discardCard(UnoCard c){
		
		//error if illegal card to place down againt the last card
		if (this.lastDiscarded !=null && !c.canBePlacedOn(this.lastDiscarded)) {
			throw new Error ("failed: illegal card to place down.");
		}
		this.discard.randomInsert(c);
		this.lastDiscarded = c;
	}

	/**
	 * Cards in deck and discard
	 * @return deck and discard
	 * @Runningtime O(n)
	 */
	public String toString(){
		return "deck: " + this.deck.toString() + "\n discard: " + this.discard.toString();
	}

}
