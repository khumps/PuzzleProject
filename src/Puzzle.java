import java.util.ArrayList;

public class Puzzle {

	private Board board;
	protected final int rows;
	protected final int cols;
	private Piece[] unusedPieces;

	private boolean isSolved = false;

	public Puzzle(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.board = new Board(rows, cols);
	}

	public Puzzle(int rows, int cols, Piece[] startingPieces) {
		this.rows = rows;
		this.cols = cols;
		this.board = new Board(rows, cols);
		unusedPieces = startingPieces;
	}

	public Puzzle(Board board) {
		this.rows = board.getRows();
		this.cols = board.getCols();
		this.board = board;
	}

	// checks piece1's edge with piece2's opposite edge
	public boolean matchEdge(Piece piece1, Piece piece2, int p1edge) {
		// figure out the opposite edge of p1edge
		int p2edge = (p1edge + 180) % 360;
		if (piece1.getEdge(p1edge) + piece2.getEdge(p2edge) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public Piece getPiece(int row, int col) {
		return board.getPiece(row, col);
	}

	public Piece setPiece(int row, int col, Piece piece) {
		// removeUsedPieceFromUnusedPieces(piece);
		return board.setPiece(row, col, piece);

	}

	public Piece removePiece(int row, int col) {
		return board.removePiece(row, col);
	}

	// copy refs of unused pieces into an array list
	// in the first call, should return all pieces
	public ArrayList<Piece> getUnusedPieces() {
		ArrayList<Piece> unused = new ArrayList(rows * cols);
		for (int i = 0; i < unusedPieces.length; i++) {
			if (unusedPieces[i] != null) {
				unused.add(unusedPieces[i]);
			}
		}
		return unused;
	}

	// restart the puzzle: clear the board and restore the unused pieces
	public void restart() {
		board.clear();
		isSolved = false;
	}

	// does the given piece's east edge fit in the given location
	public boolean checkEast(int r, int c, Piece piece) {
		if (r < 0 || r > rows)
			return false;
		if (c < 0 || c > cols - 1)
			return false;
		boolean fitEast = false;
		Piece p = getPiece(r, c + 1);
		if (p == null)
			fitEast = true;
		else {
			if (matchEdge(piece, p, 90))
				fitEast = true;
		}
		return fitEast;
	}

	// does the given piece's south edge fit in the given location
	public boolean checkSouth(int r, int c, Piece piece) {
		if (r < 0 || r > rows - 1)
			return false;
		if (c < 0 || c > cols)
			return false;
		boolean fitSouth = false;
		Piece p = getPiece(r + 1, c);
		if (p == null)
			fitSouth = true;
		else {
			if (matchEdge(piece, p, 180))
				fitSouth = true;
		}
		return fitSouth;
	}

	// does the given piece's north edge fit in the given location
	public boolean checkNorth(int r, int c, Piece piece) {
		if (r < 1 || r > rows)
			return false;
		if (c < 0 || c > cols)
			return false;
		boolean fitNorth = false;
		Piece p = getPiece(r - 1, c);
		if (p == null)
			fitNorth = true;
		else {
			if (matchEdge(piece, p, 0))
				fitNorth = true;
		}
		return fitNorth;
	}

	// does the given piece's west edge fit in the given location
	public boolean checkWest(int r, int c, Piece piece) {
		if (r < 0 || r > rows)
			return false;
		if (c < 1 || c > cols)
			return false;
		boolean fitWest = false;
		Piece p = getPiece(r, c - 1);
		if (p == null)
			fitWest = true;
		else {
			if (matchEdge(piece, p, 270))
				fitWest = true;
		}
		return fitWest;
	}

	// does a gven piece fit in a location
	public boolean doesFit(int row, int col, Piece piece) {
		boolean fitEast = false;
		boolean fitSouth = false;
		boolean fitNorth = false;
		boolean fitWest = false;
		// if the place is already occupied it doesn't fit
		if (getPiece(row, col) != null)
			return false;

		if (col == 0) {
			// first check if row is also 0, then check east and south
			if (row == 0) {
				if (checkEast(row, col, piece))
					fitEast = true;
				if (checkSouth(row, col, piece))
					fitSouth = true;
				fitWest = true;
				fitNorth = true;
			}// end of col = 0, row =0
				// else check if row = row - 1, then check north and east
			else if (row == rows - 1) {
				if (checkNorth(row, col, piece))
					fitNorth = true;
				if (checkEast(row, col, piece))
					fitEast = true;
				fitWest = true;
				fitSouth = true;
			}
			// all other cases, check north, east and south
			else {
				if (checkNorth(row, col, piece))
					fitNorth = true;
				if (checkEast(row, col, piece))
					fitEast = true;
				if (checkSouth(row, col, piece))
					fitSouth = true;
				fitWest = true;
			}
		} else if (row == 0) {
			// if col = col - 1, check west and south
			if (col == cols - 1) {
				if (checkWest(row, col, piece))
					fitWest = true;
				if (checkSouth(row, col, piece))
					fitSouth = true;
				fitEast = true;
				fitNorth = true;
			}
			// else check east, south and west
			else {
				if (checkEast(row, col, piece))
					fitEast = true;
				if (checkSouth(row, col, piece))
					fitSouth = true;
				if (checkWest(row, col, piece))
					fitWest = true;
				fitNorth = true;
			}// row and col both 0 is already taken care of
		}

		else if (col == cols - 1) {
			// row == 0 already taken care of
			// if row = rows - 1, check west and north
			if (row == rows - 1) {
				if (checkWest(row, col, piece))
					fitWest = true;
				if (checkNorth(row, col, piece))
					fitNorth = true;
				fitEast = true;
				fitSouth = true;
			}
			// all other cases, check north, west and south
			else {
				if (checkNorth(row, col, piece))
					fitNorth = true;
				if (checkWest(row, col, piece))
					fitWest = true;
				if (checkSouth(row, col, piece))
					fitSouth = true;
				fitEast = true;
			}
		}

		else if (row == rows - 1) {
			// col is 0 and row is rows - 1 already done, and so is row and col
			// = the last one
			// all other cases, check north, east, and west
			if (checkNorth(row, col, piece))
				fitNorth = true;
			if (checkEast(row, col, piece))
				fitEast = true;
			if (checkWest(row, col, piece))
				fitWest = true;
			fitSouth = true;
		}

		else {
			if (checkNorth(row, col, piece))
				fitNorth = true;
			if (checkEast(row, col, piece))
				fitEast = true;
			if (checkSouth(row, col, piece))
				fitSouth = true;
			if (checkWest(row, col, piece))
				fitWest = true;
		}

		return fitEast && fitSouth && fitNorth && fitWest;
	}

	// does any rotation of a given piece fit in a given location
	private boolean doesAnyRotationFit(int row, int col, Piece p) {
		boolean fit = false;
		boolean notdone = true;
		int i = 0;
		while (notdone) {
			p.rotate();
			i++;
			if (doesFit(row, col, p))
				fit = true;
			if (i == 4 || fit == true)
				notdone = false;
		}
		return fit;
	}

	// Right now it is not used
	// Does a given piece fit anywhere in the board
	// if so, return true and set the piece in the location
	private boolean doesFitInBoard(Piece p) {
		int i = 0;
		boolean done = false;
		while (!done && i < rows) {
			int j = 0;
			while (!done && j < cols) {
				if (board.hasPiece(i, j)) {
				} else if (doesAnyRotationFit(i, j, p)) {
					setPiece(i, j, p);
					done = true;
				}
				j++;
			}
			i++;
		}
		return done;
	}

	// generate a permutation of the pieces and see if it solves
	public void solve() {
		permute_and_test(unusedPieces, 0);
	}

	// recursive permutation of unused pieces
	// to be invoked with k = 0
	public void permute_and_test(Piece[] a, int k) {

		// base case
		if (isSolved)
			return;
		if (k == a.length) {
			// Test this permutation
			board.clear();
			ArrayList<Piece> al = getUnusedPieces();
			if (test_solution(al)) {
				isSolved = true;
				return;
			}

		} else {
			for (int i = k; i < a.length; i++) {
				// swap ith and k th elements
				Piece temp = a[k];
				a[k] = a[i];
				a[i] = temp;
				permute_and_test(a, k + 1);
				// swap back to how they were
				temp = a[k];
				a[k] = a[i];
				a[i] = temp;
			}
		}

	}

	// test_solution takes in the permutation of pieces to use provided by the
	// top solver and
	// tries to see if the given permutation is a solution
	// Since we are generating all permutation, we will be seeing if the 1st
	// piece fits in (0,0),
	// 2nd i (0,1), 3rd in (0,2), 4th in (1,0) etc
	// However, for each piece we have to see whether any rotation fits in that
	// given position

	public boolean test_solution(ArrayList<Piece> a) {
		boolean retval = true;
		Piece p;
		int index;
		boolean bb = false;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				index = i * cols + j;
				p = a.get(index);
				bb = doesAnyRotationFit(i, j, p);
				if (bb) {
					setPiece(i, j, p);
				} else {
					retval = false;
					return retval;
				}

			}
		}
		return retval;
	}

	// Puzzle is solved if isSolved is set to true
	public boolean isSolved() {
		return isSolved;
	}

	// print gameboard
	public String toString() {
		String s = "";
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Piece p = getPiece(i, j);
				String x;
				if (p != null)
					x = p.toString();
				else
					x = "[ , , , ]";
				s = s + "Row =" + i + " Col = " + j + " Piece = " + x + "\t\t";
			}
			s = s + "\n";
		}
		return s;
	}

	public static String arrayToString(Piece[] a) {
		String s = "";
		for (Piece n : a)
			s += n + " ";
		return s;
	}

	// creat a sample 3x3 game
	public static void main(String[] args) {
		Piece[] pieces = new Piece[9];

		pieces[0] = new Piece(Piece.CLUBS_OUT, Piece.HEARTS_OUT,
				Piece.DIAMONDS_IN, Piece.CLUBS_IN);
		pieces[1] = new Piece(Piece.SPADES_OUT, Piece.DIAMONDS_OUT,
				Piece.SPADES_IN, Piece.HEARTS_IN);
		pieces[2] = new Piece(Piece.HEARTS_OUT, Piece.SPADES_OUT,
				Piece.SPADES_IN, Piece.CLUBS_IN);
		pieces[3] = new Piece(Piece.HEARTS_OUT, Piece.DIAMONDS_OUT,
				Piece.CLUBS_IN, Piece.CLUBS_IN);
		pieces[4] = new Piece(Piece.SPADES_OUT, Piece.SPADES_OUT,
				Piece.HEARTS_IN, Piece.CLUBS_IN);
		pieces[5] = new Piece(Piece.HEARTS_OUT, Piece.DIAMONDS_OUT,
				Piece.DIAMONDS_IN, Piece.HEARTS_IN);
		pieces[6] = new Piece(Piece.SPADES_OUT, Piece.DIAMONDS_OUT,
				Piece.HEARTS_IN, Piece.DIAMONDS_IN);
		pieces[7] = new Piece(Piece.CLUBS_OUT, Piece.HEARTS_OUT,
				Piece.SPADES_IN, Piece.HEARTS_IN);
		pieces[8] = new Piece(Piece.CLUBS_OUT, Piece.CLUBS_IN,
				Piece.DIAMONDS_IN, Piece.DIAMONDS_OUT);
		Puzzle pz = new Puzzle(3, 3, pieces);
		for (int i = 0; i < pieces.length; i++)
			System.out.print(pieces[i].toString() + " ");
		System.out.println("\n  --Initial pieces-- \n");

		pz.solve();
		if (pz.isSolved()) {
			System.out.println("Solution is:");
			System.out.println(pz.toString());
			pz.restart();
			System.out.println("Restarted puzzle and calling solve again:");
			pz.solve();
			if (pz.isSolved()) {
				System.out.println("Solution after restart is:");
				System.out.println(pz.toString());
			}

		} else {
			System.out.println("There is no solution");
		}

	}

}
