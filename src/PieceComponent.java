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


public class PieceComponent extends JComponent {

	public final static int CELL_SIZE = 15;
	private static Piece piece = new Piece(5,4, 3, 2);
	private BufferedImage piecePic;

	public PieceComponent(BufferedImage image, Piece piece) {

		PieceComponent.piece = piece;
		piecePic = image;

		// Check for which piece fits the given piece, sets the correct image
		// for it

//		if (piece.getEdge(Piece.NORTH) == Piece.CLUBS_OUT) {
//			if (piece.getEdge(Piece.WEST) == Piece.CLUBS_IN) {
//				// This is Piece 1, get the image and assign it
//				piecePic = ImageIO.read(new File("resources/piece_1.png"));
//			}
//			
//			if (piece.getEdge(Piece.WEST) == PIECE.HEARTS_IN) {
//				// This is Piece 8, get the image and assign it
//				piecePic = ImageIO.read(new File("resources/piece_8.png"));
//			}
//			
//			if (piece.getEdge(Piece.WEST) == PIECE.DIAMONDS_OUT) {
//				// This is Piece 9, get the image and assign it
//				piecePic = ImageIO.read(new File("resources/piece_9.png"));
//			}
//			
//		}
//
//		if (piece.getEdge(Piece.NORTH) == Piece.HEARTS_OUT) {
//			if (piece.getEdge(Piece.SOUTH) == Piece.SPADES_IN) {
//				// This is Piece 3, get the image and assign it
//				piecePic = ImageIO.read(new File("resources/piece_3.png"));
//			}
//			
//			if (piece.getEdge(Piece.SOUTH) == Piece.CLUBS_IN) {
//				// This is Piece 4, get the image and assign it
//				piecePic = ImageIO.read(new File("resources/piece_4.png"));
//			}
//			
//			if (piece.getEdge(Piece.SOUTH) == Piece.DIAMONDS_IN) {
//				// This is Piece 6, get the image and assign it
//				piecePic = ImageIO.read(new File("resources/piece_6.png"));
//			}
//			
//		}
//
//		if (piece.getEdge(Piece.NORTH) == Piece.SPADES_OUT) {
//			if (piece.getEdge(Piece.WEST) == Piece.HEARTS_IN) {
//				// This is Piece 2, get the image and assign it
//				piecePic = ImageIO.read(new File("resources/piece_2.png"));
//			}
//			
//			if (piece.getEdge(Piece.WEST) == Piece.CLUBS_IN) {
//				// This is Piece 5, get the image and assign it
//				piecePic = ImageIO.read(new File("resources/piece_5.png"));
//			}
//			
//			if (piece.getEdge(Piece.WEST) == Piece.DIAMONDS_IN) {
//				// This is Piece 7, get the image and assign it
//				piecePic = ImageIO.read(new File("resources/piece_7.png"));
//			}
//		}
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
		piece.rotate(2);
		frame.setVisible(true);
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
