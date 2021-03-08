package JuliasSudoku;

public class SudokuSolve implements SudokuSolver{
	
	private int [][] sudoku;
	private int size;
	
	public SudokuSolve() {
		size = getDimension();
		sudoku = new int [size][size];
	}

	@Override
	public void setNumber(int r, int c, int nbr) {
		if (r < 0 || r > size-1 || c < 0 || c > size-1) {
			throw new IllegalArgumentException("Illegal type, r and c must be numbers between 0 and " + (getDimension()-1));
		} else if (nbr < 1 || nbr > size) {
			throw new IllegalArgumentException("Illegal type, nbr must be between 1 and 9");
		}
		sudoku[r][c] = nbr;
	}

	@Override
	public int getNumber(int r, int c) {
		if (r < 0 || r > size-1 || c < 0 || c > size-1) {
			throw new IllegalArgumentException("Illegal type, r and c must be numbers between 0 and " + (getDimension()-1));
		}
		return sudoku[r][c];
	}

	@Override
	public void clearNumber(int r, int c) {
		if (r < 0 || r > size-1 || c < 0 || c > size-1) {
			throw new IllegalArgumentException("Illegal type, r and c must be numbers between 0 and " + (getDimension()-1));
		} 
		sudoku[r][c] = 0;
	}

	@Override
	public boolean isValid(int r, int c, int nbr) {
		int rootSize = (int)Math.sqrt(size);
		if (r < 0 || r > size-1 || c < 0 || c > size-1) {
			throw new IllegalArgumentException("Illegal type, r and c must be numbers between 0 and " + (getDimension()-1));
		} else if (nbr < 1 || nbr > size) {
			throw new IllegalArgumentException("Illegal type, nbr must be between 1 and 9");
		}
		for (int row = 0; row < size; row++) {
			if (sudoku[row][c] == nbr && row != r) {
				return false;
			}
		}
		for (int col = 0; col < size; col++) {
			if (sudoku[r][col] == nbr && col != c) {
				return false;
			}
		}
		for (int row = -(r % rootSize); row < (-(r % rootSize))+rootSize; row++) {
			for (int col = -(c % rootSize); col < (-(c % rootSize))+rootSize; col++) {
				if (sudoku[r+row][c+col] == nbr && !(c == c+col && r == r+row)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean isAllValid() {
		for (int r = 0; r < size-1; r++) {
			for (int c = 0; c < size-1; c++) {
				if (sudoku[r][c] != 0 && !isValid(r,c,sudoku[r][c])) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean solve() {
		return solve(0,0);
	}
	
	private boolean solve(int r, int c) {
		if (sudoku[r][c] != 0) {
			if (!isValid(r,c, sudoku[r][c])) {
				return false;
			}
			if (c == size-1) {
				if (r == size-1) {
					return true;
				}
				return solve(r+1,0);
			}
			else {
				return solve(r, c+1);
			}
		}
		for (int nbr = 1; nbr <= size; nbr++) {
			if (isValid(r,c,nbr)) {
				setNumber(r, c, nbr);
				if (c == size-1) {
					if (r == size-1) {
						return true;
					}
					if (solve(r+1,0)) {
						return true;
					}
				} else {
					if (solve(r, c+1)) {
						return true;
					}
				}
			} 
		}
		clearNumber(r,c);
		return false;
	}

	@Override
	public void clear() {
		sudoku = new int [size][size];
	}

	@Override
	public int[][] getMatrix() {
		int [][] matrix = new int [size][size];
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				matrix[r][c] = sudoku[r][c];
			}
		}
		return matrix;
	}

	@Override
	public void setMatrix(int[][] nbrs) {
		if (nbrs.length != size || nbrs[0].length != size) {
			throw new IllegalArgumentException("Wrong dimension of matrix");
		}
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (nbrs[r][c] < 0 || nbrs[r][c] > size) {
					throw new IllegalArgumentException("Illegal type, nbr must be between 0 and " + size);
				}
				sudoku[r][c] = nbrs[r][c];
			}
		}	
	}

}
