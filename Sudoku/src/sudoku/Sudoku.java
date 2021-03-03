package sudoku;

import java.util.Arrays;

public class Sudoku implements SudokuSolver {
	private int board [][];
	
	public Sudoku() {
		setMatrix(new int[9][9]);
	}
	
	public Sudoku(int[][] board) {
		this.board = board;
	}
	
	public Sudoku(int dimension) {
		this.board = new int[dimension][dimension];
	}
	
	/**
	 * Sets the number nbr in box r, c.
	 * 
	 * @param r
	 *            The row
	 * @param c
	 *            The column
	 * @param nbr
	 *            The number to insert in box r, c
	 * @throws IllegalArgumentException        
	 *             if r or c is outside [0..getDimension()-1] or
	 *             number is outside [1..9] 
	 */
	@Override
	public void setNumber(int r, int c, int nbr) {
		if(!checkDimension(r, c) || nbr < 1 || nbr > getDimension()) {
			throw new IllegalArgumentException();
		} else {
			board[r][c] = nbr;
		}
	} 
	
	private boolean checkDimension(int r, int c) {
		return ((r >= 0 && r < getDimension()) && (c >= 0 && c < getDimension()));	
	}
	
	/**
	 * Returns the number in box r,c. If the box i empty 0 is returned.
	 * 
	 * @param r
	 *            The row
	 * @param c
	 *            The column
	 * @param number
	 *            The number to insert in r, c
	 * @return the number in box r,c or 0 if the box is empty.
	 * @throws IllegalArgumentException
	 *             if r or c is outside [0..getDimension()-1]
	 */
	@Override
	public int getNumber(int r, int c) {
		if(!checkDimension(r, c)) {
			throw new IllegalArgumentException();
		} else {
			return board[r][c];
		}
	}
	
	/**
	 * Clears number at position r, c.
	 * @param r
	 * 				The row
	 * @param c
	 * 				The column
	 * @throws IllegalArgumentException
	 * 				if r or c is outside [0...getDimension()-1]
	 */
	@Override
	public void clearNumber(int r, int c) {
		if(!checkDimension(r, c)) {
			throw new IllegalArgumentException();
		} else {
			board[r][c] = 0;
		}
	}
	
	/**
	 * Checks if number at position r, c is valid according to Sudoku rules.
	 * 
	 * @param r
	 * 				The row
	 * @param c
	 * 				The column
	 * @param nbr
	 * 				The number to check
	 * @return true if number is valid else false.
	 * @throws IllegalArgumentException() 
	 * 				if r or c is outside [0..getDimension()-1] or 
	 * 				number is outside [1..9] 
	 */
	@Override
	public boolean isValid(int r, int c, int nbr) {
		if(!checkDimension(r, c) || nbr < 1 || nbr > 9) {
			throw new IllegalArgumentException();
		} else {
			return (checkRowsCols(r, c, nbr, 0) && checkQuadrant(r, c, nbr, 0));
		}
	}
	
	private boolean checkRowsCols(int r, int c, int nbr, int unique) {
//		if(nbr == 0) {
//		return true;
//	}
	
//		int ff = 0;
		

		
		
		for(int i = 0; i < getDimension(); i++) {
			if(board[r][i] == nbr && i != c) {
				return false;
			}
		}
		
		for(int i = 0; i < getDimension(); i++) {
			if(board[i][c] == nbr && i != r) {
				return false;
			}
		}
		
//		for(int i = 0; i < getDimension(); i++) {
//			if((board[i][c] == nbr && i != c)|| (board[r][i] == nbr && i != r)) {
//				ff++;
//				
//				return false;
//			}
//		}
		//ff = ff - ff/2;
		//return (ff == unique);	
		
		return true;
	}
			
	
	
	/**
	 * Checks that all filled in numbers are valid according to sudoku rules.
	 * @return true if all numbers are valid, else false.
	 */
	@Override
	public boolean isAllValid() {
			for(int i = 0; i < getDimension(); i++) {
				for(int j = 0; j < getDimension(); j++) {
					if(board[i][j] != 0) {
						if(!isValid(i, j, board[i][j])) {
						//if(!checkRowsCols(i,j,board[i][j], 0) || !(checkQuadrant(i, j, board[i][j], 0))) {
			
						return false;
						}
					}	
				}
				
			}
			//return isAllValid();
		return true;
	}
	
	private boolean checkQuadrant(int r, int c, int nbr, int unique) {
//		if(nbr == 0) {
//			return true;
//		}
		
		int ncol = 3 * (c / 3);
		int nrow = 3 * (r / 3);
		int ff = 0;
		
		
		for(int row = nrow; row < nrow + 3; row++) {
			for(int col = ncol; col < ncol + 3; col++) {
//				if(board[row][col] == nbr) {
//					f++;
//				}
				if(row != r && c != col && board[row][col] == nbr) {
					return false;
				}
			}
		}
		return true;
		//return (ff == unique);
	}

	@Override
	public boolean solve() {
		return  isAllValid() && solve(0,0);
	}
	
	private boolean solve(int r, int c) {
		
		if(c == getDimension()) {
			if(r < getDimension() - 1) {
				r++;
				c = 0;			
			} else {
				return true;
			}
		}

		if(getNumber(r, c) == 0) {
			for(int i = 1; i <= 9; i++) {
				if(isValid(r, c, i)) {
						setNumber(r, c, i);	 
					if(solve(r, c + 1)) {
						return true;
					} else {
						clearNumber(r, c);
					}
				}
			}
			return false;
		}
		else {
			if(isValid(r, c, getNumber(r, c))) {
				return solve(r, c + 1);
			}
			return false;
		}
	}

	
	/**
	 * Prints the sudoku to the user (if preferred used without GUI).
	 */
	public void printSudoku() {
		System.out.println();
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(" | " + board[i][j] + "| ");
			}
		System.out.println();
		}
	}
	
	/**
	 * Clears all positions in the sudoku.
	 */
	@Override
	public void clear() {
		for(int i = 0; i < getDimension(); i++) {
			for(int j = 0; j < getDimension(); j++) {
				board[i][j] = 0;
				
			}
		}
	}
	
	/**
	 * Returns the numbers in the grid. An empty box i represented
	 * by the value 0.
	 * 
	 * @return the numbers in the grid
	 */
	@Override
	public int[][] getMatrix() {
		int[][] temp = new int[getDimension()][getDimension()];
		for(int i = 0; i < getDimension(); i++) {
			for(int j = 0; j < getDimension(); j++) {
				temp[i][j] = board[i][j];	
			}
		}
		return temp;
	}
	
	/**
	 * Fills the grid with the numbers in nbrs.
	 * 
	 * @param nbrs the matrix with the numbers to insert
	 * @throws IllegalArgumentException
	 *             if nbrs have wrong dimension or containing values not in [0..9] 
	 */
	@Override
	public void setMatrix(int[][] nbrs) {
		boolean inValid = false;
		
		for(int i = 0; i < nbrs.length; i++) {
			for(int j = 0; j < nbrs.length; j++) {
				if((nbrs[i][j] < 0) || (nbrs[i][j] > 9)) {
					inValid = true;
				}
			}
		}
		
		if(nbrs.length != getDimension() || nbrs[0].length != getDimension() || inValid) {
			throw new IllegalArgumentException();
		} else {
			int[][] temp = new int[getDimension()][getDimension()];
			for(int i = 0; i < getDimension(); i++) {
				for(int j = 0; j < getDimension(); j++) {
					temp[i][j] = nbrs[i][j];	
				}
			}
		board = temp;
		}
	}

	
	//test med main
	
	public static void main(String[] args) {
		Sudoku s = new Sudoku();
		
		//s.setNumber(8, 2, 2);
		//s.setNumber(2, 4, 3);
		//System.out.println(s.isValid(0, 0, 1));
		//System.out.println(s.isValid(1, 1, 3));
		System.out.println(s.isAllValid());
		System.out.println("row "  + s.checkRowsCols(0, 0, 2, 0));
		
		System.out.println(s.getNumber(0, 1));
		
		//System.out.print(s.checkQuadrant(2, 4, 2, 1));
		//System.out.print(s.checkQuadrant(2, 4, 2, 1));
		
		System.out.println("row "  + s.checkRowsCols(8, 2, 2, 1));		
		System.out.println("solve = " + s.solve());
		//s.printSudoku();
	}
}
	
	





