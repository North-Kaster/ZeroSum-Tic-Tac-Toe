package core_algorithms;

import problems.Game;

public class Minimax<S, A> {
    private final Game<S, A> game;
    private final boolean pruning;

    public record Best<A>(int value, A action) {};

    public Minimax(Game<S, A> game, boolean pruning) {
        this.game = game;
        this.pruning = pruning;
    }

    public A minimaxSearch(S state) {
        int alpha = Integer.MIN_VALUE; // Set alpha to negative infinity equivalent for ints
        int beta = Integer.MAX_VALUE; // Set beta to positive infinity equivalent for ints
        Best<A> max = maxValue(state, alpha, beta);
        return max.action();
    }

    public Best<A> maxValue(S state, int alpha, int beta) {
        int maxValue = Integer.MIN_VALUE;
        A maxAction = null;
        if (game.isTerminal(state)) {
            maxValue = game.utility(state);
        } else {
            for (A action : game.actions(state)) {
                S newState = game.execute(action, state);
                Best<A> min = minValue(newState, alpha, beta);
                if (min.value() > maxValue) {
                    maxValue = min.value();
                    maxAction = action;
                }
                // If pruning enabled and maxValue is greater than or equal to beta
                if (pruning && maxValue >= beta) {
                    return new Best<>(maxValue, maxAction);
                }
                alpha = Math.max(alpha, maxValue);
                game.undo(action, newState);
            }
        }
        return new Best<>(maxValue, maxAction);
    }

    public Best<A> minValue(S state, int alpha, int beta) {
        int minValue = Integer.MAX_VALUE;
        A minAction = null;
        if (game.isTerminal(state)) {
            minValue = game.utility(state);
        } else {
            for (A action : game.actions(state)) {
                S newState = game.execute(action, state);
                Best<A> max = maxValue(newState, alpha, beta);
                if (max.value() < minValue) {
                    minValue = max.value();
                    minAction = action;
                }
                // If pruning enabled and minValue is less than or equal to alpha
                if (pruning && minValue <= alpha) { 
                    return new Best<>(minValue, minAction);
                }
                beta = Math.min(beta, minValue);
                game.undo(action, newState);
            }
        }
        return new Best<>(minValue, minAction);
    }
}
