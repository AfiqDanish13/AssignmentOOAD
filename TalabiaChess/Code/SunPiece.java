public class SunPiece extends Piece {
    
    public SunPiece(boolean isYellow) {
        super(isYellow);
    }

    public String toString() {
        return "Sun " + getSide() + " is here";
    }
}
