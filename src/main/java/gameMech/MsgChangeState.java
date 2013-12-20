package gameMech;

import base.Address;
import base.Msg;
import frontend.GameSessionReplica;
import frontend.MsgUpdateState;
import gameMech.GameMech;
import gameMech.GameSession;
import gameMech.Position;
import gameMech.UserSession;
import messageSystem.MsgToGM;

/**
 * User: maxim
 * Date: 30.11.13
 * Time: 6:21
 */
public class MsgChangeState extends MsgToGM {
	Integer gameSessionId;
	Position pos;
	Long userId;
	Boolean usLeft;

	public MsgChangeState(Address from, Address to, Integer gameSessionId, Position pos, Long userId, Boolean usLeft){
		super(from, to);
		this.gameSessionId = gameSessionId;
		this.pos = pos;
		this.userId = userId;
		this.usLeft = usLeft;
		System.out.println("MsgChangeState has been created.");
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return "MsgChangeState{" +
				"gameSessionId=" + gameSessionId +
				", pos=" + pos +
				", userId=" + userId +
				", usLeft=" + usLeft +
				'}';
	}

	public void exec(GameMech gameMech){
		GameSession gameSession = gameMech.getGameSession(gameSessionId);
		System.out.println("gameSession = gameMech.getGameSession(gameSessionId) gameSessionId: " + gameSessionId);
		gameSession.setState(pos, usLeft);
		System.out.println("gameSession.setState(pos, usLeft);");
		System.out.println(gameSession.toString());
		GameSessionReplica gameSessionReplica = gameSession.getReplica();
		System.out.println("gameSessionReplica = gameSession.getReplica();");
		System.out.println("gameSessionReplica: " + gameSessionReplica.toString());
		Msg back = new MsgUpdateState(getTo(), getFrom(), gameSessionId, gameSessionReplica );
		gameMech.getMessageSystem().sendMessage(back);

	}
}
