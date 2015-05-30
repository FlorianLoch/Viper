package net.fdloch.viper.gamelogic;

import java.util.IdentityHashMap;

/**
 * Created by florian on 25.05.15.
 */
public enum Direction {
    NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);

    int shiftHorizontal;
    int shiftVertical;
//    private IdentityHashMap<Direction, Direction> clockwiseFollowingDirection = new IdentityHashMap() {{
//        put(NORTH, EAST);
//        put(EAST, SOUTH);
//        put(SOUTH, WEST);
//        put(WEST, NORTH);
//    }};

    Direction(int shiftHorizontal, int shiftVertical) {
        this.shiftHorizontal = shiftHorizontal;
        this.shiftVertical = shiftVertical;
    }

    public BoardPosition nextPositionOriginatingFrom(BoardPosition boardPosition) {
        return new BoardPosition(boardPosition, this);
    }

    public Direction nextDirectionClockwise() {
        switch (this) {
            case NORTH: return EAST;
            case EAST: return SOUTH;
            case SOUTH: return WEST;
            case WEST: return NORTH;
            default: return null;
        }
    }
}
