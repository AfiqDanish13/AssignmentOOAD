public class TimePiece extends Piece {

    private PieceBehaviour behaviour;

    public TimePiece(boolean isYellow) {
        super(isYellow);
    }

    // public TimePiece() {
    //     this.behaviour = new TimeBehaviour();
    // }

    public void changeToPlus() {
        this.behaviour = new PlusBehaviour();
    }

    public String toString() {
        return "Time " + getSide() + " is here";
    }
}
