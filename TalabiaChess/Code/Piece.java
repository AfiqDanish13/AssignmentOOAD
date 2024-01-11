public abstract class Piece {
    
    private boolean isYellowSide;

    public Piece(boolean isYellow) {
        this.isYellowSide = isYellow;
    }

    public boolean getSide() {
        return isYellowSide;
    }

    public String toString() {
        return "Piece is here";
    }
}
