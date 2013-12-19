package frontend;

import base.Address;
import base.Frontend;
import gameMech.UserSession;
import messageSystem.MsgToFrontend;

/**
 * User: maxim
 * Date: 19.12.13
 * Time: 22:50
 */
public class MsgSetGameSessionId extends MsgToFrontend {
	Long userId;
	Integer gameSessionId;
	public MsgSetGameSessionId(Address from, Address to, Long userId, Integer gameSessionId){
		super(from, to);
		this.userId = userId;
		this.gameSessionId = gameSessionId;
	}

	public void exec(Frontend frontend){
		UserSession userSession = frontend.getUserSession(userId);
		userSession.setGameSessionId(gameSessionId);
	}
}
