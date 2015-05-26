package net.fdloch.viper.gamelogic;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by florian on 26.05.15.
 */
public class AISnake extends Snake {
    public AISnake(Game game) {
        super(game);
    }

    @Override
    public Direction determineMovingDirection() {
        BoardPosition headPosition = pathway.getFirst();

        Direction[] shuffledDirection = shuffleArray(Direction.values());
        for (Direction direction : shuffledDirection) {
            BoardPosition newHeadPosition = direction.nextPositionOriginatingFrom(headPosition);
            if (!isPositionTwiceInPathway(newHeadPosition)) {
                return direction;
            }
        }

        System.out.println("LOST");
        return Direction.EAST;
    }

    private <T> T[] shuffleArray(T[] array) {
        int index;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            if (index != i) {
                T tmp = array[index];
                array[index] = array[i];
                array[i] = tmp;
            }
        }

        return array;
    }
}
