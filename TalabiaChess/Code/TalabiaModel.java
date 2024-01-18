public class TalabiaModel {

    private Board board;
    private Player yellowPlayer;
    private Player bluePlayer;
    private Player currentPlayer;
    private int moveNum = 0;

    public TalabiaModel() {
        this.board = Board.getBoard();
        this.yellowPlayer = new Player(true);
        this.bluePlayer = new Player(false);
        currentPlayer = yellowPlayer; // set yellow player to start the game by default
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getMoveNum() {
        return moveNum;
    }

    public void addMoveNum() {
        moveNum++;
    }

    public void switchPlayer() {
        currentPlayer = ( currentPlayer == yellowPlayer ) ? bluePlayer : yellowPlayer;
    }

    public void resetGame() {
        this.board.resetBoard();
        this.yellowPlayer = new Player(true);
        this.bluePlayer = new Player(false);
        this.currentPlayer = yellowPlayer;
        moveNum = 0;
    }
}
