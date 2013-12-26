package gameMech;

import frontend.GameSessionReplica;

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
		System.out.println("GameSession.setState(Position pos, Boolean left) called. pos= " + pos + " left= " + left);
		if(left){
			System.out.println("planeL.getDirection().toString(): " + planeL.getDirection().toString());
			planeL.changeDirection(pos.getX(), pos.getY());
			System.out.println("planeL.changeDirection(pos.getX(), pos.getY());");
			System.out.println("planeL.getDirection().toString(): " + planeL.getDirection().toString());
		} else {
			System.out.println("planeR.getDirection().toString(): " + planeR.getDirection().toString());
			planeR.changeDirection(pos.getX(), pos.getY());
			System.out.println("planeR.changeDirection(pos.getX(), pos.getY());");
			System.out.println("planeR.getDirection().toString(): " + planeR.getDirection().toString());
		}
	}

	public Direction getDirection(Boolean left){
		if (left){
			return planeL.getDirection();
		} else{
			return planeR.getDirection();
		}
	}

	@Override
	public String toString() {
		return "GameSession{" +
				"planeL=" + planeL.toString() +
				", planeR=" + planeR.toString() +
				'}';
	}

	public GameSessionReplica getReplica(){
		return new GameSessionReplica(planeL.getPosition(), planeL.getRotation(), planeR.getPosition(), planeR.getRotation());
	}
}
