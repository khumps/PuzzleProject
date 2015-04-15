import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class PieceComponent extends JComponent {

	public final static int CELL_SIZE = 15;
	private Piece piece;
	private BufferedImage piecePic;

	public PieceComponent(BufferedImage image, Piece piece) {

		this.piece = piece;
		piecePic = image;
		System.out.println("piece Created");

	}

	public PieceComponent() {
		piece = new Piece(Piece.HEARTS_IN, Piece.HEARTS_IN, Piece.HEARTS_IN,
				Piece.HEARTS_IN);
		try {
			piecePic = ImageIO.read(getClass().getResourceAsStream(
					"piece_1.png"));
			// piecePic = ImageIO.read(new File("resources/piece_7.png"));
		} catch (IOException e) {
			System.out.println("Hi");
		}
<<<<<<< HEAD
		
		
=======

>>>>>>> origin/master
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(piecePic, 0, 0, 100, 100, null);

	}

	public void rotate(int rotations) {
		piece.rotate(rotations);
		AffineTransform tx = new AffineTransform();
		tx.rotate(rotations * Math.toRadians(90), piecePic.getWidth() / 2,
				piecePic.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		piecePic = op.filter(piecePic, null);
		repaint();
	}

	public Piece getPiece() {
		return piece;
	}

	public BufferedImage getPiecePic() {
		return piecePic;
	}
<<<<<<< HEAD
	
	public String toString()
	{
		return "Piece";
	}
=======

	public Point getCenter() {
		return new Point(piecePic.getWidth() / 2, piecePic.getHeight() / 2);
	}

>>>>>>> origin/master
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		PieceComponent piece = new PieceComponent();
		frame.setBackground(Color.BLUE);

		frame.add(piece);
		piece.rotate(2);
		frame.setBackground(Color.BLUE);
		frame.setVisible(true);
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
