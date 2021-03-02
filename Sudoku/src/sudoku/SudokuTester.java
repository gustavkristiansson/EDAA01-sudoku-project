package sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuTester {
	private Sudoku mySudoku;
	private int[][] board;

	@BeforeEach
	void setUp() throws Exception {
		board = new int[9][9];
		mySudoku = new Sudoku();	
	}

	@AfterEach
	void tearDown() throws Exception {
		mySudoku = null;
		board = null;
	}

	@Test
	void testUnsolvable() {
		board = new int[][] { 	{ 2, 0, 0, 3, 4, 5, 6, 7, 8 }, 
								{ 3, 4, 5, 0, 8, 0, 1, 2, 6 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
		mySudoku = new Sudoku(board);
		assertFalse(mySudoku.solve(), "Sudoku should not be solvable");
	}
	
	@Test
	void testSolvableFromCourse() {
		board = new int[][] { 	{ 0, 0, 8, 0, 0, 9, 0, 6, 2 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
								{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
								{ 0, 5, 0, 0, 0, 0, 6, 0, 0 },
								{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
								{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
								{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 4, 0, 0 } };
		mySudoku = new Sudoku(board);
		assertTrue(mySudoku.solve(), "Sudoku shuold be solvable");				
	}

	@Test
	void testEmpty() {
		board = new int[][] { 	{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
		mySudoku = new Sudoku(board);
		assertTrue(mySudoku.solve(), "Sudoku should be solvable");
	}
}


