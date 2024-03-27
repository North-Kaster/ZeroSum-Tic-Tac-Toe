package solutions;

import core_algorithms.Minimax;
import problems.TicTacToe;

public class TicTacToePlayer extends Minimax<char[][], int[]> {

    public TicTacToePlayer(int size) {
        super(new TicTacToe(size, TicTacToe.Marks.O), false);
    }

    public void play() {
        TicTacToe game = new TicTacToe(3, TicTacToe.Marks.X);
        char[][] board = new char[3][3];
        int x = 0;

        while (x == 0) {
            printBoard(board);
            int[] aiMove = minimaxSearch(board);// Use minimaxSearch for AI move
            board = game.execute(aiMove, board);
            printBoard(board);
            x++;
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
        TicTacToePlayer player = new TicTacToePlayer(3);
        player.play();
    }
}
