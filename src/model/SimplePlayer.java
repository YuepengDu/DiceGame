package model;

import model.interfaces.DicePair;
import model.interfaces.Player;

public class SimplePlayer implements Player{

	private String playerName;
	private String playerId;
	private int points;
	private DicePair result;
	private int bet;
	
	public SimplePlayer(String id, String name, int points) {
		
		this.playerId = id;
		this.playerName = name;
		//If this.points is smaller than 0, then this.points = 0;
		this.points = points > 0 ? points : 0;
		
	}

	@Override
	public String getPlayerName() {
		
		return this.playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public int getPoints() {
		
		return this.points;
	}

	@Override
	public void setPoints(int points) {
		
		if(points >= 0) this.points = points;
		
	}

	@Override
	public String getPlayerId() {
		
		return this.playerId;
	}

	@Override
	public boolean setBet(int bet) {
		//If bet is smaller or equals to zero or more than points, return false;
		if(bet <= 0 || this.points < bet){
			return false;
		}
		this.points -= bet;
		this.bet = bet;
		return true;
	}

	@Override
	public int getBet() {
	
		return this.bet;
	}

	@Override
	public void resetBet() {
		
		this.bet = 0;
	}

	@Override
	public DicePair getResult() {
		
		return this.result;
	}

	@Override
	public void setResult(DicePair rollResult) {
		
		this.result = rollResult;
		
	}
	
	@Override
	public String toString() {
		
		if(this.result == null) {
			return String.format("Player: id=%d, name=%s, bet=%d, points=%d"
					, this.getPlayerId(),this.getPlayerName(),this.getBet(),this.getPoints());
		}
		return String.format("Player: id=%s, name=%s, bet=%d, points=%d, RESULT .. %s"
				, this.getPlayerId(),this.getPlayerName(),this.getBet(),this.getPoints(),this.result.toString());
	}
	
}
