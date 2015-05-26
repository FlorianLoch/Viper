package net.fdloch.viper.gamelogic;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by florian on 25.05.15.
 */
public abstract class Snake {
    protected LinkedList<BoardPosition> pathway = new LinkedList<>();
    protected Game game;

    public Snake(Game game) {
        this.game = game;

        int centerHorizontal = Consts.BOARD_COLUMN_COUNT / 2;
        int centerVertical = Consts.BOARD_ROW_COUNT / 2;

        for (int i = 0; i < Consts.SNAKE_DEFAULT_LENGTH; i++) {
            int row = i - Consts.SNAKE_DEFAULT_LENGTH / 2;
            pathway.addFirst(new BoardPosition(centerHorizontal, centerVertical + row));
        }
    }

    public List<BoardPosition> getPathway() {
        return Collections.unmodifiableList(pathway);
    }

    public boolean doesSnakeBiteItself() {
        return (isPositionTwiceInPathway(pathway.getFirst()) || isPositionTwiceInPathway(pathway.getLast()));
    }

    protected final boolean isPositionTwiceInPathway(BoardPosition position) {
        int matchCounter = 0;
        for (BoardPosition p : pathway) {
            if (p.equals(position)) {
                matchCounter++;

                if (matchCounter == 2) return true;
            }
        }

        return false;
    }

    public void move() {
        Direction dir = determineMovingDirection();

        //Move head forward
        BoardPosition newHeadPosition = dir.nextPositionOriginatingFrom(pathway.getFirst());
        pathway.addFirst(newHeadPosition);

        //Strip tail only if snake doesn't get longer due to food item
        Item item = game.getItemAt(newHeadPosition);
        if (!item.isFood()) {
            pathway.removeLast();
        }
    }

    public abstract Direction determineMovingDirection();
}
