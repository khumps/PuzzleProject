import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class UnusedPiecePanel extends JPanel {

	private PuzzlePanel puzzle;
	public UnusedPiecePanel(PuzzlePanel puzzle) {
		this.puzzle = puzzle;

		setVisible(true);
		repaint();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int index = 0;
		g.drawRect(0, 0, getWidth(),getHeight());
/*		for(int i = 0; i < getWidth(); i++)
		{
			//g.drawImage(puzzle.getUnusedPieces().get(index++).getPiecePic(),i,0,i + puzzle.cellSize, puzzle.cellSize,null);
			
		}*/
	}

}
