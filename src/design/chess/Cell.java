package design.chess;

import design.chess.pieces.Piece;

public class Cell {
    private int x;
    private int y;

    private Piece currentPiece;

    public void setCurrentPiece(Piece piece) {
        currentPiece = piece;
    }

    public boolean isOccupied() {
        return currentPiece != null;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
