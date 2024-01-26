public class Square {
    
    private int positionCol;
    private int positionRow;
    private Piece piece;

    public Square(int positionRow, int positionCol) {
        this.positionRow = positionRow;
        this.positionCol = positionCol;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void removePiece() {
        this.piece = null;
    }

    public int getpositionCol() {
        return positionCol;
    }

    public int getPositionRow() {
        return positionRow;
    }

    public String toString(){
        return "Col: " + getpositionCol() + ", Row: " + getPositionRow() +
        ", Piece: " + getPiece();
    }
}
