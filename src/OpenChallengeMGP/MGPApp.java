package OpenChallengeMGP;
import java.util.Scanner;
import java.util.Random;
//Page312, OpenChallenge, 작동함
//Human 클래스 next()구현방법을 모르겠음,
//next()가 결정하고 입력과 다르면 거르고 다시 결정하게 만드는 식으로 사용
//같으면 인간 객체 필드에 직접 대입
class Human extends Player {

	protected Human(String name) {
		super(name);
	}

	@Override
	public String next() {
		Random r = new Random();
		lastBet = bet[r.nextInt(bet.length)];
		return lastBet;
	}
	
}
class Computer extends Player {

	protected Computer(String name) {
		super(name);
	}

	@Override
	public String next() {
		Random r = new Random();
		System.out.println(getName() + ">> 결정하였습니다.");
		lastBet = bet[r.nextInt(bet.length)];
		return lastBet;
	}
}

class Game {
	Scanner scanner = new Scanner(System.in);
	Player [] players = new Player[2];
	private final int HUMAN = 0;
	private final int COMPUTER = 1;
	public Game() {}
	public void run() {
		Player owner;
		System.out.println("***** 묵찌빠 게임을 시작합니다. *****");
		createPlayers();
		owner = players[HUMAN];  //실수: createPlayers() 다음에 대입해야했음
		turnPlayer(owner);
		while(true) {
			if(judge(players)) {
				System.out.println(owner.getName() + "이 이겼습니다.");
				System.out.println("게임을 종료합니다.");
				break;
			}
			Player newOwner = setOwner(players);
			if (newOwner != owner) {
				System.out.println("오너가 " + newOwner.getName() + "으로 변경되었습니다.");
				owner = newOwner;
			}
			turnPlayer(owner);
		}
		scanner.close();
	}
	
	private void createPlayers() {
		System.out.print("선수이름 입력>>");
		players[HUMAN] = new Human(scanner.nextLine());
		System.out.print("컴퓨터이름 입력>>");
		players[COMPUTER] = new Computer(scanner.nextLine());
		System.out.println(players.length + "명의 선수를 생성 완료하였습니다.");
	}
	private void betHuman() {
		while(true) {
			System.out.print(players[HUMAN].getName() + ">>");
			String input = scanner.nextLine();
			if (input.equals(players[HUMAN].bet[0]) || input.equals(players[HUMAN].bet[1]) || input.equals(players[HUMAN].bet[2])) {
				players[HUMAN].lastBet = input;
				break;
			}
			else {
				System.out.println("묵 찌 빠 중에서 다시 입력하세요. " );
				continue;
			}
		}
	}
	
	private void turnPlayer(Player owner) {
		if(owner == players[HUMAN]) {
			betHuman();
			players[COMPUTER].next();
		}
		else {
			players[COMPUTER].next();
			betHuman();
		}
		System.out.println(players[HUMAN].getName() + " : " + players[HUMAN].getBet() + ", " + players[COMPUTER].getName() + " : " + players[COMPUTER].getBet() );
	}
	private Player setOwner(Player[] p) {
		
		//bet[0, 1, 2]->묵 찌 빠
		//0->1->2->0 
		//비김?->윗줄에서 루프 탈출->실행안되니까 고려할 필요없음
		int human, ai;
		Player owner = null;

		for (human = 0 ; human < p[HUMAN].bet.length ; human++) {
			if(p[HUMAN].getBet().equals(p[HUMAN].bet[human]))
				break;
		}
		for (ai = 0 ; ai < p[COMPUTER].bet.length ; ai++) {
			if(p[COMPUTER].getBet().equals(p[COMPUTER].bet[ai]))
				break;
		}
		
		if (human == 0) {
			if(ai == 1)
				owner = p[HUMAN];
			if(ai == 2)
				owner = p[COMPUTER];
		}
		if (human == 1) {
			if(ai == 0)
				owner = p[COMPUTER];
			if(ai == 2)
				owner = p[HUMAN];
		}
		if (human == 2) {
			if(ai == 0)
				owner = p[HUMAN];
			if(ai == 1)
				owner = p[COMPUTER];
		}
		
		return owner;
	}
	
	private boolean judge(Player[] p) {
		if(p[HUMAN].getBet().equals(p[COMPUTER].getBet())) return true;
		else return false;
	}
}

public class MGPApp {

	public static void main(String[] args) {
		new Game().run();
	}
}
