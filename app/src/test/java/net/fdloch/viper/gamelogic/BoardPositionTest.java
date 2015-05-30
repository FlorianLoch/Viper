package net.fdloch.viper.gamelogic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by florian on 30.05.15.
 */
@RunWith(Parameterized.class)
public class BoardPositionTest {

    private BoardPosition origin;
    private BoardPosition target;
    private Direction expected;

    public BoardPositionTest(BoardPosition origin, BoardPosition target, Direction expected) {
        this.origin = origin;
        this.target = target;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new BoardPosition(0, 0), new BoardPosition(2, 0), Direction.EAST},         //0
                {new BoardPosition(0, 0), new BoardPosition(0, 2), Direction.NORTH},
                {new BoardPosition(0, 0), new BoardPosition(2, 2), Direction.EAST},
                {new BoardPosition(2, 0), new BoardPosition(2, 2), Direction.NORTH},

                {new BoardPosition(2, 2), new BoardPosition(0, 0), Direction.WEST},
                {new BoardPosition(0, 2), new BoardPosition(0, 0), Direction.SOUTH},        //5

                {new BoardPosition(14, 14), new BoardPosition(2, 2), Direction.EAST},
                {new BoardPosition(2, 14), new BoardPosition(2, 2), Direction.NORTH},

                {new BoardPosition(2, 2), new BoardPosition(14, 14), Direction.WEST},
                {new BoardPosition(14, 2), new BoardPosition(14, 14), Direction.SOUTH},

                {new BoardPosition(5, 5), new BoardPosition(12, 5), Direction.EAST},        //10
                {new BoardPosition(5, 5), new BoardPosition(13, 5), Direction.WEST},
                {new BoardPosition(5, 5), new BoardPosition(5, 5), null}
        });
    }

    @Test
    public void testDirectionRelativeTo() throws Exception {
        assertEquals(expected, origin.directionRelativeTo(target));
    }
}