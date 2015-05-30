package net.fdloch.viper.gamelogic;

import android.support.annotation.NonNull;

import java.util.*;

/**
 * Created by florian on 25.05.15.
 */
public class Game {
    private ArrayList<Snake> snakes = new ArrayList<>();
    private HashMap<BoardPosition, Item> items = new HashMap<>();
    private BoardPosition foodItemPosition = null;
    private int moves = 0;
    private int foodConsumed = 0;

    public void addSnake(Snake snake) {
        snakes.add(snake);
    }

    public BoardPosition getFoodItemPosition() {
        return foodItemPosition;
    }

    public void nextRound() {
        moves++;
        for (Snake s : snakes) {
            MoveResult res = s.move();
            if (res == MoveResult.GAME_OVER) {
                gameOver();
            }
        }
    }

    void addFoodItem(final BoardPosition position) {
        if (foodItemPosition != null) {
            return;
        }

        foodItemPosition = position;
        items.put(position, new Item() {
            @Override
            public boolean isFood() {
                return true;
            }

            @Override
            public void getsConsumed() {
                foodConsumed++;

                items.remove(position);
                foodItemPosition = null;

                //Place new food item (but not inside snake)
                if (Consts.SNAKE_DEFAULT_LENGTH + foodConsumed == Consts.BOARD_COLUMN_COUNT * Consts.BOARD_ROW_COUNT) {
                    gameOver();
                    return;
                }

                Random rand = new Random();
                BoardPosition newFoodPosition;
                do {
                    int col = rand.nextInt(Consts.BOARD_COLUMN_COUNT);
                    int row = rand.nextInt(Consts.BOARD_ROW_COUNT);

                    newFoodPosition = new BoardPosition(col, row);
                } while(isPositionInsideAnySnake(newFoodPosition));

                addFoodItem(newFoodPosition);
            }

            @Override
            public String getSign() {
                return "\u001B[32m\u25cf\u001B[0m";
            }
        });
    }

    boolean isPositionInsideAnySnake(BoardPosition position) {
        for (Snake s : snakes) {
            if (s.isPositionInPathway(position)) {
                return true;
            }
        }

        return false;
    }

    void gameOver() {
        System.out.println("GAME OVER!");
        System.exit(0);
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
        StringBuilder out = new StringBuilder(String.format("After %d moves:\n", moves));

        for (Snake s : snakes) {
            List<BoardPosition> pathway = s.getPathway();

            out = out.append("\u250C");
            for (int j = 0; j < Consts.BOARD_COLUMN_COUNT * 3; j++) {
                out = out.append("\u2500");
            }
            out = out.append("\u2510\n");

            for (int i = Consts.BOARD_ROW_COUNT - 1; i >= 0; i--) {
                out = out.append("\u2502");

                for (int j = 0; j < Consts.BOARD_COLUMN_COUNT; j++) {
                    BoardPosition p = new BoardPosition(j, i);

                    if (pathway.contains(p)) {
                        if (pathway.get(0).equals(p)) {
                            out = out.append(" \u001B[31m\u25cf\u001B[0m ");
                        }
                        else {
                            out = out.append(" \u001B[33m\u25cf\u001B[0m ");
                        }
                        continue;
                    }

                    out = out.append(" ").append(getItemAt(p, true).getSign()).append(" ");
                }

                out = out.append("\u2502\n");
            }

            out = out.append("\u2514");
            for (int j = 0; j < Consts.BOARD_COLUMN_COUNT * 3; j++) {
                out = out.append("\u2500");
            }
            out = out.append("\u2518\n");
        }

        return out.toString();
    }
}
