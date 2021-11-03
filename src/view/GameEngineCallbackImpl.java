package view;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
   private static final Logger logger = Logger.getLogger(GameEngineCallback.class.getName());

   public GameEngineCallbackImpl()
   {
      // FINE shows rolling output, INFO only shows result
      logger.setLevel(Level.FINE);
   }

   @Override
   public void playerDieUpdate(Player player, Die die, GameEngine gameEngine)
   {   
      // intermediate results logged at Level.FINE
      logger.log(Level.INFO, String.format("%s die %d rolled to %s", player.getPlayerName(),die.getNumber(),die.toString()));
      // TODO: complete this method to log results
   }

   @Override
   public void playerResult(Player player, DicePair result, GameEngine gameEngine)
   {
      // final results logged at Level.INFO
      logger.log(Level.INFO, String.format("%s *RESULT*: Dice 1: %s,  Dice 2: %s .. Total: %d ", 
    		  player.getPlayerName(), result.getDie1().toString(), result.getDie2().toString(), result.getTotal()));
      // TODO: complete this method to log results
   }

	@Override
	public void houseDieUpdate(Die die, GameEngine gameEngine) 
	{
		//intermediate house die results logged at Level.INFO
		 logger.log(Level.INFO, String.format("House die %d rolled to %s", die.getNumber(),die.toString()));
	}
	
	@Override
	public void houseResult(DicePair result, GameEngine gameEngine) 
	{
		//Final house Resut logged at Level.INFO
	      logger.log(Level.INFO, String.format("House *Result*: Dice 1: %s,  Dice 2: %s, Total: %d", 
	    		  result.getDie1(), result.getDie2(),result.getTotal()));
	      String finalResult = getFinalResult(gameEngine.getAllPlayers());
	      //Each players' result logged at Level.INFO.
	      logger.log(Level.INFO,finalResult);
	      
	      
	}
	private String getFinalResult(Collection<Player> players) {
		StringBuffer finalResult = new StringBuffer("FINAL PLAYER RESULTS");
		//Log each players' result at the end
		for(Player player : players) {
			finalResult.append("\n").append(player.toString());
		}
		return finalResult.toString();
		
		
	}
	   // TODO implement rest of interface
	}
