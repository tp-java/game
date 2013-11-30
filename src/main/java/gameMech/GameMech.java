package gameMech;

import base.Abonent;
import base.Address;
import base.MessageSystem;
import messageSystem.MessageSystemImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * User: maxim
 * Date: 30.11.13
 * Time: 4:55
 */
public class GameMech implements Runnable, Abonent{
	private MessageSystemImpl ms;
	private Address address;
	private ArrayList<UserSession> userSessions;
	private Map<Long,Position> userIdToPosition;
	private Map<Long,Direction> userIdToDirection;

	public GameMech(MessageSystem ms){
		this.ms = (MessageSystemImpl)ms;
		userSessions = new ArrayList<UserSession>();
		userIdToPosition = new HashMap<>();
		userIdToDirection = new HashMap<>();
	}

	public void run(){

	}
	public Address getAddress(){
		return this.address;
	}

	public void addUser(UserSession userSession){
		userSessions.add(userSession);
		userIdToDirection.put(userSession.getUserId(), userSession.getDirection());
		userIdToPosition.put(userSession.getUserId(), userSession.getPosition());
	}

	//TODO: запилить сюда проверку на столкновение
	public void move(Long userId, Direction direction){
		Position position = userIdToPosition.get(userId);
		Direction userDirection = userIdToDirection.get(userId);
		userDirection.sum(direction);// изменили направление движения
		position.move(direction);
	}

}
