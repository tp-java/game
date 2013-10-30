import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 14.09.13
 * Time: 15:05
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        //messagesystem
		MessageSystem ms = new MessageSystem();

		Frontend frontend = new Frontend(ms);
		AccountService accountService = new AccountService(ms);

		(new Thread(frontend)).start();
		(new Thread(accountService)).start();

        Server server = new Server(8078);

        //обрабатываются сессии
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(frontend), "/*");
        //сервлетик
        server.setHandler(context);

        server.start();
        server.join();
    }
}
