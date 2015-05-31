package net.fdloch.viper.gamelogic;

/**
 * Created by florian on 31.05.15.
 */
public class SmartSnake extends Snake {
    Direction lastMove = Direction.NORTH;

    public SmartSnake(Game game) {
        super(game);
    }

    @Override
    public Direction determineMovingDirection() {
        BoardPosition headPosition = pathway.getFirst();

        if (headPosition.getColumn() != 8 && headPosition.getColumn() != 9) {
            if (headPosition.getRow() != 0 && headPosition.getRow() != Consts.BOARD_ROW_COUNT - 1) {
                return lastMove;
            }
            if (lastMove == Direction.SOUTH || lastMove == Direction.NORTH) {
                lastMove = Direction.EAST;
                return Direction.EAST;
            }
            if (headPosition.getRow() == 0) {
                lastMove = Direction.NORTH;
                return Direction.NORTH;
            }
            lastMove = Direction.SOUTH;
            return Direction.SOUTH;
        }

        if (headPosition.getColumn() == 8) {
            if (headPosition.getRow() % 2 == 0) {
                return Direction.EAST;
            }
            return Direction.SOUTH;
        }
        if (headPosition.getRow() % 2 == 0) {
            if (headPosition.getRow() == 0) {
                return Direction.EAST;
            }
            return Direction.SOUTH;
        }
        return Direction.WEST;
    }
}
