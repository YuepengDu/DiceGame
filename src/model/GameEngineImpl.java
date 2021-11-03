package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine{

	private ArrayList<GameEngineCallback> callbacks;
	private ArrayList<Player> players;
	private DicePair houseDicePair;
	private Random r;
	
	public GameEngineImpl() {
		callbacks = new ArrayList<GameEngineCallback>();
		players = new ArrayList<Player>();
		houseDicePair = new DicePairImpl();
		r = new Random();
	}
	
	
	@Override
	public void rollPlayer(Player player, int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2,
			int finalDelay2, int delayIncrement2) {
		
//		 1. dice are initialised at their random starting face
//		 2. for each die start at initialDelay then increment the delays for each die on each iteration
			
			//the logic for rolling dices
			for(;initialDelay1<=finalDelay1;initialDelay1+=delayIncrement1){
				DicePair dicePair = new DicePairImpl();
				for(GameEngineCallback callback : callbacks){
					//3. call GameEngineCallback.playerDieUpdate(...) for each die separately
					callback.playerDieUpdate(player, dicePair.getDie1(), this);
					callback.playerDieUpdate(player, dicePair.getDie2(), this);
				}
				
				try {
					Thread.sleep(initialDelay1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//4. continue until both delays >= finalDelay 
			}

			//5. call GameEngineCallback.playerResult(...) to finish
			DicePair result = new DicePairImpl();
			for(GameEngineCallback callback : callbacks){
				player.setResult(result);
				callback.playerResult(player, result, this);
			}
		
	}

	@Override
	public void rollHouse(int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2, int finalDelay2,
			int delayIncrement2) {
//		 1. dice are initialised at their random starting face
//		 2. for each die start at initialDelay then increment the delays for each die on each iteration
			
			//the logic for rolling dices
			for(;initialDelay1<=finalDelay1;initialDelay1+=delayIncrement1){
				DicePair dicePair = new DicePairImpl();
				for(GameEngineCallback callback : callbacks){
					//3. call GameEngineCallback.playerDieUpdate(...) for each die separately
					callback.houseDieUpdate(dicePair.getDie1(), this);
					callback.houseDieUpdate(dicePair.getDie2(), this);
				}
				
				try {
					Thread.sleep(initialDelay1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//4. continue until both delays >= finalDelay 
			}

			//5. call GameEngineCallback.playerResult(...) to finish			
			//6.update each player's points
			houseDicePair = new DicePairImpl();
			for(Player player : players) {
				this.applyWinLoss(player, houseDicePair);
			}
			for(GameEngineCallback callback : callbacks){
				callback.houseResult(houseDicePair, this);
			}
			//Reset bet after print final result
			for(Player player : players) {
				player.resetBet();	
			}
			
	}
	

	@Override
	public void applyWinLoss(Player player, DicePair houseResult) {
		int reuslt = player.getResult().compareTo(houseResult);
		if(reuslt == 0) {
			//get a draw 
			player.setPoints(player.getPoints() + player.getBet());
		}else if(reuslt > 0) {
			//win this game
			player.setPoints(player.getPoints()+player.getBet()*2);
		}else {
			//do nothing ,lost the bet
		}
	}

	@Override
	public void addPlayer(Player player) {
		
		if(player == null) {
			return;
		}
		//1.to remove the player with same ID in list
		players.remove(player);
		//2.then add the new player into list
		players.add(player);
	}

	@Override
	public Player getPlayer(String id) {
		
		Player player = null;
		for(Player p : players){
			if(p.getPlayerId().equals(id)){
				player = p;
				break;
			}
		}
		return player;
	}

	@Override
	public boolean removePlayer(Player player) {
		
		return players.remove(player);
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		
		if(player == null) {
			return false;
		}
		return player.setBet(bet);
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		
		if(gameEngineCallback != null) {
			callbacks.add(gameEngineCallback);
		}
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		
		return callbacks.remove(gameEngineCallback);
	}

	@Override
	public Collection<Player> getAllPlayers() {
		
		Collection<Player> playersCopy = new ArrayList<Player>();
		playersCopy.addAll(players);
		return playersCopy;
	}


}
