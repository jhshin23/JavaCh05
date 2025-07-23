package ch05;

class Point {
	private int x, y;
	public Point(int x, int y) { this.x = x; this.y = y; }
	public int getX() { return x; }
	public int getY() { return y; }
	protected void move(int x, int y) { this.x = x; this.y = y; }
}
class ColorPoint extends Point {
	private String color;
	public ColorPoint(int x, int y, String color) {
		super(x, y);
		this.color = color;
	}
	public void setXY(int x, int y) {
		move(x, y);
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getColor() { return color; }
	public String toString() {
		return getColor() + "색의 (" + getX() + "," + getY() + ")의 점"; 
	}
}
public class P03_07_Point {

	public static void main(String[] args) {
		ColorPoint cp = new ColorPoint(5, 5, "RED");
		cp.setXY(10, 20);
		cp.setColor("BLUE");
		String str = cp.toString();
		System.out.println(str + "입니다.");
	}

}
