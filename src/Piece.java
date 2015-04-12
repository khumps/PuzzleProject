
public class Piece {
	private final int NORTH;
	private final int SOUTH;
	private final int EAST;
	private final int WEST;
	public static final int HEARTS_IN = -1;
	public static final int HEARTS_OUT = 1;
	public static final int DIAMONDS_IN = -2;
	public static final int DIAMONDS_OUT = 2;
	public static final int SPADES_IN = -3;
	public static final int SPADES_OUT = 3;
	public static final int CLUBS_IN = -4;
	public static final int CLUBS_OUT = 4;
	
	public Piece(int north, int east, int south, int west)
	{
		NORTH = north;
		SOUTH = south;
		EAST = east;
		WEST = west;
	}

/*	public Piece(int[] edges)
	{
		
	}*/
	// array containing north, east, south, west in that order
	
	public int getEdge(int direction)
	{
		return direction;
	}
	public void rotate(int rotations)
	{
		
	}
}
