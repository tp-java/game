package base;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 18.10.13
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
public class Address {
	static private AtomicInteger abonentIdCreator = new AtomicInteger();
	final private int abonentId;

	public Address(){
		this.abonentId = abonentIdCreator.incrementAndGet();
	}
}
