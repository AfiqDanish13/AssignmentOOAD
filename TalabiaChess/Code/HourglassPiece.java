public class HourglassPiece extends Piece {
    
    public HourglassPiece(boolean isYellow) {
        super(isYellow);
    }

    public String toString() {
        return "Hourglass " + getSide() + " is here";
    }
}
