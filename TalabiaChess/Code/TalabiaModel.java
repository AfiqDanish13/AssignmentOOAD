import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

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

    // load game
    // public TalabiaModel(){

    // }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getMoveNum() {
        return moveNum;
    }

    public void setMoveNum(int moveNum) {
        this.moveNum = moveNum;
    }
    public void setCurrentBlue(){
        this.currentPlayer = bluePlayer;
    }
    public void setCurrentYellow(){
        this.currentPlayer = yellowPlayer;
    }

    public void setPlayer(Player curPlayer) {
        this.currentPlayer = curPlayer;
    }
    public void setdefaultPlayer(){
        this.yellowPlayer = new Player(true);
        this.bluePlayer = new Player(false);
    }

    public void addMoveNum() {
        moveNum++;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == yellowPlayer) ? bluePlayer : yellowPlayer;
    }

    public void resetGame() {
        this.board.resetBoard();
        this.yellowPlayer = new Player(true);
        this.bluePlayer = new Player(false);
        this.currentPlayer = yellowPlayer;
        moveNum = 0;
    }

    

    
}
