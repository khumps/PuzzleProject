// Kevin Humphreys, Vijay Jeevanandam, Akshay Karthik

// Creates a Point object that serves to hold a coordinate position based on an x-coordinate and a y-coordinate. This Point is used to store the location of the mouse in the PuzzlePanel class.

public class Point {
	protected final int x;
	protected final int y;

	// Creates a Point object given two integers that are set equal to the x- and y-coordinates, respectively.
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// Prints the Point as a coordinate pair of the form (x, y)
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
