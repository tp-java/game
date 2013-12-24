package accountService;

import base.Abonent;
import base.AccountService;
import base.Address;
import base.MessageSystem;
import database.Database;
import messageSystem.MessageSystemImpl;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 18.10.13
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */
public class AccountServiceImpl implements Abonent, Runnable, AccountService {


	private final Address address;
	private final MessageSystemImpl ms;
    private final Connection connection;
	AccountServiceImpl(MessageSystem ms){
		this.ms = (MessageSystemImpl)ms;
		this.address = new Address();
		ms.addService(this);
		ms.getAddresService().setAccountService(address);
        Map<String, Long> nameToUserId = new HashMap<String, Long>();
        connection = Database.getConnect();
        if (connection != null) {
            System.out.print("Updated: " + Database.set(connection, nameToUserId, "javabase") + "\n");
        }
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
		return Database.get(connection, name, "javabase");
	}
	public MessageSystem getMessageSystem(){
		 return ms;
	}
}
