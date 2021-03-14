package design.chess;

import design.chess.pieces.Piece;

public class Board {
    //Has 2D array of a type (we call it cell)
    Cell[][] cells;
    Player[] players;

    public Cell[][] getCells() {
        return cells;
    }

    public Board() {
        cells = new Cell[8][8];
        players = new Player[2];

    }

    public Cell getCell(int x, int y) {
        if (x < 0 || y > cells.length) {
            throw new IllegalArgumentException("Invalid x and y specified");
        }
        return cells[x][y];
    }

    public void makeMove(Player player, Cell from, Cell to) {
        if (to.getX() < 0 || to.getX() >= cells.length
                || to.getY() < 0 || to.getY() >= cells.length
                || to.isOccupied() && (to.getCurrentPiece().getColor() == player.getColor())) {
            throw new IllegalArgumentException("Invalid value for x and y");
        }
        player.makeMove(from, to);
    }

    //check if piece can be killed
    //is check?

}
