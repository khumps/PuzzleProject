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

import javax.swing.SwingUtilities;

public class Listener extends MouseAdapter implements ActionListener, KeyListener {
	private String command;
	private Display display;
	protected boolean holdPiece = false;
	protected PieceComponent pieceHeld = null;
	int test1, test2;
	
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
		int x,y,row,col;
		System.out.println(e.getComponent());
		if(e.getComponent().equals(display.getPuzzlePanel()))
		{
			System.out.println(Arrays.toString(display.getPuzzlePanel().pieces));
			x = (e.getX() - display.getPuzzlePanel().puzzleArea.x);
			y = (e.getY() - display.getPuzzlePanel().puzzleArea.y);
			row = x / display.getPuzzlePanel().cellSize;
			col = y / display.getPuzzlePanel().cellSize;
			System.out.println(row + " " + col);
			System.out.println(x + " " + y);
			if(!holdPiece)
			{
				holdPiece = true;
				display.getPuzzlePanel().removePiece(row, col);
					//pieceHeld = display.getPuzzlePanel().removePiece(row, col);
/*				else
				{
					pieceHeld = display.getPuzzlePanel().getUnusedPieces().get(col);
				*/}
				
			}
		display.getPuzzlePanel().repaint();
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
		display.getPuzzlePanel().repaint();
	}

	public void mouseMoved(MouseEvent e) {
		display.getPuzzlePanel().mouseLocation = new Point(e.getPoint().x,e.getPoint().y);
		System.out.println(display.getPuzzlePanel().mouseLocation);
/*		Rectangle r = SwingUtilities.convertRectangle(display.getPuzzlePanel(),display.getPuzzlePanel().puzzleArea , null);
		int row,col;
		System.out.println(display.getPuzzlePanel().puzzleArea.x);
		row = (e.getX() - display.getPuzzlePanel().puzzleArea.x) / (display.getPuzzlePanel().cellSize);
		col = (e.getY() - display.getPuzzlePanel().puzzleArea.y) / display.getPuzzlePanel().pieceSize;
		System.out.println(row + " " + col);*/
	}
}

