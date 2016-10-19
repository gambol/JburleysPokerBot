package main.Classes.ExternalClasses.Chen;

import java.util.Random;

public class zDeck {
	
	private Random random = new Random();
	
	private zCard[] cards = new zCard[zCard.NUM_CARDS];
	
	private int position;
	
	public zDeck() {
		position = 0;
		for(int i = 0; i < zCard.NUM_CARDS; i++) {
			cards[i] = new zCard(i);
		}
		random.setSeed(System.currentTimeMillis());
		random.nextInt();
		random.nextInt();
		random.nextInt();
		random.nextInt();
		random.nextInt();
	}
	
   /**
    * Shuffles the deck
    */
	public void shuffle() {
		zCard  tempCard;
		for (int i = 0; i < zCard.NUM_CARDS; i++) {
			int j = i + random.nextInt(zCard.NUM_CARDS - i);
			tempCard = cards[j];
			cards[j] = cards[i];
			cards[i] = tempCard;
		}
		position = 0;
	}
	
	public boolean removeCards(String cards) {
		int l = cards.length(); 
		if(l % 2 != 0) {
			return false;
		} else {
			int numOfCards = l / 2;
			for(int i = 0; i < numOfCards; i++) {
				zCard card = new zCard(cards.substring(2 * i, 2 * i +2));
				if(!remove(card)) return false;
			}
			return true;
		}
	}
	
	public boolean remove(zCard card) {
		int index = card.getIndex();
		if(cards[index] != null) {
			cards[index] = null;
			return true;
		}  else {
			return false;
		}
	}
	
	public boolean putBack(zCard card) {
		int index = card.getIndex();
		if(cards[index] == null) {
			cards[index] = card;
			return true;
		}  else {
			return false;
		}
	}
	
	public void reset() { 
		position = 0;
	}
	
	public boolean hasCards() {
		return (position < zCard.NUM_CARDS);
	}
	
	public zCard getCard(int index) {
		return cards[index];
	}
	
	public zCard nextCard() {
		while(cards[position] == null) {
			position++;
		}
		return cards[position];
	}

}
