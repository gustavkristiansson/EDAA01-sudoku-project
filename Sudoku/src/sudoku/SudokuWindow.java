package sudoku;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SudokuWindow {
	JFrame frame;
	JButton solveButton, clearButton;
	JPanel buttonPanel;
	JPanel boardPanel;
	JLabel message;
	
	
	
	
	public SudokuWindow() {
		
		/** Instansierar samtliga element*/
		frame = new JFrame("Sudoku");
		buttonPanel = new JPanel();
		boardPanel = new JPanel();
		solveButton = new JButton("Solve");
		clearButton = new JButton("Clear");
		message = new JLabel("Message prompt");
		Sudoku sudoku = new Sudoku();
		
		
		/** Sätter gränser för fönster*/
		frame.setSize(new Dimension(250, 300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		buttonPanel.add(solveButton);
		buttonPanel.add(clearButton);
		
		
		boardPanel.setLayout(new GridLayout(9,9));
		
		
		
		
		frame.add(buttonPanel);
		frame.add(boardPanel);
		
		
		
		frame.setVisible(true);
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
