package ShapeEx;
//Page330, P15, ShapeEx
interface Shape {
	final double PI = 3.14;
	void draw();
	double getArea();
	default public void redraw() {
		System.out.print("--- 다시 그립니다. ");
		draw();
	}
}

class Circle implements Shape {
	private int radius;
	public Circle(int radius) {
		this.radius = radius;
	}
	@Override
	public void draw() {
		System.out.println("반지름이 " + radius + "인 원");
		
	}

	@Override
	public double getArea() { return PI * radius * radius; }
	
}
class Oval implements Shape {
	private int w, h;
	
	public Oval(int w, int h) {
		this.w = w;
		this.h = h;
	}
	@Override
	public void draw() {
		System.out.println(w + "x" + h + "에 내접하는 타원");
	}

	@Override
	public double getArea() { return w * h * PI; }
	
}
class Rect implements Shape {
	private int w, h;

	public Rect(int w, int h) {
		this.w = w;
		this.h = h;
	}
	@Override
	public void draw() {
		System.out.println(w + "x" + h + "크기의 사각형");
	}

	@Override
	public double getArea() { return w * h; }
	
}
public class ShapeEx {

	public static void main(String[] args) {
		Shape [] list = new Shape[3];
		list[0] = new Circle(5);
		list[1] = new Oval(20, 30);
		list[2] = new Rect(10, 40);
		
		for(int i = 0 ; i < list.length ; i++) list[i].redraw();

		for(int i = 0 ; i < list.length ; i++) System.out.println("면적은 " + list[i].getArea());
		
	}

}
