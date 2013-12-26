package frontend;

import base.Address;
import base.Frontend;
import gameMech.GameMech;
import gameMech.UserSession;
import messageSystem.MsgToFrontend;
import messageSystem.MsgToGM;

/**
 * User: maxim
 * Date: 30.11.13
 * Time: 4:59
 */

//TODO удалить ненужные import-ы
public class MsgUpdateState extends MsgToFrontend{
	private Integer gameSessionId;
	GameSessionReplica gameSessionReplica;

	public MsgUpdateState(Address from, Address to, Integer gameSessionId, GameSessionReplica gameSessionReplica){
		super(from, to);
		this.gameSessionId = gameSessionId;
		this.gameSessionReplica = gameSessionReplica;
		System.out.println("MsgUpdateState has been created.");
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return "MsgUpdateState{" +
				"gameSessionId=" + gameSessionId +
				", gameSessionReplica=" + gameSessionReplica +
				'}';
	}

	public void exec(Frontend frontend){
		//UserSession userSession = frontend.getUserSession(userId);
		//userSession.setRotate(left, rotate);
		frontend.setGameSessionReplica(gameSessionId, gameSessionReplica);
		System.out.println("frontend.setGameSessionReplica(gameSessionId, gameSessionReplica); gameSessionId= " + gameSessionId + "gameSessionReplica" + gameSessionReplica);
	}
}
