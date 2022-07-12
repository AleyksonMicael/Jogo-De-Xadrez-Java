package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;

public class ChessMatch {
    private int turn;
    private Color currentPlayer;
    private Board board;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.JOGADOR_BRANCO;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }
    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        nextTurn();
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return (ChessPiece) capturedPiece;
    }

    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException("Não existe peça nessa posição!");
        }
        if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
            throw new ChessException("Esta peça não é sua!");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("Não existe movimentos possiveis!");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("A peça escolhida não pode ir pra o destino escolhido!");
        }
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.JOGADOR_BRANCO) ? Color.JOGADOR_PRETO : Color.JOGADOR_BRANCO;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup() {
        placeNewPiece('a', 1, new Rook(board, Color.JOGADOR_BRANCO));
        placeNewPiece('c', 2, new Rook(board, Color.JOGADOR_BRANCO));
        placeNewPiece('d', 2, new Rook(board, Color.JOGADOR_BRANCO));
        placeNewPiece('e', 2, new Rook(board, Color.JOGADOR_BRANCO));
        placeNewPiece('e', 1, new Rook(board, Color.JOGADOR_BRANCO));
        placeNewPiece('d', 1, new King(board, Color.JOGADOR_BRANCO));

        placeNewPiece('c', 7, new Rook(board, Color.JOGADOR_PRETO));
        placeNewPiece('c', 8, new Rook(board, Color.JOGADOR_PRETO));
        placeNewPiece('d', 7, new Rook(board, Color.JOGADOR_PRETO));
        placeNewPiece('e', 7, new Rook(board, Color.JOGADOR_PRETO));
        placeNewPiece('e', 8, new Rook(board, Color.JOGADOR_PRETO));
        placeNewPiece('d', 8, new King(board, Color.JOGADOR_PRETO));
    }
}
