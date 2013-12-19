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
			position = new Position(0, 0);
		} else {
			position = new Position(960, 0);
		}
		direction = new Direction(0, 0);
	}

	public void move(){
		position.move(direction);
	}

	public void changeDirection(Integer x, Integer y){
		direction.changeDirection(x, y);
	}

	public Direction getDirection(){
		return direction;
	}

	//	public void damage(Integer minusHealth){
//		health -= minusHealth;
//	}
}
