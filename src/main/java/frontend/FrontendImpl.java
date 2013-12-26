package frontend;

import base.Abonent;
import base.Address;
import base.Frontend;
import base.MessageSystem;
import gameMech.MsgChangeState;
import gameMech.MsgStandToQueue;
import gameMech.Position;
import gameMech.UserSession;
import messageSystem.MessageSystemImpl;
import source.*;

import javax.servlet.http.*;
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
	private Map<Long, UserSession> userIdToUserSession = new HashMap<>();
	private Map<Integer, GameSessionReplica> gameSessionIdToReplica = new HashMap<>();

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
			try {
				Thread.sleep(10);
			} catch (Exception e){}
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

	public UserSession getUserSession(Long userId){
		return userIdToUserSession.get(userId);
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
		userIdToUserSession.put(userId, userSession);

		Address frontendAddress = getAddress();
		Address gameMechAddress = userSession.getGameMech();
		System.out.println("frontend.setId(){");
		ms.sendMessage(new MsgStandToQueue(frontendAddress, gameMechAddress, userId, this ));
		System.out.println("ms.sendMessage(new MsgStandToQueue), userId= " + userId);
	}

	public void setGameSession(Long userId, GameSessionReplica gameSessionReplica){
		getUserSession(userId).setGameSession(gameSessionReplica);
	}

	public void setReady(Long userId){
		/*здесь берется сокет по userId и ему кидается сообщение 2.1.1.1.1.1*/
	}

	public void setGameSessionReplica(Integer gameSessionReplicaId, GameSessionReplica gameSessionReplica){
		gameSessionIdToReplica.put(gameSessionReplicaId, gameSessionReplica);
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
					Cookie cookie = new Cookie("id", userId.toString());
					cookie.setMaxAge(3600 * 24 * 30);
					response.addCookie(cookie);
				}
			//финально окошечко приветствия
			}else if (requestURI.equals("/greeting")){
				String username = userSession.getUsername();
				Long userId = userSession.getUserId();
				data.put("username", username);
				data.put("userId",userId);
				Writter.print(response, PageGenerator.getPage("greeting.html", data));
			//первый приход юзера
			} else if (requestURI.equals("/getstate")){
				System.out.println("state requested. gameSessionIdToReplica.get(userSession.getGameSessionId()).getJSON()");
				Writter.print(response, gameSessionIdToReplica.get(userSession.getGameSessionId()).getJSON());
			} else	{
				if( (userSession == null) || (userSession.getUserId() == -1L)) {
					data.put("sessionId", sessionId);
					response.setContentType("text/html;charset=utf-8");
					Writter.print(response, PageGenerator.getPage("index1.html", data));
				} else {
					response.sendRedirect("/greeting");
				}
			}
        } catch (Exception e){System.out.println(e.toString()); e.printStackTrace();}
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
		String requestURI = request.getRequestURI();
		String requestSessionId = request.getSession().getId();
		if (requestURI.equals("/")){
			handleCount++;
			String requestUsername = request.getParameter("name");
			if(requestUsername != null){
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
    	} else if(requestURI.equals("/change")){
			System.out.println("post-request on /change");
			Integer u = Integer.parseInt(request.getParameter("u")),
					d = Integer.parseInt(request.getParameter("d")),
					r = Integer.parseInt(request.getParameter("r")),
					l = Integer.parseInt(request.getParameter("l"));
			Position pos = new Position(r-l, u-d);
			System.out.println("position change: " + pos.toString());
			Cookie cookie[] = request.getCookies();
			Long userId = -1L;
			for (int i = 0; i < cookie.length; i++){
				if (cookie[i].getName().equals("id")){
					userId = Long.parseLong(cookie[i].getValue());
				}
			}
			System.out.println("userId: " + userId);
			UserSession us = userIdToUserSession.get(userId);
			Boolean usLeft = us.getLeft();
			System.out.println("userSession.left: " + usLeft);
			Integer gsId = us.getGameSessionId();
			System.out.println("gsId " + gsId);
			Address frontendAddress = getAddress();
			Address gameMechAddress = us.getGameMech();

			ms.sendMessage(new MsgChangeState(frontendAddress, gameMechAddress, gsId, pos, userId, usLeft));
			System.out.println("MsgChangeState has been sent. ");
			System.out.println("MsgChangeState(frontendAddress, gameMechAddress, gsId, pos, userId, usLeft); ");
		}
	}
}
