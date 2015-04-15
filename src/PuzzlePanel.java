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

	private PieceComponent[][] pieces = new PieceComponent[3][3];
	private PieceComponent p;
	private ArrayList<PieceComponent> unusedPieces = new ArrayList<PieceComponent>();
	private Puzzle puzzle;
	private JPanel unusedPiecePanel;
	private Listener listener;
	private Rectangle puzzleArea;
	private Grid grid;
	public int cellSize;
	private int pieceSize;
	
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
		this.listener = listener;
		for(int i = 0; i < pieces.length; i++)
		{
			unusedPieces.add(new PieceComponent(imgs[i],pieces[i]));
		}
		puzzle = new Puzzle(3,3,pieces);
	}


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		puzzleArea = new Rectangle();
		//puzzleArea.setBounds(getWidth() / 5, getHeight() / 5, getWidth() / 3, getWidth() / 3);
		puzzleArea.setFrameFromCenter(getHeight() / 3, getHeight() / 3, 100, 100);
		//puzzleArea.setFrameFromCenter(500, 500, 300, 300);
		Graphics2D gr2 = (Graphics2D)g;
		cellSize =(int) (puzzleArea.getWidth() / 3);
		pieceSize = (int)(cellSize * 1.345);
		//1.94
		int row = 0; int col = 0;
		
		//g.setColor(c);
		System.out.println("size" + cellSize);
		for(int i = (int) puzzleArea.getMinX(); i < (int)puzzleArea.getMaxX() && col < pieces.length; i+= cellSize)
		{
			for(int j = (int) puzzleArea.getMinY(); j < (int)puzzleArea.getMaxY() && row < pieces[0].length; j += cellSize)
			{
				System.out.println(i + " " + j);
				g.drawRect(i , j, cellSize, cellSize);
				try{
					g.drawImage(pieces[row][col].getPiecePic(), i - (pieceSize - cellSize), j - (pieceSize - cellSize), i + pieceSize , j + pieceSize,0,0,pieces[row][col].getPiecePic().getWidth(),pieces[row][col].getPiecePic().getHeight(), this);
					
				}
				catch(NullPointerException e)
				{
					if(pieces[row][col] != null)
					System.out.println("No piece at: (" + row + "," + col + ")");
				}
				//g.drawImage(pieces[row][col].getPiecePic(), i - pieceSize + offsetX, j - pieceSize + offsetY, pieceSize + (pieceSize - cellSize) , pieceSize + (pieceSize - cellSize), this);
				row++;
			}
			col++;
			row = 0;
		}
	}


	public void setPiece(int row, int col, PieceComponent p){
		pieces[row][col] = p;
		puzzle.setPiece(row, col, p.getPiece());
		unusedPieces.remove(p);

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
