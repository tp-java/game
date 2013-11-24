package source;

import static org.mockito.Mockito.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

import source.Writter;


/**
 * User: maxim
 * Date: 14.11.13
 * Time: 22:35
 */
public class FrontendTest {
	MessageSystem mockedMs;
	Frontend frontend;
	Address from;
	Address to;
	String username;
	String sessionId;
	HttpServletRequest mockedRequestPost,
					mockedRequestGetTime,
					mockedRequestGetMessage,
					mockedRequestGetGreeting,
					mockedRequestGetIndex;
	HttpSession httpSession;
	HttpServletResponse mockedResponse;
	Map<String, UserSession> mockedSessionIdToUserSession;
	UserSession userSession;
	AddressService addressService;
	Writter w;
	Msg msg;
	UserSession mockedUserSession;
	@Before
	public void setUp() throws Exception {
		w = mock(Writter.class);
		addressService = new AddressService();
		sessionId = "5mn7m7bgm38t16e769b6thqyr";

		msg = mock(Msg.class);

		mockedMs = spy(new MessageSystem());


		frontend = new Frontend(mockedMs);

		Address address = new Address();
		userSession = mock(UserSession.class);
		when(userSession.getAccountService()).thenReturn(address);
		//???doNothing().when(mockedMs).sendMessage(new MsgGetUserId(frontend.getAddress(), userSession.getAccountService(), username, sessionId));

		httpSession = mock(HttpSession.class);
		when(httpSession.getId()).thenReturn(sessionId);

		//testDoPost
		mockedRequestPost = mock(HttpServletRequest.class);
		when(mockedRequestPost.getParameter("name")).thenReturn("user");
		when(mockedRequestPost.getSession()).thenReturn(httpSession);
		mockedResponse = mock(HttpServletResponse.class);


		//testDoGet
		mockedRequestGetMessage = mock(HttpServletRequest.class);
		when(mockedRequestGetMessage.getRequestURI()).thenReturn("/message");
		when(mockedRequestGetMessage.getSession()).thenReturn(httpSession);

		mockedRequestGetGreeting = mock(HttpServletRequest.class);
		when(mockedRequestGetGreeting.getRequestURI()).thenReturn("/greeting");
		when(mockedRequestGetGreeting.getSession()).thenReturn(httpSession);

		mockedRequestGetIndex = mock(HttpServletRequest.class);
		when(mockedRequestGetIndex.getRequestURI()).thenReturn("");

		//testSetId
		mockedUserSession = mock(UserSession.class);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testRun() throws Exception {
		//frontend.ms.sendMessage(new MsgGetUserId(frontendAddress, accountServiveAddress, username, sessionId));

	}

	@Test
	public void testGetUserId() throws Exception {

	}

	@Test
	public void testSetId() throws Exception {
		Frontend frontendSet = spy(frontend);

		//нормальный вариант
		when(frontendSet.getUserSession(sessionId)).thenReturn(mockedUserSession);
		frontendSet.setId(sessionId, 1L);
		verify(mockedUserSession).setUserId(1L);

		//нет userSession для sessionId
		when(frontendSet.getUserSession(sessionId)).thenReturn(null);
		frontendSet.setId(sessionId, 1L);
		verify(w).printConsole("Cant find user session for: 5mn7m7bgm38t16e769b6thqyr");

		//не найден такой userName => устанавливается -1L
		when(mockedUserSession.getUserId()).thenReturn(null);
		when(frontendSet.getUserSession(sessionId)).thenReturn(mockedUserSession);
		frontendSet.setId(sessionId, null);
		verify(mockedUserSession).setUserId(-1L);


	}

	@Test
	public void testDoGet() throws Exception {	//TODO: обработка ошибок авторизации
		frontend.doGet(mockedRequestGetMessage, mockedResponse);
		verify(w).print(mockedResponse,"Ждите авторизации");

		userSession = new UserSession(sessionId, "user", addressService);
		frontend.putSessionIdUserSession(sessionId, userSession);
		frontend.doGet(mockedRequestGetMessage, mockedResponse);
		verify(w).print(mockedResponse, "userId = 5mn7m7bgm38t16e769b6thqyr");

		userSession.setUserId(1L);
		frontend.doGet(mockedRequestGetGreeting, mockedResponse);
		verify(w).print(mockedResponse,"<!DOCTYPE html> <html> <head> <style type=\"text/css\" src=\"style_new.css\"></style> </head> <body> <div class=\"page\"> <div class=\"row__title_greeting\"> Здравствуйте, <span class=\"row__title__greeting__name\">${username}</span>. Ваш userId = <span class=\"row__title__greeting__name\">${userId}</span> </div> </div> </body> </html>");

		frontend.doGet(mockedRequestGetIndex, mockedResponse);
		verify(w).print(mockedResponse,"<!DOCTYPE html> <html> <head> <title>ldskg</title> <meta charset=\"utf-8\"/> <link rel=\"stylesheet\" type=\"text/css\" href=\"style_new.css\"> </head> <body> <div class=\"page\"> <div class=\"row\"> <div class=\"row__title\">ID Вашей сессии:</div> <div class=\"row__id\">${sessionId}</div> </div> <div class=\"row\"> <div class=\"row__title\">Введите свое имя:</div> <form action=\"/\" method=\"POST\"> <input name=\"name\" type=\"text\" class=\"row__input\" autofocus/> <button type=\"submit\" class=\"row__button_index\">Отправить</button> </form> </div> </div> </body> </html>");
	}

	@Test
	public void testDoPost() throws Exception { //TODO: хз. как правильно проверять???
		//frontend.doPost(mockedRequestPost, mockedResponse);//вызвали метод, теперь смотрим, что внутри навызывалось
		//userSession = new UserSession(sessionId, "user", addressService);
		//надо вообще?
		verify(mockedMs).sendMessage(msg);
			//???verify(frontend.SessionIdToUserId).put("SessionId", userSession);
			//???verify(frontend.ms.sendMessage(new MsgGetUserId(frontendAddress, accountServiveAddress, username, sessionId));\
		verify(mockedResponse).setContentType("text/html;charset=utf-8");
		verify(mockedResponse).setStatus(HttpServletResponse.SC_OK);

		//результат, но зависимый от Page generator
		verify(w).print(mockedResponse, "<!DOCTYPE html> <html> <head> <title>Самолётики</title> <meta charset=\"utf-8\"/> <link rel=\"stylesheet\" type=\"text/css\" href=\"style_new.css\"> </head> <body> <div class=\"page\"> <div class=\"row\"> <div class=\"row__title\">ID Вашей сессии:</div> <div class=\"response\">${sessionId}</div> </div> <div class=\"row\"> <div class=\"row__title\">Ответ сервера: <span id=\"message\" class=\"response\">${message}</span></div> <div class=\"counter-blocks\"> <span class=\"capture\"> <span id=\"counter\">0</span> <div class=\"capture__corner capture__corner_bottom\"></div> <div class=\"capture__text capture__text_bottom\">запросов</div> </span> <span class=\"capture\"> <div class=\"counterr\">0</div> <div class=\"capture__corner capture__corner_bottom\"></div> <div class=\"capture__text capture__text_bottom\">ответов</div> </span> </div> <div class=\"row__button\" id=\"button_stop\"> <a href=\"#\">Прекратить!</a> </div> </div> </div> <script type=\"text/javascript\" src=\"waiting.js\"></script> </body> </html>");
	}
}
