package base;

/**
 * User: maxim
 * Date: 30.11.13
 * Time: 1:23
 */
public interface AddressService {
	public void setAccountService(Address accountService);
	public Address getAccountService();
	public void setGameMech(Address gameMech);
	public Address getGameMech();
}
