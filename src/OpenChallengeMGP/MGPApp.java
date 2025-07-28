package OpenChallengeMGP;
import java.util.Scanner;
import java.util.Random;
//Page312, OpenChallenge, 묵찌빠 만드는 중
class Human extends Player {

	protected Human(String name) {
		super(name);
	}

	@Override
	public String next() {
		return null;
	}
	
}
class Computer extends Player {

	protected Computer(String name) {
		super(name);
	}

	@Override
	public String next() {
		return null;
	}
	
}

class Game {
	Scanner scanner = new Scanner(System.in);
	Player [] players = new Player[2];
	public Game() {
		
	}
	public void run() {
		Player owner;
		System.out.println("***** 묵찌빠 게임을 시작합니다. *****");
		createPlayers();
		gameStart();
		owner = setOwner(players);
		while(true) {
			break;
		}
		scanner.close();
	}
	
	private void createPlayers() {
		System.out.print("선수이름 입력>>");
		players[0] = new Human(scanner.nextLine());
		System.out.print("컴퓨터이름 입력>>");
		players[1] = new Computer(scanner.nextLine());
		System.out.println(players.length + "명의 선수를 생성 완료하였습니다.");
	}
	private void betHuman() {
		System.out.print(players[0].getName() + ">>");
		String inputBet = scanner.nextLine();
		while(true) {
			if(inputBet.equals(players[0].bet[0]) || inputBet.equals(players[0].bet[1])|| inputBet.equals(players[0].bet[2])) {
				players[0].lastBet = inputBet;
				break;
			}
			else System.out.println("묵 찌 빠 중에서 다시 입력하세요. " );
		}
	}
	
	private void betComputer() {
		Random r = new Random();
		players[1].lastBet = players[1].bet[r.nextInt(players[1].bet.length)];
		System.out.println(players[1].getName() + ">> 결정하였습니다.");
	}
	private void gameStart() {
		betHuman();
		betComputer();
	}
	private Player setOwner(Player[] p) {
		Player owner = null;
		
		return players[0];
	}
}

public class MGPApp {

	public static void main(String[] args) {
		new Game().run();
		
	}

}
