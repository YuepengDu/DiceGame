package model;

import model.interfaces.Die;

public class DieImpl implements Die{
	
	private int number;
	private int value;
	private int numFaces;

	public DieImpl(int value, int numFaces) throws IllegalArgumentException{
		this(1,value,numFaces);
	}
	
	public DieImpl(int number, int value, int numFaces) throws IllegalArgumentException{
		if(number < 1 || number > 3 || value < 1 || value > numFaces
				|| numFaces < 1 ) {
			throw new IllegalArgumentException();
		}
		this.number = number;
		this.value = value;
		this.numFaces = numFaces;
		
	}
	

	@Override
	public int getNumber() {
		return number;
		//returns a number between 1 - 2
	}

	@Override
	public int getValue() {
		return value;
		//returns a number between 1 - 6
	}

	@Override
	public int getNumFaces() {
		return numFaces;
		//returns the value of NUM_FACES
	}

	@Override
	public boolean equals(Die die) {
		if(die == null)	return false;
		return this.value == die.getValue() && this.numFaces == die.getNumFaces();
		}
	@Override
	public boolean equals(Object die) {
		
		return !(die instanceof Die) ? false : this.equals((Die) die);
		
	}
	@Override
	public int hashCode(){
		return this.number*10 + this.value;
		
	}
	@Override
	public String toString() {
		String str = null;
		switch(this.value) {
			case 1:
				str = "One";
				break;
			case 2:
				str = "Two";
				break;
			case 3:
				str = "Three";
				break;
			case 4:
				str = "Four";
				break;
			case 5:
				str = "Five";
				break;
			case 6:
				str = "Six";
				break;
			case 7:
				str = "Seven";
				break;
			case 8:
				str = "Eight";
				break;
			case 9:
				str = "Nine";
				break;
			default:
				str = "> Nine";
				break;
		}
		return str;
		
	}

}
