package solutions;

import core_algorithms.Minimax;
import problems.TicTacToe;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToePlayer extends Minimax<char[][], int[]> {

    public TicTacToePlayer(int size) {
        super(new TicTacToe(size, TicTacToe.Marks.O), false);
    }

    public void play() {
        TicTacToe game = new TicTacToe(3, TicTacToe.Marks.X);
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(board[i], ' ');
        }
        Scanner scanner = new Scanner(System.in);
        

        while (!game.isTerminal(board)) {
            System.out.println("While loop start: ");
            printBoard(board);
            int[] aiMove = minimaxSearch(board);
            board = game.execute(aiMove, board);
            System.out.println("AI's move: " + Arrays.toString(aiMove));
            printBoard(board);
            int [] humanMove = humanMove(scanner);
            board = game.execute(humanMove, board);
            System.out.println("Your move:");
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
    public int [] humanMove(Scanner scanner){
        System.out.println("Enter your row: ");
        int row =  scanner.nextInt() - 1;
        System.out.println("Enter your column: ");
        int column = scanner.nextInt() - 1;
        return new int[]{row, column};
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
        TicTacToePlayer player = new TicTacToePlayer(3);
        player.play();
    }
}
