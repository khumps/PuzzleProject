import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class PuzzlePanel extends JPanel {
	
	private Puzzle puz;
	private PieceComponent[] p;
	
	public PuzzlePanel(){
		
	}
	
	public void paintComponent(Graphics g){
		Graphics2D gr2 = (Graphics2D)g;
		super.paintComponent(g);		
	}
	
	public void setPiece(int row, int col){
		
	}
	
	public void removePiece(int row, int col){
		
	}
	
	
	public static void main(String[] args) {


	}

}
