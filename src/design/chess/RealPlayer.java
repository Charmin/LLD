package design.chess;

import design.chess.pieces.Piece;

import java.util.List;

public class RealPlayer extends Player {

    public RealPlayer(List<Piece> pieces, Color color) {
        super(pieces, color);
    }

    /*
     * @param piece
     * @param from
     * @param to
     * @return true indicates its a check mate
     */
    @Override
    public void makeMove(Cell from, Cell to) {
        Piece currentPiece = from.getCurrentPiece();
        currentPiece.validateMove(from, to);
        from.setCurrentPiece(null);
        if(to.isOccupied()) {
            to.getCurrentPiece().kill();
        }
        to.setCurrentPiece(currentPiece);
    }
}
