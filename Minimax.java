
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Minimax<Action> {

    public Coordinate bestMove(Board state, Player player) {
        Coordinate bestAction = null;
        int maxValue = Integer.MIN_VALUE;
        
        for (Coordinate action : state) {
            Board newState = state.next(action, player);
            if (newState.wins(Player.X)) {
                return action;
            }
            int value = minValue(newState, player.other(), 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
            
            if (value > maxValue) {
                maxValue = value;
                bestAction = action;
            }
        }

        return bestAction;
    }

    public int maxValue(Board state, Player player, int plies, int alpha, int beta) {
        if (state.isTerminal() || plies >= TicTacToe.PLIES){
            return state.evaluate();
        }
        
        int maximum = Integer.MIN_VALUE;
        
        
        for (Coordinate action : state) {
            Board newState = state.next(action, player);
            if (newState.wins(Player.X)) {
                return Integer.MAX_VALUE;
            }
            int value = minValue(newState, player.other(), plies + 1, alpha, beta);
            if (value > alpha) {
                alpha = value;
            }
            
            if (value > beta) {
                return value;
            }
            
            if (value > maximum) {
                maximum = value;
            }
        }
        
        return maximum;
    }

    public int minValue(Board state, Player player, int plies, int alpha, int beta) {
        if (state.isTerminal() || plies >= TicTacToe.PLIES){
            return state.evaluate();
        }
        
        int minimum = Integer.MAX_VALUE;
        
        for (Coordinate action : state) {
            Board newState = state.next(action, player);
            if (newState.wins(Player.O)) {
                return Integer.MIN_VALUE;
            }
            int value = maxValue(newState, player.other(), plies + 1, alpha, beta);
            
            if (value < beta) {
                beta = value;
            }
            
            if (value < alpha) {
                return value;
            }
            
            if (value < minimum) {
                minimum = value;
            }
        }
        
        return minimum;
    }
}
