import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Display extends JFrame {
	private Listener listener;
	private JMenuBar menu;
	private PuzzlePanel puzzlePanel;
	private Piece[] pieces = new Piece[9];
	private BufferedImage[] imgs = new BufferedImage[9];
	

	Display() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createPieces();
		loadImages();
		setPreferredSize(new Dimension(900,900));
		setMinimumSize(new Dimension(800,800));
		listener = new Listener(this);
		puzzlePanel = new PuzzlePanel(listener,imgs,pieces);
		menu = new JMenuBar();
		JTextField instructions = new JTextField();
		instructions.setText("To rotate a piece use the scrollwheel");
		add(instructions,BorderLayout.NORTH);
		instructions.setEditable(false);
		JMenuItem clear = Utils.newMenuItem(Utils.button, "Clear", "clear", listener, menu);
		JMenuItem solve = Utils.newMenuItem(Utils.button, "Solve", "solve", listener, menu);
		setJMenuBar(menu);
		getContentPane().add(puzzlePanel);
		pack();
		setVisible(true);
		

	}
	
	private void loadImages() /*Loops through all the images and loads them into imgs[]*/
	{
		for(int index = 0; index < 9; index++)
		{
			try{
				imgs[index] = ImageIO.read(getClass().getResourceAsStream("resources//piece_" + (index + 1) + ".png"));
			}
			catch (IOException e) {
				System.out.println("ERROR ON PIC LOAD");
			}
		}
	}

	private void createPieces() /*Creates a piece for every image*/
	{
		pieces[0] = new Piece(Piece.CLUBS_OUT,Piece.HEARTS_OUT,Piece.DIAMONDS_IN,Piece.CLUBS_IN);
		pieces[1] = new Piece(Piece.SPADES_OUT,Piece.DIAMONDS_OUT,Piece.SPADES_IN,Piece.HEARTS_IN);
		pieces[2] = new Piece(Piece.HEARTS_OUT,Piece.SPADES_OUT,Piece.SPADES_IN,Piece.CLUBS_IN);
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
			Display puzzle = new Display();
		
		

	}
}
