package frontend;

import base.Address;
import base.Frontend;
import gameMech.UserSession;
import messageSystem.MsgToFrontend;

/**
 * User: maxim
 * Date: 19.12.13
 * Time: 22:20
 */
public class MsgSetGameReady extends MsgToFrontend {
	Long userId;

	public MsgSetGameReady(Address from, Address to, Long userId){
		super(from, to);
		this.userId = userId;
		System.out.println("MsgSetGameReady has been created, userId = " + userId);
	}

	public void exec(Frontend frontend){
		UserSession userSession = frontend.getUserSession(userId);
		userSession.setGameReady(userId, frontend);
		System.out.println("userSession.setGameReady(userId,frontend) has been called. userId = " + userId);
	}

	public Long getUserId(){
		return userId;
	}
}
