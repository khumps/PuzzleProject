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
	private int orientation;

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
		piece.rotate(rotations);
		AffineTransform tx = new AffineTransform();
		tx.rotate(rotations * Math.toRadians(90), piecePic.getWidth() / 2,
				piecePic.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		piecePic = op.filter(piecePic, null);
		repaint();
	}

	/**
	 * Checks if the Implicit piece is the same as the explicit piece by
	 * rotating it and seeing if they are equal
	 * 
	 * @param p
	 * @return
	 */
	public boolean isSamePiece(Piece p) {

		if (PuzzlePanel.equals(piece, p)) {
			rotate(p.getOrientation());
			return true;
		}

		return false;
	}

	// isSamePiece must return true

	/**
	 * Given that the two pieces are the same rotates the implicit to match the
	 * explicit
	 * 
	 * @param p
	 * @return
	 */

	public void defaultOrientation() {
//		System.out.println(piece.getOrientation());
		rotate(4 - (piece.getOrientation() % 4));
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

}