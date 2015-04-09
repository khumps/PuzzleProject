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

public class Listener extends MouseAdapter implements ActionListener, KeyListener {
	private String command;
	private Display display;
	private boolean holdPiece = false;
	
	/**
	 * 
	 * @param display a display object that contains the necessary components for the class to function
	 */
	public Listener(Display display)
	{
		this.display = display;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		command = e.getActionCommand();
		if (command == "solve") {
			display.getPuzzlePanel().solve();
		}

		if (command == "clear") {
			display.getPuzzlePanel().getPuzzle().restart();
		}

		if (command == "hint") {

		}
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	public void mouseClicked(MouseEvent e) {
		int col;
		int row;
		PieceComponent clickedPiece;
		if(e.getComponent().equals(display.getPuzzlePanel()))
		{
			col = (e.getX() - e.getComponent().getX()) / PieceComponent.CELL_SIZE;
			row = e.getY() - e.getComponent().getY() / PieceComponent.CELL_SIZE;
			if(!holdPiece)
			{
				holdPiece = true;
				if(e.getComponent().equals(display.getPuzzlePanel().getUnusedPiecePanel()))
				{
					clickedPiece = display.getPuzzlePanel().getUnusedPieces().get(col);
				}
				else
				{
					clickedPiece = display.getPuzzlePanel().removePiece(row, col);
				}
			}
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
		//e.getSource().get

	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

	}
}

