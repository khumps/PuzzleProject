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
	protected boolean holdPiece = false;
	protected PieceComponent pieceHeld = null;
	
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
		// TODO Auto-generated method stub
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouse");
		int col;
		int row;
		if(e.getComponent().equals(display.getPuzzlePanel()))
		{
			col = (e.getComponent().getX()) / display.getPuzzlePanel().cellSize;
			row = (e.getComponent().getY()) / display.getPuzzlePanel().cellSize;
			System.out.println(e.getComponent());
			if(!holdPiece)
			{
				holdPiece = true;
				if(e.getComponent().equals(display.getPuzzlePanel()))
				//{
					//col = (e.getComponent().getX() - display.getPuzzlePanel().puzzleArea.width) / display.getPuzzlePanel().cellSize;
					//row = e.getY() - e.getComponent().getY() / display.getPuzzlePanel().cellSize;
				{
					pieceHeld = display.getPuzzlePanel().removePiece(row, col);
				}
				else
				{
					pieceHeld = display.getPuzzlePanel().getUnusedPieces().get(col);
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

