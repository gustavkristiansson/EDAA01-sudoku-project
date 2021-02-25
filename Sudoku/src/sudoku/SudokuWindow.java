package sudoku;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SudokuWindow {
	JFrame frame;
	JButton solveButton, clearButton;
	private static Sudoku sudoku;
	
	
	public SudokuWindow() {
		
		
	}
	
	public static void main(String[] args) {
		new SudokuWindow();
		
		sudoku = new Sudoku();
		
		
		sudoku.setNumber(3, 4, 2);
		
		System.out.println(sudoku.getNumber(3, 4));
		
		
		//sudoku.setNumber(3, 4, 25);
		
		sudoku.printSudoku();
		
		System.out.println(sudoku.isValid(3, 5, 3));
		
		System.out.println(sudoku.isAllValid());
		
		sudoku.printSudoku();
		
	}

}
