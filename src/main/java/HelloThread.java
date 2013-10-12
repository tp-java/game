/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 07.10.13
 * Time: 0:27
 * To change this template use File | Settings | File Templates.
 */
//<ДЗ №3.2 последовательная работа потоков>
public class HelloThread extends Thread {
	public static int currentId = 0;
	private int innerId;
	private Object lockObject;

	public HelloThread(int innerId, Object lockObject){
		this.innerId = innerId;
		this.lockObject = lockObject;
	}

	public void run(){
		try {
			synchronized (lockObject){
				//заставляем ждать каждый раз, пока не наступит очередь текужего потока
				while(innerId > currentId){
					lockObject.wait();
				}
				currentId++;
				System.out.println(innerId);
				lockObject.notifyAll();
			}
		} catch (Exception e){
			System.out.println(e.toString());
		}
	}
}
//</ДЗ №3.2 последовательная работа потоков>
