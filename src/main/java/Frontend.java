import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 21.09.13
 * Time: 10:02
 * To change this template use File | Settings | File Templates.
 */

public class Frontend extends HttpServlet implements Runnable, Abonent{
    private MessageSystem ms;
	private Address address;
	private Map<String, UserSession> sessionIdToUserSession= new HashMap<>();

    Map<Long, Long> SessionIdToUserId = new HashMap<Long, Long>();
    Map<String, Object> data = new HashMap<String, Object>();
    Calendar date;

	private static int handleCount = 0; //количество запросов

    public Frontend(MessageSystem ms){
		this.ms = ms;
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

    void setId(String sessionId, Long userId){
		UserSession userSession = sessionIdToUserSession.get(sessionId);
		if (userSession == null) {
			System.out.append("Can't find user session for: ").append(sessionId);
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
			response.setContentType("text/html;charset=utf-8");

			//запрос времени сервера
            if (request.getRequestURI().equals("/time")){
                date = Calendar.getInstance();
                response.getWriter().print(date.getTime());
			//запрос сообщения от сервера (найден или нет userId) (аяксом фигачит)
            }else if (request.getRequestURI().equals("/message")){
				HttpSession session = request.getSession();
				UserSession userSession = sessionIdToUserSession.get(session.getId());
				if (userSession.getUserId() == null) {
					response.getWriter().print("Ждите авторизации");
					return;
				} else if(userSession.getUserId() == -1L){
					response.getWriter().print("Неправильный username");
				} else{
					response.getWriter().print("userId = " + userSession.getUserId());
				}
			//финально окошечко приветствия
			}else if (request.getRequestURI().equals("/greeting")){
				HttpSession session = request.getSession();
				UserSession userSession = sessionIdToUserSession.get(session.getId());
				data.put("username", userSession.getUsername());
				data.put("userId", userSession.getUserId());
				response.getWriter().print(PageGenerator.getPage("greeting.html", data));
			//первый приход юзера
			} else {
				HttpSession session = request.getSession();
				UserSession userSession = sessionIdToUserSession.get(session.getId());
				if( (sessionIdToUserSession.get(request.getSession().getId()) == null) || (userSession.getUserId() == -1L))
				{
				data.put("sessionId", request.getSession().getId());
				try {
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().print(PageGenerator.getPage("index1.html", data));
				} catch (Exception e){}
				} else {
					response.sendRedirect("/greeting");
				}
			}
        } catch (Exception e){System.out.println(e.toString());}
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
		handleCount++;
        if(request.getParameter("name") != null){
			String username = request.getParameter("name");
			String sessionId = request.getSession().getId();
			UserSession userSession = new UserSession(sessionId, username, ms.getAddresService());
			sessionIdToUserSession.put(sessionId, userSession);

			Address frontendAddress = getAddress();
			Address accountServiveAddress = userSession.getAccountService();

			ms.sendMessage(new MsgGetUserId(frontendAddress, accountServiveAddress, username, sessionId));
			response.setContentType("text/html;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);
			try {
				data.put("message","Ждите авторизации");
				response.getWriter().print(PageGenerator.getPage("waiting.html", data));
			} catch (Exception e){}
		}

    }
}
