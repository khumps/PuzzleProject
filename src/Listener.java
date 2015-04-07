import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Listener {
	private String command;
	private PuzzlePanel panel;
	private PieceComponent piece;
	
	public Listener(PuzzlePanel panel)
	{
		this.panel = panel;
	}

	public class ButtonListener implements ActionListener {
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
	}

	public class KeyListener implements java.awt.event.KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public class MouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if(e.getComponent().equals(panel.))
			int col = (e.getX() - e.getComponent().getX()) / PieceComponent.CELL_SIZE;
			int row = e.getY() - e.getComponent().getY() / PieceComponent.CELL_SIZE;
			panel.getPuzzle(row,col);

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
}
