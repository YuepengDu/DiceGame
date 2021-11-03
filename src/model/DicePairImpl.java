package model;

import java.util.Random;

import model.interfaces.DicePair ;
import model.interfaces.Die;

public class DicePairImpl implements DicePair{
	
	public static final int NUM_FACE = 6;
	private Die die1;
	private Die die2;
	private Random random = new Random();
	
	public DicePairImpl() {
		die1 = new DieImpl(generateRandomValue(NUM_FACE),NUM_FACE);
		die2 = new DieImpl(generateRandomValue(NUM_FACE),NUM_FACE);
		
	}

	@Override
	public Die getDie1() {
		return die1;
	}

	@Override
	public Die getDie2() {
		return die2;
	}

	@Override
	public int getTotal() {
		return die1.getValue() + die2.getValue();
	}

	@Override
	public boolean equals(DicePair dicePair) {
		if(dicePair == null) {
			return false;
		}
		return (dicePair.getDie1().equals(this.die1) &&
				dicePair.getDie2().equals(this.die2));
	}
	@Override
	public boolean equals(Object dicePair) {
		return !(dicePair instanceof DicePair) ? false : this.equals((DicePair) dicePair);
		
	}

	@Override
	public int compareTo(DicePair dicePair) {
		/**
		    * Used to compare the totals of two DicePairs for {@literal <}, {@literal >} or equality
		    * See API docs of java.lang.Comparable{@literal <}T{@literal >} for details
		    */
		return this.getTotal() - dicePair.getTotal();
	}
	
	private int generateRandomValue(int numFace) {
		return random.nextInt(NUM_FACE) + 1;
	}
	
	@Override
	public int hashCode() {
		return die1.getValue()*10 + die2.getValue();
	}
	
	@Override
	public String toString() {
		return  String.format("Dice 1: %s,  Dice 2: %s .. Total: %d", die1, die2,this.getTotal());  
	}
	
	

}
