package ch05;
import java.util.Scanner;
//Page328, P13, P13_Calculator
abstract class Calc {
	protected String errorMsg;
	protected int a, b;
	public void setValue(int a, int b) {
		this.a = a;
		this.b = b;
	}
	public abstract int calculate();
	
}

class Add extends Calc {

	@Override
	public int calculate() { return a + b; }
	
}
class Sub extends Calc {

	@Override
	public int calculate() { return a - b; }
	
}
class Mul extends Calc {

	@Override
	public int calculate() { return a * b; }
	
}
class Div extends Calc {
	
	public boolean isError() {
		if (errorMsg != null)
			return true;
		else return false;
	}
	
	public String getErrorMsg() { return errorMsg; }
	
	@Override
	public void setValue(int a, int b) {
		if (b == 0) {
			errorMsg = "0으로 나눌수 없음.";
		}
		this.a = a;
		this.b = b;
		
	}
	@Override
	public int calculate() { return a / b; }
	
}


public class P13_Calculator {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int a, b;
		boolean exit = false;
		
		do {
			System.out.print("두 정수와 연산자를 입력하시오>>");
			String input = scanner.nextLine();
			String num2Op1[] = input.split(" ");
			if(num2Op1.length < 3) continue;
			try {
				a = Integer.parseInt(num2Op1[0]);
				b = Integer.parseInt(num2Op1[1]);
			}
			catch (NumberFormatException e) {
				System.out.println("정수 정수 연산자 순서대로 입력해주세요");
				continue;
			}
			switch (num2Op1[2]) {
			case "+":
				Add add = new Add();
				add.setValue(a, b);
				System.out.println(add.calculate());
				break;
			case "-":
				Sub sub = new Sub();
				sub.setValue(a, b);
				System.out.println(sub.calculate());
				break;
			case "*":
				Mul mul  = new Mul();
				mul.setValue(a, b);
				System.out.println(mul.calculate());
				break;
			case "/":
				Div div = new Div();
				div.setValue(a, b);
				exit = div.isError();
				if(exit) {
					System.out.print(div.getErrorMsg());
					break;
				}
				System.out.println(div.calculate());
				break;
				default:
					System.out.println("연산자 +, -, *, / 중 하나를 입력해주세요.");
					continue;
			}
			if(exit) {
				System.out.println("프로그램 종료");
				break;
			}
		} while(true);
		
		scanner.close();

	}

}
