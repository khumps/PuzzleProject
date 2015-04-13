import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PuzzlePanel extends JPanel {
	
	private PieceComponent[][] pieces;
	private ArrayList<PieceComponent> unusedPieces = new ArrayList<PieceComponent>();
	private Puzzle puzzle;
	private JPanel unusedPiecePanel;
	static Color c = new Color(150,200,255);
	
	public PuzzlePanel(){
		
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
	}
	
	public void paintComponent(Graphics g){
		Graphics2D gr2 = (Graphics2D)g;
		super.paintComponent(gr2);
		super.paintComponent(g);
		g.setColor(c);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	

	public void setPiece(int row, int col, Piece p){
		
		puzzle.setPiece(row, col, p);
		
	}
	

	public void removePiece(int row, int col){
		puzzle.removePiece(row,col);
		unusedPieces.add(pieces[row][col]);
	}
	
	public void solve(){
		Puzzle.solve();
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
//		Piece pp = new Piece(1, 1, 1, 1);
		PuzzlePanel p = new PuzzlePanel();
		f.add(new PieceComponent());
		f.add(new PieceComponent());
//		p.setPiece(1,1,pp);
		f.setVisible(true);
		f.add(p);
		f.pack();
		
	}

}
