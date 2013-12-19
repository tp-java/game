package gameMech;

/**
 * User: maxim
 * Date: 30.11.13
 * Time: 5:37
 */
public class Position {
	Integer X;
	Integer Y;
	public Position(Integer X, Integer Y){
		this.X = X;
		this.Y = Y;
	}
	public void move(Direction direction){
		this.X += direction.getX();
		this.Y +=direction.getY();
	}
	public Integer getX(){
		return X;
	}
	public Integer getY(){
		return Y;
	}
}
