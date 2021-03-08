package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class SudokuWindow {
	JFrame frame;
	JButton solveButton, clearButton;
	JPanel buttonPanel;
	JPanel boardPanel;
	OneLetterField[][] field;
	SudokuSolver sudoku;
	Container pane; 
	
	/**
	 * Creates a new sudoku window with a visible GUI.
	 */
	public SudokuWindow() {
		SwingUtilities.invokeLater(() -> createWindow("Sudoku", 400, 500));
	}
	
	private void createWindow(String title, int width, int height) {
		
		/** Instantiates all elements*/
		frame = new JFrame(title);
		buttonPanel = new JPanel();
		boardPanel = new JPanel();
		pane = frame.getContentPane();
		solveButton = new SolveButton("Solve");
		clearButton = new ClearButton("Clear");
		sudoku = new Sudoku();
		field = new OneLetterField[9][9];
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				field[i][j] = new OneLetterField();
				
				if(i < 3 && (j < 3 || j >= 6) ||
							i >= 3 && i < 6 && j >= 3 && j < 6 ||
							i >= 6 && (j < 3 || j >= 6)) {
					field[i][j].setBackground(new Color(255, 69, 0));
				}
				field[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				boardPanel.add(field[i][j]);
			}
		}
		
		/** Sets borders and settings for window */
		frame.setSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		buttonPanel.add(solveButton);
		buttonPanel.add(clearButton);
		
		boardPanel.setLayout(new GridLayout(9,9));
		
		pane.add(buttonPanel, BorderLayout.SOUTH);
		pane.add(boardPanel, BorderLayout.CENTER);

		frame.setVisible(true);
	}
	

	private class OneLetterField extends JTextField {
		
		public OneLetterField() {
			super("0");
			setDocument(new OneNumberDocument());
		}
		
		private class OneNumberDocument extends PlainDocument {
			
			OneNumberDocument() {
				super();
			}
			
			/**
			 * Checks whether a String can be inserted. 
			 */
			@Override
			public void insertString(int comp, String string, AttributeSet set) throws BadLocationException {
				if(string.equals("") || string.equals("0")) {
					return;
				}
				if((getLength() + string.length() > 1)) {
					return;
				}
				if(!Character.isDigit(string.charAt(0))) {
					return;
				}
				super.insertString(comp, string, set);	
			}	
		}
	}
	
	private class SolveButton extends JButton implements ActionListener {
		
		ImageIcon icon = new ImageIcon(Sudoku.class.getResource("problemSolved.gif").getFile());
		
		SolveButton(String name) {
			super(name);
			
			addActionListener(this);
		}
		
		
		/**
		 * Fills the grid with numbers fetched from solvable sudoku.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					try {
						sudoku.setNumber(i, j, Integer.parseInt(field[i][j].getText()));
					} catch(NumberFormatException x) {
						sudoku.clearNumber(i, j);
					}
				}
			}
			
			if(sudoku.solve()) {
				for(int i = 0; i < 9; i++) {
					for(int j = 0; j < 9; j++) {
						field[i][j].setText(String.valueOf(sudoku.getNumber(i, j)));
					}
				}
				JOptionPane.showMessageDialog(boardPanel, "", "Sudoku löst", JOptionPane.INFORMATION_MESSAGE, icon);
			}
			else {
				JOptionPane.showMessageDialog(boardPanel, "Sudokut ej lösbart");
			}
		}	
	}
	
	private class ClearButton extends JButton implements ActionListener {
		
		ClearButton(String name) {
			super(name);
			
			addActionListener(this);
		}
		
		/**
		 * Clears all numbers in the sudoku grid.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					sudoku.clear();
					field[i][j].setText("0");
				}
			}
		}
	}
		
	public static void main(String[] args) {
		new SudokuWindow();
	}
}
