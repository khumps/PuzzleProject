import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Listener extends MouseAdapter implements ActionListener, KeyListener {
	private String command;
	private PuzzlePanel panel;
	private PieceComponent piece;
	private boolean holdPiece = false;
	
	public Listener(PuzzlePanel panel)
	{
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		command = e.getActionCommand();
		if (command == "solve") {

		}

		if (command == "clear") {

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
		if(e.getComponent().equals(panel))
		{
			col = (e.getX() - e.getComponent().getX()) / PieceComponent.CELL_SIZE;
			row = e.getY() - e.getComponent().getY() / PieceComponent.CELL_SIZE;
			if(!holdPiece)
			{
				holdPiece = true;
				if(e.getComponent().equals(panel.getUnusedPiecePanel()))
				{
					clickedPiece = panel.getUnusedPieces().get(col);
				}
				else
				{
					clickedPiece = panel.removePiece(row, col);
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
		e.getSource().get

	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

	}
}

