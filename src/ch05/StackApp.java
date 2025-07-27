package ch05;
import java.util.Scanner;
import java.util.InputMismatchException;

interface IStack {
	int capacity();
	int length();
	boolean push(String val);
	String pop();
}

class StringStack implements IStack {
	private int capacity;
	private String stack[];
	private int nextIndex = 0;
	
	public StringStack(int capacity) {
		this.capacity = capacity;
		stack = new String[capacity];
	}
	
	@Override
	public int capacity() { return capacity; }

	@Override
	public int length() { return nextIndex; }

	@Override
	public boolean push(String val) {
		if (nextIndex < stack.length) {
			stack[nextIndex] = val;
			nextIndex++;
			return true;
		}
		return false;
	}

	@Override
	public String pop() {
		String t = null;
		t = stack[nextIndex-1];
		stack[nextIndex-1] = null;
		nextIndex--;
		return t;
	}
	
}

public class StackApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringStack ss;
		System.out.print("스택 용량>>");
		do {
			try {
				ss = new StringStack(scanner.nextInt());
				scanner.nextLine();
				break;
			}
			catch(InputMismatchException e) {
				System.out.println("용량을 정수로 입력");
				scanner.nextLine();
				continue;
			}
		} while(true);
		
		while(true) {
			System.out.print("문자열 입력>>");
			String val = scanner.nextLine();
			if (val.equals("그만")) {
				System.out.print("스택에 저장된 문자열 팝 : ");
				int length = ss.length();
				for(int i = 0 ; i < length ; i++)
					System.out.print(ss.pop() + " ");
				break;
			}
			if (ss.push(val)) continue; 
			else System.out.println("스택이 꽉 차서 " + val + " 저장 불가");
		}
		
		scanner.close();
	}

}
