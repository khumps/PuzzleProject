import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PuzzlePanel extends JPanel {
	
	private PieceComponent[] pieces;
	private ArrayList<PieceComponent> unusedPieces;
	private Puzzle puzzle;
	private JPanel unusedPiecePanel;
	Color c = new Color(150,200,255);
	
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
	public PuzzlePanel(Puzzle puzzle, Listener listener){
		this.puzzle = puzzle;
		pieces = new PieceComponent[puzzle.getUnusedPieces().size()];
		for(Piece p: puzzle.getUnusedPieces()){
			
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
	
	public PieceComponent removePiece(int row, int col){
		puzzle.removePiece(row,col);
		return null; //PieceComponent that was there
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
		f.add(new PuzzlePanel());
		f.setSize(200, 200);
		f.setVisible(true);
	}

}
