public abstract class Piece {
    
    private boolean isYellowSide;

    public Piece(boolean isYellow) {
        this.isYellowSide = isYellow;
    }

    public boolean getSide() {
        return isYellowSide;
    }

    public abstract boolean allowedMove(Square start, Square end, Board board);

    public String toString() {
        return "Piece";
    }
}
