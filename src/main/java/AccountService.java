import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 18.10.13
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */
public class AccountService implements Abonent, Runnable{

	Map<String, Long> nameToUserId = new HashMap<String, Long>();
	Address address;
	MessageSystem ms;

	AccountService(MessageSystem ms){
		this.ms = ms;
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
		}
	}

	Long getUserId(String name){
		//TODO: sleep!!!
		try {
			System.out.println("sleep");
			Thread.sleep(8000);
		} catch (Exception e){}
		System.out.println("wake");
		return nameToUserId.get(name);
	}
	MessageSystem getMessageSystem(){
		 return ms;
	}
}
