package source;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 21.09.13
 * Time: 10:10
 * To change this template use File | Settings | File Templates.
 */

public class JettyServer extends AbstractHandler{
    public void handle (String target, Request baseRequest,HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);
            response.getWriter().println("Hello!");
    }
}
