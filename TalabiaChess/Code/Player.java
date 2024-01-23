public class Player {

    private boolean isYellowSide;

    public Player(boolean isYellow) {
        this.isYellowSide = isYellow;
    }

    public boolean getIsYellowSide() {
        return isYellowSide;
    }

    public String toString(){
        if (isYellowSide) {
            return "Yellow";
        }else{
            return "Blue";
        }
    }
}
