import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Display extends JFrame {
	private Listener listener;
	private JMenuBar menu;
	private PuzzlePanel puzzlePanel;
	private UnusedPiecePanel unusedPieces;
	private Piece[] pieces = new Piece[9];
	private BufferedImage[] imgs = new BufferedImage[9];
	

	Display() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createPieces();
		loadImages();

		puzzlePanel = new PuzzlePanel(listener,imgs,pieces);
		unusedPieces = new UnusedPiecePanel(puzzlePanel);
		listener = new Listener(this);
		menu = new JMenuBar();
		JTextField instructions = new JTextField();
		instructions.setText("To rotate a piece use the scrollwheel or the arrow keys");
		add(instructions,BorderLayout.NORTH);
		instructions.setEditable(false);
		JMenuItem clear = Utils.newMenuItem(Utils.button, "Clear", "clear", listener, menu);
		JMenuItem hint = Utils.newMenuItem(Utils.button,"Hint","hint",listener,menu);
		JMenuItem solve = Utils.newMenuItem(Utils.button, "Solve", "solve", listener, menu);
		setJMenuBar(menu);
		getContentPane().add(unusedPieces);
		getContentPane().add(puzzlePanel);
		pack();
		setVisible(true);
		

	}
	
	private void loadImages()
	{
		for(int index = 0; index < 10; index++)
		{
			try{
				imgs[index++] = ImageIO.read(getClass().getResourceAsStream("piece_" + index + ".png"));
			}
			catch (IOException e) {
				System.out.println("Hi");
			}
		}
	}

	private void createPieces()
	{
		pieces[0] = new Piece(Piece.CLUBS_OUT,Piece.HEARTS_OUT,Piece.DIAMONDS_IN,Piece.CLUBS_IN);
		pieces[1] = new Piece(Piece.SPADES_OUT,Piece.DIAMONDS_OUT,Piece.SPADES_IN,Piece.HEARTS_IN);
		pieces[2] = new Piece(Piece.HEARTS_OUT,Piece.SPADES_OUT,Piece.HEARTS_IN,Piece.CLUBS_IN);
		pieces[3] = new Piece(Piece.HEARTS_OUT,Piece.DIAMONDS_OUT,Piece.CLUBS_IN,Piece.CLUBS_IN);
		pieces[4] = new Piece(Piece.SPADES_OUT,Piece.SPADES_OUT,Piece.HEARTS_IN,Piece.CLUBS_IN);
		pieces[5] = new Piece(Piece.HEARTS_OUT,Piece.DIAMONDS_OUT,Piece.DIAMONDS_IN,Piece.HEARTS_IN);
		pieces[6] = new Piece(Piece.SPADES_OUT,Piece.DIAMONDS_OUT,Piece.HEARTS_IN,Piece.DIAMONDS_IN);
		pieces[7] = new Piece(Piece.CLUBS_OUT,Piece.HEARTS_OUT,Piece.SPADES_IN,Piece.HEARTS_IN);
		pieces[8] = new Piece(Piece.CLUBS_OUT,Piece.CLUBS_IN,Piece.DIAMONDS_IN,Piece.DIAMONDS_OUT);
	}
	
	public PuzzlePanel getPuzzlePanel() {
		return puzzlePanel;
	}

	public static void main(String[] args) {
		Display d = new Display();
		System.out.println(d.getPuzzlePanel().getUnusedPieces().toString());
		d.getPuzzlePanel().setPiece(1, 1, d.getPuzzlePanel().getUnusedPieces().get(0));
		

	}
}
