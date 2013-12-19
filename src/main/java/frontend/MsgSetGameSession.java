package frontend;

import base.Address;
import base.Frontend;
import messageSystem.MsgToFrontend;

/**
 * User: maxim
 * Date: 18.12.13
 * Time: 23:27
 */
public class MsgSetGameSession extends MsgToFrontend{
	Long userId;
	GameSessionReplica gameSessionReplica;
	Boolean left;

	public MsgSetGameSession(Address from, Address to, Long userId, GameSessionReplica gameSessionReplica, Boolean left){
		super(from, to);
		this.userId = userId;
		this.gameSessionReplica = gameSessionReplica;
		this.left = left;
	}

	public void exec(Frontend frontend){

	}
}
