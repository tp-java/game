package frontend;

import base.Address;
import base.Frontend;
import gameMech.UserSession;
import messageSystem.MsgToFrontend;

/**
 * User: maxim
 * Date: 19.12.13
 * Time: 22:34
 */
public class MsgSetLeft extends MsgToFrontend {
	Long userId;
	Boolean left;

	public MsgSetLeft(Address from, Address to, Long userId, Boolean left){
		super(from, to);
		this.left = left;
		this.userId = userId;
	}

	public void exec(Frontend frontend){
		UserSession userSession = frontend.getUserSession(userId);
		userSession.setLeft(left);
	}
}
