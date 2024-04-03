package solutions;

import core_algorithms.Minimax;
import problems.TicTacToe;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToePlayer extends Minimax<char[][], int[]> {

    public TicTacToePlayer(int size, boolean pruning) {
        super(new TicTacToe(size, TicTacToe.Marks.X), pruning);
    }

    public void play() {
        char[][] board = game.getBoard();
        Scanner scanner = new Scanner(System.in);
        printBoard(board);

        while (!game.isTerminal(board)) {
            int[] aiMove = minimaxSearch(board);
            board = game.execute(aiMove, board); // Update board state
            // format print in 1-based index to match human move
            System.out.println("AI's move: [Row: " + (aiMove[0] + 1) + ", Column: " + (aiMove[1] + 1) + "]"); 
            printBoard(board);

            if (game.isTerminal(board)) {
                break; // Break out of the loop if the AI's move ends the game
            }

            int[] humanMove = humanMove(scanner);
            board = game.execute(humanMove, board); // Update board state
            System.out.println("Your move: [Row: " + (humanMove[0] + 1) + ", Column: " + (humanMove[1] + 1) + "]"); 
            printBoard(board);
        }

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
        System.out.println("Enter your row: ");
        int row = scanner.nextInt() - 1;
        System.out.println("Enter your column: ");
        int column = scanner.nextInt() - 1;
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
        System.out.println("Tic Tac Toe started");
        TicTacToePlayer player = new TicTacToePlayer(3, false);
        player.play();
    }
}