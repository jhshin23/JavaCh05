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

class ColorPoint2 extends Point {
	private String color;
	public ColorPoint2() {
		super(0, 0);
		color = "WHITE";
	}
	public ColorPoint2(int x, int y, String color) {
		super(x, y);
		this.color = color;
	}
	public ColorPoint2(int x, int y) {
		super(x, y);
		color = "BLACK";
	}
	
	public void set(int x, int y) { move(x, y); }
	public void set(String color) { this.color = color; }
	public String getColor() { return color; }
	public String toString() {
		return getColor() + "색의 (" + getX() + "," + getY() + ")의 점"; 
	}
	public double getDistance(ColorPoint2 thresholdPoint) {
		return Math.sqrt((this.getX() - thresholdPoint.getX()) * (this.getX() - thresholdPoint.getX()) + (this.getY() - thresholdPoint.getY()) * (this.getY() - thresholdPoint.getY()));
	}
}

class Point3D extends Point {
	private int z;
	public Point3D(int x, int y, int z) {
		super(x, y);
		this.z = z;
	}
	
	public int getZ() { return z; }
	
	@Override
	public String toString() {
		return "(" + getX() + "," + getY() + "," + getZ() + ")의 점";
	}
	
	public void move(int x, int y, int z) { 
		move(x, y); 
		this.z = z;
	}
	
	public void moveUp(int addZ) {
		this.z += addZ;
	}
	public void moveDown(int subZ) {
		this.z -= subZ;
	}
}

class PositivePoint extends Point {
	public PositivePoint(int x, int y) {
		super(1, 1);
		if(x > 0 && y > 0)
			move(x, y);
	}
	
	@Override
	protected void move(int x, int y) {
		if(x > 0 && y > 0)
			super.move(x, y);
	}
	
	public String toString() {
		return "(" + getX() + "," + getY() + ")의 점"; 
	}

}

class Point3DColor extends Point {
	private int z;
	private String color;

	public Point3DColor(int x, int y, int z, String color) {
		super(x, y);
		this.z = z;
		this.color = color;
	}
	
	public int getZ() { return z; }
	public String getColor() { return color; }
	
	public void setColor(String color) {
		this.color = color;
	}
	public void move(int x, int y, int z) { 
		move(x, y); 
		this.z = z;
	}
	
	public void move(Point3DColor desP) { 
		move(desP.getX(), desP.getY(), desP.getZ()); 
		setColor(desP.getColor());
	}
	
	@Override
	public String toString() {
		return "(" + getX() + "," + getY() + "," + getZ() + ")" + getColor() + "점";
	}
	
	public boolean equals(Point3DColor p) {
		if(getX() == p.getX() && getY() ==p.getY() && getColor() == p.getColor())
			return true;
		return false;
	}
}

public class P03_07_Point {

	public static void main(String[] args) {
		ColorPoint cp = new ColorPoint(5, 5, "RED");
		cp.setXY(10, 20);
		cp.setColor("BLUE");
		String str = cp.toString();
		System.out.println(str + "입니다.");
		
		ColorPoint2 zeroPoint = new ColorPoint2();
		System.out.println(zeroPoint.toString() + "입니다.");
		
		ColorPoint2 cp2 = new ColorPoint2(10, 10, "RED");
		
		cp2.set("BLUE");
		cp2.set(10, 20);
		System.out.println(cp2.toString() + "입니다.");
		
		ColorPoint2 thresholdPoint = new ColorPoint2(100, 100);
		
		System.out.println("cp2에서 임계점까지의 거리는 " + cp2.getDistance(thresholdPoint));
		
		
		Point3D p = new Point3D(3, 2, 1);
		System.out.println(p.toString() + "입니다.");
		
		p.moveUp(3);
		System.out.println(p.toString() + "입니다.");
		
		p.moveDown(2);
		System.out.println(p.toString() + "입니다.");
		
		p.move(5, 5);
		System.out.println(p.toString() + "입니다.");
		
		p.move(100, 200, 300);
		System.out.println(p.toString() + "입니다.");
		
		
		PositivePoint pp = new PositivePoint(10, 10);
		System.out.println(pp.toString() + "입니다.");
		
		pp.move(5, 5);
		System.out.println(pp.toString() + "입니다.");
		
		pp.move(2, -2);
		System.out.println(pp.toString() + "입니다.");
		
		PositivePoint q = new PositivePoint(-10, -10);
		System.out.println(q.toString() + "입니다.");
		
		
		
		Point3DColor p3c = new Point3DColor(10, 20, 30, "RED");
		System.out.println(p3c.toString() + "입니다.");
		
		Point3DColor q3c = new Point3DColor(1, 2, 3, "BLUE");
		p3c.move(q3c);
		System.out.println(p3c.toString() + "입니다.");
		
		Point3DColor r3c = new Point3DColor(1, 2, 3, "BLUE");
		
		if(p3c.equals(r3c)) System.out.println("예. 같은 위치 같은 색깔의 점입니다.");
		else System.out.println("아니오");
		
	}
	
	

}
