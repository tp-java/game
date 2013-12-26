package accountService;

import base.Abonent;
import base.AccountService;
import base.Address;
import base.MessageSystem;
import messageSystem.MessageSystemImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 18.10.13
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */
public class AccountServiceImpl implements Abonent, Runnable, AccountService {

	Map<String, Long> nameToUserId = new HashMap<String, Long>();
	Address address;
	MessageSystemImpl ms;

	AccountServiceImpl(MessageSystem ms){
		this.ms = (MessageSystemImpl)ms;
		this.address = new Address();
		ms.addService(this);
		ms.getAddresService().setAccountService(address);
		nameToUserId.put("user", 1L);
		nameToUserId.put("user2", 2L);
	}

	public Address getAddress(){
		return address;
	}
	public void run(){
		while (true){
			ms.execForAbonent(this);
			try {
				Thread.sleep(10);
			} catch (Exception e){}
		}
	}

	public Long getUserId(String name){
		//TODO: sleep!!!
		try {
			System.out.println("sleep");
			Thread.sleep(3000);
		} catch (Exception e){}
		System.out.println("wake");
		System.out.println(	nameToUserId.get(name));
		return nameToUserId.get(name);
	}
	public MessageSystem getMessageSystem(){
		 return ms;
	}
}
