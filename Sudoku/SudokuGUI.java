package Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuGUI {
    private JFrame frame;
    private JTextField[][] cells = new JTextField[9][9];

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new SudokuGUI().createAndShowGUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void createAndShowGUI() {
        frame = new JFrame("Sudoku Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(600, 600); // Increased size for better visibility

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new JTextField(3); // Increased width
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                panel.add(cells[i][j]);
            }
        }

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new SolveButtonListener());

        frame.add(panel, BorderLayout.CENTER);
        frame.add(solveButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private class SolveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[][] board = new int[9][9];
            try {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        String text = cells[i][j].getText();
                        if (!text.isEmpty()) {
                            board[i][j] = Integer.parseInt(text);
                        }
                    }
                }

                if (SudokuSolver.solveSudoku(board)) {
                    updateGrid(board);
                } else {
                    JOptionPane.showMessageDialog(frame, "No solution exists");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter numbers between 1 and 9.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "An unexpected error occurred.");
            }
        }
    }

    private void updateGrid(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setText(board[i][j] == 0 ? "" : String.valueOf(board[i][j]));
            }
        }
    }
}
