package net.fdloch.viper.gamelogic;

import java.util.Timer;

/**
 * Created by florian on 25.05.15.
 */
public class CLI {

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        Snake snake = new Snake(game) {
            @Override
            public Direction determineMovingDirection() {
                return Direction.NORTH;
            }
        };

        game.addSnake(new AISnake(game));

        game.addFoodItem(new BoardPosition(7, 2));

        while (1 != 2) {
            game.nextRound();
            System.out.println(game.toString());

            Thread.sleep(1000);
        }
    }

}
