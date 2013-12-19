package gameMech;

/**
 * User: maxim
 * Date: 30.11.13
 * Time: 5:39
 */
public class Direction {
	private Integer X;
	private Integer Y;
	public Direction(Integer X, Integer Y){
		this.X = X;
		this.Y = Y;
	}

	public Integer getX(){
		return X;
	}

	public Integer getY(){
		return Y;
	}

	public void changeDirection(Integer x, Integer y){
		//TODO: тут не просто надо X у вектора прибавлять. надо вектор изменять - x и y
		//TODO  аргумент x - изменение градуса. Отсюда sin - y, cos - x;
		if (this.X == 0){  //если не двигаемся, то начинаем двигаться
			this.X += x;
		}
		this.Y += y;
	}

//	public void sum(Direction direction){
//		this.X += direction.getX();
//		this.Y += direction.getY();
//	}
}
