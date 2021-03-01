package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	JLabel message;
	OneLetterField[][] field;
	Sudoku sudoku;
	
	public SudokuWindow() {
		SwingUtilities.invokeLater(() -> createWindow("Sudoku", 400, 500));
	}
	
	private void createWindow(String title, int width, int height) {
		
		/** Instansierar samtliga element*/
		frame = new JFrame(title);
		buttonPanel = new JPanel();
		boardPanel = new JPanel();
		solveButton = new SolveButton("Solve");
		clearButton = new ClearButton("Clear");
		message = new JLabel("Message prompt");
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
		
		
		/** Sätter gränser för fönster*/
		frame.setSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setResizable(false);
		
		buttonPanel.add(solveButton);
		buttonPanel.add(clearButton);
		
		
		boardPanel.setLayout(new GridLayout(9,9));
		
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.add(boardPanel);

		frame.setVisible(true);
	}
	

	private class OneLetterField extends JTextField {
		
		public OneLetterField() {
			super("0");
			setDocument(new OneNumberDocument());
		}
		
		public void clearField() {
			removeAll();
			setDocument(new OneNumberDocument());
		}
		
		private class OneNumberDocument extends PlainDocument {
			
			OneNumberDocument() {
				super();
			}
			
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
	
	/** Här kan man plocka inspiration från, fanns tips på kurshemsidan*/
//	public class OneLetterTextField extends TextField {
//
//		@Override
//		public void replaceText(int start, int end, String text) {
//			if (matches(text)) {
//				super.replaceText(start, end, text);
//			}
//		}
//		
//		@Override
//		public void replaceSelection(String text) {
//			if (matches(text)) {
//				super.replaceSelection(text);
//			}
//		}
//
//		private boolean matches(String text) {
//			return text.isEmpty() || (getText().length() < 1) && text.matches("[A-Z]") ;
//		}
//
//	}
	
	private class SolveButton extends JButton implements ActionListener {
		
		SolveButton(String name) {
			super(name);
			
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					try {
						sudoku.setNumber(i, j, Integer.parseInt(field[i][j].getText()));
					} catch(NumberFormatException x) {
						sudoku.setNumber(i, j, 0);	
					}
				}
			}
			
			if(sudoku.solve()) {
				for(int i = 0; i < 9; i++) {
					for(int j = 0; j < 9; j++) {
						field[i][j].setText(String.valueOf(sudoku.getNumber(i, j)));
					}
				}
			}
			else {
				System.out.println("Sudokut ej lösbart");
			}
		}	
	}
	
	private class ClearButton extends JButton implements ActionListener {

		ClearButton(String name) {
			super(name);
			
			addActionListener(this);
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					sudoku.setNumber(i, j, 0);
					field[i][j].setText("0");
				}
			}
		}
	}
		
	public static void main(String[] args) {
		new SudokuWindow();
	
		
		
//		sudoku.setNumber(3, 4, 2);
//		sudoku.setNumber(3, 4, 2);
//		sudoku.setNumber(3, 4, 2);
//		sudoku.setNumber(3, 4, 2);
//		sudoku.setNumber(3, 4, 2);
//		sudoku.setNumber(3, 4, 2);
//		sudoku.setNumber(3, 4, 2);
//		sudoku.setNumber(3, 4, 2);
//		sudoku.setNumber(3, 4, 2);
//		sudoku.setNumber(3, 4, 2);
//		sudoku.setNumber(3, 4, 2);
//		
//		sudoku.setNumber(3, 4, 2);
//		sudoku.setNumber(3, 4, 2);
//		sudoku.setNumber(3, 4, 2);
//		sudoku.setNumber(3, 4, 2);
//		sudoku.setNumber(3, 4, 2);
//		
//		System.out.println(sudoku.getNumber(3, 4));
//		
//		
//		//sudoku.setNumber(3, 4, 25);
//		
//		sudoku.printSudoku();
//		
//		System.out.println(sudoku.isValid(3, 5, 3));
//		
//		System.out.println(sudoku.isAllValid());
//		
//		sudoku.printSudoku();

	}

}
