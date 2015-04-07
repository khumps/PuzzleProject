import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PuzzlePanel extends JPanel {
	
	private PieceComponent[] pieces;
	private ArrayList<PieceComponent> unusedPieces;
	private Puzzle puzzle;
	private JPanel unusedPiecePanel;
	
	public JPanel getUnusedPiecePanel() {
		return unusedPiecePanel;
	}

	public ArrayList<PieceComponent> getUnusedPieces() {
		return unusedPieces;
	}

	public PuzzlePanel(Puzzle puzzle, Listener listener){
		this.puzzle = puzzle;
		pieces = new PieceComponent[puzzle.getUnusedPieces().size()];
		for(Piece p: puzzle.getUnusedPieces()){
			
		}
	}
	
	public void paintComponent(Graphics g){
		Graphics2D gr2 = (Graphics2D)g;
		super.paintComponent(g);
	}
	
	public void setPiece(int row, int col, Piece p){
		puzzle.setPiece(row, col, p);
	}
	
	public PieceComponent removePiece(int row, int col){
		puzzle.removePiece(row,col);
		return //PieceComponent that was there
	}

	
	public static void main(String[] args) {

	}

}
