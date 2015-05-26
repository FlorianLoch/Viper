package net.fdloch.viper.gamelogic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by florian on 25.05.15.
 */
public class SnakeTest {

    @Test
    public void testDoesSnakeBiteItself() throws Exception {
        Snake instance = new Snake(null) {
            {
                pathway.add(new BoardPosition(0, 0));
                pathway.add(new BoardPosition(1, 0));
                pathway.add(new BoardPosition(2, 0));
                pathway.add(new BoardPosition(2, 1));
                pathway.add(new BoardPosition(1, 1));
            }

            @Override
            public Direction determineMovingDirection() {
                //Let the snake bite itself (this isn't that nice from a semantically point of view)
                pathway.add(new BoardPosition(0, 0));

                return null;
            }
        };

        assertFalse(instance.wouldSnakeBiteItself(new BoardPosition(0, 0)));

        //Make snake bite itself
        instance.determineMovingDirection();

        assertTrue(instance.wouldSnakeBiteItself(new BoardPosition(0, 0)));
    }
}