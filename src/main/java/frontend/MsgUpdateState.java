package frontend;

import base.Address;
import gameMech.GameMech;
import messageSystem.MsgToGM;

/**
 * User: maxim
 * Date: 30.11.13
 * Time: 4:59
 */
public class MsgUpdateState extends MsgToGM{
	public MsgUpdateState(Address from, Address to){
		super(from, to);
	}

	public void exec(GameMech gameMech){

	}
}
