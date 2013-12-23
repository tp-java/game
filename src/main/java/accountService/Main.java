package accountService;

import org.eclipse.jetty.server.Handler;
import gameMech.GameMech;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import frontend.FrontendImpl;
import messageSystem.MessageSystemImpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

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
		MessageSystemImpl ms = new MessageSystemImpl();

		FrontendImpl frontend = new FrontendImpl(ms);
		AccountServiceImpl accountService = new AccountServiceImpl(ms);

		GameMech gameMech = new GameMech(ms);

		(new Thread(frontend)).start();
		(new Thread(accountService)).start();
		(new Thread(gameMech)).start();

        Server server = new Server(8078);

        //обрабатываются сессии
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(frontend), "/*");

		ResourceHandler resource_handler = new ResourceHandler();
		resource_handler.setDirectoriesListed(true);
		resource_handler.setResourceBase("static");

		HandlerList handlers = new HandlerList();
		//сервлетики
		handlers.setHandlers(new Handler[]{resource_handler, context});
		server.setHandler(handlers);

        server.start();
        server.join();



    }
}
