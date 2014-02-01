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
	private double rotationL, rotationR;
	//private Integer healthL, healthR;
	private Boolean isChanged = true;


	private Position lastPositionL = new Position(), lastPositionR = new Position();





	public GameSessionReplica(/*TODO написать  конструктор не по умолчанию*/){
		this.positionL = new Position(0.0, 0.0);
		this.positionR = new Position(900.0, 0.0);
		this.rotationR = 0.0;
		this.rotationL = 0.0;
	}

	public GameSessionReplica(Position positionL, double rotationL, Position positionR, double rotationR){
		this.positionL = positionL;
		this.positionR = positionR;
		this.rotationR = rotationR;
		this.rotationL = rotationL;

	}

	public Boolean isChanged(){
		//отдается 2м клиентам => 2 раза должно отдаться
		return !(positionL.equals(lastPositionL)&&positionR.equals(lastPositionR)) || isChanged;
	}

	public void setRotate(Boolean left, double degree){
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

	public double getRotationL(){
		return rotationL;
	}

	public double getRotationR(){
		return rotationR;
	}

	public String getJSON(){
		String result = "{";
		result += "\"L\":{";
		result += "\"X\":" + this.getPositionL().getX() + ",";
		result += "\"Y\":" + this.getPositionL().getY() + ",";
		result += "\"R\":" + this.getRotationL() + "},";
		result += "\"R\":{";
		result += "\"X\":" + this.getPositionR().getX() + ",";
		result += "\"Y\":" + this.getPositionR().getY() + ",";
		result += "\"R\":" + this.getRotationR() + "}";
		result += "}";
		lastPositionL.setX(positionL.getX());
		lastPositionL.setY(positionL.getY());

		lastPositionR.setX(positionR.getX());
		lastPositionR.setY(positionR.getY());

		return result;
	}

}
