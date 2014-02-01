package gameMech;

/**
 * User: maxim
 * Date: 30.11.13
 * Time: 5:39
 */
public class Direction {
	private double X;
	private double Y;

	public Direction(double X, double Y){
		this.X = X;
		this.Y = Y;
	}

	public double getX(){
		return X;
	}

	public double getY(){
		return Y;
	}

	public void setDirection(double x, double y){
		this.X = x;
		this.Y = y;
	}

	public void changeY(double y){
		//TODO: тут не просто надо X у вектора прибавлять. надо вектор изменять - x и y
		//TODO  аргумент x - изменение градуса. Отсюда sin - y, cos - x;
		//если не двигаемся, то начинаем двигаться - убрали
		System.out.println("");
		System.out.println("");
		System.out.println("public void changeDirection(double y) y= " + y);
		System.out.println("");
		System.out.println("");

		this.Y = y;
	}

	public void changeX(double x){
		if (this.X != 0)
			this.X += x;
	}

	@Override
	public String toString() {
		return "Direction{" +
				"X=" + X +
				", Y=" + Y +
				'}';
	}
//	public void sum(Direction direction){
//		this.X += direction.getX();
//		this.Y += direction.getY();
//	}
}
