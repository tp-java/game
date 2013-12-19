package gameMech;

import base.Address;
import base.Msg;
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
	}

	public void exec(GameMech gameMech){
		GameSession gameSession = gameMech.getGameSession(gameSessionId);
		gameSession.setState(pos, usLeft);
		Integer rotate = GameMech.getDegree(gameSession.getDirection(usLeft));
		Msg back = new MsgUpdateState(getTo(), getFrom(), rotate, usLeft, userId );
		gameMech.getMessageSystem().sendMessage(back);
	}
}
