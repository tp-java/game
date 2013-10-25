/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 18.10.13
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */
public abstract class Msg {
	final private Address from;
	final private Address to;
	public Msg(Address from, Address to){
		this.from = from;
		this.to = to;
	}
	protected Address getFrom(){
		return from;
	}
	protected Address getTo(){
		return to;
	}
	public abstract void exec(Abonent abonent);

}
