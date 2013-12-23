package tests;

import gameMech.Direction;
import gameMech.GameMech;
import gameMech.Position;
import messageSystem.MessageSystemImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


/**
 * User: maxim
 * Date: 23.12.13
 * Time: 23:04
 */
public class GameMechTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testFindAngle() throws Exception {   //TODO: тупые углы
		double  delta = 0.0,
				expected,
				actual;

		actual =  GameMech.findAngle(0.0, 1.0, 1.0, 1.0);
		expected = 45.0;
		assertEquals(expected, actual, delta);

		actual = GameMech.findAngle(-1.0, 0.0, 0.0, 1.0);
		expected = 90.0;
		assertEquals(expected, actual, delta);

	}

	@Test
	public void testGetVector() throws Exception {
		Direction delta = new Direction(0.0, 0.0),
				expected,
				actual;

		//left plane
		actual =  GameMech.getVector(1.0, 0.0, 90.0, true);
		expected = new Direction(0.0, 1.0);
		assertEquals(expected.getY(), actual.getY(), delta.getY());
		assertEquals(expected.getX(), actual.getX(), delta.getX());

		actual =  GameMech.getVector(1.0, 0.0, -90.0, true);
		expected = new Direction(0.0, -1.0);
		assertEquals(expected.getY(), actual.getY(), delta.getY());
		assertEquals(expected.getX(), actual.getX(), delta.getX());

		//right plane
		actual =  GameMech.getVector(-1.0, 0.0, 90.0, false);
		expected = new Direction(0.0, 1.0);
		assertEquals(expected.getY(), actual.getY(), delta.getY());
		assertEquals(expected.getX(), actual.getX(), delta.getX());

		actual =  GameMech.getVector(-1.0, 0.0, -90.0, false);
		expected = new Direction(0.0, -1.0);
		assertEquals(expected.getY(), actual.getY(), delta.getY());
		assertEquals(expected.getX(), actual.getX(), delta.getX());

	}
}
