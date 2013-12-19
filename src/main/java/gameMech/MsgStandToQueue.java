package gameMech;

import base.Address;
import messageSystem.MsgToGM;

/**
 * User: maxim
 * Date: 18.12.13
 * Time: 22:56
 */
public class MsgStandToQueue extends MsgToGM{
	Long userId;

	MsgStandToQueue(Address from, Address to, Long userId){
		super(from, to);
		this.userId = userId;
	}

	public void exec(GameMech gameMech){
		gameMech.standToQueue(userId, getFrom(), getTo());
	}
}
