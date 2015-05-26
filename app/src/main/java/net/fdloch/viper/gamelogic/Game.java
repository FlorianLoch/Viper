package net.fdloch.viper.gamelogic;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by florian on 25.05.15.
 */
public class Game {
    private ArrayList<Snake> snakes = new ArrayList<>();
    private HashMap<BoardPosition, Item> items = new HashMap<>();

    public void addSnake(Snake snake) {
        snakes.add(snake);
    }

    public void nextRound() {
        for (Snake s : snakes) {
            s.move();
            if (s.doesSnakeBiteItself()) {
                gameOver();
            }
        }
    }

    void addFoodItem(final BoardPosition position) {
        items.put(position, new Item() {
            @Override
            public boolean isFood() {
                return true;
            }

            @Override
            public void getsConsumed() {
                items.remove(position);
            }

            @Override
            public char getSign() {
                return 'f';
            }
        });
    }

    void gameOver() {

    }

    @NonNull
    public Item getItemAt(BoardPosition headPosition) {
        return getItemAt(headPosition, false);
    }

    @NonNull
    public Item getItemAt(BoardPosition headPosition, boolean nonConsuming) {
        Item item = items.get(headPosition);

        if (item == null) {
            return Item.NO_ITEM;
        }

        if (!nonConsuming) item.getsConsumed();

        return item;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        for (Snake s : snakes) {
            HashSet<BoardPosition> pathway = new HashSet<>(s.getPathway());

            for (int i = Consts.BOARD_ROW_COUNT - 1; i >= 0; i--) {
                for (int j = 0; j < Consts.BOARD_COLUMN_COUNT; j++) {
                    BoardPosition p = new BoardPosition(j, i);

                    if (pathway.contains(p)) {
                        out = out.append(" s ");
                        continue;
                    }

                    out = out.append(" ").append(getItemAt(p, true).getSign()).append(" ");
                }

                out = out.append("\n");
            }
        }

        return out.toString();
    }
}
