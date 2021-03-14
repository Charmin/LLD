package design.chess.pieces;

import design.chess.Cell;
import design.chess.Color;
import design.chess.PieceType;

import java.util.List;

public abstract class Piece {
    private final Color color;
    private boolean isKilled;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isKilled() {
        return isKilled;
    }

    public void kill() {
        isKilled = true;
    }

    public abstract PieceType getType();
    public abstract List<Cell> getNextPossibleCells(Cell current);
    public abstract void validateMove(Cell from, Cell destination);
}
