package gameMech;

/**
 * User: maxim
 * Date: 30.11.13
 * Time: 5:37
 */
public class Position {
	double X;
	double Y;
	public Position(double X, double Y){
		this.X = X;
		this.Y = Y;
	}
	public void move(Direction direction){
		this.X += direction.getX();
		this.Y += direction.getY();
	}
	public double getX(){
		return X;
	}
	public double getY(){
		return Y;
	}

	@Override
	public String toString() {
		return "Position{" +
				"X=" + X +
				", Y=" + Y +
				'}';
	}
}
