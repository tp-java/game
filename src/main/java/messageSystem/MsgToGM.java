package messageSystem;

import base.Abonent;
import base.Address;
import base.Msg;
import gameMech.GameMech;

/**
 * User: maxim
 * Date: 30.11.13
 * Time: 5:00
 */
public abstract class MsgToGM extends Msg {
	public MsgToGM(Address from, Address to){
		super(from, to);
	}
	public void exec(Abonent abonent){
		if (abonent instanceof GameMech){
			exec((GameMech)abonent);
		}
	}
	public abstract void exec(GameMech gameMech);
}
