
import java.util.HashSet;
import java.util.Set;

public class DomineeringBoard extends Board<DomineeringMove> {

	private static final Player H = Player.MAXIMIZER;
	private static final Player V = Player.MINIMIZER; 

	private final Player turn;
	private final boolean[][] board;
	/*
	 * board is false if there is no domino there and true if there is.
	 */

	public DomineeringBoard() { // the default board is 4 by 4
		this.board = new boolean[4][4];
		this.turn = Player.MAXIMIZER;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = false;
			}
		}
	}

	public DomineeringBoard(int width, int height) {// create a board with
													// custom width and height
		this.board = new boolean[width][height];
		this.turn = Player.MAXIMIZER;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = false;
			}
		}
	}
	
	private DomineeringBoard(boolean[][] board, Player turn){	 // create a new board in the tree

		this.turn = turn;
		this.board = board;
		System.out.println(this.toString());

	}

	@Override
	Set<DomineeringMove> availableMoves() {
		HashSet<DomineeringMove> availableMoves = new HashSet<DomineeringMove>();
		
		if(turn == H){
			for (int x = 0; x < this.board.length - 1; x++) {
				for (int y = 0; y < this.board[0].length; y++) {
					if (!this.board[x+1][y] && !this.board[x][y]) {
						availableMoves.add(new DomineeringMove(x, y, x+1, y));
					}
				}
			}
		return availableMoves;
		} else {
			for (int x = 0; x < this.board.length ; x++) {
				for (int y = 0; y < this.board[0].length -1; y++) {
					if (!this.board[x + 1][y] && !this.board[x ][y]) {
						availableMoves.add(new DomineeringMove(x, y, x, y+1));
					}
				}
			}
			return availableMoves;
			
		}
		
			

	}

	@Override
	int value() {
		if(availableMoves().isEmpty()){
			if(this.turn == Player.MAXIMIZER){
				return -1;
			}
			else{
				return 1;
			}
		}
		return 0;
	}

	@Override
	Board<DomineeringMove> play(DomineeringMove move) {
		boolean[][] newBoard = new boolean[this.board.length][this.board[0].length];
		
		for (int x = 0; x < newBoard.length; x++) {
			for (int y = 0; y < newBoard[0].length; y++) {
				newBoard[x][y] = this.board[x][y];
			}
		}
		
		newBoard[move.x1][move.y1] = true;
		newBoard[move.x2][move.y2] = true;
		
		
		return new DomineeringBoard(newBoard, nextPlayer());
	}

	@Override
	Player nextPlayer() {
		int noMoves = 0;
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if(board[i][j] = true){
					noMoves++;
				}
			}
		}
		noMoves = noMoves / 2;  
		return noMoves % 2 == 0 ? H : V;
	}

	public String toString() {
		String string = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {

				if (board[i][j] == false) {
					string += "|F";
				} else {
					string += "|T";
				}
			}
			string += "|" + "\n";
		}

		return string;

	}
}
