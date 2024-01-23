public class HourglassPiece extends Piece {
    
    public HourglassPiece(boolean isYellow) {
        super(isYellow);
    }

    @Override
    public boolean allowedMove(Square start, Square end, Board board) {
        boolean validMovement = false;
        boolean noObstacle = true;
        int startCol = start.getpositionCol();
        int startRow = start.getPositionRow();
        int endCol = end.getpositionCol();
        int endRow = end.getPositionRow();

        int xMovement = Math.abs(startCol - endCol);
        int yMovement = Math.abs(startRow - endRow);

        int destinationRow;
        int destinationCol;

        if(startRow < endRow) {
            destinationRow = startRow + yMovement;
        } else {
            destinationRow = startRow - yMovement;
        }

        if(startCol < endCol) {
            destinationCol = startCol + xMovement;
        } else {
            destinationCol = startCol - xMovement;
        }
        validMovement = ((xMovement == 1) && (yMovement == 2)) || ((xMovement == 2) && (yMovement == 1));
        noObstacle = board.getSquares()[destinationRow][destinationCol].getPiece() == null ||  board.getSquares()[destinationRow][destinationCol].getPiece().getSide() != this.getSide();
        return validMovement && noObstacle;
    }

    public String toString() {
        return "Hourglass " + getSide();
    }
}
