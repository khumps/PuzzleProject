import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Display extends JFrame {
	private Listener listener;
	private JMenuBar menu;
	private PuzzlePanel puzzlePanel;
	private JPanel unusedPieces;
	private Puzzle puzzle;
	

	Display() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		listener = new Listener(this);
		menu = new JMenuBar();
		JMenuItem clear = Utils.newMenuItem(Utils.button, "Clear", "clear", listener, menu);
		JMenuItem hint = Utils.newMenuItem(Utils.button,"Hint","clear",listener,menu);
		JMenuItem solve = Utils.newMenuItem(Utils.button, "Solve", "solve", listener, menu);
		setJMenuBar(menu);
		puzzlePanel = new PuzzlePanel(puzzle,listener);
		getContentPane().add(puzzlePanel);
		pack();
		setVisible(true);
		

	}

	public PuzzlePanel getPuzzlePanel() {
		return puzzlePanel;
	}

	public static void main(String[] args) {
		new Display();

	}
}
