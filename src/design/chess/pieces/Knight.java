package design.chess.pieces;

import design.chess.Cell;
import design.chess.Color;
import design.chess.PieceType;

import java.util.List;

public class Knight extends Piece {
    public Knight(Color color) {
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
