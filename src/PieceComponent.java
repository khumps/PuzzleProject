import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;


public class PieceComponent extends JComponent {
	public static final int CELL_SIZE = 15;
	private final Piece piece;
	private BufferedImage piecePic;
	 
	
	public PieceComponent(MouseListener listener, Piece piece)
	{
		this.piece = piece;
		
	}
	
	public void paintComponent(Graphics g)
	{
		
	}
	
	public void rotate(int rotations)
	{
		rotate(rotations * 90);
	}
	
	public void rotate(int degrees)
	{
		
	}
}