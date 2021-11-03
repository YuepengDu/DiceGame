package client;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import validate.Validator;
import view.GameEngineCallbackImpl;

public class MyTestClient {

	private Testcase[] testcases;

	public static void main(String[] args) {
		
		new MyTestClient().runTestCases();
		
		final GameEngine gameEngine = new GameEngineImpl();
		Validator.validate(true);
		
		Player[] players = new Player[] {new SimplePlayer("1", "The Roller", 5000), new SimplePlayer("2", "The Loser", 500)};
		
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		
		for(Player player : players)
			gameEngine.addPlayer(player);
		
		for(Player player : gameEngine.getAllPlayers()) {
			gameEngine.placeBet(player, 200);
			gameEngine.rollPlayer(player, 100, 1000, 100, 50, 500, 50);
		}
		
		gameEngine.rollHouse(100, 1000, 200, 50, 500, 25);
		
		         
	}
	
	public void runTestCases() {
		
		testcases = new Testcase[]{ new PlacebetTestCase(), new    PlayerWithSameIDTestCase(), new SetPointTestCase(), new RemovePlayerTestCase()};
		//Run all testcases, if no case fails, will run the dice game.
		for(Testcase testcase : testcases) {
			
			if(!testcase.runTestcase()) {
				System.out.println("One test case fails");
				break;
			}
		}
		
	}

}
