package ch05;

class TV {
	private int size;
	public TV(int size) { this.size = size; }
	protected int getSize() { return size; }
}

class ColorTV extends TV {
	public ColorTV(int size, int colorNum) {
		super(size);
		this.colorNum = colorNum;		
	}
	private int colorNum;
	
	public int getColorNum() { return colorNum;	}
	
	public void printProperty() {
		System.out.println(getSize() + "인치 " + getColorNum() + "컬러");
	}
}

class SmartTV extends ColorTV {
	private String address;
	public SmartTV(String address, int size, int colorNum) {
		super(size, colorNum);
		this.address = address;
	}
	
	public String getAddress() { return address; }
	
	@Override
	public void printProperty() {
		System.out.print("나의 SmartTV는 " + getAddress() + " 주소의 ");
		super.printProperty();
	}
	
}
public class P01_02_TV {

	public static void main(String[] args) {
		ColorTV myTv = new ColorTV(65, 65536);
		myTv.printProperty();
		
		SmartTV smartTV = new SmartTV("192.168.0.5", 77, 20000000);
		smartTV.printProperty();
	}

}
