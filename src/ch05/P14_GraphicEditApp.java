package ch05;
import java.util.Scanner;
//Page329, P14, P14_GraphicEditApp, delete() 작성중
abstract class Shape {
	private Shape next;
	public Shape() { next = null; }
	public void setNext(Shape obj) { next = obj; }
	public Shape getNext() { return next; }
	public abstract void draw();
}

class Line extends Shape {
	@Override
	public void draw() {
		System.out.println("Line");
	}
}
class Rect extends Shape {
	@Override
	public void draw() {
		System.out.println("Rect");
	}
}
class Circle extends Shape {
	@Override
	public void draw() {
		System.out.println("Circle");
	}
}

class GraphicEditor {
	private Shape startShape, last, obj2;
	private boolean isExit = false;
	
	private String menu[] = {
					"삽입",
					"삭제",
					"모두 보기",
					"종료"
	};
	private String ShapeOption[] = {
			"Line",
			"Rect",
			"Circle"
	};
	
	
	public GraphicEditor() {
		System.out.println("그래픽 에디터 Beauty Graphic Editor를 실행합니다.");
	}
	
	public boolean isExit() { return isExit; }
	
	public void printMenu() {
		for(int i = 0 ; i < menu.length ; i++) {
			System.out.print(menu[i] + "(" + (i+1) + ")");
			if (i == menu.length-1) {
				System.out.print(">>");
				break;
			}	
			System.out.print(", ");
		}		
	}
	public void printShapeOption() {
		for(int i = 0 ; i < ShapeOption.length ; i++) {
			System.out.print(ShapeOption[i] + "(" + (i+1) + ")");
			if (i == ShapeOption.length-1) {
				System.out.print(">>");
				break;
			}	
			System.out.print(", ");
		}		
	}
	
	public boolean start(int optionNum) {
		switch (optionNum) {
		case 1:
			printShapeOption();
			return true;
		case 2:
			System.out.print("삭제할 도형의 위치>>");
			return true;
		case 3:
			paintAll();
			return false;
		case 4:
			System.out.println("Beauty Graphic Editor를 종료합니다.");
			isExit = true;
			return false;
				
		default: return false;
		}
	}
	
	public void insert(int shapeNum) {
		if (startShape == null) {
			switch (shapeNum) {
			case 1:
				startShape = new Line();
				break;
			case 2:
				startShape = new Rect();
				break;
			case 3:
				startShape = new Circle();
				break;
				default:					
			}
			last = startShape;
		} else {
			switch (shapeNum) {
			case 1:
				obj2 = new Line();
				break;
			case 2:
				obj2 = new Rect();
				break;
			case 3:
				obj2 = new Circle();
				break;
				default:					
			}
			last.setNext(obj2);
			last = obj2;
		}
	}
	
	public void update(int optionNum, int modiNum) {
		switch (optionNum) {
		case 1:
			insert(modiNum);
			break;
		case 2:
			delete(modiNum);
			break;
			default:
		}
	}
	
	public void delete(int linkNum) {
		
	}
	
	public void paintAll() {
		Shape p = startShape;
		while (p != null) {
			p.draw();
			p = p.getNext();
		}
	}
}
public class P14_GraphicEditApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int optionNum;
		
		GraphicEditor ge = new GraphicEditor();
		
		do {
			ge.printMenu();
			try {
				String optionInput = scanner.nextLine();
				optionNum = Integer.parseInt(optionInput);
				if(optionNum < 1 || optionNum > 4) continue;
			} 
			catch (NumberFormatException e) {
				continue;
			}
			if(ge.start(optionNum)) {
				int modiNum;
				do {
					try {
						String shapeInput = scanner.nextLine();
						modiNum = Integer.parseInt(shapeInput);
						if(optionNum < 1 || optionNum > 3) continue;
						break;
					} 
					catch (NumberFormatException e) {
						continue;
					}
				} while (true);
				ge.update(optionNum, modiNum);				
			}
			
			if (ge.isExit()) break;
		} while(true);
		
		scanner.close();
		
	}

}
