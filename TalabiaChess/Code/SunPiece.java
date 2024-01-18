public class SunPiece extends Piece {

    private boolean isCaptured = false;
    
    public SunPiece(boolean isYellow) {
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

        // x can be 0 while y is 1
        // x can be 1 while y is 1
        // x can be 1 while y is 0
        int xMovement = Math.abs(startCol - endCol);
        int yMovement = Math.abs(startRow - endRow);
        int destinationRow;
        int destinationCol;

        if(startRow <= endRow) {
            destinationRow = startRow + yMovement;
        } else {
            destinationRow = startRow - yMovement;
        }

        if(startCol <= endCol) {
            destinationCol = startCol + xMovement;
        } else {
            destinationCol = startCol - xMovement;
        }

        validMovement = (xMovement == 1 && (yMovement == 1 || yMovement == 0)) || (xMovement == 0 && (yMovement == 1));
        noObstacle = board.getSquares()[destinationRow][destinationCol].getPiece() == null || board.getSquares()[destinationRow][destinationCol].getPiece().getSide() != this.getSide();

        return validMovement && noObstacle;
    }

    public void setIsCaptured() {
        isCaptured = !isCaptured;
    }

    public boolean getIsCaptured() {
        return isCaptured;
    }

    public String toString() {
        return "Sun " + getSide() + " is here";
    }
}
