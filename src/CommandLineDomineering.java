import java.util.Scanner;

public class CommandLineDomineering implements MoveChannel<DomineeringMove> {

	private Player player;
	public CommandLineDomineering(Player player){
		this.player = player ;
	}
	@Override
	public DomineeringMove getMove() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("input move in form x1, y1,(comma included): ");
		String input = scanner.nextLine();

		int x = getX(input);
		int y = getY(input);
		if (this.player == Player.MAXIMIZER){
			return new DomineeringMove(x, y, x + 1, y);
		} else {
			return new DomineeringMove(x, y, x, y +1);
		}
		
	}

	private int getX(String input) {
		int commaAt = input.indexOf(',');
		if (commaAt == -1) { // null
			return -1;
		}
		String xString = input.substring(0, commaAt);
		int x = Integer.parseInt(xString);

		return x;
	}

	private int getY(String input) {
		int commaAt = input.indexOf(',');
		if (commaAt == -1) { // null
			return -1;
		}
		String yString = input.substring(commaAt + 1);
		int y = Integer.parseInt(yString);

		return y;
	}

	@Override
	public void giveMove(DomineeringMove move) {
		System.out.println(move);

	}

	@Override
	public void end(int value) {
		System.out.println("result : " + value);
	}

	@Override
	public void comment(String msg) {
		System.out.println(msg);

	}

	public static void main(String[] args) {

		DomineeringBoard board = new DomineeringBoard(4, 4);
		System.out.println(board);
		board.tree().firstPlayer(new CommandLineDomineering(Player.MAXIMIZER));
		//board.tree().secondPlayer(new commandLineDom());
	}

}
