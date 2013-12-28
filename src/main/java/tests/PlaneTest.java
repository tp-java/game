package tests;

import gameMech.Direction;
import gameMech.Plane;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * User: maxim
 * Date: 24.12.13
 * Time: 2:24
 */
public class PlaneTest {
	Plane planeL,
			planeR;

	@Before
	public void setUp() throws Exception {
		planeL = new Plane(true);
		planeR = new Plane(false);

		planeL.setDirection(new Direction(0.7071, 0.7071)); // Pi/4
		planeR.setDirection(new Direction(-0.7071, 0.7071));

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testChangeDirection() throws Exception {
		double delta = 0.0,
				expected,
				actual;
		System.out.println("planeL test:");
		System.out.println("planeL.changeDirection(0.0, 45.0);");
		planeL.changeDirection(0.0, 45.0);

		actual = planeL.getDirection().getX();
		expected = 0.0;
		assertEquals(expected, actual, delta);

		actual = planeL.getDirection().getY();
		expected = 1.0;
		assertEquals(expected, actual, delta);
		System.out.println("\taccept\n");


		System.out.println("planeL.changeDirection(1.0, 0.0);");
		planeL.changeDirection(1.0, 0.0);

		actual = planeL.getDirection().getX();
		expected = 0.0;
		assertEquals(expected, actual, delta);

		actual = planeL.getDirection().getY();
		expected = 2.0;
		assertEquals(expected, actual, delta);
		System.out.println("\taccept\n");


		System.out.println("planeL.changeDirection(-1.0, -45.0);");
		planeL.changeDirection(-1.0, -45.0);

		actual = planeL.getDirection().getX();
		expected = 0.7071;
		assertEquals(expected, actual, delta);

		actual = planeL.getDirection().getY();
		expected = 0.7071;
		assertEquals(expected, actual, delta);
		System.out.println("\taccept\n");


		System.out.println("planeR test.");
		System.out.println("planeR.changeDirection(0.0, 45.0);");
		planeR.changeDirection(0.0, 45.0);
		actual = planeR.getDirection().getX();
		expected = 0.0;
		assertEquals(expected, actual, delta);

		actual = planeR.getDirection().getY();
		expected = 1.0;
		assertEquals(expected, actual, delta);
		System.out.println("\taccept\n");


		System.out.println("planeR.changeDirection(1.0, 0.0);");
		planeR.changeDirection(1.0, 0.0);

		actual = planeR.getDirection().getX();
		expected = 0.0;
		assertEquals(expected, actual, delta);

		actual = planeR.getDirection().getY();
		expected = 2.0;
		assertEquals(expected, actual, delta);
		System.out.println("\taccept\n");


		System.out.println("planeR.changeDirection(-1.0, -45.0);");
		planeR.changeDirection(-1.0, -45.0);

		actual = planeR.getDirection().getX();
		expected = -0.7071;
		assertEquals(expected, actual, delta);

		actual = planeR.getDirection().getY();
		expected = 0.7071;
		assertEquals(expected, actual, delta);
		System.out.println("\taccept\n");


	}

	@Test
	public void testGetPosition() throws Exception {

	}

	@Test
	public void testGetRotation() throws Exception {

	}
}
