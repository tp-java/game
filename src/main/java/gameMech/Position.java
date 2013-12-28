package gameMech;

/**
 * User: maxim
 * Date: 30.11.13
 * Time: 5:37
 */
public class Position {
	double X;
	double Y;
	public Position(){
		this.X = 0.0;
		this.Y = 0.0;
	}
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

	public void setX(double x){
		this.X = x;
	}

	public void setY(double y){
		this.Y = y;
	}

	@Override
	public String toString() {
		return "Position{" +
				"X=" + X +
				", Y=" + Y +
				'}';
	}
	public Boolean equals(Position position){
		if (this.X == position.getX() && this.Y == position.getY()){
			return true;
		} else {
			return false;
		}


	}
}
