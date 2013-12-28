package gameMech;

/**
 * User: maxim
 * Date: 19.12.13
 * Time: 22:40
 */
public class GameSessionFactory {
	private static Integer nextGameSessionNumber = 0;

	public GameSession getGameSession(){
		nextGameSessionNumber++;
		return new GameSession();
	}

	public static 	Integer getLastNumber(){
		return nextGameSessionNumber-1;
	}
}
