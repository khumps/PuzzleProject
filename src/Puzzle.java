import java.util.ArrayList;
import java.util.List;


public class Puzzle {

	private Board board;
	protected int rows;
	protected int cols;
	private Piece[] unusedPieces;
	private boolean isSolved = false;
	
	public Puzzle(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		this.board = new Board(rows, cols);
	}

	public Puzzle(int rows, int cols, Piece[] startingPieces){
		this.rows = rows;
		this.cols = cols;
		this.board = new Board(rows, cols);
		unusedPieces = startingPieces;
	}

	public Puzzle(Board board){
		this.rows = board.getRows();
		this.cols = board.getCols();
		this.board = board;
	}
	//restart the board
	
		public void restart(){
			int c = 0;
			Piece[] a = new Piece[rows * cols];
			for(int b = 0; b < a.length; b++){
				if(unusedPieces[b] != null){
					a[c] = unusedPieces[b];
					c++;
				}
			}
			for(int i = 0; i < rows; i++){
				for(int j = 0; j < cols; j++){
					if(board.getPiece(i, j) != null){
						a[c] = board.removePiece(i, j);
						c++;
					}
				}
			}
			unusedPieces = a;
		}
	
	//checks piece1's edge with piece2's opposite edge
	public boolean matchEdge(Piece piece1, Piece piece2, int p1edge){
		//figure out the opposite edge of p1edge 
		int p2edge = (p1edge + 180) % 360;
		if(piece1.getEdge(p1edge) + piece2.getEdge(p2edge)== 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public Piece getPiece(int row, int col){
		return board.getPiece(row, col);
	}
	
	public Piece setPiece(int row, int col, Piece piece){
		return board.setPiece(row, col, piece);
	}

	public Piece removePiece(int row, int col){
		return board.removePiece(row, col);
	}
	
	//copy unused pieces into an array list
	//in the first call, should return all pieces
	//Question: should the unusedPices reflect the current status- i.e., should we remove pieces from it
	//as they get used? 
	public ArrayList<Piece> getUnusedPieces(){
		ArrayList<Piece> unused = new ArrayList(rows*cols);
		System.out.println("-----");
		for(int i = 0; i < unusedPieces.length; i++){
			if (unusedPieces[i] != null){
			    System.out.println("     adding " + unusedPieces[i].toString());
			    unused.add(unusedPieces[i]);
			}
		}
		System.out.println("-----");
		return unused;	
	}
	
	//Question: do we need this? 
	// remove a used piece from the unusedPieces array
	private int removeUsedPieceFromUnusedPieces(Piece p){
		int index = -1;
		for(int i = 0; i < unusedPieces.length; i++){
			if (unusedPieces[i] == p) {
				unusedPieces[i] = null;
				index = i;
			}
		}
		return index;
	}
	
	
	//does the given piece's east edge fit in the given location 
	public boolean checkEast(int r, int c, Piece piece){
		if(r < 0 || r > rows) return false;
		if(c < 0 || c > cols - 1) return false;
		boolean fitEast = false;
		Piece p = getPiece(r, c +1);
		if (p== null)
			fitEast = true;
		else{
			if(matchEdge(piece, p, Piece.EAST))
				fitEast = true;		
		}
		return fitEast;
	}
	//does the given piece's south edge fit in the given location 
	public boolean checkSouth(int r, int c, Piece piece){
		if(r < 0 || r > rows - 1) return false;
		if(c < 0 || c > cols) return false;
		boolean fitSouth = false;
		Piece p = getPiece(r+1, c);
		if (p== null)
			fitSouth = true;
		else {
			if(matchEdge(piece, p, Piece.SOUTH))
				fitSouth = true;
		}
		return fitSouth;
	}
	//does the given piece's north edge fit in the given location 
	public boolean checkNorth(int r, int c, Piece piece){
		if( r < 1 || r > rows) return false;
		if(c < 0 || c > cols) return false;
		boolean fitNorth = false;
		Piece p = getPiece(r - 1, c);
		if(p == null)
			fitNorth = true;
		else{
			if(matchEdge(piece, p, Piece.NORTH))
				fitNorth = true;
		}
		return fitNorth;
	}
	
	//does the given piece's west edge fit in the given location 
	public boolean checkWest(int r, int c, Piece piece){
		if( r < 0 || r > rows) return false;
		if(c < 1 || c > cols) return false;
		boolean fitWest = false;
		Piece p = getPiece(r, c - 1);
		if(p == null)
			fitWest = true;
		else{
			if(matchEdge(piece, p, Piece.WEST))
				fitWest = true;
		}
		return fitWest;	
	}
		
	//does a gven piece fit in a location 
	public boolean doesFit(int row, int col, Piece piece){
		boolean fitEast = false;
		boolean fitSouth = false;
		boolean fitNorth = false;
		boolean fitWest = false;
		//if the place is already occupied it doesn't fit
		if(getPiece(row,col) != null) return false;
		
		if(col == 0){
			//first check if row is also 0, then check east and south
			if(row == 0){
				if(checkEast(row,col, piece))
					fitEast = true;
				if(checkSouth(row,col, piece))
					fitSouth = true;
				fitWest = true;
				fitNorth = true;	
			}//end of col = 0, row =0
			//else check if row = row - 1, then check north and east
			else if(row == rows - 1){
				if(checkNorth(row, col, piece))
					fitNorth = true;
				if(checkEast(row,col,piece))
					fitEast = true;
				fitWest = true;
				fitSouth = true;
			}
			//all other cases, check north, east and south
			else{
				if(checkNorth(row,col,piece))
					fitNorth = true;
				if(checkEast(row,col,piece))
					fitEast = true;
				if(checkSouth(row,col,piece))
					fitSouth = true;
				fitWest = true;	
			}		
		}	
		else if(row == 0){
			//if col = col - 1, check west and south
			if(col == cols - 1){
				if(checkWest(row,col,piece))
					fitWest = true;
				if(checkSouth(row,col,piece))
					fitSouth = true;
				fitEast = true;
				fitNorth = true;	
			}
			//else check east, south and west
			else{
				if(checkEast(row,col,piece))
					fitEast = true;
				if(checkSouth(row,col,piece))
					fitSouth = true;
				if(checkWest(row,col,piece))
					fitWest = true;
				fitNorth = true;	
			}//row and col both 0 is already taken care of 
		}
		
		else if(col == cols - 1){
			//row == 0 already taken care of
			//if row = rows - 1, check west and north
			if(row == rows - 1){
				if(checkWest(row,col,piece))
					fitWest = true;
				if(checkNorth(row,col,piece))
					fitNorth = true;
				fitEast = true;
				fitSouth = true;	
			}
			//all other cases, check north, west and south
			else{
				if(checkNorth(row,col,piece))
					fitNorth = true;
				if(checkWest(row,col,piece))
					fitWest = true;
				if(checkSouth(row,col,piece))
					fitSouth = true;
				fitEast = true;	
			}
		}
		
		else if(row == rows - 1){
			//col is 0 and row is rows - 1 already done, and so is row and col = the last one
			//all other cases, check north, east, and west
			if(checkNorth(row,col,piece))
				fitNorth = true;
			if(checkEast(row,col,piece))
				fitEast = true;
			if(checkWest(row,col,piece))
				fitWest = true;
			fitSouth = true;	
		}
		
		else{
			if(checkNorth(row,col,piece))
				fitNorth = true;
			if(checkEast(row,col,piece))
				fitEast = true;
			if(checkSouth(row,col,piece))
				fitSouth = true;
			if(checkWest(row,col,piece))
				fitWest = true;	
		}
		
		return fitEast && fitSouth && fitNorth && fitWest;	
	}
	
	// does any rotation of a given piece fit in a given location
	private boolean doesAnyRotationFit(int row, int col, Piece p){
		boolean fit = false;
		boolean notdone =true;
		int i =0;
		while (notdone) {
			p.rotate();
			System.out.println("   Trying: " + p.toString() + " at " + row + "," + col + " in doesAnyRotationFit" );
			i++;
			if (doesFit(row,col, p))
				fit= true;
			if (i==4 || fit == true)
				notdone = false;
		}
	  return fit;
	}
	
	//does a given piece fit anywhere in the board
	//if so, return true and set the piece in the location
	private boolean doesFitInBoard(Piece p){
		int i = 0;
		boolean done = false;
		while (!done && i< rows ){
			int j = 0;
			while(!done && j < cols){
				System.out.println("       DoesFitInBoard " + p.toString() + " testing  ["+ i +"," + j +"]");
				if(board.hasPiece(i, j)){
					System.out.println("         is occupied");
				}
				else if (doesAnyRotationFit(i, j, p)){
					setPiece (i,j, p);
					System.out.println("       Piece: " + p.toString() + " fits in ["+ i +"," + j +"]");
					done = true;
				}
				j++;
			}
			i++;
		}
		return done;
	}
	
	//recursive solver
	//basis case: if unused pieces array list is empty, return
	//            other terminating condition?
	public boolean trysolve(Board b, ArrayList<Piece> u){
		if(u.size() == 0)
			return true;
		Piece p = u.remove(0);
		removeUsedPieceFromUnusedPieces(p);
		System.out.println(getUnusedPieces());
		//if current piece fits anywhere, try to solve with the rest of the pieces
		System.out.println("   Trying   Piece: " + p.toString() );
		if (doesFitInBoard(p)) {
			return trysolve(b, u);	
		}
		else
		//The current piece does not fit- does any other piece fit?
		{
			if (trysolve(b,u)) { //recursively invoke the rest of the pieces and then see see if p fits 
				if (doesFitInBoard(p))
					return true;
				else {
						return false;
				}
			}
			else {
					return false;
			}
		}
	}		
			
	
	
    //main solver
	public void solve(){
		isSolved = trysolve(board,getUnusedPieces());	
	}

    //check if the board has a piece in every location
	//ALTERNATIVE: have a class variable isSolved,
	//assign the result of trysolve() to it in the solve method
	//this isSolved can simply be the getter of that variable
	public boolean isSolved(){
/*	 Piece p;
	 boolean retval = true;
	 for (int i =0; i < rows; i++) {
		 for (int j =0; j< cols; j++) {
			 p= getPiece(i, j);
			 if (p == null)
				 retval = false;
		 }
	 }
	 return retval;*/
		return isSolved;
	}
	
	
	//print gameboard
	public String toString(){
		String s = "";
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j< cols; j++){
				Piece p = getPiece(i,j);
				String x;
				if (p != null)
					x= p.toString();
				else
					x= "[ , , , ]";
				s= s+"Row ="+ i+ " Col = "+ j + " Piece = " + x + "\t\t";
			}
			s= s+ "\n";
		}
		return s;
	}
	
	//creat a sample 3x3 game
	public static void main(String[] args) {
		Piece[] pieces = new Piece[4];
		pieces[0] = new Piece(1,0, 0, -3);
		pieces[1] = new Piece(-4,3,0,0);
		pieces[2] = new Piece(-2, 4, 0, 0);
		pieces[3] = new Piece(0,-1, 2, 0);
		//pieces[4] = new Piece();
		//pieces[5] = new Piece();
		//pieces[6] = new Piece();
		//pieces[7] = new Piece();
		//pieces[8] = new Piece();
		Puzzle pz = new Puzzle(2,2, pieces);
		for (int i =0; i < pieces.length; i++)
			System.out.print( pieces[i].toString() + " ");
		System.out.println("\n  --Initial pieces-- \n");
		
		pz.solve();
		if (pz.isSolved()) {
			System.out.println("Solution is:");
			System.out.println(pz.toString());
		}
		else
			System.out.println("There is no solution");
			
	}

}
