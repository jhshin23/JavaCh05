package ch05;
import java.util.Scanner;
//Page326, P09, P10
class BaseArray {
	protected int array [];
	protected int nextIndex = 0;
	public BaseArray(int size) {
		array = new int[size];
	}
	public int length() { return array.length; }
	public void add(int n) {
		if (nextIndex == array.length) return;
		array[nextIndex] = n;
		nextIndex++;
	}
	public void print() {
		for (int n : array) System.out.print(n + " ");
		System.out.println();
	}
}

class BinaryArray extends BaseArray {
	private int threshold;
	public BinaryArray(int size, int threshold) {
		super(size);
		this.threshold = threshold;
	}
	
	@Override
	public void add(int n) {
		if (nextIndex == array.length) return;
		array[nextIndex] = n > threshold? 1 : 0;
		nextIndex++;
	}
	
}

class SortedArray extends BaseArray {
	public SortedArray(int size) {
		super(size);
	}
	
	@Override
	public void add(int n) {
		if (nextIndex == array.length) return;
		if (nextIndex == 0) array[nextIndex] = n;
		else {
			if (array[nextIndex] <= n) array[nextIndex] = n;
			for (int i = (nextIndex-1) ; i >= 0 ; i--) {
				if (array[i] > n) {
					array[i+1] = array[i];
					array[i] = n;
				}
			}
			
		}
		nextIndex++;
	}
	
}


public class P09_10_ArrayClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int threshold = 50;
		BinaryArray bArray = new BinaryArray(10, threshold);
		
		System.out.println(">>");
		//10 20 30 40 50 60 70 80 90 100 -> 0 0 0 0 0 1 1 1 1 1 
		
		for (int i = 0 ; i < bArray.length() ; i++) {
			int n = scanner.nextInt();
			bArray.add(n);
		}
		bArray.print();
		
		scanner.nextLine();
		
		SortedArray sArray = new SortedArray(10);
		System.out.println(">>");
		//10 20 30 40 50 13 24 35 46 31 -> 10 13 20 24 30 31 35 40 46 50 
		for (int i = 0 ; i < sArray.length() ; i++) {
			int n = scanner.nextInt();
			sArray.add(n);
		}
		
		sArray.print();
		scanner.close();

	}

}
