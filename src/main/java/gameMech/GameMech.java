package gameMech;

import base.Abonent;
import base.Address;
import base.Frontend;
import base.MessageSystem;
import frontend.GameSessionReplica;
import frontend.MsgSetGameSession;
import messageSystem.MessageSystemImpl;

import java.util.*;

/**
 * User: maxim
 * Date: 30.11.13
 * Time: 4:55
 */
public class GameMech implements Runnable, Abonent{
	private MessageSystemImpl ms;
	private Address address;
	private Map<Integer, GameSession> gameSessionIdToGameSession= new HashMap<>();
	private PriorityQueue<MsgSetGameSession> usersQueue = new PriorityQueue<>();
//	private ArrayList<Integer> gameSessionsIds = new ArrayList<>();


//	private Map<Long,Position> userIdToPosition;
//	private Map<Long,Direction> userIdToDirection;


	public GameMech(MessageSystem ms){
		this.ms = (MessageSystemImpl)ms;
		ms.addService(this);
//		userIdToPosition = new HashMap<>();
//		userIdToDirection = new HashMap<>();
		// класс gameSession - обсчет текущего положения userIdToGameSession
	}

	public void run(){
		while (true){
			//обработка сообщений id, изменения, может ли применить измения
			//расчет следующего кадра - применение физики, самолеты сдвинулись на 1 шаг
			//отправка реплики на frontend - 1 msg для changes
			//for (int i=gameSessionsIds.)
			try {
				Thread.sleep(10);
			} catch (Exception e){}
		}
	}
	public Address getAddress(){
		return this.address;
	}

	public void addUser(UserSession userSession){
		//userSessions.add(userSession);
//		userIdToDirection.put(userSession.getUserId(), userSession.getDirection());
//		userIdToPosition.put(userSession.getUserId(), userSession.getPosition());
	}

	//TODO: запилить сюда проверку на столкновение
	public void move(Long userId, Direction direction){
//		Position position = userIdToPosition.get(userId);
//		Direction userDirection = userIdToDirection.get(userId);
//		userDirection.sum(direction);// изменили направление движения
//		position.move(direction);
	}

	public GameSession getGameSession(Integer gameSessionId){
		return gameSessionIdToGameSession.get(gameSessionId);
	}

	public static Integer getDegree(Direction direction){
		//TODO: пересчет direction в градусы для фронтенда(!!!)
		return null;
	}

	public MessageSystem getMessageSystem(){
		return ms;
	}

}
