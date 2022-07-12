package boardgame;

public class Position {
    private Integer row;
    private Integer column;

    public Position() {
    }

    public Position(Integer row, Integer colum) {
        this.row = row;
        this.column = colum;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer colum) {
        this.column = colum;
    }

    public void setValues(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return row + ", " + column;
    }
}
