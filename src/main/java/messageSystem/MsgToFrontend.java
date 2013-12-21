package messageSystem;

import base.Abonent;
import base.Address;
import base.Frontend;
import base.Msg;
import frontend.FrontendImpl;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 18.10.13
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
public abstract class MsgToFrontend extends Msg {
	public MsgToFrontend(Address from, Address to){
		super(from, to);
	}
	public void exec(Abonent abonent){ //если base.Frontend, то вызывает
		if (abonent instanceof FrontendImpl){ // перегруженный абстрактный метод
			exec((Frontend)abonent);
		}
	}

	public abstract void exec(Frontend frontend);
}
