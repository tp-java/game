package frontend;

import gameMech.Position;

/**
 * User: maxim
 * Date: 17.12.13
 * Time: 23:09
 */

//TODO сделать как внутренний класс FrontendImpl
public class GameSessionReplica {
	private Position positionL, positionR;
	private Integer rotationL, rotationR;
	//private Integer healthL, healthR;

	public GameSessionReplica(/*TODO написать  конструктор не по умолчанию*/){
		this.positionL = new Position(0, 0);
		this.positionR = new Position(900, 0);
		this.rotationR = 0;
		this.rotationL = 0;
	}

	public void setRotate(Boolean left, Integer degree){
		if (left){
			rotationL = degree;
		}else {
			rotationR = degree;
		}
	}

	public Position getPositionL(){
		return positionL;
	}

	public Position getPositionR(){
		return positionR;
	}

	public Integer getRotationL(){
		return rotationL;
	}

	public Integer getRotationR(){
		return rotationR;
	}
}
