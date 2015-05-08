/**
 * @author Kevin Humphreys
 * This class listens for events in the Display class and acts on them
 */
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Arrays;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class Listener extends MouseAdapter implements ActionListener,
		KeyListener {
	private String command;
	private Display display;
	protected boolean holdingPiece = false;
	protected PieceComponent pieceHeld = null;

	/**
	 * 
	 * @param display
	 *            a Display object that contains the necessary components for
	 *            the class to function
	 */
	public Listener(Display display) {
		this.display = display;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		command = e.getActionCommand();
		if (command == "solve") {
			display.getPuzzlePanel().solve();
			System.out.println("solve");
		}

		if (command == "clear") {
			display.getPuzzlePanel().clear();
			System.out.println("clear");
		}

		if (command == "hint") {
			System.out.println("hint");
		}
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("TEST");
		if (holdingPiece) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				pieceHeld.rotate(1);

			if (e.getKeyCode() == KeyEvent.VK_LEFT)
				pieceHeld.rotate(3);

			display.getPuzzlePanel().repaint();
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseClicked(MouseEvent e) {
		// System.out.println("mouse");
		int x, y, row, col;
		// System.out.println(e.getComponent());
		if (e.getComponent().equals(display.getPuzzlePanel())) {
			x = (e.getX() - display.getPuzzlePanel().puzzleArea.x);
			y = (e.getY() - display.getPuzzlePanel().puzzleArea.y);
			col = x / display.getPuzzlePanel().cellSize;
			row = y / display.getPuzzlePanel().cellSize;
			// System.out.println(row + " " + col);
			// System.out.println(display.getPuzzlePanel().inBoard(row,col));
			if (display.getPuzzlePanel().inBoard(row, col)) {
				if (!holdingPiece
						&& !display.getPuzzlePanel().isEmpty(row, col)) {
					/* Picks up piece off board */

					holdingPiece = true;

					pieceHeld = display.getPuzzlePanel().removePiece(row, col);
					// System.out.println("RAN");
				}

				else if (holdingPiece
						&& display.getPuzzlePanel().isEmpty(row, col)) {
					/* Places piece on board */
					if (display.getPuzzlePanel().doesFit(row, col, pieceHeld)) {
						display.getPuzzlePanel().setPiece(row, col, pieceHeld);

						pieceHeld = null;
						holdingPiece = false;
					} else
						display.getPuzzlePanel().noFit = true;
				}
			}
			// System.out.println(holdPiece);
			else {

				x = (e.getX() - display.getPuzzlePanel().unusedPieceArea.x);
				y = (e.getY() - display.getPuzzlePanel().unusedPieceArea.y);
				col = x / display.getPuzzlePanel().pieceSize;
				row = y / display.getPuzzlePanel().pieceSize;
				if (!holdingPiece) {/* Picks up piece from unusedPieces */

					// System.out.println(col * 2 + row + "DAGSDFASDF");
					if (col * 2 + row < display.getPuzzlePanel()
							.getUnusedPieces().size()
							&& display.getPuzzlePanel().inPiecesArea(x, y)) {
						holdingPiece = true;
						pieceHeld = display.getPuzzlePanel().getUnusedPieces()
								.remove(col * 2 + row);
					}
				} else {/* Puts piece in unusedPieces */
					System.out.println(x + " " + y);
					System.out
							.println(display.getPuzzlePanel().unusedPieceArea.x);
					System.out
							.println(display.getPuzzlePanel().unusedPieceArea.y);
					if (display.getPuzzlePanel().inPiecesArea(x, y)) {

						System.out.println("Ran");
						display.getPuzzlePanel().getUnusedPieces()
								.add(pieceHeld);
						pieceHeld = null;
						holdingPiece = false;
					}

				}
			}

			display.getPuzzlePanel().repaint();

		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println(e.getPreciseWheelRotation());
		if (holdingPiece) {
			int r = (int) e.getPreciseWheelRotation();
			if (r > 0)
				pieceHeld.rotate(1);
			if (r < 0)
				pieceHeld.rotate(3);
			display.getPuzzlePanel().repaint();
		}

	}

	public void mouseDragged(MouseEvent e) {
		display.getPuzzlePanel().repaint();
	}

	public void mouseMoved(MouseEvent e) {
		// System.out.println(holdPiece);
		if (holdingPiece)
			display.getPuzzlePanel().repaint();
		display.getPuzzlePanel().mouseLocation = new Point(e.getPoint().x,
				e.getPoint().y);
		// System.out.println(display.getPuzzlePanel().mouseLocation);
		/*
		 * Rectangle r =
		 * SwingUtilities.convertRectangle(display.getPuzzlePanel()
		 * ,display.getPuzzlePanel().puzzleArea , null); int row,col;
		 * System.out.println(display.getPuzzlePanel().puzzleArea.x); row =
		 * (e.getX() - display.getPuzzlePanel().puzzleArea.x) /
		 * (display.getPuzzlePanel().cellSize); col = (e.getY() -
		 * display.getPuzzlePanel().puzzleArea.y) /
		 * display.getPuzzlePanel().pieceSize; System.out.println(row + " " +
		 * col);
		 */
	}
}
