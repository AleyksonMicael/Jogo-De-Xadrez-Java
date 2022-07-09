package boardgame;

public class Board {

    private Integer rows;
    private Integer columns;
    private Piece[][] pieces;

    public Board(Integer rows, Integer columns) {
        if (rows < 1 || columns < 1) {
            throw new BoardException("Erro de tabuleiro: Deve-se ter pelo menos 1 linha e 1 coluna");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getColumns() {
        return columns;
    }

    public Piece piece(Integer row, Integer column) {
        if (!positionExists(row,column)){
            throw new BoardException("Não existe esta posição no tabuleiro!");
        }
        return pieces[row][column];
    }

    public Piece piece(Position position) {
        // PODE ESTA ERRADO ESSA VERIFICAÇÃO!
        if (!posirionExists(position)){
            throw new BoardException("Não existe esta posição no tabuleiro!");
        }
        return pieces[position.getRow()][position.getColum()];
    }

    public void placePiece(Piece piece, Position position) {
        if (thereIsAPiece(position)){
            throw new BoardException("Já existe uma peça na posição !" + position + " !");
        }
        this.pieces[position.getRow()][position.getColum()] = piece;
        piece.position = position;
    }

    public boolean positionExists(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public boolean posirionExists(Position position) {
        return positionExists(position.getRow(), position.getColum());
    }

    public boolean thereIsAPiece(Position position) {
        if (!posirionExists(position)){
            throw new BoardException("Não existe esta posição no tabuleiro!");
        }
        return piece(position) != null;
    }

}
