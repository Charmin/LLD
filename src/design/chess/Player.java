package design.chess;

import design.chess.pieces.Piece;

import java.util.List;

public abstract class Player {
    private List<String> moves;
    private List<Piece> pieces;
    private Color color;
    // since the no of pieces
    // can increase or decrease

    public Player(List<Piece> pieces, Color color) {
        this.pieces = pieces;
        this.color = color;
    }

    boolean isWinner;

    public boolean isWinner() {
        return isWinner;
    }

    /*
     * Get the piece corresponding to the type for this user
     * */
    public Piece getPiece(PieceType pieceType) {
        for (Piece piece : pieces) {
            if (piece.getType() == pieceType) {
                return piece;
            }
        }
        throw new IllegalArgumentException("No piece of the given type exists in position");
    }

    public Color getColor() {
        return color;
    }

    public abstract void makeMove(Cell from, Cell to);
}
