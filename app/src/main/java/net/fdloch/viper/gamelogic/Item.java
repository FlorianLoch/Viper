package net.fdloch.viper.gamelogic;

/**
 * Created by florian on 25.05.15.
 */
public abstract class Item {

    public abstract boolean isFood();

    public abstract void getsConsumed();

    public abstract char getSign();

    public static Item NO_ITEM = new Item() {
        @Override
        public boolean isFood() {
            return false;
        }

        @Override
        public void getsConsumed() {
            //
        }

        @Override
        public char getSign() {
            return '/';
        }
    };
}
