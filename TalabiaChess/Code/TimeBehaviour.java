public class TimeBehaviour implements PieceBehaviour {

    public boolean allowedMove(Square start, Square end, Board board) { // move diagonally like bishop
        boolean validMovement = false;
        boolean noObstacle = true;

        int startCol = start.getpositionCol();
        int startRow = start.getPositionRow();
        int endCol = end.getpositionCol();
        int endRow = end.getPositionRow();

        int xMovement = Math.abs(startCol - endCol);
        int yMovement = Math.abs(startRow - endRow);

        if(xMovement != yMovement) {
            return validMovement;
        } else {
            validMovement = true;
        }

        int xDirection = Integer.compare(endCol, startCol);
        int yDirection = Integer.compare(endRow, startRow);

        int x = startCol + xDirection;
        int y = startRow + yDirection;

        while( x != endCol && y != endRow) {
            if(board.getSquares()[y][x].getPiece() != null) return !noObstacle;
            x += xDirection;
            y += yDirection;
        }

        Piece endPiece = end.getPiece();
        if(endPiece != null && endPiece.getSide() == start.getPiece().getSide()) {
            noObstacle = false;
        }
        
        return validMovement && noObstacle;
    }
}
