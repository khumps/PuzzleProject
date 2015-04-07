import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JPanel;

public class PuzzlePanel extends JPanel {
	
	private PieceComponent[] pieces;	
	private Puzzle puzzle;
	
	public PuzzlePanel(PieceComponent[] pieces){
		this.pieces = pieces;	
	}
	
	public void paintComponent(Graphics g){
		Graphics2D gr2 = (Graphics2D)g;
		super.paintComponent(g);
	}
	
	public void setPiece(int row, int col){
		Puzzle.setPiece(row,col);
	}
	
	public void removePiece(int row, int col){
		Puzzle.setPiece(row,col);
	}

	
	public static void main(String[] args) {

	}

}
