package net.fdloch.viper.gamelogic;

import java.io.IOException;
import java.util.Timer;

/**
 * Created by florian on 25.05.15.
 */
public class CLI {

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game() {
            @Override
            public void gameOver() {
                System.out.println("GAME OVER!");
                System.out.println(this.toString());
                System.exit(0);
            }
        };
        Snake snake = new Snake(game) {
            @Override
            public Direction determineMovingDirection() {
                return Direction.NORTH;
            }
        };

        game.addSnake(new SmartSnake(game));

        game.addFoodItem(new BoardPosition(7, 2));

        int i = 0;
        while (1 != 2) {
            game.nextRound();
            if (i % 50 == 0) {
                System.out.println(game.toString());
                //Thread.sleep(300);
            }

            //clearCLI();

            i++;
        }
    }

    private static void clearCLI() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
