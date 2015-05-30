package net.fdloch.viper.gamelogic;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * Created by florian on 26.05.15.
 */
public class AISnake extends Snake {
    protected Stack<Direction> pathToFood = new Stack<>();

    public AISnake(Game game) {
        super(game);
    }

    @Override
    public Direction determineMovingDirection() {
        if (pathToFood.isEmpty()) {
            if (!determinePathToFood(game.getFoodItemPosition())) {
                return Direction.EAST; //Die by going eastwards
            }
        }

        return pathToFood.pop();
    }

    protected boolean determinePathToFood(BoardPosition foodPosition) {
        LinkedList<BoardPosition> virtPathway = new LinkedList<>(pathway);
        pathToFood = new Stack<>();

        return determinePathToFoodRecursion(virtPathway, foodPosition);
    }

    protected boolean determinePathToFoodRecursion(LinkedList<BoardPosition> virtPathway, BoardPosition foodPosition) {
        BoardPosition head = virtPathway.getFirst();

        Direction direction = head.directionRelativeTo(foodPosition);
        BoardPosition newHead = null;
        for (int i = 0; i < 4; i++) {
            LinkedList<BoardPosition> newVirtPathway = new LinkedList<>(virtPathway);
            newVirtPathway.removeLast();

            newHead = direction.nextPositionOriginatingFrom(head);

            if (newHead.equals(foodPosition)) {
                pathToFood.push(direction);
                return true;
            }

            if (!isPositionInPathway(newVirtPathway, newHead)) {
                newVirtPathway.addFirst(newHead);
                if (determinePathToFoodRecursion(newVirtPathway, foodPosition)) {
                    pathToFood.push(direction);
                    return true;
                }
            }

            direction = direction.nextDirectionClockwise();
        }

        return false;
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
