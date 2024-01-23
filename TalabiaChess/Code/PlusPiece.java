import javax.print.attribute.PrintServiceAttributeSet;

public class PlusPiece extends Piece {

    private PieceBehaviour behaviour;

    public PlusPiece(boolean isYellow) {
        super(isYellow);
        this.behaviour = new PlusBehaviour();
    }

    @Override
    public boolean allowedMove(Square start, Square end, Board board) {
        return behaviour.allowedMove(start, end, board);
    }

    public void changeBehaviour() {
        if(behaviour instanceof PlusBehaviour) {
            this.behaviour = new TimeBehaviour();
        } else {
            this.behaviour = new PlusBehaviour();
        }
    }

    public void setBehaviour(boolean cond){
        this.behaviour = (cond) ? new PlusBehaviour() : new TimeBehaviour();  
    }

    public void getBehaviour(){
        System.out.println((behaviour instanceof TimeBehaviour) ? "TimeBehaviour" : "PlusBehaviour");
    }

    public String getBehaviourName() {
        return behaviour.getClass().getSimpleName();
    }

    public String toString() {
        return "Plus " + getSide() + " " + getBehaviourName();
    }
}
