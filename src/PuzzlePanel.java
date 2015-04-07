import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JPanel;

public class PuzzlePanel extends JPanel {
	
	private PieceComponent[] pieces;	
	private Puzzle puzzle;
	private Piece piece;
	
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
	
	public void removePiece(int row, int col){
		puzzle.removePiece(row,col);
	}

	
	public static void main(String[] args) {

	}

}
