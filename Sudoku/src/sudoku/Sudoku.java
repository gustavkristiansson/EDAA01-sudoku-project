package sudoku;

public class Sudoku implements SudokuSolver {
	private int board [][];
	
	public Sudoku() {
		setMatrix(new int[9][9]);
		//board = new int[9][9]; 
	}

//	public static void main(String[] args) {
//		Sudoku s = new Sudoku(2,2);
//		
//	}
	
	
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
		if(checkDimension(r,c) && nbr > 0 && nbr <= 9) {
			board[r][c] = nbr;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	private boolean checkDimension(int r, int c) {
		return (r >= 0 && r < getDimension() && c >= 0 && c < getDimension());	
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
		if(checkDimension(r,c)) {
			if(board[r][c] == 0) {
				return 0;
			} else {
				return board[r][c];
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	
	
	
	
	@Override
	public void clearNumber(int r, int c) {
		if(checkDimension(r,c)) {
			board[r][c] = 0;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	
	// Kontrollerar om värdet nbr i rutan r,c är ok enligt reglerna.
	// IllegalArgumentException om fel värde på r, c eller nbr
	@Override
	public boolean isValid(int r, int c, int nbr) {
		
		return (checkRowsCols(r,c,nbr, 0) && checkQuadrant(r, c, nbr, 0));
	}
	
	private boolean checkRowsCols(int r, int c, int nbr, int unique) {
		//test
		if(nbr == 0) {
			return true;
		}
		
		if(checkDimension(r,c) && nbr > 0 && nbr <= 9) {
			int ff = 0;
			
			for(int i = 0; i < getDimension(); i++) {
				if((board[i][c] == nbr || board[r][i] == nbr)) {
					ff++;
				}
			}
				return (ff == unique);
			} else {	
			throw new IllegalArgumentException();
			}
	}
	
//	private boolean checkRow(int col, int nbr) {
//		for(int i = 0; i < getDimension(); i++) {
//			if(board[i][col] == nbr) {
//				return false;
//			}
//		}
//		return true;
//	}
//	
//	private boolean checkCol(int row, int nbr) {
//		for(int i = 0; i < getDimension(); i++) {
//			if(board[row][i] == nbr) {
//				return false;
//			}
//		}
//		return true;
//	}

	@Override
	public boolean isAllValid() {
		boolean isValid = true;
		for(int i = 0; i < getDimension(); i++) {
			for(int y = 0; y < getDimension(); y++) {
				isValid = checkRowsCols(i,y,board[i][y], 1);
				isValid = checkQuadrant(i, y, board[i][y], 1);
			}	
		}
		
		
		return isValid;
	}
	
	private boolean checkQuadrant(int r, int c, int nbr, int unique) {
		//test
		if(nbr == 0) {
			return true;
		}
		
		int ncol = 3 * (c / 3);
		int nrow = 3 * (r / 3);
		int ff = 0;
		
		
		for(int row = nrow; row < nrow+2; row++) {
			for(int col = ncol; col < ncol+2; col++) {
				if(board[row][col] == nbr) {
					ff ++;
				}
			}
		}
		
		return (ff == unique);
	}

	@Override
	public boolean solve() {
		return solveV2(0,0);
	}
	
	private boolean solve(int r, int c) {
		if(r == getDimension() -1 && c == getDimension() -1) {
			solveSetNumber(r,c);	
			return isAllValid();	
		} else {
			if(solveSetNumber(r,c)) {
				if(c == 8) {
					return solve(r + 1, 0);
				} else {
					return solve(r, c + 1);
				}
			} else if(c == 0){
				return solve(r - 1, 8);
			} else  {
				return solve(r, c - 1);
			}
		}
	}
	
	
	private boolean solveSetNumber(int r, int c) {
		if(board[r][c] == 0) {
			for(int i = 1; i <= 9; i++) {
				if(isValid(r,c,i)) {
					setNumber(r,c,i);
					return true;
				} 
			}
			return false;
		} else {
			return false;
		}
	}
	
	
	
	//test ny solve
	
	
	private boolean solveV2(int r, int c) {
		if(c == getDimension() - 1) {
			if(r < getDimension()) {
				r++;
				c = 0;			
			} else {
				return true;
			}
		}
		
		for(int i = 1; i <= 9; i++) {
			if(isValid(r,c,i)) {
				setNumber(r,c,i);
				if(solve(r, c + 1)) {
					return true;
				}
			}
			
		}
		
		return false;
		
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public boolean solveV3(int r, int c) {
//		if(board[r][c] == 0) {
//			
//			if(r == 8 && c == 8) { //sista rutan
//				for(int i = 1; i <= 9; i++) { 
//					board[r][c] = i;    //sätt in i
//					if(checkQuadrant(r, c)) { //kolla om i är giltigt
//						return true; // i så fall löst
//					}
//				}
//				return false;
//			}
//			for(int i = 1; i <= 9; i++) {
//				setNumber(r, c, i);
//				if(checkQuadrant(r, c)) {
//					if(c < 8) {
//						if(solve(r, c + 1)) {
//							return true;
//						}
//					}
//					else if(solve(r + 1, 0)) {
//							return true;
//					}
//				}
//			}
//			setNumber(r, c, 0);
//			return false; //går tillbaka om ej fungerar
//		
//		} else {
//			
//			if(r == 8 && col == 8) {
//				return checkQuadrant(r, c);
//			}
//			if(checkQuadrant(r, c)) {
//				if(c < 8) {
//					return solve(r, c + 1);
//				} else {
//					return solve(r + 1, 0);
//				}
//			}
//		}
//		return false;
//	}
//	
	
	
	public void printSudoku() {
		System.out.println();
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(" | " + board[i][j] + "| ");
			}
		System.out.println();
		}
	}
	

	@Override
	public void clear() {
		for(int i = 0; i < getDimension(); i++) {
			for(int y = 0; y < getDimension(); y++) {
		board[i][y] = 0;
				
			}
		}
	}

	@Override
	public int[][] getMatrix() {
		
		int[][] temp = new int[getDimension()][getDimension()];
		for(int i = 0; i < getDimension(); i++) {
			for(int y = 0; y < getDimension(); y++) {
			temp[i][y] = board[i][y];
				
			}
		}
		
		return temp;
	}

	@Override
	public void setMatrix(int[][] nbrs) {
		board = nbrs;

	}

}
