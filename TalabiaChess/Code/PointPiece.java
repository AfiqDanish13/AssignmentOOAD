public class PointPiece extends Piece {

    public PointPiece(boolean isYellow) {
        super(isYellow);
    }

    public String toString() {
        return "Point " + getSide() + " is here";
    }
    
}
