package gameMech;

import javafx.util.Pair;

import java.util.ArrayList;

/**
 * User: maxim
 * Date: 30.11.13
 * Time: 4:56
 */

//TODO:передавать размеры экрана,вычислять defaul position

public class Plane {
	Direction direction;
	Position position;
	Boolean left;
	//Integer health;
	//Integer damage;

	public Plane(Boolean left/*, Integer damage*/){
		//this.damage = damage;
		this.left = left;
		if (left){
			position = new Position(0.0, 0.0);
		} else {
			position = new Position(960.0, 0.0);
		}
		direction = new Direction(0.0, 0.0);
	}

	public void move(){
		position.move(direction);
	}

	public void changeDirection(double x, double y){
		System.out.println("changeDirection(double x, double y) x= " + Math.round(x) + " y= " + Math.round(y));
		double rotate;
		if (left){
			rotate = GameMech.findAngle(1.0, 0.0, direction.getX(), direction.getY());
		} else{
			rotate = GameMech.findAngle(-1.0, 0.0, direction.getX(), direction.getY());
		}
		System.out.println("rotate= " + rotate);


		double  oldX = direction.getX(),
				oldY = direction.getY(),
				length = Math.round(Math.sqrt(oldX*oldX + oldY*oldY)), //maybe Math.round()
				newX = ((oldX > 0)? oldX + x*oldX/length : oldX - x*oldX/length),
				newY = ((oldY > 0)? oldY + x*oldY/length : oldY - x*oldY/length);

		Direction newDirection = new Direction(newX, newY);
		if (this.left){
			newDirection = GameMech.getVector(newDirection.getX(), newDirection.getY(), rotate, true);
		} else {
			newDirection = GameMech.getVector(newDirection.getX(), newDirection.getY(), rotate, false);
		}
		System.out.println("new Direction: " + newDirection);
		System.out.println("");
		System.out.println("");
		direction = newDirection; //x прибавляется, vectorY приравнивается
	}

	public Direction getDirection(){
		return direction;
	}

	public void setDirection(Direction direction){
		this.direction = direction;
	}

	public Position getPosition(){
		return position;
	}

	@Override
	public String toString() {
		return "Plane{" +
				"direction=" + direction +
				", position=" + position +
				", left=" + left +
				'}';
	}

	public double getRotation(){
		//тут пересчет в rotation
		//надо добавить класс,
		// от которого будет наследоваться plane,
		// чтобы пули тоже так же обсчитывались
		double d;
		if (left){
			d = GameMech.findAngle(1.0, 0.0, direction.getX(), direction.getY());
		} else{
			d = GameMech.findAngle(-1.0, 0.0, direction.getX(), direction.getY());
		}
		return d;
	}

	//	public void damage(Integer minusHealth){
//		health -= minusHealth;
//	}
}
