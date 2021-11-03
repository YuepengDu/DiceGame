package client;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class PlacebetTestCase extends Testcase {
	
	private GameEngine gameEngineImpl;
	
	@Override
	public void before() {
		
		gameEngineImpl = new GameEngineImpl();
	}

	@Override
	public void after() {
		//reset gameEngineImpl after use.
		gameEngineImpl = null;
	}

	@Override
	public boolean runTestcase() {
		//run both test cases, if one of them cause an error, return false
		if(!testLargerBet()) {
			//print an error message
			System.out.println("No enough points. ");
			return false;
		}else if(!testInvalidBet()) {
			//print an error message
			System.out.println("Invalid bet input.");
			return false;
		}
		
		return true;
	}
	
	
	private boolean testLargerBet(){
		boolean testResult = true;
		before();
		//Generate a player
		Player player = new SimplePlayer("1", "The Shy", 10);
		//Test place a bet that is greater than the player's points
		gameEngineImpl.placeBet(player, 9999);
		after();
		return testResult;
	}
	
	private boolean testInvalidBet(){
		boolean testResult = true;
		before();
		//Generate a player
		Player player = new SimplePlayer("1", "The Bug", 10);
		//Test place a negative bet
		gameEngineImpl.placeBet(player, -1);
		after();
		return testResult;
	}

}
