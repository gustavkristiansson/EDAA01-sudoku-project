package sudoku;

public class Sudoku implements SudokuSolver {
	private int board [][];
	
	public Sudoku() {
		setMatrix(new int[9][9]);
	}
	
	public Sudoku(int[][] board) {
		this.board = board;
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
		if((!checkDimension(r, c)) || (nbr < 0 || nbr > getDimension())) {
			throw new IllegalArgumentException();
		} else {
			board[r][c] = nbr;
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
		if(!checkDimension(r, c)) {
			throw new IllegalArgumentException();
		} else {
			if(board[r][c] == 0) {
				return 0;
			} else {
				return board[r][c];
			}
		}
	}
	
	
	@Override
	public void clearNumber(int r, int c) {
		if(!checkDimension(r, c)) {
			throw new IllegalArgumentException();
		} else {
			board[r][c] = 0;
		}
	}
	
	
	// Kontrollerar om värdet nbr i rutan r,c är ok enligt reglerna.
	// IllegalArgumentException om fel värde på r, c eller nbr
	@Override
	public boolean isValid(int r, int c, int nbr) {
		
		return (checkRowsCols(r,c,nbr, 0) && checkQuadrant(r, c, nbr, 0));
	}
	
	
	public boolean isValidV2(int r, int c, int nbr) {
		for(int i = 0; i < getDimension(); i++) {
			if(i != c) {
				if(board[r][i] == board[r][c]) {
					return false;
				}
			}
			if(i != r) {
				if(board[i][c] == board[r][c]) {
					return false;
				}
			}
		}
		
		int ncol = 3 * (c / 3);
		int nrow = 3 * (r / 3);
		
		for(int row = nrow; row < nrow+2; row++) {
			for(int col = ncol; col < ncol+2; col++) {
				if (board[row][col] == board[r][c] && row != r && col != c) {
					return false;
				}
			}
		}
		return true;	
	}
	
	private boolean checkRowsCols(int r, int c, int nbr, int unique) {
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
				ff = ff - ff/2;
				return (ff == unique);
			} else {	
				throw new IllegalArgumentException();
			}
	}
	

	@Override
	public boolean isAllValid() {
		for(int i = 0; i < getDimension(); i++) {
			for(int y = 0; y < getDimension(); y++) {
				if(!(checkRowsCols(i,y,board[i][y], 1)) || !(checkQuadrant(i, y, board[i][y], 1))) {
	
					return false;
				}
			}	
		}
		return true;
	}
	
	private boolean checkQuadrant(int r, int c, int nbr, int unique) {
		if(nbr == 0) {
			return true;
		}
		
		int ncol = 3 * (c / 3);
		int nrow = 3 * (r / 3);
		int ff = 0;
		
		
		for(int row = nrow; row < nrow+3; row++) {
			for(int col = ncol; col < ncol+3; col++) {
				if(board[row][col] == nbr) {
					//return false;
					ff ++;
				}
			}
		}
		//return true;
		return (ff == unique);
	}

	@Override
	public boolean solve() {
		return solveV2(0, 0);
		//return solve(0,0);
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
		
		for(int i = 1; i <= 9; i++) {
			if(isValid(r,c,i) && isAllValid()) {
				if(getNumber(r,c) == 0) {
					setNumber(r,c,i);	
				} 
				if(solve(r, c + 1)) {
					return true;
				} 
				else {
					board[r][c] = 0;
				}
			}

		}
		
		return false;
		
	}

	

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
			for(int j = 0; j < getDimension(); j++) {
				temp[i][j] = board[i][j];	
			}
		}
		return temp;
	}

	@Override
	public void setMatrix(int[][] nbrs) {
		if(nbrs.length != getDimension() || nbrs[0].length != getDimension()) {
			throw new IllegalArgumentException();
		}
		board = nbrs;
	}
	
	
	
	//test med main
	
	public static void main(String[] args) {
		Sudoku s = new Sudoku();
		
		s.setNumber(8, 2, 2);
		s.setNumber(2, 4, 3);
		System.out.print(s.isAllValid());
		s.printSudoku();
		System.out.print(s.checkQuadrant(2, 4, 2, 1));
		
		System.out.print("row "  + s.checkRowsCols(8, 2, 2, 1));		
//		System.out.print(s.solve());
//		s.printSudoku();
	}
	
	
	
	
	
	
	
	
	
	
	
	
//	private boolean checkRow(int r, int nbr) {
//		for(int i = 0; i < getDimension(); i++) {
//			if(board[r][i] == nbr) {
//				return false;
//			}
//		}
//		return true;
//	}
//	
//	private boolean checkCol(int c, int nbr) {
//		for(int i = 0; i < getDimension(); i++) {
//			if(board[i][c] == nbr) {
//				return false;
//			}
//		}
//		return true;
//	}
//	
//	private boolean checkPanel(int row, int col, int number) {
//		int panelStartRow = (row / 3) * 3;
//		int panelStartCol = (col / 3) * 3;
//
//		for (int i = panelStartRow; i < panelStartRow + 3; i++) {
//			for (int j = panelStartCol; j < panelStartCol + 3; j++)
//				if (board[i][j] == number) {
//					return false;
//				}
//		}
//		return true;
//	}
//
//	
//	private boolean checkQuadrant(int r, int c) {
//		int nbrCheck = board[r][c];
//		board[r][c] = 0;
//		
//		if(checkRow(r, nbrCheck) && checkCol(c, nbrCheck)
//				&& checkPanel(r, c, nbrCheck)) {
//			board[r][c] = nbrCheck;
//			return true;
//		} else {
//			board[r][c] = 0;
//			return false;
//		}
//	}
//
//
//
//
//
//	private boolean solveV3(int row, int col) { // den rekursiva lösningen
//
//		if (board[row][col] == 0) {// ifall rutan är tom
//
//			if (row == 8 && col == 8) { // ifall vi är på sista rutan i sudokut
//				for (int i = 1; i <= 9; i++) {
//					board[row][col] = i; // prova att sätta in i
//					if (checkQuadrant(row, col)) { // om i är giltigt så är
//						return true; // sudokut är löst
//					}
//				}
//				return false;
//			}
//			for (int i = 1; i < 10; i++) {
//				board[row][col] = i; // testa med första bästa
//				if (checkQuadrant(row, col)) { // funkar det man testat?
//					if (col < 8) {
//						if (solve(row, col + 1)) {// går nästa ruta att lösa?
//							return true;
//						}
//					} else if (solve(row + 1, 0)) {// går nästa ruta att lösa?
//						return true;
//					}
//				}
//
//			}
//			board[row][col] = 0; // sätt tillbaka rutan till tom
//			return false; // ifall det inte funkar så får man hoppa tillbaka
//
//		} else { // annars är ju rutan inte tom
//
//			if (row == 8 && col == 8) {
//				return checkQuadrant(row, col);
//			}
//			if (checkQuadrant(row, col)) { // då kollar vi om det funkar
//				if (col < 8) {
//					return solve(row, col + 1);
//				} else {
//					return solve(row + 1, 0);
//				}
//			}
//		}
//
//		return false;
//	}
}
	
	





