package chess;

import boardlayer.Board;
import boardlayer.Position;
import chess.ChessPosition;
import chess.pieces.King;
import chess.pieces.Rook;
import boardlayer.Piece;

public class ChessMatch {

    private Board board;

    public ChessMatch() {
        board = new Board(8, 8);
        initialSetup();
    }

    private void initialSetup() {

        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {

        board.placePiece(piece, new ChessPosition(column, row).toPosition());
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

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {

        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {

        Piece p = board.removePiece(source);

        Piece capturedPiece = board.removePiece(target);

        board.placePiece(p, target);

        return capturedPiece;
    }

    private void validateSourcePosition(Position source) {

        if (!board.thereIsAPiece(source)) {
            throw new ChessException("There is no piece on source position");
        }

        if (!board.piece(source).isThereAnyPossibleMove()) {
            throw new ChessException("There are no possible moves for the chosen piece");
        }

    }

    private void validateTargetPosition(Position source, Position target) {

        if (!board.piece(source).possibleMove(target)) {

            throw new ChessException("The chosen piece cannot move to the target position");
        }
    }
}