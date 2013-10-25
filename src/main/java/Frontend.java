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

	private AtomicLong userIdGenerator = new AtomicLong();

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
	//<ДЗ №3.3 Frontend implements Runnable, логирование количества запросов, вывод каждые 5 сек>
	/*public void run(){
		try {
			while (true){
					System.out.println("количество запросов:" + handleCount);
					Thread.sleep(5000);
			}
		} catch (Exception e){}
	} */
	//</ДЗ №3.3 Frontend implements Runnable, логирование количества запросов, вывод каждые 5 сек>

    void setId(String sessionId, Long userId){
		UserSession userSession = sessionIdToUserSession.get(sessionId);
		if (userSession == null) {
			System.out.append("Can't find user session for: ").append(sessionId);
			return;
		}
		userSession.setUserId(userId);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response){
        try{
			//счетчик подключений. Логируется каждые 5 секунд
			handleCount++;
			response.setContentType("text/html;charset=utf-8");
            if (request.getRequestURI().equals("/time")){
                date = Calendar.getInstance();
                response.getWriter().print(date.getTime());
            }else if (request.getRequestURI().equals("/message")){
				HttpSession session = request.getSession();
				UserSession userSession = sessionIdToUserSession.get(session.getId());
				if (userSession.getUserId() == null) {
					response.getWriter().print("Ждите авторизации");
					return;
				} else {
					response.getWriter().print("userId = " + userSession.getUserId());
				}
			}else if (request.getRequestURI().equals("/greeting")){
				HttpSession session = request.getSession();
				UserSession userSession = sessionIdToUserSession.get(session.getId());
				data.put("username", userSession.getUsername());
				data.put("userId", userSession.getUserId());
				response.getWriter().print(PageGenerator.getPage("greeting.html", data));
			} else {
                /*date = Calendar.getInstance();
				data.put("time", date.getTime().toString());

                HttpSession session = request.getSession();
                Long sessionId = (Long) session.getAttribute("sessionId");

                if (sessionId != null) {    //если обновили страницу после авторизации
                    data.put("sessionId",sessionId);
                    data.put("userId",SessionIdToUserId.get(sessionId));
                    response.getWriter().print(PageGenerator.getPage("index.html", data));
                }else{ //пришли аторизовываться
                    response.getWriter().print(PageGenerator.getPage("login.html", data));
                } */

				data.put("sessionId", request.getSession().getId());
				try {
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().print(PageGenerator.getPage("index1.html", data));
				} catch (Exception e){}
			}
        } catch (Exception e){System.out.println(e.toString());}
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
		handleCount++;
		//date.getTime();
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



        //если POST-запрос не пустой
        /*if (nameToUserId.get(request.getParameter("name")) != null){
            try{
                Long userId = nameToUserId.get(request.getParameter("name"));
                HttpSession session = request.getSession();
                Long sessionId = (Long) session.getAttribute("sessionId");

                if (sessionId == null) {
                    sessionId = userIdGenerator.getAndIncrement();
                    SessionIdToUserId.put(sessionId, userId);
                    session.setAttribute("sessionId", sessionId);
                }
                data.put("userId", userId);
                data.put("sessionId", sessionId);
                data.put("time", date.toString());
                response.getWriter().print(PageGenerator.getPage("index.html", data));
            } catch (Exception e){}
        } else {    //если POST-запрос пустой, или неправильный username
            try{
                data.put("time", date.toString());
                response.getWriter().print(PageGenerator.getPage("login.html", data));
            }catch (Exception e){}
        }
        */


    }
}
