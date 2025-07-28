package ch05;
import java.util.Scanner;
//Page329, P14, P14_GraphicEditApp, 작동, 그러나 정리 필요
abstract class Shape {
	private Shape next;
	protected int orderNum;
	public Shape() { next = null; }
	public void setNext(Shape obj) { next = obj; }
	public Shape getNext() { return next; }
	public abstract void draw();
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum (int orderNum) {
		this.orderNum = orderNum;
	}
}

class Line extends Shape {
	@Override
	public void draw() {
		System.out.println("Line");
	}
	
	public Line(int orderNum) {
		this.orderNum = orderNum;
	}
	

}
class Rect extends Shape {
	@Override
	public void draw() {
		System.out.println("Rect");
	}
	public Rect(int orderNum) {
		this.orderNum = orderNum;
	}
}
class Circle extends Shape {
	@Override
	public void draw() {
		System.out.println("Circle");
	}
	public Circle(int orderNum) {
		this.orderNum = orderNum;
	}
}

class GraphicEditor {
	private Shape startShape, last, obj2;
	private int cnt = 1;
	private boolean isExit = false;
	
	private String menu[] = {
					"삽입",
					"삭제",
					"모두 보기",
					"종료"
	};
	private String shapeOption[] = {
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
		for(int i = 0 ; i < shapeOption.length ; i++) {
			System.out.print(shapeOption[i] + "(" + (i+1) + ")");
			if (i == shapeOption.length-1) {
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
				startShape = new Line(cnt);
				break;
			case 2:
				startShape = new Rect(cnt);
				break;
			case 3:
				startShape = new Circle(cnt);
				break;
				default:					
			}
			last = startShape;
			cnt++;
		} else {
			switch (shapeNum) {
			case 1:
				obj2 = new Line(cnt);
				break;
			case 2:
				obj2 = new Rect(cnt);
				break;
			case 3:
				obj2 = new Circle(cnt);
				break;
				default:					
			}
			last.setNext(obj2);
			last = obj2;
			cnt++;
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
		Shape p = startShape;
		if(p != null && p.getOrderNum() == linkNum) {//1번을 지우려면
			if(p.getNext() != null) {//1번 다음이 있으면
				startShape = startShape.getNext();
				startShape.setOrderNum(linkNum);
				p = startShape.getNext();
				cnt--;
			}
			else {//1번만 있고 그걸 지우려면
				startShape = null;
				cnt--;
				return;
			}
			while (p != null) {//2번 이후도 있으면
				linkNum++;
				p.setOrderNum(linkNum);//순번을 당긴다
				p = p.getNext();
			}
			return;
		}
		while (p != null && p.getNext() != null) {//다음 도형이 있고
			if(p.getNext().getOrderNum() == linkNum) {//다음 도형이 삭제할 도형이고
				if (p.getNext().getNext() != null) { //다다음 도형이 있으면
					p.setNext(p.getNext().getNext());//다다음 도형에 연결한다
					p = p.getNext(); //다다음 -> 다음이 된 도형을 가져와서
					p.setOrderNum(linkNum);//순번 하나 당겨준다
					p = p.getNext(); //다음 연결을 가져와서
					cnt--;
					while (p != null) {
						linkNum++;
						p.setOrderNum(linkNum);//순번을 당긴다
						p = p.getNext();
					}
					return;
				}
				else {//다다음 도형은 없으며 마지막 도형을 삭제하는 거라면
					p.setNext(null); //다음을 삭제
					last = p;
					cnt--;
					return;
				}
				
			}
			else
				p = p.getNext();
		}
		System.out.println("삭제할 수 없습니다.");
	
	}
	
	public void paintAll() {
		Shape p = startShape;
		if(startShape == null) {
			System.out.println("그려둔 그림이 없습니다.");
			return;
		}
		while (p != null) {
			p.draw();
			System.out.println(p.getOrderNum());
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
				System.out.println("정수를 입력해주세요");
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
						System.out.println("정수를 입력해주세요");
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
