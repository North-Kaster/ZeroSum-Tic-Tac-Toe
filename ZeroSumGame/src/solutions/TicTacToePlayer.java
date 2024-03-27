import core_algorithms.Minimax;
import problems.TicTacToe;

public class TicTacToePlayer extends Minimax<char[][], int[]> {

    public TicTacToePlayer(int size) {
        super(new TicTacToe(size, TicTacToe.Marks.O), false);
    }
    public void play(){
        // LOOP: while we have not reached a terminal state:
            // Print current gameboard
            // Have AI choose starting spot
            // Print current gameboard
            // Ask for user input row# and column#. Format as (row, column)?
            // run the minimax search to find the best move
        //announce the winner or draw
    }

    public static void main(String[] args) {
        TicTacToePlayer player = new TicTacToePlayer(3); // Choose size of board
        player.play();
    }
}