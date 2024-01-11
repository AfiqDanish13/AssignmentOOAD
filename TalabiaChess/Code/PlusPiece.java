public class PlusPiece extends Piece {

    private PieceBehaviour behaviour;

    public PlusPiece(boolean isYellow) {
        super(isYellow);
    }

    // public PlusPiece() {
    //     this.behaviour = new PlusBehaviour();
    // }

    public void changeToTime() {
        this.behaviour = new PlusBehaviour();
    }

    public String toString() {
        return "Plus " + getSide() + " is here";
    }
}
