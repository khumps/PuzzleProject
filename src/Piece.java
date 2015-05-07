public class Piece {
	public static final int NORTH = 0, EAST = 90, SOUTH = 180, WEST = 270;
	public static final int HEARTS_IN = -1, HEARTS_OUT = 1, SPADES_IN = -2, SPADES_OUT = 2,
			CLUBS_IN = -3, CLUBS_OUT = 3, DIAMONDS_IN = -4, DIAMONDS_OUT = 4;
	protected int[] piece = new int[4];
	private int orientation;
	
	public Piece(int north, int east, int south, int west) {
		piece[0] = north;
		piece[1] = east;
		piece[2] = south;
		piece[3] = west;
	}
	
	public Piece(int[] edges){
		for(int i = 0; i < edges.length; i++){
			piece[i] = edges[i];
		}
	}
	
	public int getEdge(int direction){
		if(direction == NORTH) return piece[0];
		if(direction == EAST) return piece[1];
		if(direction == SOUTH) return piece[2];
		if(direction == WEST) return piece[3];
		return direction;
	}
	
	public void rotate(int rotations){
		if(rotations < 0) rotations = 4 - Math.abs(rotations % 4);
		if(rotations == 0) return;
		for(int i = 0; i < rotations; i++){
			rotate();
		}
	}
	public void rotate(){
		int temp = piece[0];
		piece[0] = piece[3];
		piece[3] = piece[2];
		piece[2] = piece[1];
		piece[1] = temp;
		orientation++;
		orientation = orientation % 4;
	}
	
	public int getOrientation() {
		return orientation;
	}
	
	public String toString(){
		String a = "";
;		a += "[" + piece[0] + ",";
		a += piece[1] + "," + piece[2];
		a += "," + piece[3] + "]";
		return a;
	}
	
	public static void main(String[] args) {
		Piece p = new Piece(1, 2, 3, 4);
		System.out.println(p.toString());
		p.rotate(-1);
		System.out.println(p.toString());
		System.out.println(p.getOrientation());
	}

}
