package design.chess.pieces;

import design.chess.Cell;
import design.chess.Color;
import design.chess.PieceType;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }

    @Override
    public List<Cell> getNextPossibleCells(Cell current) {
        return null;
    }

    //is within the board
    //The next move is on a diagonal, (abs difference) (Math.abs(dx-sx) == Math.abs(dy-sy))
    //Not occupied by its own side
    //Does not cause check on itself
    @Override
    public void validateMove(Cell from, Cell destination) {

    }
}
