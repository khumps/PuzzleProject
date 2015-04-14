import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class PuzzlePanel extends JPanel {
	
	private PieceComponent[][] pieces;
	private ArrayList<PieceComponent> unusedPieces = new ArrayList<PieceComponent>();
	private Puzzle puzzle;
	private JPanel unusedPiecePanel;
	public int cellSize;
	static Color c = new Color(150,200,255);
	
	public PuzzlePanel(){
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		for (int row = 0; row < 5; row++){
			for(int col = 0; col < 5; col ++){
				gbc.gridx = col;
				gbc.gridy = row;
				
				CellPane cellPane = new CellPane();
				Border border = null;
				if (row < 4){
					if (col < 4){
						border = new MatteBorder(1,1,0,0,Color.GRAY);
						
					}else{
						border = new MatteBorder(1,1,0,1,Color.GRAY);
					}
				}else{
					if (col < 4){
						border = new MatteBorder(1,1,1,0,Color.GRAY);
					}else{
						border = new MatteBorder(1,1,1,1,Color.GRAY);
					}
				}
				cellPane.setBorder(border);
				add(cellPane, gbc);
				
			}
		}
		
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
		for(Piece p: pieces){
			for(BufferedImage img:imgs)
			unusedPieces.add(new PieceComponent(img,p));
			puzzle = new Puzzle(3,3,pieces);
		}
		this.pieces = new PieceComponent[3][3];
	}
	
	
	public void paintComponent(Graphics g){
		Graphics2D gr2 = (Graphics2D)g;
		cellSize = getHeight() / 6;
		int row = 0; int col = 0;
		super.paintComponent(g);
		g.setColor(c);
		g.fillRect(0, 0, getWidth(), getHeight());
		for(int i = 0; i < getHeight() && row < pieces.length; i+= cellSize)
		{
			for(int j = 0; j < getWidth() && col < pieces[0].length; j += cellSize)
			{
				if(pieces[row][col] != null)
				g.drawImage(pieces[row][col].getPiecePic(), i, j, i + cellSize , j + cellSize, this);
				else System.out.println(row + "  " + col);

				col++;
			}
			row++;
			col = 0;
		}
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
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400, 500);
		JPanel panel = new JPanel(new GridBagLayout());
		f.getContentPane().add(panel, BorderLayout.NORTH);
		
		GridBagConstraints g = new GridBagConstraints();
		g.insets = new Insets(10,10,10,10);
		g.gridheight = 1;
		g.gridwidth = 1;
		PuzzlePanel p = new PuzzlePanel();
		PieceComponent piece = new PieceComponent();
		p.add(piece);
		g.gridx = 0;
		g.gridy = 0;
		panel.add(piece, g);
		f.setVisible(true);
		f.add(panel);
		f.add(p);
		f.pack();
		
	}

}
