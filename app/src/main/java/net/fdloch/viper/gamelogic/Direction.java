package net.fdloch.viper.gamelogic;

/**
 * Created by florian on 25.05.15.
 */
public enum Direction {
    NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);

    int shiftHorizontal;
    int shiftVertical;

    Direction(int shiftHorizontal, int shiftVertical) {
        this.shiftHorizontal = shiftHorizontal;
        this.shiftVertical = shiftVertical;
    }

    public BoardPosition nextPositionOriginatingFrom(BoardPosition boardPosition) {
        return new BoardPosition(boardPosition, this);
    }
}
