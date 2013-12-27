package resourceSystem;

import gameMech.Plane;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * User: maxim
 * Date: 27.12.13
 * Time: 7:05
 */
public class PlaneFactoryTest {
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testGetLeftPlane() throws Exception {
		Plane plane = PlaneFactory.getLeftPlane();
		assertEquals(true, plane.getLeft());
		Plane plane1 = PlaneFactory.getRightPlane();
		assertEquals(false, plane1.getLeft());
	}

	@Test
	public void testGetRightPlane() throws Exception {

	}
}
