package client;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class PlayerWithSameIDTestCase extends Testcase {

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
		//Run test case, if there are more than 1 player with the same ID, return false
		if(!testAddPlayerWithSameID()) {
			//print an error message
			System.out.println("Players with same ID exist");
			return false;
		}
		
		return true;
	}
	
	private boolean testAddPlayerWithSameID() {
		boolean testResult = true;
		before();
		//Generate 2 players with the same id
		Player player1 = new SimplePlayer("1", "The Shy", 10);
		Player player2 = new SimplePlayer("1", "Nuguri", 10);
		gameEngineImpl.addPlayer(player1);
		gameEngineImpl.addPlayer(player2);
		after();
		return testResult;
	}

}
