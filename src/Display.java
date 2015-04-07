import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Display extends JFrame {
	private Listener listener;
	private JComboBox menu;
	private PuzzlePanel puzzle;
	private JPanel unusedPieces;

	Display() {
		puzzle = new PuzzlePanel();
		listener = new Listener(puzzle);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
