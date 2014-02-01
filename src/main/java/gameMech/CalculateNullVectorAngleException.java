package gameMech;

/**
 * User: maxim
 * Date: 28.12.13
 * Time: 21:00
 */
public class CalculateNullVectorAngleException extends Exception {
	double aX, aY, bX, bY;
	public CalculateNullVectorAngleException(){
		super("You can't find angle between null-vector");
	}

	public CalculateNullVectorAngleException(double aX, double aY, double bX, double bY){
		this.aX = aX;
		this.aY = aY;
		this.bX = bX;
		this.bY = bY;
	}

	public CalculateNullVectorAngleException(String text,Exception innerEx){
		super(text,innerEx);
	}

	public CalculateNullVectorAngleException(String text){
		super(text);
	}

	@Override
	public String getMessage() {
		return "CalculateNullVectorAngleException. Wrong values: aX: " + aX + " aY: " + aY + " bX: " + bX + " bY: " + bY;
	}
}
