import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PuzzlePanel extends JPanel {

	protected PieceComponent[][] pieces = new PieceComponent[3][3];
	private PieceComponent p;
	private ArrayList<PieceComponent> unusedPieces = new ArrayList<PieceComponent>(
			9);
	private Puzzle puzzle;
	private Listener listener;
	protected Rectangle puzzleArea;
	protected Rectangle unusedPieceArea;
	protected int cellSize;
	protected int pieceSize;
	public Point mouseLocation;
	protected boolean noFit = false;

	static Color c = new Color(150, 200, 255);

	public ArrayList<PieceComponent> getUnusedPieces() {
		return unusedPieces;
	}

	public PuzzlePanel(Listener listener, BufferedImage[] imgs, Piece[] pieces) {
		this.listener = listener;
		addMouseListener(listener);
		addMouseMotionListener(listener);
		addMouseWheelListener(listener);
		addKeyListener(listener);
		setFocusable(true);
		requestFocusInWindow();
		for (int i = 0; i < pieces.length; i++) {
			unusedPieces.add(new PieceComponent(imgs[i], pieces[i]));
		}
		puzzle = new Puzzle(3, 3, pieces);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(c);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		Graphics2D g2 = (Graphics2D) g;
		g2 .setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		puzzleArea = new Rectangle();
		puzzleArea.setFrameFromCenter(getHeight() / 3, getHeight() / 3, 100,
				100);
		unusedPieceArea = new Rectangle();
		unusedPieceArea.setBounds(puzzleArea.x, (int) puzzleArea.getMaxY(),
				pieceSize * 5, pieceSize * 2);
		cellSize = (int) (puzzleArea.getWidth() / 3);
		pieceSize = (int) (cellSize * 1.345);
		int row = 0;
		int col = 0;

		/* Paints the puzzle */
		for (int i = puzzleArea.x; i < (int) puzzleArea.getMaxX()
				&& col < pieces.length; i += cellSize) {
			for (int j = puzzleArea.y - 20; j < (int) puzzleArea.getMaxY()
					&& row < pieces[0].length; j += cellSize) {

				if (pieces[row][col] != null && !isPieceHeld(pieces[row][col]))
					g.drawImage(pieces[row][col].getPiecePic(), i
							- (pieceSize - cellSize), j
							- (pieceSize - cellSize), i + pieceSize, j
							+ pieceSize, 0, 0, pieces[row][col].getPiecePic()
							.getWidth(), pieces[row][col].getPiecePic()
							.getHeight(), this);
				row++;
				g.drawRect(i, j, cellSize, cellSize);
			}
			col++;
			row = 0;
		}
		/* Paints the piece that is being dragged */
		if (listener.holdingPiece && listener.pieceHeld != null) {
			g.drawImage(listener.pieceHeld.getPiecePic(), mouseLocation.x
					- pieceSize / 2, mouseLocation.y - pieceSize / 2,
					mouseLocation.x + pieceSize / 2, mouseLocation.y
							+ pieceSize / 2, 0, 0, listener.pieceHeld
							.getPiecePic().getWidth(), listener.pieceHeld
							.getPiecePic().getHeight(), this);

			if (noFit) {/*
						 * If the piece doesn't fit draw a red X over the
						 * dragged piece
						 */
				noFit = false;
				g.setColor(Color.RED);
				g.drawLine(mouseLocation.x - pieceSize / 2, mouseLocation.y
						- pieceSize / 2, mouseLocation.x + pieceSize / 2,
						mouseLocation.y + pieceSize / 2);
				g.drawLine(mouseLocation.x + pieceSize / 2, mouseLocation.y
						- pieceSize / 2, mouseLocation.x - pieceSize / 2,
						mouseLocation.y + pieceSize / 2);
				g.setColor(Color.BLACK);
			}
		}

		/* Unused Piece Area */
		int index = 0; /* Index to get piece from */
		g.drawRect(unusedPieceArea.x, unusedPieceArea.y, unusedPieceArea.width,
				unusedPieceArea.height);
		for (int i = unusedPieceArea.x; i < unusedPieceArea.getMaxX(); i += pieceSize) {
			for (int j = unusedPieceArea.y; j < unusedPieceArea.getMaxY()
					&& j + pieceSize < getHeight() && index < 9; j += pieceSize) {
				if (index < unusedPieces.size())
					g.drawImage(unusedPieces.get(index).getPiecePic(), i, j, i
							+ pieceSize, j + pieceSize, 0, 0,
							unusedPieces.get(index).getPiecePic().getWidth(),
							unusedPieces.get(index).getPiecePic().getHeight(),
							this);
				index++;
			}
		}

	}

	public void setPiece(int row, int col, PieceComponent p) {
		if (doesFit(row, col, p)) {
			pieces[row][col] = p;
			puzzle.setPiece(row, col, p.getPiece());
			unusedPieces.remove(p);
			repaint();
		}

	}
	
	public void addPiece(int row, int col, PieceComponent p)
	{
		pieces[row][col] = p;
		unusedPieces.remove(p);
	}

	public PieceComponent removePiece(int row, int col) {
		puzzle.removePiece(row, col);
		PieceComponent removed = pieces[row][col];
		// unusedPieces.add(removed);
		pieces[row][col] = null;
		repaint();
		return removed;
	}

	public PieceComponent getPiece(int row, int col) {

		return pieces[row][col];
	}

	/**
	 * 
	 * @param piece
	 *            piece to merge with PieceComponent
	 * @return the correct PieceComponent
	 */
	public PieceComponent mergePiece(Piece piece) {
		for (PieceComponent p : unusedPieces) {
			if (p.isSamePiece(piece))
				return p;
		}
		return null;
	}

	public void solve() {
		clear();
		puzzle.solve();
		for (int i = 0; i < puzzle.rows; i++) {
			for (int j = 0; j < puzzle.cols; j++) {
				addPiece(i, j, mergePiece(puzzle.getPiece(i, j)));
			}
		}
		repaint();
		//return new abstract final static Object;
	}

	public void clear() { // FIX
		for (int i = 0; i < pieces[0].length; i++) {
			for (int j = 0; j < pieces.length; j++) {
				if (pieces[i][j] != null) {
					unusedPieces.add(pieces[i][j]);
					pieces[i][j] = null;
				}
			}
		}
		puzzle.restart();
		repaint();
	}

	public boolean isPieceHeld(PieceComponent p) {
		if (p != null)
			if (p.equals(listener.pieceHeld))
				return true;
		return false;
	}

	public Puzzle getPuzzle() {
		return puzzle;
	}

	public boolean isEmpty(int row, int col) {
		if (pieces[row][col] == null)
			return true;
		return false;
	}

	public boolean inBoard(int row, int col) {
		if (row >= pieces.length)
			return false;
		if (col >= pieces[0].length)
			return false;
		return true;
	}

	public boolean inPieces(int row, int col) {
		int val = col * 2 + row;
		if (val < unusedPieces.size() && val > -1)
			return true;
		return false;
	}

	public boolean inPiecesArea(int x, int y) {
		if (x < 0 || x > unusedPieceArea.getWidth())
			return false;
		if (y < 0 || y > unusedPieceArea.getHeight())
			return false;
		return true;
	}

	public boolean doesFit(int row, int col, PieceComponent p) {
		return puzzle.doesFit(row, col, p.getPiece());
	}
	
	/**
	 * Checks if the sides on the pieces are all matching
	 * @param p1 A piece
	 * @param p2 A piece
	 * @return true if they match false if they don't
	 */
	public static boolean equals(Piece p1, Piece p2)
	{
		for(int i = 0; i < p1.piece.length; i++)
		{
			if(p1.piece[i] != p2.piece[i]) return false;
		}
		return true;
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * Dimension dimension = new Dimension(550,550); JFrame f = new
	 * JFrame("Puzzle"); f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 * f.setSize(700, 700); f.setMinimumSize(dimension); JPanel panel = new
	 * JPanel(new GridBagLayout()); f.getContentPane().add(panel,
	 * BorderLayout.NORTH);
	 * 
	 * PieceComponent piece = new PieceComponent(); f.add(piece); //
	 * GridBagConstraints constraints = new GridBagConstraints(); //
	 * constraints.gridheight = 1; // constraints.gridwidth = 1;
	 * constraints.insets = new Insets(10,0,0,0); // Grid g = new Grid(); //
	 * constraints.gridx = 0; // constraints.gridy = 0; panel.add(g.run(),
	 * constraints); PuzzlePanel p = new PuzzlePanel(); f.add(p); // // //
	 * constraints.gridx = 10; // constraints.gridy = 10;
	 * 
	 * //f.add(piece); // g.gridheight = 1; // g.gridwidth = 1;
	 * 
	 * 
	 * 
	 * // g.gridx = 0; // g.gridy = 0; // panel.add(piece, g);
	 * f.setVisible(true); // f.add(panel); // f.add(p); // f.pack();
	 * 
	 * }
	 */

}
