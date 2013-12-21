package source;

import base.Address;
import base.AddressService;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 12.10.13
 * Time: 20:12
 * To change this template use File | Settings | File Templates.
 */
public class UserSession {
	Address accountService;  //адрес аккаунт-сервиса (Ваш Кэп)

	private String name; // Имя юзера
	private String sessionId;
	private Long userId;

	public UserSession(String sessionId, String name, AddressService addressService){
		this.sessionId = sessionId;
		this.name = name;
		this.accountService = addressService.getAccountService();
	}

	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Address getAccountService(){
		return accountService;
	}

	public Long getUserId(){
		return userId;
	}

	public String getUsername(){
		return name;
	}

}
