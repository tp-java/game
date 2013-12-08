package base;

import base.MessageSystem;

/**
 * User: maxim
 * Date: 30.11.13
 * Time: 1:22
 */
public interface AccountService {
	public Long getUserId(String name);
	public MessageSystem getMessageSystem();
}
