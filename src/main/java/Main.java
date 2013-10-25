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


        //если надо одинаково обрабатывать все, создаем просто джетти сервер, если нет, то делаем кучу сервлетов
        //server.setHandler(new JettyServer());

        /*ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("static");  */


		//<ДЗ №3.1 threadPool>
        ArrayList<Thread> threadPool = new ArrayList<Thread>();
		Object lockObject = new Object();
        //кладем треды в тред пулл и запускаем
        for (int i = 0; i < 10; i++){
			Thread thread = new HelloThread(i, lockObject);
			threadPool.add(thread);
			thread.start();
		}
		//</ДЗ №3.1 threadPool>

		//<ДЗ №3.3 запуск Frontend в отдельном потоке>
		/*Thread thread1 = new Thread(frontend);
		thread1.start();*/
		//</ДЗ №3.3 запуск Frontend в отдельном потоке>


        server.start();
        server.join();
    }
}
