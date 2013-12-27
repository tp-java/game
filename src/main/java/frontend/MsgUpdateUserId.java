package frontend;

import base.Address;
import base.Frontend;
import messageSystem.MsgToFrontend;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 18.10.13
 * Time: 22:34
 * To change this template use File | Settings | File Templates.
 */
public class MsgUpdateUserId extends MsgToFrontend {
	Long id;
	String sessionId;
	public MsgUpdateUserId(Address from, Address to, String sessionId, Long id){
		super(from, to);
		this.id = id;
		this.sessionId = sessionId;
	}
    public Long getId() {
        return this.id;
    }

    public String getSessionId() {
        return this.sessionId;
    }

	public void exec(Frontend frontend){
		frontend.setId(sessionId, id);
	}
    //get'-ы
}
