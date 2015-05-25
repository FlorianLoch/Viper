package net.fdloch.viper.gamelogic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by florian on 25.05.15.
 */
@RunWith(Parameterized.class)
public class DirectionTest {
    private BoardPosition origin;
    private BoardPosition expected;
    private Direction direction;
    private String comment;

    public DirectionTest(BoardPosition origin, Direction direction, BoardPosition expected, String comment) {
        this.origin = origin;
        this.expected = expected;
        this.direction = direction;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new BoardPosition(0, 0), Direction.WEST, new BoardPosition(Consts.BOARD_COLUMN_COUNT - 1, 0), "Overflow handling to the left"},
                {new BoardPosition(0, 0), Direction.EAST, new BoardPosition(1, 0), "Default behaviour, shift to the right"},
                {new BoardPosition(1, 1), Direction.NORTH, new BoardPosition(1, 2), "Default behaviour, shift upwards"},
                {new BoardPosition(Consts.BOARD_COLUMN_COUNT - 1, 0), Direction.EAST, new BoardPosition(0, 0), "Overflow handling to the right"},
                {new BoardPosition(Consts.BOARD_COLUMN_COUNT - 1, 0), Direction.WEST, new BoardPosition(Consts.BOARD_COLUMN_COUNT - 2, 0), "Default behaviour, shift to the left"}
        });
    }

    @Test
    public void testNextPositionOriginatingFrom() {
        BoardPosition computed = this.direction.nextPositionOriginatingFrom(origin);
        assertEquals(this.comment, this.expected, computed);
    }
}