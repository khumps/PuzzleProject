import java.awt.Component;


public class Puzzle {
	public Puzzle(int rows, int cols)
	{
		
	}
	// Constructs a puzzle with specified rows and cols and set of pieces to be used
	public Puzzle(int rows, int cols, Piece[] startingPieces)
	{
		
	}
	
	public Puzzle(Board board)
	{
		
	}

	public boolean doesFit(int row, int col, Piece piece)
	{
		
	}
	public void restart()
	{
		
	}
	//checks if the board is full (when all places in the board contain a piece). 
	public boolean isSolved()
	{
		
	}
	public void addPiece(Piece piece)
	{
		// adds piece to unusedPieces List
	}

		public void solve()
		{
			
		}
		public Piece setPiece(int row, int col, Piece piece)
		{
			
		}
		public Piece getPiece(int row, int col)
		{
			
		}
		public Piece removePiece(int row, int col)
		{
			return 
		}
/*		public List<Piece> getUnusedPieces()
		{
			//returns a COPY
		}*/
		public String toString()
		{
			return "";
		}

		// check whether a piece’s edge matches to another piece’s edge
		//(ex. checks if the north edge of one piece matches to the south edge of another)
	        public Boolean matchEdge(Piece piece1, Piece piece2, int directionFrom1to2)
	        {
	        	return true;
	        }
	        
			public Piece getUnusedPieces() {

			}

}
