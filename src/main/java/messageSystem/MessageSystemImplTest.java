package messageSystem;

import frontend.MsgUpdateUserId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import frontend.FrontendImpl;
import accountService.AccountServiceImpl;
import messageSystem.MessageSystemImpl;
import base.Msg;
import static org.junit.Assert.assertEquals;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: Konstantin
 * Date: 27.12.13
 * Time: 0:02
 * To change this template use File | Settings | File Templates.
 */
public class MessageSystemImplTest {
	MessageSystemImpl messageSystem = new MessageSystemImpl();
	FrontendImpl frontend;
	AccountServiceImpl accountService;
	@Before
	public void setUp() throws Exception {
		frontend = new FrontendImpl(messageSystem);
		accountService = new AccountServiceImpl(messageSystem);

	}

	@Test
	public void checkMsgQueue() throws Exception {
		Msg msg = new MsgUpdateUserId(accountService.getAddress(), frontend.getAddress(), "wlkejrvslkjfd234lkjvc", 1L);
		messageSystem.sendMessage(msg);
		Queue<Msg> queue = messageSystem.getQueue(frontend.getAddress());
		MsgUpdateUserId msg1 = (MsgUpdateUserId)queue.poll();
		long id = msg1.getId();
		String str = msg1.getSessionId();
		assertEquals(id ,1L);
		assertEquals(str, "wlkejrvslkjfd234lkjvc");
	}
}
