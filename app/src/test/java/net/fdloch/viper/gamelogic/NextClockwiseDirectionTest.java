package net.fdloch.viper.gamelogic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by florian on 30.05.15.
 */
public class NextClockwiseDirectionTest {

    @Test
    public void testNextDirectionClockwise() throws Exception {
        Direction next = Direction.EAST.nextDirectionClockwise();

        assertEquals("Expect SOUTH to follow on EAST", Direction.SOUTH, next);
    }
}