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

public class Frontend extends HttpServlet{
    private AtomicLong userIdGenerator = new AtomicLong();
    Map<String, Long> nameToUserId = new HashMap<String, Long>();
    Map<Long, Long> SessionIdToUserId = new HashMap<Long, Long>();
    Map<String, Object> data = new HashMap<String, Object>();
    Calendar date;

    public Frontend(){
        nameToUserId.put("user", 1L);
        nameToUserId.put("user2", 2L);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try{
            if (request.getRequestURI().equals("/time")){
                date = Calendar.getInstance();
                response.getWriter().print(date.getTime());
            }else{
                date = Calendar.getInstance();
                HttpSession session = request.getSession();
                Long sessionId = (Long) session.getAttribute("sessionId");
                data.put("time", date.getTime().toString());
                if (sessionId != null) {    //если обновили страницу после авторизации
                    data.put("sessionId",sessionId);
                    data.put("userId",SessionIdToUserId.get(sessionId));
                    response.getWriter().print(PageGenerator.getPage("index.html", data));
                }else{ //пришли аторизовываться
                    response.getWriter().print(PageGenerator.getPage("login.html", data));
                }
            }
        } catch (Exception e){System.out.println(e.toString());}
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        date.getTime();
        //если POST-запрос не пустой
        if (nameToUserId.get(request.getParameter("name")) != null){
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


    }
}
