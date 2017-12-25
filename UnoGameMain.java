/**
 * @author sunceli
 * @email sunceli@brandeis.edu
 * @brief Main method to run Uno Game. user-interacted. 
 */

import java.util.*;

public class UnoGame {

	// get player info, set up player in circle, start game
	public static void main(String[] args) {
		
		 //get player info
		 Scanner console = new Scanner(System.in);
		 System.out.println("Enter names of players:(at least 6, names separate by space)");
		 String data = console.nextLine();
		 String[] plist = data.split("\\s+");		
		 
		 
		// build player circle. add 5 players to player circle
		 PlayerCircle playerCircle = new PlayerCircle();
		 for (int i=0; i<5; i++){
			 playerCircle.addToCircle(new Player(plist[i])); 		
		 }
		 
		// build wait list (queue). add additional players to waiting list
		 Queue<Player> wl;
		 wl = new Queue<Player>(plist.length-5);
		 for (int j=5; j<plist.length; j++){
				 wl.enqueue(new Player(plist[j]));
			 }
		 
		 // Start game. 
		 // swap between player circle and wait list
		 Player loser = start(playerCircle);
		 Player newPlayer;
		 while (!wl.isEmpty()){
			 
			 newPlayer = wl.dequeue();
			 wl.enqueue(loser);
			 playerCircle.addToCircle(newPlayer);
			 playerCircle.removePlayer(loser);
			 System.out.println("\n\n" + newPlayer.name()+ " (from wait list) IN, " + loser.name() + " OUT. \n\n");
			 System.out.println("=================> Ready For Next Round <============= \n\n");
			 loser = start(playerCircle);			 
		 }
	}
	
	
	
	/**
	 * Start game until get a winner and a loser among the circle, then game finishes and report the game.
	 * @param deck
	 * @param playerCircle
	 * @return player loser
	 * @runningtime N/A
	 */
	public static Player start(PlayerCircle playerCircle){
		
		// Set Up.
		// initialize the deck, each draws 7 first cards.
		
		UnoDeck deck = new UnoDeck();
		Player p = playerCircle.getFirstPlayer();
		System.out.println("Every one get 7 cards.");
		for (int i=0; i < playerCircle.getSize(); i++){
			// draw 7 cards for each player 
			for (int j=0; j<7; j++){
			 p.addToHand(deck.drawCard());
		 }
		 System.out.println(p.name() + ": " + p.getHand().toString());
		 p = p.getNextPlayer();
	     }	 
	     deck.discardCard(deck.drawCard());
		 System.out.println("\nSystem place down: " + deck.getLastDiscarded().toString() + "\n");
		 System.out.println("Game Starts.\n");
		 
		 
		 
		 // Start.
		 //play in one direction (start with CW) until one player places out all his/her cards and wins.
		 //cards that can be placed down will be suggested for user to pick in each round;
		 //if no cards can be placed, one card will be automatically drawn from the deck and notify the user.
		 //the the round finished, a winner and a loser will be obtained.		 
		 Player winner = null;
		 Player loser = null;
		 Player pp = playerCircle.getFirstPlayer();
		 Scanner console = new Scanner(System.in);
		 int most_num_cards = 0;    	// keep track of the most # cards a player in circle holds, used to identify loser easily
		 int dir = 1 ;         	// direction of playing, 1 for next ->next, 0 for prev-> prev player.
		 int r = 0; 			//record number of rounds
		 		 
		 while (true){
			 System.out.println(pp.name() + "'s turn  ---------------------- " + "(" + pp.getHand().size() + ")");
			 		 			 
			 // Handle special card from LAST round
			 // e.g. draw two card, wild draw four, skip card
			 int draw2;
			 int draw4;
			 draw2 = deck.getLastDiscarded().isDrawTwo()? 2:-1;
			 draw4 = deck.getLastDiscarded().isWildDrawFour()? 4:-1;
			 if (Math.max(draw2, draw4) > 0 ){
				 System.out.print("	special card confronted. auto draw "+ Math.max(draw2, draw4) + " cards from deck :+ ");
				 for (int i=0; i< Math.max(draw2, draw4); i++){
						UnoCard newCard = deck.drawCard();
						pp.addToHand(newCard);
						System.out.print(newCard.toString()+ ", "); 
					 }
			 }
			 if (deck.getLastDiscarded().isSkip()){ 
				 System.out.println("\n" + pp.name()+ " is skiped. \n");
				 pp = dir==1? pp.getNextPlayer():pp.getPrevPlayer();
				 System.out.println(pp.name() + "'s turn  ---------------------- " + "(" + pp.getHand().size() + ")");
			 }
			 System.out.println("");
			 
			 // Current player pick to go.	 
			 // generate list of all legal cards from hand that can be placed down			 
			 String[] suggested = suggested(pp.getHand(), deck.getLastDiscarded()); 
			 
			 // no card to place down, 
			 // draw card and get to next player
			 if (suggested.length == 0){
				 UnoCard newCard = deck.drawCard();
				 pp.addToHand(newCard);
				 System.out.println("	No card to place down, auto draw one card from deck :+ " + newCard.toString() + "\n");
				 System.out.println("	now : " + pp.getHand().toString()+ " (" +pp.getHand().size() + ")\n");
		     //provide a suggested list of cards to pick, 
			 //user pick by entering name of card
			 }else{
				 System.out.println("	suggested (pick by entering name): "+ Arrays.toString(suggested));
				 String card = console.nextLine();
				 
				 // card must be legal (in suggested list)
				 while (! Arrays.asList(suggested).contains(card)){
					 System.out.println("	illegal. pick again (space sensitive)");
					 card = console.nextLine();
				 }
				 				 
				 System.out.println("	Placed Down: " + card);
				 UnoCard discard = pp.removeFromHand(pp.getHand().indexOf(card));
				 deck.discardCard(discard);
				 System.out.println("	now : " + pp.getHand().toString()+ " (" +pp.getHand().size() + ")\n");
				 
				 // Observe state.
				 // If player wins, game stops.
				 if(pp.winner()){
					 winner = pp;
					 break;
				 }
				 
				 // Handle special card placed down. change state of game. 
				 // e.g. reverse direction, skip player.
				 if (card.contains("reverse")){
					 dir = dir==1? 0:1;
					 System.out.println("\n\n					-- dir reversed -- \n\n");
				 }	 				 				 
			 }	 
			 pp = dir==1 ? pp.getNextPlayer(): pp.getPrevPlayer();
			 r++;
		}
		 
		 
		//========    Game finished.    ======= 
		// Compute loser.
		loser = playerCircle.getFirstPlayer();
		for (int i=0; i<5; i++){
			if(loser.getHand().size() >= most_num_cards){
				most_num_cards = loser.getHand().size();
			}
			loser = loser.getNextPlayer();
		}
		// Game report.
		System.out.println("\n********************************");
		System.out.println("Game finished. Report: ");
		System.out.println("number of rounds: " + r/5);
		System.out.println("+ winner: " + winner.name());
		System.out.println("+ loser:  " + loser.name() +" ("+most_num_cards+")");
		
		return loser; 		 
	} 
	
	
	/**
	 * From all cards in hand, find all legal cards that can be placed
	 * on against the last card being placed. suggested to user to pick.
	 * @param hand
	 * @param last
	 * @return string[]
	 * @runningtime O(n)
	 */
	public static String[] suggested(SinglyLinkedList<UnoCard> hand, UnoCard last){
		
		String[] suggested = new String[hand.size()];
		SinglyLinkedNode<UnoCard> card = hand.getHead();
		int i =0;
		
		while (card != null){
			if(last.canBePlacedOn(card.getData())){
				suggested[i] = card.getData().toString();
				i++;
			}
			card = card.getNext();			
		}
		return Arrays.copyOfRange(suggested, 0, i);
	}		
}
