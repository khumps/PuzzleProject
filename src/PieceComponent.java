import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

// Akshay Karthik, Kevin Humphreys, Vijay Jeevanandam

// Creates a PieceComponent object, which can be added as a JComponent to JPanels and JFrames to display on a screen. This PieceComponent serves to display an image representing a puzzle piece. Methods create a PieceComponent, rotate it, return the values of private data points, and draw the PieceComponent onto the screen.

public class PieceComponent extends JComponent {

	public final static int CELL_SIZE = 15;
	private Piece piece;
	private BufferedImage piecePic;
<<<<<<< HEAD
	private int orientation = 0;
=======
	private int orientation;
>>>>>>> origin/master

	// Constructs a PieceComponent given a BufferedImage for the image of the
	// piece it represents as well as a Piece for the Piece object associated
	// with that image.

	public PieceComponent(BufferedImage image, Piece piece) {

		this.piece = piece;
		piecePic = image;
		orientation = piece.getOrientation();

	}

	// Paints the PieceComponent onto the screen by drawing piecePic.

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(piecePic, 0, 0, 100, 100, null);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
	}

	// Rotates piecePic around its center 90 degrees using AffineTransform,
	// rotates piece and repaints the PieceComponent.

	public void rotate(int rotations) {
		orientation = (orientation + rotations) % 4;
		piece.rotate(rotations);
		AffineTransform tx = new AffineTransform();
		tx.rotate(rotations * Math.toRadians(90), piecePic.getWidth() / 2,
				piecePic.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		piecePic = op.filter(piecePic, null);
		repaint();
	}

<<<<<<< HEAD
	public void rotateImage(int rotations) {
		orientation = (orientation + rotations) % 4;
		AffineTransform tx = new AffineTransform();
		tx.rotate(rotations * Math.toRadians(90), piecePic.getWidth() / 2,
				piecePic.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		piecePic = op.filter(piecePic, null);
		repaint();
	}

=======
>>>>>>> origin/master
	/**
	 * Checks if the Implicit piece is the same as the explicit piece by
	 * rotating it and seeing if they are equal
	 * 
	 * @param p
	 * @return
	 */
<<<<<<< HEAD
	public int isSamePiece(Piece p) {
		int numRotations = 0;
		boolean foundMatch = false;
		for (int i = 0; i < 4; i++) {
			if (!foundMatch) {
				if (piece.getEdge(1) == p.getEdge(i)) {
					numRotations = i;
				}
				foundMatch = true;
			}
			/*
			 * if (PuzzlePanel.equals(piece, p)) { return true;
			 */
			else {
				if (piece.getEdge(i + numRotations) != p.getEdge(i))
					return -1;
			}

			return numRotations;
		}
		// System.out.println("rotate");
		// rotate(1);
		// repaint();

		return -1;
	}

	// isSamePiece must return true
=======
	public boolean isSamePiece(Piece p) {

		if (PuzzlePanel.equals(piece, p)) {
			rotate(p.getOrientation());
			return true;
		}

		return false;
	}

	// isSamePiece must return true

>>>>>>> origin/master
	/**
	 * Given that the two pieces are the same rotates the implicit to match the
	 * explicit
	 * 
	 * @param p
	 * @return
	 */
<<<<<<< HEAD
	public PieceComponent rotateToMatch(Piece p) {
		for (int i = 0; i < 4; i++) {
			if (PuzzlePanel.equals(piece, p)) {
				return this;
			}
			System.out.println("rotate " + i);
			rotate(1);
		}
		return null;
=======

	public void defaultOrientation() {
//		System.out.println(piece.getOrientation());
		rotate(4 - (piece.getOrientation() % 4));
>>>>>>> origin/master
	}

	// Returns the value of piece (private data).

	public Piece getPiece() {
		return piece;
	}

	// Returns the value of piecePic (private data).

	public BufferedImage getPiecePic() {
		return piecePic;
	}

	// Returns a Point representing the center coordinate for the
	// PieceComponent.

	public Point getCenter() {
		return new Point(piecePic.getWidth() / 2, piecePic.getHeight() / 2);
	}

<<<<<<< HEAD
	// Tests the functionality of the PieceComponent class.

	/*
	 * public static void main(String[] args) { JFrame frame = new JFrame();
	 * PieceComponent piece = new PieceComponent();
	 * frame.setBackground(Color.BLUE);
	 * 
	 * frame.add(piece); piece.rotate(2); frame.setBackground(Color.BLUE);
	 * frame.setVisible(true); frame.setSize(1000, 1000);
	 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 * 
	 * }
	 */

=======
>>>>>>> origin/master
}
