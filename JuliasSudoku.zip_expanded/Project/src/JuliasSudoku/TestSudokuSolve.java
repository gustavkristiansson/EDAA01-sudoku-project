package JuliasSudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestSudokuSolve {
	
	private SudokuSolve s;

	@BeforeEach
	void setUp() throws Exception {
		s = new SudokuSolve();
	}

	@AfterEach
	void tearDown() throws Exception {
		s = null;
	}

	@Test
	void testSolveEmptySudoku() {
		assertTrue(s.solve(), "Wrong boolean");
	}
	
	@Test
	void testSolveExampleSudoku() {
		int [][] example = {
				{0,0,8,0,0,9,0,6,2},
				{0,0,0,0,0,0,0,0,5},
				{1,0,2,5,0,0,0,0,0},
				{0,0,0,2,1,0,0,9,0},
				{0,5,0,0,0,0,6,0,0},
				{6,0,0,0,0,0,0,2,8},
				{4,1,0,6,0,8,0,0,0},
				{8,6,0,0,3,0,1,0,0},
				{0,0,0,0,0,0,4,0,0}
		};
		s.setMatrix(example);
		assertTrue(s.solve(), "Wrong boolean");
	}
	
	@Test
	void testSolveUnsolvableSudoku() {
		int [][] example = {
				{0,0,8,0,0,9,0,6,2},
				{0,0,0,0,0,0,0,0,5},
				{1,0,2,5,0,0,0,0,0},
				{0,0,0,2,1,0,0,9,0},
				{0,5,0,0,0,0,6,0,0},
				{6,0,0,2,0,0,0,2,8},
				{4,1,0,6,0,8,0,0,0},
				{8,6,0,0,3,0,1,0,0},
				{0,0,0,0,0,0,4,0,0}
		};
		s.setMatrix(example);
		assertFalse(s.solve(), "Wrong boolean");
	}

}
