package client;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class SetPointTestCase extends Testcase {
	
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
		//It would return false, if the player has negative point.
		if(!testNegativePoint()) {
			System.out.println("One or more player has negative point. ");
			return false;
		}
		return true;
	}

	private boolean testNegativePoint() {
		boolean testResult = true;
		before();
		//Generate a player has negative points
		Player player = new SimplePlayer("1", "The Shy", -10);
		gameEngineImpl.addPlayer(player);
		after();
		return testResult;
	}
}
