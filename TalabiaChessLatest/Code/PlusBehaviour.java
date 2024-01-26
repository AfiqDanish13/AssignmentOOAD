public class PlusBehaviour implements PieceBehaviour {
    
    public boolean allowedMove(Square start, Square end, Board board) { // move like a rook
        boolean validMovement = false;
        boolean noObstacle = true;
        int startCol = start.getpositionCol();
        int startRow = start.getPositionRow();
        int endCol = end.getpositionCol();
        int endRow = end.getPositionRow();

        int xMovement = Math.abs(startCol - endCol);
        int yMovement = Math.abs(startRow - endRow);

        if((xMovement == 0 && yMovement > 0) || (xMovement > 0 && yMovement == 0)) {
            validMovement = true;
        } else {
            return validMovement;
        }

        int xDirection = Integer.compare(endCol, startCol);
        int yDirection = Integer.compare(endRow, startRow);
        int x = startCol + xDirection;
        int y = startRow + yDirection;

        if(xMovement == 0) {
            while(y != endRow) {
                if(board.getSquares()[y][x].getPiece() != null) return !noObstacle;
                y += yDirection;
            } 
        } else if(yMovement == 0) {
            while(x != endCol) {
                if(board.getSquares()[y][x].getPiece() != null) return !noObstacle;
                x += xDirection;
            } 
        }

        Piece endPiece = end.getPiece();
        if(endPiece != null && endPiece.getSide() == start.getPiece().getSide()) {
            noObstacle = false;
        }

        return validMovement && noObstacle;
    }
}
