package messageSystem;

import base.Address;
import base.AddressService;

/**
 * User: maxim
 * Date: 18.10.13
 * Time: 23:20
 */
public class AddressServiceImpl implements AddressService {
	private Address accountService;
	private Address gameMech;
	public Address getAccountService(){
		return accountService;
	}
	public void setAccountService(Address accountService){
		this.accountService = accountService;
	}
	public void setGameMech(Address gameMech){
		this.gameMech = gameMech;
	}
	public Address getGameMech(){
		return gameMech;
	}
}
