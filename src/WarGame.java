import java.util.ArrayList;
import java.util.Scanner;
import SpecialLinkedList.*;

public class WarGame {
	Scanner input = new Scanner(System.in);
	private boolean game_over = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinkedList<Card> deck = new LinkedList<Card>();
		LinkedList<Card> p1 = new LinkedList<Card>(), p2 = new LinkedList<Card>();
		
		WarGame game = new WarGame();
		
		game.setupGame(deck, p1, p2);
		
		game.game(deck, p1, p2);
		
	}
	
	public void setupGame(LinkedList<Card> deck, LinkedList<Card> p1, LinkedList<Card> p2) {
		
		
		for (int i = 1; i < 14; i++) {
			deck.add(new Card(i, "hearts"));
			deck.add(new Card(i, "diamonds"));
			deck.add(new Card(i, "spades"));
			deck.add(new Card(i, "clubs"));
		}
		
		//shuffle a random number of times (4-9 times)
		for(int i = 0; i < ((int)(Math.random()*6) + 4); i++) {
			deck.shuffle();
		}
		//deal the cards
		deck.split(p1, p2);
		System.out.println(p1.length + " " + p2.length);
	}
	
	public void game(LinkedList<Card> deck, LinkedList<Card> p1, LinkedList<Card> p2) {
		
		System.out.println("Player 1 deck size: " + p1.length + ". Player 2 deck size: " + p2.length);
		
		System.out.println("Welcome to War!");
		
		
		
		while(!game_over) {
			//storing drawn cards to reuse
			Node<Card> removed_p1, removed_p2;
			System.out.println("\n\nPLAYER 1 || Press enter to play a card:");
			input.nextLine();
			
			removed_p1 = p1.remove();
			
			//remove() would return null if there was no card to draw -> game over
			if(removed_p1 == null) {
				System.out.println("Game Over! Player 2 wins!");
				return;
			}
			
			System.out.println("" + removed_p1.value.number + " of " + removed_p1.value.suit + " played for PLAYER 1");
			System.out.println("\nPLAYER 2 || Press enter to play a card:");
			input.nextLine();
			
			removed_p2 = p2.remove();
			
			//remove() would return null if there was no card to draw -> game over
			if(removed_p2 == null) {
				System.out.println("Game Over! Player 1 wins!");
				return;
			}
			
			System.out.println("" + removed_p2.value.number + " of " + removed_p2.value.suit + " played for PLAYER 2");
			
			//comparing value of the cards -> player w/ higher value takes both cards
			if(removed_p1.value.number > removed_p2.value.number) {
				
				p1.add(new Card(removed_p1.value.number, removed_p1.value.suit));
				p1.add(new Card(removed_p2.value.number, removed_p2.value.suit));
				System.out.println("\nPLAYER 1 takes both cards.\n\nPlayer 1 deck now has: " + p1.length + " cards\nPlayer 2 deck now has: " + p2.length + " cards");
			
			}else if(removed_p1.value.number < removed_p2.value.number) {
				
				p2.add(new Card(removed_p1.value.number, removed_p1.value.suit));
				p2.add(new Card(removed_p2.value.number, removed_p2.value.suit));
				System.out.println("\nPLAYER 2 takes both cards.\n\nPlayer 2 deck now has: " + p2.length + " cards\nPlayer 1 deck now has: " + p1.length + " cards");
			
			}else if(removed_p1.value.number == removed_p2.value.number) {
				
				//war --> draw 4 cards and play the fourth one
				System.out.println("\n\nWar!\nPlayer 1 deck now has: " + p1.length + " cards");
				System.out.println("\nPlayer 2 deck now has: " + p2.length + " cards");
				
				//this list stores all the cards played for war for each player -> to add them later to a player
				ArrayList<Node<Card>> war_draw_p1 = new ArrayList<Node<Card>>();
				//add the drawn card before drawing 4 war cards (5 elements total after war finishes)
				war_draw_p1.add(removed_p1);
				
				//and the same for player 2
				ArrayList<Node<Card>> war_draw_p2 = new ArrayList<Node<Card>>();
				war_draw_p2.add(removed_p2);
				
				war(deck, p1, p2, war_draw_p1, war_draw_p2);
				
			}
			
			
			
		}
	}
	
	/*put the lists of cards as parameters because we need to keep track of cards through
	recursive loops of the function if we have back-to-back wars
	war_draw lists are used to store the 4 draws from each player each war cycle*/
	public void war(LinkedList<Card> deck, LinkedList<Card> p1, LinkedList<Card> p2, ArrayList<Node<Card>> war_draw_p1, ArrayList<Node<Card>> war_draw_p2) {
		
		for (int i = 1; i <= 4; i++) {
			
			System.out.println("\n\nWAR!\nPLAYER 1 || Press enter to draw card number " + i);
			input.nextLine();
			//storing drawn card to reuse
			Node<Card> removed_p1;
			removed_p1 = p1.remove();
			System.out.println("" + removed_p1.value.number + " of " + removed_p1.value.suit + " played for PLAYER 1");
			
			//if while drawing cards for war a player finishes all his cards, he loses
			if(p1.length == 0) {
				
				System.out.println("Player 1 finished his cards. Player 2 wins!! Game Over!");
				//stop war and game
				game_over = true;
				return;
			
			}else {
				war_draw_p1.add(removed_p1);
			}
			
		}
		
		for (int i = 1; i <= 4; i++) {
			
			System.out.println("\n\nWAR!\nPLAYER 2 || Press enter to draw card number " + i);
			input.nextLine();
			//storing drawn card to reuse
			Node<Card> removed_p2;
			removed_p2 = p2.remove();
			System.out.println("" + removed_p2.value.number + " of " + removed_p2.value.suit + " played for PLAYER 2");
			
			//if while drawing cards for war a player finishes all his cards, he loses
			if(p2.length == 0) {
				
				System.out.println("Player 2 finished his cards. Player 1 wins!! Game Over!");
				//stop war and game
				game_over = true;
				return;
			
			}else {
				war_draw_p2.add(removed_p2);
			}
		}
		
		//the last element in the war_draw lists is the last card drawn which is used for comparison
		if(war_draw_p1.get(4).value.number > war_draw_p2.get(4).value.number) {
			
			//player 1 wins -> add all the cards drawn by both players (10 cards total added) to player 1 deck
			war_draw_p2.forEach(card -> p1.add(new Card(card.value.number, card.value.suit)));
			war_draw_p1.forEach(card -> p1.add(new Card(card.value.number, card.value.suit)));
			System.out.println("\nPlayer 1 wins this war.\nPlayer 1 deck now has: " + p1.length + "\nPlayer 2 deck now has: " + p2.length + " cards");
			return;
		
		}else if(war_draw_p1.get(4).value.number < war_draw_p2.get(4).value.number) {
			
			//player 2 wins -> add all the cards drawn by both players (10 cards total added) to player 2 deck
			war_draw_p1.forEach(card -> p2.add(new Card(card.value.number, card.value.suit)));
			war_draw_p2.forEach(card -> p2.add(new Card(card.value.number, card.value.suit)));
			System.out.println("\nPlayer 2 wins this war.\nPlayer 2 deck now has: " + p2.length + "\nPlayer 1 deck now has: " + p1.length + " cards");
			return;
		
		}else {
			
			//same value so another war -> recursion while saving all the past drawn cards
			System.out.println("\nWAR AGAIN!!");
			war(deck, p1, p2, war_draw_p1, war_draw_p2);
			
		}
	}
		

}
