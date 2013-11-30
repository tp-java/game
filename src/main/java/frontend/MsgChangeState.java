package frontend;

import base.Address;
import gameMech.GameMech;
import messageSystem.MsgToGM;

/**
 * User: maxim
 * Date: 30.11.13
 * Time: 6:21
 */
public class MsgChangeState extends MsgToGM {
	Integer direction;
	public MsgChangeState(Address from, Address to, Integer direction){
		super(from, to);
		this.direction = direction;
	}

	public void exec(GameMech gameMech){
		//gameMech
	}
}
