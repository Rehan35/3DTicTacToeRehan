
import java.io.Console;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rehanparwani
 */
public class TicTacToe {
    
    public static int PLIES = 3;
    
    public static void main(String[] args) {
        Player player = Player.X;
        Board board = new Board();
        Player evaluatingPlayer = Player.X;
        
        int i = 1;
        int total = 5;
        int movesMade = 0;
        
        Minimax<Coordinate> minimax = new Minimax<Coordinate>();
        
        boolean isSecond = false;
        
        for (String arg : args) {
            try {
                TicTacToe.PLIES = Integer.valueOf(arg);
                continue;
            } catch (NumberFormatException e) {
            }
            switch(arg.toLowerCase()) {
                case "-first":
                    player = Player.X;
                    break;
                case "-second":
                    player = Player.O;
                    isSecond = true;
                    break;
                default:
                    board = new Board(arg);
                    break;
            }
        }
        
        //put that evaluation in evaluation when fixing two player game
        
//        int evaluation = evaluatingPlayer == Player.X ? board.evaluate() : board.evaluate() * -1;
//        
//        board.print();
//        
//        if (board.wins(Player.O)) {
//            System.out.println("LOSS"); 
//        } else if (board.wins(Player.X)){
//            System.out.println("WIN");
//        }
        
//        String output = evaluation == Integer.MAX_VALUE ? "WIN" : evaluation == Integer.MIN_VALUE ? "LOSS" : String.valueOf(evaluation);
//        System.out.println(output);

        while(!board.isTerminal()) {
            
            board.print();
            
            Console console = System.console();
            
            if (!isSecond) {
                Coordinate bestMove = minimax.bestMove(board, player);
                int bestMoveValue = Integer.MIN_VALUE;
                if (bestMove == null) {
                    for (Coordinate action : board) {
                        int valuation = board.next(action, player).evaluate();
                        if (valuation > bestMoveValue) {
                            bestMove = action;
                            bestMoveValue = valuation;
                        }
                    }
                }
                board = board.next(bestMove, player);
                
                System.out.println(bestMove);

                System.out.println();

                board.print();

                player = player.other();

                if (board.wins(Player.X)) {
                    System.out.println("I win!!!");
                    break;
                }
            }
            
            isSecond = false;
            
            int x = 0;
            int y = 0;
            int z = 0;
            String line = "";
            do {
                do {
                    line = console.readLine("Row: ");
                    try {
                        x = Integer.parseInt(line) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid row: " + line);
                    }
                    if (x >= 0 && x < 4) break;
                    System.out.println("Invalid row: " + line);
                } while (true);
                
                do {
                    line = console.readLine("Col: ");
                    try {
                        y = Integer.parseInt(line) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid column: " + line);
                    }
                    if (y >= 0 && y < 4) break;
                    System.out.println("Invalid col: " + line);
                } while (true);
                
                do {
                    line = console.readLine("Level: ");
                    try {
                        z = Integer.parseInt(line) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid level: " + line);
                    }
                    if (z >= 0 && z < 4) break;
                    System.out.println("Invalid level: " + line);
                } while (true);
        
                if (board.isEmpty(x, y, z)) break;
                System.out.println("Square is not empty");
            } while (true);

            board = board.next(Coordinate.valueOf(x, y, z), player);
            
            player = player.other();
            
//            movesMade++;
//            
//            if (movesMade == total) {
//                TicTacToe.PLIES++;
//                total += 3/i++ * movesMade;
//            }
        }
        
        if (board.wins(Player.O)) {
            System.out.println("You win :(");
        }
        
    }
    
}
