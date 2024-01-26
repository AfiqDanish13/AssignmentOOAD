public class TimePiece extends Piece {
    private PieceBehaviour behaviour;
    
    public TimePiece(boolean isYellow) {
        super(isYellow);
        behaviour = new TimeBehaviour();
    }

    @Override
    public boolean allowedMove(Square start, Square end, Board board) {
        return behaviour.allowedMove(start, end, board);
    }

    public void changeBehaviour() {
        this.behaviour = (behaviour instanceof TimeBehaviour) ? new PlusBehaviour() : new TimeBehaviour();
        
    }

    public void setBehaviour(boolean cond){
        this.behaviour = (cond) ? new TimeBehaviour() : new PlusBehaviour();  
    }

    public void getBehaviour(){
        System.out.println((behaviour instanceof TimeBehaviour) ? "TimeBehaviour" : "PlusBehaviour");
    }


    public String getBehaviourName() {
        return behaviour.getClass().getSimpleName();
    }

    public String toString() {
        return "Time " + getSide() + " " + getBehaviourName();
    }
}
