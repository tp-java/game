package source;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 18.10.13
 * Time: 22:15
 * To change this template use File | Settings | File Templates.
 */
public abstract class MsgToAS extends Msg {
	public MsgToAS(Address from, Address to){
		super(from, to);
	}
	public void exec(Abonent abonent){
		if(abonent instanceof AccountService){
			exec((AccountService)abonent);
		}
	}
	abstract void exec(AccountService accountService);
}
