package gameMech;

import base.Address;
import base.AddressService;
import frontend.GameSessionReplica;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 12.10.13
 * Time: 20:12
 * To change this template use File | Settings | File Templates.
 */
public class UserSession {
	Address accountService;  //адрес аккаунт-сервиса (Ваш Кэп)
	Address gameMech; //TODO push in constructor

	private String name; // Имя юзера
	private String sessionId;
	private Long userId;
	//угол здесь, координаты
	//чендж отправляется

	//private Position position;
	private Integer rotation;
	private Integer health;

	private Boolean left; //TODO push in constructor
	private Integer gameSessionId;
	private GameSessionReplica gameSessionReplica;

	public UserSession(String sessionId, String name, AddressService addressService){
		this.sessionId = sessionId;
		this.name = name;
		this.accountService = addressService.getAccountService();
	}

	public Boolean getLeft(){
		return left;
	}

	public Integer getGameSessionId(){
		return gameSessionId;
	}

	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Address getAccountService(){
		return accountService;
	}

	public Address getGameMech(){
		return gameMech;
	}

	public Long getUserId(){
		return userId;
	}

	public String getUsername(){
		return name;
	}


//	public void move(Direction direction){
//		this.position.move(direction);
//	}
//	public Position getPosition(){
//		return position;
//	}

	public void setRotate(Boolean left, Integer rotation){
		gameSessionReplica.setRotate(left, rotation);
	}

	public void setGameSession(GameSessionReplica gameSessionReplica){
		this.gameSessionReplica = gameSessionReplica;
	}

	public String getJSON(){
		String result = "{";
		result += "L:{";
			result += "X:" + gameSessionReplica.getPositionL().getX() + ",";
			result += "Y:" + gameSessionReplica.getPositionL().getY() + ",";
			result += "R:" + gameSessionReplica.getRotationL() + "},";
		result += "R:{";
			result += "X:" + gameSessionReplica.getPositionR().getX() + ",";
			result += "Y:" + gameSessionReplica.getPositionR().getY() + ",";
			result += "R:" + gameSessionReplica.getRotationR() + "}";
		result += "}";

		return result;
	}

}
