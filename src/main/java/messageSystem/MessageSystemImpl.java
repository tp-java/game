package messageSystem;

import base.*;
import gameMech.GameMech;
import messageSystem.AddressServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * User: maxim
 * Date: 18.10.13
 * Time: 22:54
 */
public class MessageSystemImpl implements MessageSystem {
	AddressServiceImpl addressService = new AddressServiceImpl();
	private Map<Address, ConcurrentLinkedQueue<Msg>> messages = new HashMap<Address, ConcurrentLinkedQueue<Msg>>();

	public void addService(Abonent abonent){
		messages.put(abonent.getAddress(), new ConcurrentLinkedQueue<Msg>());
	}

	public void sendMessage(Msg message){
		Queue<Msg> messageQueue = messages.get(message.getTo());
		messageQueue.add(message);
	}
    public Queue<Msg> getQueue(Address address) {
        return messages.get(address);
    }

	public void execForAbonent(Abonent abonent){
		Queue<Msg> messageQueue = messages.get(abonent.getAddress());
		while (!messageQueue.isEmpty()){
			Msg message = messageQueue.poll();
			message.exec(abonent);
		}
	}

	public AddressService getAddresService(){
		return addressService;
	}
}
