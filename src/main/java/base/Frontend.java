package base;

import frontend.GameSessionReplica;
import gameMech.UserSession;

/**
 * User: maxim
 * Date: 30.11.13
 * Time: 1:21
 */
public interface Frontend {
	public void setId(String sessionId, Long userId);
	public UserSession getUserSession(String sessionId);
	public UserSession getUserSession(Long userId);
	public void setGameSession(Long userId, GameSessionReplica gameSessionReplica);
	public void setReady(Long userId);
}
