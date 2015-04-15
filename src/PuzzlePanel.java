import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PuzzlePanel extends JPanel {

	private PieceComponent[][] pieces;
	private PieceComponent p;
	private ArrayList<PieceComponent> unusedPieces = new ArrayList<PieceComponent>();
	private Puzzle puzzle;
	private JPanel unusedPiecePanel;
	public int cellSize;
	private Rectangle puzzleArea;
	private Grid grid;
	static Color c = new Color(150,200,255);

	public PuzzlePanel(){
//		Grid g = new Grid();
//		g.run();
//		pieces = new PieceComponent[3][3];
//		PieceComponent p = new PieceComponent();
//		p.getPiece();
//		p.getPiecePic();
		unusedPieces.add(p);
		
		
	}

	public JPanel getUnusedPiecePanel() {
		return unusedPiecePanel;
	}

	public ArrayList<PieceComponent> getUnusedPieces() {
		return unusedPieces;
	}

	public void repaint(){

	}
	public PuzzlePanel(Listener listener, BufferedImage[] imgs, Piece[] pieces){
		grid = new Grid();
		for(Piece p: pieces){
			for(BufferedImage img:imgs)
				unusedPieces.add(new PieceComponent(img,p));
			puzzle = new Puzzle(3,3,pieces);
		}
		this.pieces = new PieceComponent[3][3];
	}


	public void paintComponent(Graphics g){
		puzzleArea = new Rectangle();
		//puzzleArea.setBounds(getWidth() / 5, getHeight() / 5, getWidth() / 3, getWidth() / 3);
		puzzleArea.setFrameFromCenter(getHeight() / 3, getHeight() / 3, 10, 10);
		//puzzleArea.setFrameFromCenter(500, 500, 300, 300);
		Graphics2D gr2 = (Graphics2D)g;
		cellSize = getHeight() / 6;
		int row = 0; int col = 0;
		super.paintComponent(g);
<<<<<<< HEAD
		for(int i = (int) puzzleArea.getMinX(); i < puzzleArea.getMaxX() && row < pieces.length; i+= cellSize)
=======
		g.setColor(c);
		g.drawRect(0, 0, 50,50);
//		g.fillRect(0, 0, getWidth(), getHeight());
		for(int i = 0; i < getHeight() && row < pieces.length; i+= cellSize)
>>>>>>> origin/master
		{
			for(int j = (int) puzzleArea.getMinY(); j < puzzleArea.getMaxY() && col < pieces[0].length; j += cellSize)
			{
				g.drawRect(i, j, i + cellSize - 10, j + cellSize - 10);
				if(pieces[row][col] != null)
					g.drawImage(pieces[row][col].getPiecePic(), i, j, i + cellSize , j + cellSize, this);
				
				else System.out.println(row + "  " + col);
				g.drawImage(unusedPieces.get(0).getPiecePic(), i, j, i + cellSize , j + cellSize, this);
				col++;
			}
			row++;
			col = 0;
		}
		g.drawRect((int)puzzleArea.getMinX(), (int)puzzleArea.getMinY(), (int)puzzleArea.getMaxX(), (int)puzzleArea.getMaxY());
	}


	public void setPiece(int row, int col, PieceComponent p){
		pieces[row][col] = p;
		puzzle.setPiece(row, col, p.getPiece());

	}


	public PieceComponent removePiece(int row, int col){
		puzzle.removePiece(row,col);
		unusedPieces.add(pieces[row][col]); 
		return unusedPieces.get(unusedPieces.size() - 1);
	}

	public void solve(){
		puzzle.solve();
	}

	public void clear(){
		puzzle.restart();
	}

	public Puzzle getPuzzle(){
		return puzzle;
	}

	public static void main(String[] args) {

		Dimension dimension = new Dimension(550,550);
		JFrame f = new JFrame("Puzzle");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(700, 700);
		f.setMinimumSize(dimension);
		JPanel panel = new JPanel(new GridBagLayout());
		f.getContentPane().add(panel, BorderLayout.NORTH);
		
		PieceComponent piece = new PieceComponent();
		f.add(piece);
//		
		GridBagConstraints constraints = new GridBagConstraints();
//		constraints.gridheight = 1;
//		constraints.gridwidth = 1;
		constraints.insets = new Insets(10,0,0,0);
//
		Grid g = new Grid();
//		constraints.gridx = 0;
//		constraints.gridy = 0;
		panel.add(g.run(), constraints);
		PuzzlePanel p = new PuzzlePanel();
		f.add(p);
//
//		
//		constraints.gridx = 10;
//		constraints.gridy = 10;
		
		//f.add(piece);
		//		g.gridheight = 1;
		//		g.gridwidth = 1;
		

		
		//		g.gridx = 0;
		//		g.gridy = 0;
		//		panel.add(piece, g);
		f.setVisible(true);
//				f.add(panel);
		//		f.add(p);
		//		f.pack();

	}

}
