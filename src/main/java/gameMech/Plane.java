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
	Integer health;
	Boolean left;
	Integer damage;

	public Plane(Boolean left, Integer damage){
		this.damage = damage;
		this.left = left;
		if (left){
			position = new Position(0, 0);
		} else {
			position = new Position(960, 0);
		}
		direction = new Direction(0, 0);
	}
	public void damage(Integer minusHealth){
		health -= minusHealth;
	}
	public void move(){
		position.move(direction);
	}
}
