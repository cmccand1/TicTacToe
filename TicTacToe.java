package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TicTacToe {

    private static String player1, player2;
    private static String turn;
    private static int turnCount;
    private static JButton[][] gameBoard;

    /**
     * Checks the game board for a winning combination in all possible directions
     * @param a    the JButton[][] array to be checked
     * @param symbol    either "X" or "O"
     * @return    true if a winning combination exists, false otherwise
     */
    private static Boolean winnerExists(JButton[][] a, String symbol) {
        // This is the middle of the 3x3 matrix
        int i = 1;
        int j = 1;

        // Checks the rows for 3 in a row
        if        (a[i - 1][j - 1].getText().equals(symbol)
                && a[i - 1][j].getText().equals(symbol)
                && a[i - 1][j + 1].getText().equals(symbol))
            return true;
        else if   (a[i][j - 1].getText().equals(symbol)
                && a[i][j].getText().equals(symbol)
                && a[i][j + 1].getText().equals(symbol))
            return true;
        else if   (a[i + 1][j - 1].getText().equals(symbol)
                && a[i + 1][j].getText().equals(symbol)
                && a[i + 1][j + 1].getText().equals(symbol))
            return true;

        // Checks the columns for 3 in a row
        else if   (a[i - 1][j - 1].getText().equals(symbol)
                && a[i][j - 1].getText().equals(symbol)
                && a[i + 1][j - 1].getText().equals(symbol))
            return true;
        else if   (a[i - 1][j].getText().equals(symbol)
                && a[i][j].getText().equals(symbol)
                && a[i + 1][j].getText().equals(symbol))
            return true;
        else if   (a[i - 1][j + 1].getText().equals(symbol)
                && a[i][j + 1].getText().equals(symbol)
                && a[i + 1][j + 1].getText().equals(symbol))
            return true;

            // Checks the diagonals for 3 in a row
        else if   (a[i - 1][j - 1].getText().equals(symbol)
                && a[i][j].getText().equals(symbol)
                && a[i + 1][j + 1].getText().equals(symbol))
            return true;
        else if   (a[i + 1][j - 1].getText().equals(symbol)
                && a[i][j].getText().equals(symbol)
                && a[i - 1][j + 1].getText().equals(symbol))
            return true;
        else
            return false;
    }

    /**
     * Checks if a box has already been played
     * @param rowIdx    the row index
     * @param colIdx    the column index
     * @return    true if the box hasn't been played, false otherwise
     */
    private static Boolean isValidMove(int rowIdx, int colIdx) {
        return gameBoard[rowIdx][colIdx].getText().equals("");
    }

    /**
     * Populates the selected box with the current players piece
     * @param rowIdx    the row index
     * @param colIdx    the column index
     */
    private static void makeMove(int rowIdx, int colIdx) {
        if (turn.equals(player1)) {
            if (isValidMove(rowIdx, colIdx)) {
                gameBoard[rowIdx][colIdx].setText("X");
                turn = player2;
                turnCount++;
            }
        } else if (turn.equals(player2)) {
            if (isValidMove(rowIdx, colIdx)) {
                gameBoard[rowIdx][colIdx].setText("O");
                turn = player1;
                turnCount++;
            }
        }
    }


    public static void main(String[] args) {

        // Create the board
        gameBoard = new JButton[3][3];
        JPanel gamePanel = new JPanel(new GridLayout(3,3));
        JFrame gameFrame = new JFrame("Tic Tac Toe");
        gameFrame.setSize(800,800);
        Font font = new Font("Calibri", Font.PLAIN, 200);

        // Add the buttons to the board
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                gameBoard[i][j] = new JButton();
                gameBoard[i][j].setFont(font);
                gamePanel.add(gameBoard[i][j]);
            }
        }

        gamePanel.setBackground(Color.BLACK);
        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        player1 = JOptionPane.showInputDialog("Player 1: ");
        player2 = JOptionPane.showInputDialog("Player 2: ");
        turn = player1;
        turnCount = 0;

        // Specify cell functionality
        class ClickListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == gameBoard[0][0]) {
                    makeMove(0, 0);
                } else if (e.getSource() == gameBoard[0][1]) {
                    makeMove(0, 1);
                } else if (e.getSource() == gameBoard[0][2]) {
                    makeMove(0, 2);
                } else if (e.getSource() == gameBoard[1][0]) {
                    makeMove(1, 0);
                } else if (e.getSource() == gameBoard[1][1]) {
                    makeMove(1, 1);
                } else if (e.getSource() == gameBoard[1][2]) {
                    makeMove(1, 2);
                } else if (e.getSource() == gameBoard[2][0]) {
                    makeMove(2, 0);
                } else if (e.getSource() == gameBoard[2][1]) {
                    makeMove(2, 1);
                } else if (e.getSource() == gameBoard[2][2]) {
                    makeMove(2, 2);
                }
                if (winnerExists(gameBoard, "X")) {
                    JOptionPane.showMessageDialog(gameFrame, player1 + " is the winner!");
                    System.exit(0);
                } else if (winnerExists(gameBoard, "O")) {
                    JOptionPane.showMessageDialog(gameFrame, player2 + " is the winner!");
                    System.exit(0);
                }
                if (turnCount == 9 && !(winnerExists(gameBoard, "X") && winnerExists(gameBoard, "O"))) {
                    JOptionPane.showMessageDialog(gameFrame, "Draw!");
                    System.exit(0);
                }
            }
        }
        ActionListener listener = new ClickListener();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j].addActionListener(listener);
            }
        }

    } // end of main

} // end of class
