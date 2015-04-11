import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.bind.Marshaller.Listener;

@SuppressWarnings("serial")
public class PieceComponent extends JComponent {

	public final int CELL_SIZE = 15;
	private static Piece piece = new Piece(5,4, 3, 2);
	private BufferedImage piecePic;

	public PieceComponent(BufferedImage image, Piece piece) {

		PieceComponent.piece = piece;
		piecePic = image;
		
	}
	
	public PieceComponent() {
		
		try {
//			piecePic = ImageIO.read(getClass().getResourceAsStream("resources/piece_7.png"));
			piecePic = ImageIO.read(new File("resources/piece_7.png"));
		} catch (IOException e) {
			System.out.println("Hi");
		}
		
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(piecePic, 0, 0, 100, 100, null);
		
	}

	public void rotate(int rotations) {
		piece.rotate(rotations);
		AffineTransform tx = new AffineTransform();
		tx.rotate(rotations * Math.toRadians(90), piecePic.getWidth() / 2, piecePic.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		piecePic = op.filter(piecePic, null);
		
		repaint();
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		PieceComponent piece = new PieceComponent();
		frame.add(piece);
		piece.rotate(1);
		frame.setVisible(true);
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
