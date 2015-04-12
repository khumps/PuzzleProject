import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PuzzlePanel extends JPanel {
	
	private PieceComponent[][] pieces;
	private ArrayList<PieceComponent> unusedPieces = new ArrayList<PieceComponent>();
	private Puzzle puzzle;
	private JPanel unusedPiecePanel;
	static Color c = new Color(150,200,255);
	
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
	public PuzzlePanel(Listener listener, BufferedImage[] imgs, Piece[] pieces){
		for(Piece p: pieces){
			for(BufferedImage img:imgs)
			unusedPieces.add(new PieceComponent(img,p));
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
	
//	For this method to work we need a PieceComponent constructor that has take int row and col
//	public PieceComponent removePiece(int row, int col){
//		puzzle.removePiece(row,col);
//		return null; //PieceComponent that was there
//	}
	
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
		f.setSize(200, 200);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel p = new JPanel();
		f.add(new PuzzlePanel());
		GridBagConstraints g = new GridBagConstraints();
		f.getContentPane().add(p, BorderLayout.NORTH);
		g.insets = new Insets(20,10,10,10);
		
		PieceComponent piece = new PieceComponent();
		g.gridx = 0;
		g.gridy = 0;
		p.add(piece, g);
		
		JLabel l2 = new JLabel("Label 2");
		g.gridx = 1;
		g.gridy = 0;
		p.add(l2, g);
		JButton b = new JButton("Button");
		g.gridx = 2;
		g.gridy = 1;
		p.add(b, g);
		f.pack();
		
	}

}
