package ch05;
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

	@Override
	public boolean consume() {
		return true;
	}

	@Override
	public void print() {
		for (int i = 0 ; i < size ; i++) {
			System.out.print("*");
		}
		System.out.println();
	}
	
}
public class CoffeeVendingMachine {
//다방커피 1 1 1		설탕커피 1 0 1		블랙커피 1 0 0 	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int SIZE = 3;
		IngredientBox[] allIngred = new IngredientBox[SIZE];

	}

}
