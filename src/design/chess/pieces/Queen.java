package design.chess.pieces;

import design.chess.Cell;
import design.chess.Color;
import design.chess.PieceType;

import java.util.List;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public PieceType getType() {
        return null;
    }

    @Override
    public List<Cell> getNextPossibleCells(Cell current) {
        return null;
    }

    @Override
    public void validateMove(Cell from, Cell destination) {

    }
}
