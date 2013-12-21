package frontend;

import base.AccountService;
import base.Address;
import base.Msg;
import messageSystem.MsgToAS;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 18.10.13
 * Time: 22:24
 * To change this template use File | Settings | File Templates.
 */
public class MsgGetUserId extends MsgToAS {
	private String name;
	private String sessionId;

	public MsgGetUserId(Address from, Address to, String name, String sessionId){
		super(from, to);
		this.name = name;
		this.sessionId = sessionId;
	}

	public void exec(AccountService accountService){
		Long id = accountService.getUserId(name);
		Msg back = new MsgUpdateUserId(getTo(), getFrom(), sessionId, id);
		accountService.getMessageSystem().sendMessage(back);
	}
}
