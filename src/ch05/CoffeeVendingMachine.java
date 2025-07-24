package ch05;
import java.util.InputMismatchException;
import java.util.Scanner;

abstract class Box {
	protected int size;
	public Box(int size) {
		this.size = size;
	}
	public boolean isEmpty() { return size == 0; }
	public abstract boolean consume();
	public abstract void print();
}

class IngredientBox extends Box {
	private String name;
	public IngredientBox(String name, int size) {
		super(size);
		this.name = name;
	}

	public String getName() { return name; }
	
	@Override
	public boolean consume() {
		if(size > 0) {
			size--;
			return true;
		}
		else 
			return false;
	}

	@Override
	public void print() {
		
		if(size > 0) {
			for (int i = 0 ; i < size ; i++) {
				System.out.print("*");
			}
			System.out.print(size);
			System.out.println();
		}
		else {
			System.out.print(size);
			System.out.println();
		}
	}
	
}
public class CoffeeVendingMachine {
//다방커피 1 1 1		설탕커피 1 0 1		블랙커피 1 0 0 	
	private final int INGREDIENT_INIT_SIZE = 5;
	
	private final int 커피 = 0;
	private final int 프림 = 1;
	private final int 설탕 = 2;
	private final int[][] recipe = { 
			{ 커피, 프림, 설탕 },
			{ 커피, 설탕 },
			{ 커피 }	
	};
	
	private boolean isExit = false;
	
	private final String[] menuName = { "다방커피", "설탕커피", "블랙커피" };
	private final String[] IngredientName = { "커피", "프림", "설탕" };
	private IngredientBox[] allBox = new IngredientBox[IngredientName.length];
	
	public CoffeeVendingMachine() {
		for (int i = 0 ; i <  IngredientName.length ; i++) {
			allBox[i] = new IngredientBox(IngredientName[i], INGREDIENT_INIT_SIZE);
		}
		System.out.println("*****청춘 커피 자판기 입니다.*****");
	}
	
	public int getOptionSize() { return menuName.length + 1; }
	public boolean isExit() { return isExit; }
	
	public void run() {
		for (int i = 0 ; i < allBox.length ; i++) {
			System.out.print(allBox[i].getName() + " ");
			allBox[i].print();
		}
		
		for (int i = 0 ; i < menuName.length + 1 ; i++) {
			if (i < menuName.length) {
				System.out.print(menuName[i] + ":" + (i+1) + ", ");
			}
			else
				System.out.print("종료:" + (menuName.length + 1) + ">>");
		}
		
	}
	
	private void makeMenu(int menuNum) {
		for (int i = 0 ; i < recipe[menuNum].length ; i++) {
			if(allBox[recipe[menuNum][i]].isEmpty()) {
				System.out.println("원료가 부족합니다");
				return;
			}
		}
		for (int i = 0 ; i < recipe[menuNum].length ; i++) {
			allBox[recipe[menuNum][i]].consume();
		}
	}
	
	public void doByOrder(int optionNum) {
		if (optionNum >= 1 && optionNum <= menuName.length)
			makeMenu(optionNum-1);
		if (optionNum == menuName.length + 1) {
			isExit = true;
			System.out.println("청춘 커피 자판기 프로그램을 종료합니다");			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		CoffeeVendingMachine ccc = new CoffeeVendingMachine();
		
		while (ccc.isExit() == false) {
			ccc.run();
			int optionNum;
			do {
				try {
					optionNum = scanner.nextInt();
					if (optionNum < 1 || optionNum > ccc.getOptionSize()) {
						System.out.println("메뉴 숫자 " + 1 + "부터 " + ccc.getOptionSize() + " 중에서 입력해주세요.");
						continue;
					}
					break;
				}
				catch (InputMismatchException e) {
					scanner.nextLine();
					System.out.println("메뉴를 숫자로 입력해주세요.");
					continue;
				}				
			} while(true);
			ccc.doByOrder(optionNum);
		}
		
		scanner.close();
	}

}
