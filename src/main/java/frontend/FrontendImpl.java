package frontend;

import base.Abonent;
import base.Address;
import base.Frontend;
import base.MessageSystem;
import gameMech.UserSession;
import messageSystem.MessageSystemImpl;
import source.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 21.09.13
 * Time: 10:02
 * To change this template use File | Settings | File Templates.
 */

public class FrontendImpl extends HttpServlet implements Runnable, Abonent, Frontend {
    private MessageSystemImpl ms;
	private Address address;
	private Map<String, UserSession> sessionIdToUserSession= new HashMap<>();

    Map<String, Object> data = new HashMap<String, Object>();
    Calendar date;

	private static int handleCount = 0; //количество запросов

    public FrontendImpl(MessageSystem ms){
		this.ms = (MessageSystemImpl)ms;
		this.address = new Address();
		ms.addService(this);
    }
	public Address getAddress(){
		return this.address;
	}
	public void run(){
		while(true){
			ms.execForAbonent(this);
		}
	}
	public Long getUserId(String sessionId){
		UserSession userSession = sessionIdToUserSession.get(sessionId);
		return userSession.getUserId();
	}
	public void putSessionIdUserSession(String sessionId, UserSession userSession){
		sessionIdToUserSession.put(sessionId, userSession);
	}
	public UserSession getUserSession(String sessionId){
		return sessionIdToUserSession.get(sessionId);
	}

    public void setId(String sessionId, Long userId){
		UserSession userSession = getUserSession(sessionId);
		if (userSession == null) {
			Writter.printConsole("Can't find user session for: " + sessionId);
			return;
		}
		if (userId == null) {
			userSession.setUserId(-1L);
			return;
		}
		userSession.setUserId(userId);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response){
        try{
			//счетчик подключений. Логируется каждые 5 секунд
			handleCount++;


			HttpSession session = request.getSession();
			String sessionId = session.getId();

			UserSession userSession = sessionIdToUserSession.get(sessionId);

			String requestURI = request.getRequestURI();

			response.setContentType("text/html;charset=utf-8");

			//запрос сообщения от сервера (найден или нет userId) (аяксом фигачит)
            if (requestURI.equals("/message")){
				String username = userSession.getUsername();
				Long userId = userSession.getUserId();
				if (userId == null) {
					Writter.print(response, "Ждите авторизации");
					return;
				} else if(userId == -1L){
					Writter.print(response, "Неправильный username");
				} else{
					Writter.print(response, "userId = " + userId);
				}
			//финально окошечко приветствия
			}else if (requestURI.equals("/greeting")){
				String username = userSession.getUsername();
				Long userId = userSession.getUserId();
				data.put("username", username);
				data.put("userId",userId);
				Writter.print(response, PageGenerator.getPage("greeting.html", data));
			//первый приход юзера
			} else {
				if( (userSession == null) || (userSession.getUserId() == -1L)) {
					data.put("sessionId", sessionId);
					response.setContentType("text/html;charset=utf-8");
					Writter.print(response, PageGenerator.getPage("index1.html", data));
				} else {
					response.sendRedirect("/greeting");
				}
			}
        } catch (Exception e){System.out.println(e.toString());}
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
		handleCount++;
		String requestUsername = request.getParameter("name");
        if(requestUsername != null){
			String requestSessionId = request.getSession().getId();
			UserSession userSession = new UserSession(requestSessionId, requestUsername, ms.getAddresService());
			sessionIdToUserSession.put(requestSessionId, userSession);

			Address frontendAddress = getAddress();
			Address accountServiveAddress = userSession.getAccountService();

			ms.sendMessage(new MsgGetUserId(frontendAddress, accountServiveAddress, requestUsername, requestSessionId));

			try {
				response.setContentType("text/html;charset=utf-8");
				response.setStatus(HttpServletResponse.SC_OK);
				data.put("message","Ждите авторизации");
				Writter.print(response,PageGenerator.getPage("waiting.html", data));
			} catch (Exception e){}
		}

    }
}
