package net.fdloch.viper.gamelogic;

import java.util.LinkedList;

/**
 * Created by florian on 25.05.15.
 */
public abstract class Snake {
    LinkedList<BoardPosition> pathway = new LinkedList<>();

    public abstract BoardPosition determineMove();
}
