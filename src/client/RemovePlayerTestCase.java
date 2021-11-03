package client;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class RemovePlayerTestCase extends Testcase{
	
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
		
		if(!RemovePlayer()) {
			System.out.println("Remove Player Fails");
			return false;
		}
		return true;
	}
	
	private boolean RemovePlayer() {
		boolean testResult = true;
		before();
		//Add two players 
		Player player1 = new SimplePlayer("1","The Shy", 100);
		Player player2 = new SimplePlayer("2", "Missing", 100);
		gameEngineImpl.addPlayer(player1);
		gameEngineImpl.addPlayer(player2);
		//Remove player1
		gameEngineImpl.removePlayer(player1);
		return testResult;
	}

}
