public class Board {
	private Piece[][] board;
	private int rows;
	private int cols;

	public Board(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		board = new Piece[rows][cols];
	}

	public Board(int square) {
		this.rows = square;
		this.cols = square;
		board = new Piece[rows][cols];
	}

	public Piece setPiece(int row, int col, Piece p) {
		Piece temp = board[row][col];
		board[row][col] = p;
		return temp;
	}

	public Piece removePiece(int row, int col) {
		Piece p = board[row][col];
		board[row][col] = null;
		return p;
	}

	public Piece getPiece(int row, int col) {
		if (isValid(row, col))
			return board[row][col];
		return null;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public String toString() {
		String a = "";
		for (int i = 0; i < board[0].length; i++) {
			for (int j = 0; j < board.length; j++) {
				a += "   " + board[i][j].getEdge(1);
			}
			a += "\n";
			for (int k = 0; k < board.length; k++) {
				a += board[i][k].getEdge(2) + "   " + board[i][k].getEdge(-2);
			}
			a += "\n";
			for (int l = 0; l < board.length; l++) {
				a += "   " + board[i][l].getEdge(-1);
			}
			a += "\n";
		}
		return a;
	}

	public void clear() {
		Piece[][] clear = new Piece[rows][cols];
		board = clear;
	}

	public boolean isValid(int row, int col) {
		if (board[row][col] == null)
			return false;
		return true;
	}

	public boolean hasPiece(int row, int col) {
		if (this.getPiece(row, col) != null)
			return true;
		else
			return false;

	}
}
