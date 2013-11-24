package source;

import org.apache.xpath.SourceTree;

import javax.servlet.http.HttpServletResponse;

/**
 * User: maxim
 * Date: 16.11.13
 * Time: 21:08
 */
public class Writter {
	public static void print(HttpServletResponse response, String s){
		try {
			response.getWriter().print(s);
		} catch (Exception e){}
	}

	public static void printConsole(String s){
		System.out.println(s);
	}
}
