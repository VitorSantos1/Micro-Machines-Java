package racing.logic;

public class Point {
	
	double x;
	double y;
	
	public Point(){
		this.x = 0;
		this.y = 0;
	}
	
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}
	
	public void show() {
		System.out.println("X: " + this.x + "\n");
		System.out.println("Y: " + this.y + "\n");
	}
}
