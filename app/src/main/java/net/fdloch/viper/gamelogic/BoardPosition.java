package net.fdloch.viper.gamelogic;

/**
 * Created by florian on 25.05.15.
 */
public class BoardPosition {
    private int column;
    private int row;

    public BoardPosition(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public BoardPosition(BoardPosition that, Direction direction) {
        this.setColumn(that.getColumn() + direction.shiftHorizontal);
        this.setRow(that.getRow() + direction.shiftVertical);

        if (this.getColumn() < 0) {
            this.setColumn(Consts.BOARD_COLUMN_COUNT - 1);
        }

        if (this.getColumn() == Consts.BOARD_COLUMN_COUNT) {
            this.setColumn(0);
        }

        if (this.getRow() < 0) {
            this.setRow(Consts.BOARD_ROW_COUNT - 1);
        }

        if (this.getRow() == Consts.BOARD_ROW_COUNT) {
            this.setRow(0);
        }
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Direction directionRelativeTo(BoardPosition target) {
        Direction dir;

        //First resolve horizontal component
        if (column != target.column) {
            int lengthWest;
            int lengthEast;
            if (column > target.column) {
                lengthWest = column - target.column;
                lengthEast = Consts.BOARD_COLUMN_COUNT - column + target.column;
            }
            else {
                lengthWest = column + Consts.BOARD_COLUMN_COUNT - target.column;
                lengthEast = target.column - column;
            }

            if (lengthWest < lengthEast) {
                return Direction.WEST;
            }
            return Direction.EAST;
        }

        int lengthNorth;
        int lengthSouth;
        if (row > target.row) {
            lengthNorth = Consts.BOARD_ROW_COUNT - row + target.row;
            lengthSouth = row - target.row;
        }
        else {
            lengthNorth = target.row - row;
            lengthSouth = row + Consts.BOARD_ROW_COUNT - target.row;
        }

        if (lengthNorth < lengthSouth) {
            return Direction.NORTH;
        }
        return Direction.SOUTH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoardPosition that = (BoardPosition) o;

        if (column != that.column) return false;
        return row == that.row;

    }

    @Override
    public int hashCode() {
        int result = column;
        result = 31 * result + row;
        return result;
    }

    @Override
    public String toString() {
        return "BoardPosition{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }
}
