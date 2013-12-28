package base;

import base.Abonent;
import base.AddressService;
import base.Msg;

/**
 * User: maxim
 * Date: 30.11.13
 * Time: 1:24
 */
public interface MessageSystem {
	public void addService(Abonent abonent);
	public AddressService getAddresService();
	public void sendMessage(Msg message);

}
