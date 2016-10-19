package main.Classes.ExternalClasses.Chen;

/*
This chen system has been modified and sets the cards into several "grades".
So the actions can run on an equation.
A*  = AA, KK
A   = QQ, JJ
B   = TT  AK.s/o
C   = AQ, Mid pairs, high drawing hands.
D   = Low pairds and low drawing hands
E   = Junk. Steal blinds and run.
*/

public class Chen {

	public final static double CHEN_MAX = 20.0d;
	public final static double CHEN_MIN = -1.5d;

	public static double ChenFormula(String holeCards) {

		zCard c1 = new zCard(holeCards.substring(0, 2));
		zCard c2 = new zCard(holeCards.substring(2, 4));
		int rank1 = c1.getRank();
		int rank2 = c2.getRank();
		int suit1 = c1.getSuit();
		int suit2 = c2.getSuit();
		int maxRank = Math.max(rank1, rank2);
		double strength = 0;

		// High card
		if(maxRank == zCard.ACE) strength += 20;
		else if(maxRank == zCard.KING) strength += 20;
		else if(maxRank == zCard.QUEEN) strength += 20;
		else if(maxRank == zCard.JACK) strength += 20;
		else if(maxRank == zCard.TEN) strength += 20;
		//Low card
		else if(maxRank < zCard.TEN) strength -= 25;

		//else strength += ((maxRank + 1) / 2.0);
		// Pairs
		if(rank1 == rank2)
		{

			if(rank1 <= zCard.ACE)
			{
				strength=+99;
			}

			if(rank1 <= zCard.KING)
			{
				strength=85;
			}

		}

		// Suited
		if(suit1 == suit2) strength += 30;
		// Non-suited
		if(!(suit1 == suit2)) strength -= 30;
		if (rank1 == rank2 && suit1 ==suit2) strength = -1; // non-existant omega

		// Closeness
		int gap = maxRank - Math.min(rank1, rank2) - 1;
		switch(gap) {
			case -1: break;
			case 0: strength -= 0; break;
			case 1: strength -= 0; break;
			case 2: strength -= 0; break;
			case 3: strength -= 0; break;
			default: strength = -10; break;
		}
		//Modifiers
		if(gap == 0 && maxRank == zCard.ACE) strength=55;
		if(gap >= 4 && gap <= 11 && maxRank == zCard.ACE&& suit1==suit2) strength +=15;
		if(gap == 3  && maxRank == zCard.KING) strength -=10;
		if(gap >= 2 && gap <=3  && maxRank == zCard.QUEEN) strength -=10;
		if(gap >= 1 && gap <=3  && maxRank == zCard.JACK) strength -=10;
		if(gap >= 0 && gap <=3  && maxRank == zCard.TEN) strength -=10;

		return strength;
	}

	public static void main(String[] args) {

		System.out.println("Testing Chen's formula for hole cards.");

		String ex1 = "AsKs";
		String ex2 = "8sTs";
		String ex3 = "5h5d";
		String ex4 = "QHQS";
		String ex5 = "5h5d";

		System.out.println(ex1 + "  " + ChenFormula(ex1));
		System.out.println(ex2 + "  " + ChenFormula(ex2));
		System.out.println(ex3 + "  " + ChenFormula(ex3));
		System.out.println(ex4 + "  " + ChenFormula(ex4));
		System.out.println(ex5 + "  " + ChenFormula(ex5));
	}
}
