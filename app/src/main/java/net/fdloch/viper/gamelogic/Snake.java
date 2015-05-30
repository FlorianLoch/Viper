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

    public boolean wouldSnakeBiteItself(BoardPosition newHeadPosition) {
        return isPositionInPathway(newHeadPosition);
    }

    protected final boolean isPositionInPathway(BoardPosition position) {
        return isPositionInPathway(pathway, position);
    }

    protected static boolean isPositionInPathway(LinkedList<BoardPosition> pathway, BoardPosition position) {
        for (BoardPosition p : pathway) {
            if (p.equals(position)) {
                return true;
            }
        }

        return false;
    }

    public MoveResult move() {
        Direction dir = determineMovingDirection();

        //Move head forward
        BoardPosition newHeadPosition = dir.nextPositionOriginatingFrom(pathway.getFirst());

        //Strip tail only if snake doesn't get longer due to food item
        Item item = game.getItemAt(newHeadPosition);
        if (!item.isFood()) {
            pathway.removeLast();
        }

        if (wouldSnakeBiteItself(newHeadPosition)) {
            return MoveResult.GAME_OVER;
        }

        pathway.addFirst(newHeadPosition);

        return MoveResult.ROGER_ROGER;
    }

    public abstract Direction determineMovingDirection();
}
