package tests;

import gameMech.Direction;
import gameMech.Plane;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

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
		/*System.out.println("planeL test:");
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



		planeL.setDirection(new Direction(0.0, 0.0), 91.0);
		System.out.println("0.0, 0.0  ->  planeL.changeDirection(-1.0, 0.0);");
		planeL.changeDirection(-1.0, 0.0);

		actual = planeL.getDirection().getX();
		expected = 0.0;
		assertEquals(expected, actual, delta);

		actual = planeL.getDirection().getY();
		expected = 0.0;
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


		System.out.println("planeR.setDir(0.0;0.0) planeR.changeDir(-1, 0)");
		planeR.setDirection(new Direction(0.0,0.0));
		planeR.changeDirection(-1, 0);
		*/

		System.out.println("for(;;) tests");
		Map<Integer, Double> degNToSinus = new HashMap<>();
		degNToSinus.put(0, 0.0);
		degNToSinus.put(1, 0.5);
		degNToSinus.put(2, 0.7071);
		degNToSinus.put(3, 0.86603);
		degNToSinus.put(4, 1.0);
		degNToSinus.put(5, 0.86603);
		degNToSinus.put(6, 0.7071);
		degNToSinus.put(7, 0.5);
		degNToSinus.put(8, 0.0);
		degNToSinus.put(9, -0.5);
		degNToSinus.put(10, -0.7071);
		degNToSinus.put(11, -0.86603);
		degNToSinus.put(12, -1.0);
		degNToSinus.put(13, -0.86603);
		degNToSinus.put(14, -0.7071);
		degNToSinus.put(15, -0.5);
		degNToSinus.put(16, 0.0);

		Map<Integer, Double> degNToCosinus = new HashMap<>();
		degNToCosinus.put(0, 1.0);
		degNToCosinus.put(1, 0.86603);
		degNToCosinus.put(2, 0.7071);
		degNToCosinus.put(3, 0.5);
		degNToCosinus.put(4, 0.0);
		degNToCosinus.put(5, -0.5);
		degNToCosinus.put(6, -0.7071);
		degNToCosinus.put(7, -0.86603);
		degNToCosinus.put(8, -1.0);
		degNToCosinus.put(9, -0.86603);
		degNToCosinus.put(10, -0.7071);
		degNToCosinus.put(11, -0.5);
		degNToCosinus.put(12, 0.0);
		degNToCosinus.put(13, 0.5);
		degNToCosinus.put(14, 0.7071);
		degNToCosinus.put(15, 0.86603);
		degNToCosinus.put(16, 1.0);


		Map<Integer, Integer> degNToDegree = new HashMap<>();
		degNToDegree.put(0, 0);
		degNToDegree.put(1, 30);
		degNToDegree.put(2, 45);
		degNToDegree.put(3, 60);
		degNToDegree.put(4, 90);
		degNToDegree.put(5, 120);
		degNToDegree.put(6, 135);
		degNToDegree.put(7, 150);
		degNToDegree.put(8, 180);
		degNToDegree.put(9, 210);
		degNToDegree.put(10, 225);
		degNToDegree.put(11, 240);
		degNToDegree.put(12, 270);
		degNToDegree.put(13, 300);
		degNToDegree.put(14, 315);
		degNToDegree.put(15, 330);
		degNToDegree.put(16, 360);

		int i = 0;
		double sinus, cosinus, degSet, oldLength;

		for (int diffX = -2; diffX <= 2; diffX++ ){                                                 //
			for (int diffY = -1; diffY <= 1; diffY++ ){                                             //
				for (int length = 0; length <= 2; length++){                                        //153
					for (int degN = 0; degN <= 16; degN++){		// == oldDeg - мб 360 градусов     //51
						for (int coefficient = -1; coefficient <= 1; coefficient++ ){ //мб 0. cap.  //3
							if (i == 576|| i == 576)
								System.out.println("ff");


							System.out.println("count(i) = " + i);
							sinus = degNToSinus.get(degN);
							cosinus = degNToCosinus.get(degN);
							degSet = degNToDegree.get(degN);

							if (length*cosinus == 0 && length*sinus==0){
								planeL.setDirection(new Direction(length*cosinus, length*sinus),degSet); //проверяем все начальные состояния - все стандартные углы и длины векторов
								planeR.setDirection(new Direction(length*cosinus, length*sinus), degSet);
							} else {
								planeL.setDirection(new Direction(length*cosinus, length*sinus));
								planeR.setDirection(new Direction(length*cosinus, length*sinus));
							}

							oldLength = planeL.getDirectionLength();
							double deg = (15 + diffY) * coefficient; //угол, на который меняем

							planeL.changeDirection(diffX, deg);
							planeR.changeDirection(diffX, deg);

							//проверка изменения по x
							if ((oldLength > 0 && oldLength + diffX <= 0)){ //переход через 0
								assertEquals(0.0, planeL.getDirection().getX(), delta);
								assertEquals(0.0, planeL.getDirection().getY(), delta);
							}else if ((oldLength > 0 && oldLength - diffX <= 0)){
								assertEquals(0.0, planeR.getDirection().getX(), delta);
								assertEquals(0.0, planeR.getDirection().getY(), delta);
							} else if (oldLength == 0 && diffX < 0){	//для правого самолета наоборот второе условие
								assertEquals(0.0, planeL.getDirection().getX(), delta);
								assertEquals(0.0, planeL.getDirection().getY(), delta);

							} else if (oldLength == 0 && diffX > 0){

								assertEquals(0.0, planeR.getDirection().getX(), delta);
								assertEquals(0.0, planeR.getDirection().getY(), delta);
							} else if (length*cosinus == 0 && length*sinus==0 && diffX == 0){
								assertEquals(diffX, planeL.getDirectionLength(), delta);
								assertEquals(diffX, planeR.getDirectionLength(), delta);
							} else {
								//if (cosinus > 0){
									assertEquals(diffX,
											-(new BigDecimal(         (oldLength - planeL.getDirectionLength())       , new MathContext(7, RoundingMode.HALF_UP)).setScale(4, RoundingMode.HALF_UP).doubleValue()),
											delta);
									//assertEquals(diffX,
									//		(new BigDecimal(         (oldLength - planeR.getDirectionLength())       , new MathContext(7, RoundingMode.HALF_UP)).setScale(4, RoundingMode.HALF_UP).doubleValue()),
									//		delta);
								//} else { //TODO: а с нулем как быть?
									//assertEquals(diffX,
									//		(new BigDecimal(         (oldLength - planeL.getDirectionLength())       , new MathContext(7, RoundingMode.HALF_UP)).setScale(4, RoundingMode.HALF_UP).doubleValue()),
									//		delta);
									assertEquals(diffX,
											(new BigDecimal(         (oldLength - planeR.getDirectionLength())       , new MathContext(7, RoundingMode.HALF_UP)).setScale(4, RoundingMode.HALF_UP).doubleValue()),
											delta);
								//}
							}



							//стоит на месте с углом
							if (length == 0 && coefficient == 0){ //TODO: сделать для ненулевого коэф, для diffY проверку

								//в том ли направлении полетел
								if (diffX < 0){
									assertEquals(0.0, planeL.getDirection().getX(), delta);
									assertEquals(0.0, planeL.getDirection().getY(), delta);
								}else{
									assertEquals(
							new BigDecimal(      diffX*cosinus                      , new MathContext(7, RoundingMode.HALF_UP)).setScale(4, RoundingMode.HALF_UP).doubleValue(),
							new BigDecimal(      planeL.getDirection().getX()        , new MathContext(7, RoundingMode.HALF_UP)).setScale(4, RoundingMode.HALF_UP).doubleValue()
											, delta);
									assertEquals(
							new BigDecimal(      diffX*sinus                        , new MathContext(7, RoundingMode.HALF_UP)).setScale(4, RoundingMode.HALF_UP).doubleValue(),
							new BigDecimal(      planeL.getDirection().getY()        , new MathContext(7, RoundingMode.HALF_UP)).setScale(4, RoundingMode.HALF_UP).doubleValue()
											, delta);
								}


								if (diffX > 0){
									assertEquals(0.0, planeR.getDirection().getX(), delta);
									assertEquals(0.0, planeR.getDirection().getY(), delta);
								}else{
									//TODO: изменение
									assertEquals(
											new BigDecimal(      diffX*cosinus                      , new MathContext(7, RoundingMode.HALF_UP)).setScale(4, RoundingMode.HALF_UP).doubleValue(),
											new BigDecimal(      planeR.getDirection().getX()        , new MathContext(7, RoundingMode.HALF_UP)).setScale(4, RoundingMode.HALF_UP).doubleValue()
											, delta);
									assertEquals(
											new BigDecimal(      -diffX*sinus                        , new MathContext(7, RoundingMode.HALF_UP)).setScale(4, RoundingMode.HALF_UP).doubleValue(),
											new BigDecimal(      planeR.getDirection().getY()        , new MathContext(7, RoundingMode.HALF_UP)).setScale(4, RoundingMode.HALF_UP).doubleValue()
											, delta);
								}

							}
							i++;


						}

					}
				}
			}
		}

		System.out.println("i= " + i);
		planeR.setDirection(new Direction(-0.98163, 0.19081));
		planeR.changeDirection(1.0, 0.0);

		assertEquals(0.0, planeR.getDirectionLength(), delta);
		i++;
		System.out.println("i= " + i);

//		planeL.setDirection(new Direction(0.98163, -0.19081));
//		planeL.changeDirection(-1.0, 0.0);
//		assertEquals(0.0, planeL.getDirectionLength(), delta);

		i++;
		System.out.println("i= " + i);

		planeR.setDirection(new Direction(0.97815, 0.20791));
		planeR.changeDirection(-1.0, 0.0);
		assertEquals(2.0, planeR.getDirectionLength(), delta);

		i++;
		System.out.println("i= " + i);

		planeL.setDirection(new Direction(0.98163, -0.19081));
		planeL.changeDirection(-1.0, 0.0);
		assertEquals(0.0, planeL.getDirectionLength(), delta);

		i++;
		System.out.println("i= " + i);

		planeR.setDirection(new Direction(1.67314, -0.06032));
		planeR.changeDirection(-1.0, 1.0);
		assertEquals(2.67423, planeR.getDirectionLength(), delta);

		i++;
		System.out.println("i= " + i);

		planeR.setDirection(new Direction(-4.72762, 0.32556));
		planeR.changeDirection(-1.0, 1.0);
		assertEquals(5.73881, planeR.getDirectionLength(), delta);

		planeR.setDirection(new Direction(1.78207, -0.908));
		planeR.changeDirection(1.0, 1.0);
		assertEquals(1.8, planeR.getDirection().getX(), delta);
		assertEquals(1.8, planeR.getDirection().getY(), delta);

	}


	@Test
	public void testGetPosition() throws Exception {

	}

	@Test
	public void testGetRotation() throws Exception {

	}
}
