package gameMech;

import javafx.util.Pair;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
	//тригонометрическо-округляющая магия
	public static final MathContext myMathContext = new MathContext(6, RoundingMode.HALF_UP);
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
		//System.out.println("changeDirection(double x, double y) x= " + Math.round(x) + " y= " + Math.round(y));
//		double rotate;
//		if (left){
//			rotate = GameMech.findAngle(1.0, 0.0, direction.getX(), direction.getY());
//		} else{
//			rotate = GameMech.findAngle(-1.0, 0.0, direction.getX(), direction.getY());
//		}
		if (!(direction.getX() == 0  &&  direction.getY()== 0  &&  x == 0)){
			double  oldX = direction.getX(),
					oldY = direction.getY(),
					length = (new BigDecimal(Math.sqrt(oldX*oldX + oldY*oldY), myMathContext)).setScale(4, RoundingMode.HALF_UP).doubleValue(),
					newX, newY;
			if (oldX != 0  || oldY !=0){
				if (oldX >= 0){
					newX = oldX + x*oldX/length;
				} else {
					newX = oldX - x*oldX/length;
				}

				if (oldY >= 0){
					newY = oldY + x*oldY/length;
				} else {
					newY = oldY - x*oldY/length;
				}
			} else {
				if (left){
					newX = 0.0 + x;
				} else {
					newX = -1.0 + x;
				}
				newY = 0.0;
			}
			//newX = (oldX != 0  || oldY !=0)  ?  ((oldX >= 0)? oldX + x*oldX/length : oldX - x*oldX/length)  :  ((left)?1.0:-1.0);


			//newY = (oldX != 0  || oldY !=0)  ?  ((oldY >= 0)? oldY + x*oldY/length : oldY - x*oldY/length)  :  0;

			Direction newDirection = new Direction(newX, newY);
			if (this.left){
				newDirection = GameMech.getVector(newDirection.getX(), newDirection.getY(), y*4, true);
			} else {
				newDirection = GameMech.getVector(newDirection.getX(), newDirection.getY(), y*4, false);
			}

			//System.out.println("new Direction: " + newDirection);
			direction = newDirection;
		}
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

	public Boolean getLeft(){
		return left;
	}

	//	public void damage(Integer minusHealth){
//		health -= minusHealth;
//	}
}
