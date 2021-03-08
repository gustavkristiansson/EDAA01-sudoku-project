package JuliasSudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SudokuController {

	public SudokuController(SudokuSolver s) {
		SwingUtilities.invokeLater(() -> createWindow(s, "Sudoku", 550, 600));
	}

	private void createWindow(SudokuSolver s, String title, int width, int height) {
		int size = s.getDimension();
		int rootSize = (int)Math.sqrt(size);
		Font font = new Font("Serif", Font.PLAIN, 30);

		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();

		JPanel southPanel = new JPanel();
		JButton solve = new JButton("Solve");
		JButton clear = new JButton("Clear");
		southPanel.add(solve);
		southPanel.add(clear);

		JPanel centerPanel = new JPanel();
		GridLayout grid = new GridLayout(size, size);

		JTextField [][] textGrid = new JTextField [size][size];

		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				JTextField field = new JTextField(1);
				textGrid[r][c] = field;
				centerPanel.add(field);
				field.setFont(font);
				field.setHorizontalAlignment(JTextField.HORIZONTAL);
				if (((r / rootSize) % 2 == 0 && (c / rootSize) % 2 == 0) || (r / rootSize) % 2 == 1 && ((c / rootSize) % 2) == 1) {
					field.setBackground(Color.pink);
				} 
			}
		}

		centerPanel.setLayout(grid);

		clear.addActionListener(event -> {
			s.clear();
			for (int r = 0; r < size; r++) {
				for (int c = 0; c < size; c++) {
					textGrid[r][c].setText("");
				}
			}
		});

		solve.addActionListener(event -> {
			for (int r = 0; r < size; r++) {
				for (int c = 0; c < size; c++) {
					String text = textGrid[r][c].getText();
					if (text.equals("")) {
						s.clearNumber(r, c);
					} else {
						try {
							s.setNumber(r, c, Integer.parseInt(text));
						} catch (IllegalArgumentException e) {
							JOptionPane.showMessageDialog(frame, "Illegal values!");
							return;
						}
					}
				}
			}
			if (s.solve()) {
				for (int r = 0; r < size; r++) {
					for (int c = 0; c < size; c++) {
						textGrid[r][c].setText((Integer.toString(s.getNumber(r, c))));
					}
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Unsolvable!");
			}
		});

		pane.add(southPanel, BorderLayout.SOUTH);
		pane.add(centerPanel, BorderLayout.CENTER);

		frame.setSize(width, height);
		frame.setVisible(true);
	}

}


