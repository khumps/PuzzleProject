import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class Grid extends JPanel{


	//    public static void main(String[] args) {
	//        new Grid();
	//    }

	public Grid() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
				}

			}
		});
	}

	public class Pane extends JPanel {

		public Pane() {
			setLayout(new GridBagLayout());

			GridBagConstraints gbc = new GridBagConstraints();
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					gbc.gridx = col;
					gbc.gridy = row;

					CellPane cellPane = new CellPane();
					Border border = null;
					if (row < 2) {
						if (col < 2) {
							border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
						} else {
							border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
						}
					} else {
						if (col < 2) {
							border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
						} else {
							border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
						}
					}
					cellPane.setBorder(border);
					add(cellPane, gbc);
				}
			}
		}
	}

	public class CellPane extends JPanel {

		private Color defaultBackground;

		public CellPane() {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					defaultBackground = getBackground();
					setBackground(Color.BLUE);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					setBackground(defaultBackground);
				}
			});
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(100, 100);
		}
	}

	public Component run() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
		}
		JComponent j = new Grid();
		//        JFrame frame = new JFrame("Testing");
		//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setLayout(new BorderLayout());
		j.add(new Pane());
		//j.pack();
		//j.setLocationRelativeTo(null);
		//j.setVisible(true);
		return j;
	}

}
