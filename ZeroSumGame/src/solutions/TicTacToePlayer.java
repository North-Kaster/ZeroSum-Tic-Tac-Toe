package solutions;

import core_algorithms.Minimax;
import problems.TicTacToe;
import java.util.Scanner;

public class TicTacToePlayer extends Minimax<char[][], int[]> {
    private int boardSize;

    public TicTacToePlayer(int size, boolean pruning) {
        super(new TicTacToe(size, TicTacToe.Marks.X), pruning);
        this.boardSize = size;
    }
    public int getBoardSize() {
        return this.boardSize;
    }

    public void play() {
        char[][] board = game.getBoard();
        Scanner scanner = new Scanner(System.in);
        printBoard(board);

        while (!game.isTerminal(board)) {
            System.out.println("AI's turn!");
            int[] aiMove = minimaxSearch(board);
            board = game.execute(aiMove, board); // Update board state
            // format print in 1-based index to match human move
            System.out.println("AI's move was: [Row: " + (aiMove[0] + 1) + ", Column: " + (aiMove[1] + 1) + "]"); 
            printBoard(board);

            if (game.isTerminal(board)) {
                break; // Break out of the loop if the AI's move ends the game
            }
            System.out.println("Your turn!");
            int[] humanMove = humanMove(scanner);
            board = game.execute(humanMove, board); // Update board state
            System.out.println("Your move was: [Row: " + (humanMove[0] + 1) + ", Column: " + (humanMove[1] + 1) + "]"); 
            printBoard(board);
        }
        DecideAndPrintWinner(board);
    }

    private void DecideAndPrintWinner(char[][] board) {
        System.out.println("Game over!");
        int utility = game.utility(board);
        if (utility > 0) {
            System.out.println("AI wins!");
        } else if (utility < 0) {
            System.out.println("You win!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    public int[] humanMove(Scanner scanner) {
        int row, column;
        do {
            System.out.println("Enter row #: ");
            row = scanner.nextInt() - 1;
            System.out.println("Enter column #: ");
            column = scanner.nextInt() - 1;
            if (row < 0 || row >= boardSize || column < 0 || column >= boardSize) {
                System.out.println
                ("Invalid move, please enter a row and column within the board size. The current board size is: " + boardSize + "x" + boardSize + ".");
            } else if (game.getMarked()[row][column]) {
                System.out.println("That space is already marked, please choose a different space.");
            } else {
                break;
            }
        } while (true);
        return new int[] { row, column };
    }

    public void printBoard(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                System.out.print(board[row][column]);
                if (column < board.length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row < board.length - 1) {
                for (int column = 0; column < board.length; column++) {
                    System.out.print("-");
                    if (column < board.length - 1) {
                        System.out.print("+");
                    }
                }
                System.out.println();
            } else {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("""
            Welcome to Tic Tac Toe. The AI will play as X and you will play as O. 
            On your turn, enter your desired row and column numbers to make your mark. 
            The board is 1-indexed, starting at (1,1) in the top left corner.

            For reference, here are the row#/column# combinations for a 3x3 board:

            1,1|1,2|1,3
            ---+---+---
            2,1|2,2|2,3
            ---+---+---
            3,1|3,2|3,3

            --------------------------------------------------------------------------
            
            Good luck!
            """);
        // Adjust board size and pruning here
        TicTacToePlayer player = new TicTacToePlayer(3, false);
        player.play();
    }
}