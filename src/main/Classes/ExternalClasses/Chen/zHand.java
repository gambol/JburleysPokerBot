/*
 * LICENSE
 */
package main.Classes.ExternalClasses.Chen;

/**
 * @author Kyriakos Chatzidimitriou (kyrcha@gmail.com)
 */
public class zHand {

	// Relative power of hands
	
	public final static int NUM_REL_RANKS = 3;
	public final static int WORSE = 0;
	public final static int TIED = 1;
	public final static int BETTER = 2;
    
    /** Maximum number of cards in a hand (Texas hold'em). */
    public final static int MAX_CARDS = 7;

    /** Number of different types of hands */
    public final static int NUM_HANDS = 9;

    // Types of hands
    
    public final static int STRAIGHT_FLUSH = 8;
    public final static int FOUR = 7;
    public final static int FULL_HOUSE = 6;
    public final static int FLUSH = 5;
    public final static int STRAIGHT = 4;
    public final static int TRIPS = 3;
    public final static int TWO_PAIRS = 2;
    public final static int PAIR = 1;
    public final static int HIGH_CARD = 0;
    public final static int BAD_HAND = -1;

    private final static int SHIFT = zCard.NUM_RANKS * zCard.NUM_RANKS * 
    								 zCard.NUM_RANKS * zCard.NUM_RANKS * 
    								 zCard.NUM_RANKS;
    
    public final static int NUM_POTENTIALS = 2;
    public final static int POSITIVE = 0;
    public final static int NEGATIVE = 1;

    private int[] cards;

    private int cardIndex;
    
    private static int[][] rankCards;

    /**
     * Initializes hand with no cards.
     */
    public zHand() { 
    	cardIndex = 0;
    	cards = new int[MAX_CARDS];
    }

    /**
     * Initializes a hand given a hand string.
     * @param handString
     */
    public zHand(String handString) {
    	cardIndex = 0;
    	cards = new int[MAX_CARDS];
    	addCards(handString);
    }
    
    /**
     * Compares two hands and returns their relative rank
     * 
     * @param one first hand
     * @param two second hand
     * @return with respect to first hand, 0 if worse, 1 if tied, 2 if better
     */
    public final static int compareHands(zHand one, zHand two) {
    	int rankOne = one.rankHand();
    	int rankTwo = two.rankHand();
    	if(rankOne < rankTwo) {
    		return WORSE;
    	} else if(rankOne > rankTwo) {
    		return BETTER;
    	} else {
    		return TIED;
    	}
    }
    
    /**
     * Rank this hand
     * @return the rank
     */
    public int rankHand() {
    	return rankHand(this);
    }
    
    /**
     * Get the number of cards in hand
     * @return number of cards
     */
    public int getNumOfCards() {
    	return cardIndex;
    }
    
    /**
     * Compare this hand given a hand
     * @param hand a hand
     * @return relative rank (worse, tied or better)
     */
    public int compareHandWith(zHand hand) {
    	return compareHands(this, hand);
    }
    
    /**
     * Given a position in the hand get the card
     * @param position position in hand
     * @return the card
     */
    public zCard getCard(int position) {
    	if(position > (cardIndex - 1)) {
    		return new zCard(cards[position]);
    	}
    	return null;
    }
    
    /**
     * Given a position in the hand get the card rank
     * @param position position in the hand
     * @return the card rank
     */
    public int getCardIndex(int position) {
    	if(position <= (cardIndex - 1)) {
    		return cards[position];
    	}
    	return -1;
    }
    
    /**
     * Add a card in the hand
     * 
     * @param card a card
     * @return true if addition was successful, false otherwise
     */
    public boolean addCard(zCard card) {
    	if(cardIndex >= MAX_CARDS) {
    		return false;
    	} else {
    		cards[cardIndex] = card.getIndex();
    		cardIndex++;
    		return true;
    	}
    }
    
    /**
     * Add a batch of cards in the hand in String format
     * 
     * @param handString a string containing cards
     * @return true if addition was successful, false otherwise
     */
    public boolean addCards(String handString) {
    	int l = handString.length();
    	int numOfCards = l / 2;
    	if(l % 2 != 0 || numOfCards > (MAX_CARDS - cardIndex)) {
    		return false;
    	} else {
    		for(int i = 0; i < numOfCards; i++) {
    			addCard(new zCard(handString.substring(2 * i, 2 * i + 2)));
    		}
    		return true;
    	}
    }
    
    public String toString() {
    	String string = new String();
    	for(int i = 0; i < cards.length; i++) {
    		string += (new zCard(cards[i])).toString();
    	}
    	return string;
    }
    
    /**
     * Given a board, cache all possible two card combinations of hand ranks, so
     * that lightening fast hand comparisons may be done later.
     */
    public static int[][] updateBoardRanks(String boardCards) {
    	zHand board = new zHand(boardCards);
    	int[][] rc = new int[52][52];
    	zDeck d = new zDeck();
    	d.removeCards(boardCards);
    	for (int i = 0; i < zCard.NUM_CARDS - 1; i++) {
    		zCard c1 = d.getCard(i);
    		if(c1 == null) continue;
    		board.addCard(c1);
    		for(int j = i + 1; j < zCard.NUM_CARDS; j++) {
    			zCard c2 = d.getCard(j);
    			if(c2 == null) continue;
    			board.addCard(c2);
    			int ind1 = c1.getIndex();
    			int ind2 = c2.getIndex();
    			rc[ind1][ind2] = rc[ind2][ind1] = rankHand(board);
    		}	
    	}
    	setRankCards(rc);
    	return rc;
    }
    
	/**
	 * Given my hole cards and the board cards so far calculate the
	 * hand strength
	 * 
	 * @param holeCards the cards in my hand
	 * @param boardCards community cards
	 * @return hand strength in [0,1]
	 */
    public static double handStrength(String holeCards, String boardCards) {
    	double ahead = 0;
    	double tied = 0;
    	double behind = 0;
    	// Rank my hand based on hole and board cards
    	zHand myHand = new zHand(holeCards + boardCards);
    	int rank = myHand.rankHand();
    	zDeck deck = new zDeck();
    	deck.removeCards(holeCards);
    	deck.removeCards(boardCards);
    	for(int i = 0; i < zCard.NUM_CARDS - 1; i++) {
    		zCard oppHoleCard1 = deck.getCard(i);
    		if(oppHoleCard1 == null) continue;
    		for(int j = i + 1; j < zCard.NUM_CARDS; j++) {
    			zCard oppHoleCard2 = deck.getCard(j);
        		if(oppHoleCard2 == null) continue;
        		zHand oppHand = new zHand();
        		oppHand.addCards(boardCards);
        		oppHand.addCard(oppHoleCard1);
        		oppHand.addCard(oppHoleCard2);
        		int oppRank = oppHand.rankHand();
            	if(rank > oppRank) ahead++;
            	else if(rank == oppRank) tied++;
            	else behind++;
    		}
    	}
    	return (ahead + (tied / 2)) / (ahead + tied + behind);
    }
    
    public static double[] handPotential(String holeCards, 
    									 String boardCards, 
    									 int ahead) {
    	StringBuilder sb, sbOpp;
    	zCard first, second;
    	int i, j, oppRank, index, myRank, oppBest, 
    		ourBest;
    	int[][] hp = new int[NUM_REL_RANKS][NUM_REL_RANKS];
    	int[] hpTotal = new int[NUM_REL_RANKS];
    	double[] potentials = new double[NUM_POTENTIALS];
    	sb = new StringBuilder(boardCards);
    	sbOpp = new StringBuilder(boardCards);
    	sb.append(holeCards);
    	zDeck deck = new zDeck();
    	deck.removeCards(holeCards);
    	deck.removeCards(boardCards);
    	// Rank my hand
    	zHand myHand = new zHand(sb.toString());
    	myRank = myHand.rankHand();
    	for(i = 0; i < zCard.NUM_CARDS - 1; i++) {
    		zCard oppHoleCard1 = deck.getCard(i);
    		if(oppHoleCard1 == null) continue;
    		deck.remove(oppHoleCard1);
    		sbOpp.append(oppHoleCard1);
    		for(j = i + 1; j < zCard.NUM_CARDS; j++) {
    			zCard oppHoleCard2 = deck.getCard(j);
        		if(oppHoleCard2 == null) continue;
        		deck.remove(oppHoleCard2);
        		sbOpp.append(oppHoleCard2);
        		// Rank opponent's hand
        		zHand oppHand = new zHand(sb.toString());
        		oppRank = oppHand.rankHand();
            	if(myRank > oppRank) index = BETTER;
            	else if(myRank == oppRank) index = TIED;
            	else index = WORSE;
            	// Simulate cards ahead
            	for(int t = 0; t < zCard.NUM_CARDS + 1 - ahead; t++) {
            		first = deck.getCard(t);
            		if(first == null) continue;
            		deck.remove(first);
        			sb.append(first.toString());
        			sbOpp.append(first.toString());
            		if(ahead == 1) {
            			zHand myExpHand = new zHand(sb.toString());
            			ourBest = myExpHand.rankHand();
            			zHand oppExpHand = new zHand(sbOpp.toString());
            			oppBest = oppExpHand.rankHand();
            			if(ourBest > oppBest) hp[index][BETTER]++;
            			else if(ourBest == oppBest) hp[index][TIED]++;
            			else hp[index][WORSE]++;
            			hpTotal[index]++;
            		} else if(ahead == 2){
	            		for(int r = t+1; r < zCard.NUM_CARDS; r++) {
	            			second = deck.getCard(r);
	            			if(second == null) continue;
	            			sb.append(second.toString());
	            			sbOpp.append(second.toString());
	            			zHand myExpHand = new zHand(sb.toString());
	            			ourBest = myExpHand.rankHand();
	            			zHand oppExpHand = new zHand(sbOpp.toString());
	            			oppBest = oppExpHand.rankHand();
	            			if(ourBest > oppBest) hp[index][BETTER]++;
	            			else if(ourBest == oppBest) hp[index][TIED]++;
	            			else hp[index][WORSE]++;
	            			hpTotal[index]++;
	            			sb.delete(12, 14);
	            			sbOpp.delete(12, 14);
	            		}
            		}
            		sb.delete(10, 12);
            		sbOpp.delete(10, 12);
            		deck.putBack(first);
            	}
            	sbOpp.delete(8, 10);
            	deck.putBack(oppHoleCard2);
    		}
    		sbOpp.delete(6, 8);
    		deck.putBack(oppHoleCard1);
    	}
    	
    	potentials[POSITIVE] = (hp[WORSE][BETTER] + 
    						   (hp[WORSE][TIED]/2.0) + 
    						   (hp[TIED][BETTER]/2.0)) / 
    						   (hpTotal[WORSE] + (hpTotal[TIED] / 2.0));
    	
    	potentials[NEGATIVE] = (hp[BETTER][WORSE] + 
			    			   (hp[TIED][WORSE]/2.0) + 
			    			   (hp[BETTER][TIED]/2.0)) / 
			    			   (hpTotal[BETTER] + (hpTotal[TIED] / 2.0));
    	return potentials;
    }

    /**
     * Name the hand
     * 
     * @param rank the rank of the hand
     * @return a String with the name of the hand
     */
    public final static String nameOfHand(int rank){
    	int majorHand = rank / SHIFT;
    	switch(majorHand) {
    	case STRAIGHT_FLUSH: return "Straight Flush";
    	case FOUR: return "Four of a Kind";
    	case FULL_HOUSE: return "Full House";
    	case FLUSH: return "Flush"; 
    	case STRAIGHT: return "Straight"; 
    	case TRIPS: return "Three of a Kind"; 
    	case TWO_PAIRS: return "Two Pairs"; 
    	case PAIR: return "Pair"; 
    	case HIGH_CARD: return "High Card"; 
    	default: return "Bad Card"; 
    	}
    }
    
    public final static int rankHand(zHand hand) {
    	boolean[] hands = new boolean[NUM_HANDS];
    	boolean foundHand = false;
    	int[] ranks = new int[zCard.NUM_RANKS];
    	int[] suits = new int[zCard.NUM_SUITS];
    	int[] kickers = {zCard.BAD_CARD, zCard.BAD_CARD, zCard.BAD_CARD, 
    					 zCard.BAD_CARD, zCard.BAD_CARD};

    	// Parse with one pass and msg ranks and suits registers
    	for(int i = 0; i < hand.getNumOfCards(); i++) {
    		int index = hand.getCardIndex(i);
    		ranks[zCard.getRank(index)]++;
    		suits[zCard.getSuit(index)]++;
    	}

    	// Check for flush
    	int flushSuit = -1;
    	for(int i = 0; i < zCard.NUM_SUITS; i++) {
    		if(suits[i] >= 5) {
    			hands[FLUSH] = true;
    			foundHand = true;
    			flushSuit = i;
    			break;
    		}
    	}
    	if(hands[FLUSH]) {
    		// Add kickers
    		for(int i = 0; i < hand.getNumOfCards(); i++) {
    			int index = hand.getCardIndex(i);
    			int cardRank = zCard.getRank(index);
    			int cardSuit = zCard.getSuit(index);
    			if(cardSuit == flushSuit) {
    				for(int j = 0; j < kickers.length; j++) {
    					if(kickers[j] > cardRank) {
    						continue;
    					} else {
    						// Shift
    						for(int k = (kickers.length-2); k >= j; k--) {
    							kickers[k + 1] = kickers[k];
    						}
    						kickers[j] = cardRank;
    						break;
    					}
    				}
    			}
    		}
    	}

    	// Check for straight
    	int consecutive = (ranks[zCard.ACE] > 0) ? 1 : 0;
    	int highestRank = -1;
    	for(int i = 0; i < zCard.NUM_RANKS; i++) {
    		if(ranks[i] > 0) {
    			consecutive++;
    			if(consecutive >= 5) {
    				hands[STRAIGHT] = true;
    				foundHand = true;
    				highestRank = i;
    			}
    		} else {
    			consecutive = 0;
    		}
    	}
	
    	// Check for straight-flush
    	if(hands[FLUSH] && hands[STRAIGHT]) {
    		int counter = 0;
    		for(int i = 0; i < hand.getNumOfCards(); i++) {
    			int index = hand.getCardIndex(i);
    			int cardRank = zCard.getRank(index);
    			int cardSuit = zCard.getSuit(index);
    			if(cardSuit == flushSuit) {
    				if(highestRank == 3 && cardRank == zCard.ACE) {
    					counter++;
    					continue;
    				}
    				if((cardRank <= highestRank) && cardRank >= (highestRank - 4)) {
    					counter++;
    				}		    
    			}
    		}
    		if(counter >= 5) {
    			hands[STRAIGHT_FLUSH] = true;
    			foundHand = true;
    		}
    	}

    	// Check for full house, four cards, three cards, two pairs or one pair
    	int pairOne = -1;
    	int pairTwo = -1;
    	int trips = -1;
    	int quads = -1;
    	int kickerOne = -1;
    	int kickerTwo = -1;
    	int kickerThree = -1;
    	for(int i = 0; i < zCard.NUM_RANKS; i++) {
    		if(ranks[i] > 1) {
    			if(ranks[i] == 4) {
    				hands[FOUR] = true;
    				foundHand = true;
    				quads = i;
    				break;
    			} else if(ranks[i] == 3) {		    
    				trips = i;
    			} else {
    				if(pairOne == -1) {
    					pairOne = i;
    				} else {
    					pairTwo = pairOne;
    					pairOne = i;
    				}
    			}
    		} else if(ranks[i] == 1) {
    			if(kickerOne < i) {
    				kickerThree = kickerTwo;
    				kickerTwo = kickerOne;
    				kickerOne = i;
    			} else if(kickerTwo < i) {
    				kickerThree = kickerTwo;
    				kickerTwo = i;
    			} else if(kickerThree < i) {
    				kickerThree = i;
    			}
    		}
    	}
    	if(trips != -1 && pairOne != -1) {
    		hands[FULL_HOUSE] = true;
    		foundHand = true;
    	} else if(trips != -1 && pairOne == -1) {
    		hands[TRIPS] = true;
    		foundHand = true;
    	} else if(pairOne != -1 && pairTwo != -1) {
    		hands[TWO_PAIRS] = true;
    		foundHand = true;
    	} else if(pairOne != -1) {
    		hands[PAIR] = true;
    		foundHand = true;
    	}

    	if(!foundHand) {
    		// High card
    		hands[HIGH_CARD] = true;	    
    		// Add kickers
    		for(int i = 0; i < hand.getNumOfCards(); i++) {
    			int index = hand.getCardIndex(i);
    			int cardRank = zCard.getRank(index);
    			for(int j = 0; j < kickers.length; j++) {
    				if(kickers[j] >= cardRank) {
    					continue;
    				} else {
    					// Shift
    					for(int k = (kickers.length - 2); k >= j; k--) {
    						kickers[k + 1] = kickers[k];
    					}
    					kickers[j] = cardRank;
    					break;
    				}
    			}
    		}
    	}
	
		// Calculate rank
		int rank = -1;
		if(hands[STRAIGHT_FLUSH]) { // STRAIGHT FLUSH
		    rank = STRAIGHT_FLUSH * SHIFT;
		    rank += highestRank;
		} else if(hands[FOUR]) { // FOUR OF A KIND
		    rank = FOUR * SHIFT;
		    rank += quads;
		} else if(hands[FULL_HOUSE]) { // FULL HOUSE
		    rank = FULL_HOUSE * SHIFT;
		    rank += trips * zCard.NUM_RANKS;
		    rank += pairOne;
		} else if(hands[FLUSH]) { // FLUSH
		    rank = FLUSH * SHIFT;
		    int power = 1;
		    for(int i = (kickers.length-1); i >= 0; i--) {
		    	rank += kickers[i] * power;
		    	power *= zCard.NUM_RANKS;
		    }
		} else if(hands[STRAIGHT]) { //STRAIGHT
		    rank = STRAIGHT * SHIFT;
		    rank += highestRank;
		} else if(hands[TRIPS]) { // TRIPS
		    rank = TRIPS * SHIFT;
		    rank += trips * zCard.NUM_RANKS * zCard.NUM_RANKS;
		    rank += kickerOne * zCard.NUM_RANKS;
		    rank += kickerTwo;
		} else if(hands[TWO_PAIRS]) { // TWO PAIRS
		    rank = TWO_PAIRS * SHIFT;
		    rank += pairOne * zCard.NUM_RANKS * zCard.NUM_RANKS;
		    rank += pairTwo * zCard.NUM_RANKS;
		    rank += kickerOne;
		} else if(hands[PAIR]) { // ONE PAIR
		    rank = PAIR * SHIFT;
		    rank += pairOne * zCard.NUM_RANKS * zCard.NUM_RANKS * zCard.NUM_RANKS;
		    rank += kickerOne * zCard.NUM_RANKS * zCard.NUM_RANKS;
		    rank += kickerTwo * zCard.NUM_RANKS;
		    rank += kickerThree;
		} else if(hands[HIGH_CARD]) { // HIGH CARD
		    rank = HIGH_CARD * SHIFT;
		    int power = 1;
		    for(int i = (kickers.length-1); i >= 0; i--) {
		    	rank += kickers[i] * power;
		    	power *= zCard.NUM_RANKS;
		    }
		}
		return rank;
    }
    
    public static void main(String[] args) {
    	zHand handOne = new zHand();
    	handOne.addCards("7c8sAhKh7d2hQh");
    	zHand handTwo = new zHand("Ad2sKcQd4c5d");
    	zHand handThree= new zHand("4hJc6c3h5h");
    	System.out.println("Rank " + handOne.toString() + " " + handOne.rankHand());
    	System.out.println("Rank " + handTwo.toString() + " " + handTwo.rankHand());
    	System.out.println("Rank " + handThree.toString() + " " + handThree.rankHand());
    	System.out.println("Name " + handOne.toString() + " " + nameOfHand(handOne.rankHand()));
    	System.out.println("Name " + handTwo.toString() + " " + nameOfHand(handTwo.rankHand()));
    	System.out.println("Name " + handThree.toString() + " " + nameOfHand(handThree.rankHand()));
    	System.out.println("Hand Strength of " + "QcJd-Js8h6d : " +  
    						handStrength("QcJd", "Js8h6d"));
    	System.out.println("Hand Strength of " + "KcKs-8h6c4h : " +  
				handStrength("KcKs", "8h6c4h"));
    	System.out.println("Hand Strength of " + "7h9h-8h6c4h : " +  
				handStrength("7h9h", "8h6c4h"));
    	System.out.println("Hand Strength of " + "4hJc-6c3h5h : " +  
				handStrength("4hJc", "6c3h5h"));
    	System.out.println("Positive Potential of " + "4hJc-6c3h5h : " +  
				handPotential("4hJc", "6c3h5h", 2)[0]);
    	System.out.println("Positive Potential of " + "7h9h-8h6c4h : " +  
				handPotential("7h9h", "8h6c4h", 2)[0]);
    	// Benchmark hand potential
    	long sum = 0;
    	int runs = 100;
    	for(int i = 0; i < runs; i++) {
    		zDeck deck = new zDeck();
    		deck.shuffle();
    		String holeCards = deck.nextCard().toString() + 
    				           deck.nextCard().toString();
    		String boardCards = deck.nextCard().toString() + 
    							deck.nextCard().toString() +
			           			deck.nextCard().toString();
    		long start = System.currentTimeMillis();
    		handPotential(holeCards, boardCards, 2);
    		long end = System.currentTimeMillis();
    		sum += end - start;
    	}
    	System.out.println("Avg Performance (sec): " + ((sum / (double)runs) / 1000));
    }

	public static int[][] getRankCards() {
		return rankCards;
	}

	public static void setRankCards(int[][] rankCards) {
		zHand.rankCards = rankCards;
	}
    
}