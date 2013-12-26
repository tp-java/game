package gameMech;

import base.*;
import frontend.MsgSetGameReady;
import frontend.MsgSetGameSessionId;
import frontend.MsgSetLeft;
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
	private PriorityQueue<MsgSetGameReady> usersQueue = new PriorityQueue<>();
//	private ArrayList<Integer> gameSessionsIds = new ArrayList<>();


//	private Map<Long,Position> userIdToPosition;
//	private Map<Long,Direction> userIdToDirection;

	private GameSessionFactory gameSessionFactory;

	public GameMech(MessageSystem ms){
		this.ms = (MessageSystemImpl)ms;
		this.address = new Address();
		ms.addService(this);
		ms.getAddresService().setGameMech(address);
//		userIdToPosition = new HashMap<>();
//		userIdToDirection = new HashMap<>();
		// класс gameSession - обсчет текущего положения userIdToGameSession

		gameSessionFactory = new GameSessionFactory();
	}

	public void run(){
		while (true){
			//обработка сообщений id, изменения, может ли применить измения
			//расчет следующего кадра - применение физики, самолеты сдвинулись на 1 шаг
			//отправка реплики на frontend - 1 msg для changes
			//for (int i=gameSessionsIds.)
			ms.execForAbonent(this);
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

	/* - теперь в plane
	public static Integer getDegree(Direction direction){
		//TODO: пересчет direction в градусы для фронтенда(!!!)
		return null;
	} */

	public MessageSystem getMessageSystem(){
		return ms;
	}

	public void setToQueue(Long userId, Address gameMech, Address frontendAddr, Frontend frontend){
		System.out.println("GM.setToQueue is working. userId= " + userId);
		if (usersQueue.size() == 0){
			System.out.println("usersQueue.size==0");
			usersQueue.add(new MsgSetGameReady(gameMech, frontendAddr, userId));
			System.out.println("msg added to usersQueue. MsgSetGameReady(userId), userId= " + userId);
			ms.sendMessage(new MsgSetLeft(gameMech, frontendAddr, userId, true));
			System.out.println("MsgSetLeft has been sent. MsgSetLeft(userId, true), userId= " + userId);
			GameSession gameSession = gameSessionFactory.getGameSession();
			System.out.println("gameSession has been created");
			Integer gameSessionId = gameSessionFactory.getLastNumber();
			System.out.println("gameSessionId= " + gameSessionId);
			gameSessionIdToGameSession.put(gameSessionId, gameSession);
			frontend.setGameSessionReplica(gameSessionId, gameSession.getReplica());
			System.out.println("put()'ed gsIdToGS ");
			ms.sendMessage(new MsgSetGameSessionId(gameMech, frontendAddr, userId, gameSessionId));
			System.out.println("MsgSetGameSessionId has been sent. userId= " + userId + "gameSessionId= " + gameSessionId);
		} else {
			System.out.println("usersQueue.size!=0");
			ms.sendMessage(new MsgSetLeft(gameMech, frontendAddr, userId, false));
			System.out.println("MsgSetLeft(userId, false) has been sent. userId= " + userId);
			System.out.println("usersQueue.size() before remove()" + usersQueue.size());
			MsgSetGameReady msg = usersQueue.remove();
			System.out.println("usersQueue.size() after remove()" + usersQueue.size());

			ms.sendMessage(msg);
			System.out.println("MsgSetGameReady hes been sent. userId = " + msg.getUserId());

			Long userId1 = msg.getUserId();
			System.out.println("Long userId1 = msg.getUserId(); userId1 = " + userId1);
			Integer gameSessionId = frontend.getUserSession(userId1).getGameSessionId();
			System.out.println("frontend.getUserSession(userId1).getGameSessionId(); gameSessionId = " + gameSessionId);
			ms.sendMessage(new MsgSetGameSessionId(gameMech, frontendAddr, userId, gameSessionId));
			System.out.println("ms.sendMessage(new MsgSetGameSessionId(userId, gameSessionId)); userId= " + userId + "gameSessionId = " + gameSessionId);
			ms.sendMessage(new MsgSetGameReady(gameMech, frontendAddr, userId));
			System.out.println("ms.sendMessage(new MsgSetGameReady(userId)); userId" + userId);
		}
	}

}
