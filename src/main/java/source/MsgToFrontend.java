package source;

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
	public void exec(Abonent abonent){ //если source.Frontend, то вызывает
		if (abonent instanceof Frontend){ // перегруженный абстрактный метод
			exec((Frontend)abonent);
		}
	}

	abstract void exec(Frontend frontend);
}
