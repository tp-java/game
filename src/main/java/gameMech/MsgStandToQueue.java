package gameMech;

import base.Address;
import base.Frontend;
import messageSystem.MsgToGM;

/**
 * User: maxim
 * Date: 18.12.13
 * Time: 22:56
 */
public class MsgStandToQueue extends MsgToGM{
	Long userId;
	Frontend frontend;

	public MsgStandToQueue(Address from, Address to, Long userId, Frontend frontend){
		super(from, to);
		this.userId = userId;
		this.frontend = frontend;
		System.out.println("public MsgStandToQueue() : MsgStandToQueue was created");
	}

	public void exec(GameMech gameMech){
		gameMech.setToQueue(userId, getTo(), getFrom(), frontend);
		System.out.println("MsgStandToQueue was executed, gameMech.setToQueue(userId) called, userId= " + userId);
	}
}
