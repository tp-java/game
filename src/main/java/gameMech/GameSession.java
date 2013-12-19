package gameMech;

/**
 * User: maxim
 * Date: 16.12.13
 * Time: 3:21
 */
public class GameSession {
	private static Integer id;

	Plane planeL;
	Plane planeR;

	public GameSession(){
		planeL = new Plane(true);
		planeR = new Plane(false);
	}

	public void setState(Position pos, Boolean left){
		if(left){
			planeL.changeDirection(pos.getX(), pos.getY());
		} else {
			planeR.changeDirection(pos.getX(), pos.getY());
		}
	}

	public Direction getDirection(Boolean left){
		if (left){
			return planeL.getDirection();
		} else{
			return planeR.getDirection();
		}
	}
}
