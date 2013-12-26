package accountService;

import database.Database;
import gameMech.GameMech;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import frontend.FrontendImpl;
import messageSystem.MessageSystemImpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

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
        //сервлетик
        server.setHandler(context);

        server.start();
        server.join();



    }
}
