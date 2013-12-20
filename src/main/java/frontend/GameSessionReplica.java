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

	public GameSessionReplica(Position positionL, Integer rotationL, Position positionR, Integer rotationR){
		this.positionL = positionL;
		this.positionR = positionR;
		this.rotationR = rotationR;
		this.rotationL = rotationL;
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

	public String getJSON(){
		String result = "{";
		result += "L:{";
		result += "X:" + this.getPositionL().getX() + ",";
		result += "Y:" + this.getPositionL().getY() + ",";
		result += "R:" + this.getRotationL() + "},";
		result += "R:{";
		result += "X:" + this.getPositionR().getX() + ",";
		result += "Y:" + this.getPositionR().getY() + ",";
		result += "R:" + this.getRotationR() + "}";
		result += "}";

		return result;
	}
}
