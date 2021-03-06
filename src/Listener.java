/**
 * @author Kevin Humphreys
 * This class listens for events in the Display class and acts on them
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Listener extends MouseAdapter implements ActionListener {
	private String command;
	private Display display;
	protected boolean holdingPiece = false;
	protected PieceComponent pieceHeld = null;
	protected boolean solved = false; /*
									 * if solve is called becomes true and
									 * prevents solve being clicked again until
									 * clear is called
									 * 
									 * /**
									 * 
									 * @param display a Display object that
									 * contains the necessary components for the
									 * class to function
									 */

	public Listener(Display display) {
		this.display = display;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		command = e.getActionCommand();
		if (command == "solve") { /* Solve button commands */
			{
				if (!solved) {
					display.getPuzzlePanel().solve();
					solved = true;
				}
			}
		}

		if (command == "clear") {
			display.getPuzzlePanel().clear();
			solved = false;
		}
	}

	public void mouseClicked(MouseEvent e) {
		int x, y, row, col;
		if (e.getComponent().equals(display.getPuzzlePanel())) {
			x = (e.getX() - display.getPuzzlePanel().puzzleArea.x);
			y = (e.getY() - display.getPuzzlePanel().puzzleArea.y);
			col = x / display.getPuzzlePanel().cellSize;
			row = y / display.getPuzzlePanel().cellSize;
			if (display.getPuzzlePanel().inBoard(row, col)) {
				if (!holdingPiece
						&& !display.getPuzzlePanel().isEmpty(row, col)) {
					/* Picks up piece off board */

					holdingPiece = true;

					pieceHeld = display.getPuzzlePanel().removePiece(row, col);
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
			} else {

				x = (e.getX() - display.getPuzzlePanel().unusedPieceArea.x);
				y = (e.getY() - display.getPuzzlePanel().unusedPieceArea.y);
				col = x / display.getPuzzlePanel().pieceSize;
				row = y / display.getPuzzlePanel().pieceSize;
				if (!holdingPiece) {/* Picks up piece from unusedPieces */

					if (col * 2 + row < display.getPuzzlePanel()
							.getUnusedPieces().size()
							&& display.getPuzzlePanel().inPiecesArea(x, y)) {
						holdingPiece = true;
						pieceHeld = display.getPuzzlePanel().getUnusedPieces()
								.remove(col * 2 + row);
					}
				} else {/* Puts piece in unusedPieces */
					if (display.getPuzzlePanel().inPiecesArea(x, y)) {
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

	/* Rotates pieces */
	public void mouseWheelMoved(MouseWheelEvent e) {
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

	public void mouseMoved(MouseEvent e) { /* Drags pieces */
		if (holdingPiece)
			display.getPuzzlePanel().repaint();
		display.getPuzzlePanel().mouseLocation = new Point(e.getPoint().x,
				e.getPoint().y);
	}
}
