public class PointPiece extends Piece {

    private boolean isEnd; // false if point move forward, true if point move backward.

    public PointPiece(boolean isYellow) {
        super(isYellow);
        this.isEnd = false;
    }

    @Override
    public boolean allowedMove(Square start, Square end, Board board) {
        
        // for yellow: 
        // if the isYellow is true (means it is yellow), isEnd is false (isEnd == false) means it move forward, 
        // movement must make sure that startRow > endRow
        // then, if the point reach the end of board (row == 0), isEnd will be true (isEnd == true),
        // movement must make sure that startRow < endRow
        // when reach row == 0, isEnd == true
        // when reach row == 5, isEnd == false

        // opposite happen for isYellow false

        boolean validMovement = false;
        boolean noObstacle = true;
        int startCol = start.getpositionCol();
        int startRow = start.getPositionRow();
        int endCol = end.getpositionCol();
        int endRow = end.getPositionRow();

        int xMovement = Math.abs(startCol - endCol); // must be 0 (no diagonal movement)
        int yMovement = getSide() ? (isEnd ? endRow - startRow : startRow - endRow) : (isEnd ? startRow - endRow : endRow - startRow)  ; // must move forward 1 or 2 square (no skip other piece)

        if(yMovement < 1) {

            return validMovement && noObstacle;

        } else {

            Piece potentialCapturePiece = board.getSquares()[endRow][endCol].getPiece();
            boolean isCaptureMovement = potentialCapturePiece != null && potentialCapturePiece.getSide() != this.getSide();
            if(xMovement == 0) {
                if(isCaptureMovement) { // movement with capture
                    validMovement = (yMovement == 1) || (yMovement == 2);
                    for(int i = 1; i < yMovement; i++) {
                        int pathY = getSide() ? (isEnd ? startRow + i : startRow - i) : (isEnd ? startRow - i : startRow + i);
                        if(board.getSquares()[pathY][endCol].getPiece() != null) {
                            noObstacle = false;
                            break;
                        }
                    }
                } else { // movement with no capture

                    validMovement = (yMovement == 1) || (yMovement == 2);
                    for(int i = 1; i <= yMovement; i++) {
                        int pathY = getSide() ? (isEnd ? startRow + i : startRow - i) : (isEnd ? startRow - i : startRow + i);
                        if(board.getSquares()[pathY][endCol].getPiece() != null) {
                            //System.out.println("x boleh langkau");
                            noObstacle = false;
                            break;
                        }
                    }
                }
            }

            return validMovement && noObstacle;
        }
        
    }

    public void setIsEnd() {
        this.isEnd = !isEnd;
    }

    public void loadIsEnd(boolean cond){
        this.isEnd = cond;
    }

    public boolean getIsEnd() {
        return isEnd;
    }

    public String toString() {
        return "Point " + getSide() + " " + getIsEnd();
    }
    
}
