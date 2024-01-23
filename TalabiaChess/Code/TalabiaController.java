import java.awt.Component;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.scene.effect.Light.Point;

public class TalabiaController {

    TalabiaView theView;
    TalabiaModel theModel;
    private ChessButton selectedButton;
    private Piece clickedPiece;
    private Piece currentPiece;
    private Piece pieceToMove;
    private boolean isGameEnd = false;
    private static final String FILE_NAME = "TalabiaChess.txt";

    public TalabiaController(TalabiaView theView, TalabiaModel theModel) {

        this.theView = theView;
        this.theModel = theModel;

        theView.startButtonListener(new StartListener());
        theView.loadButtonListener(new LoadListener());
        theView.exitButtonListener(new ExitListener());
        theView.exitToMenuButtonListener(new ExitToMenuListener());
        theView.saveButtonListener(new SaveListener());
    }

    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() instanceof ChessButton) {
                ChessButton clickedButton = (ChessButton) e.getSource();
                clickedPiece = theModel.getBoard().getSquares()[clickedButton.getRow()][clickedButton.getCol()]
                        .getPiece();

                if (selectedButton == null) {
                    if (clickedPiece != null
                            && clickedPiece.getSide() == theModel.getCurrentPlayer().getIsYellowSide()) {
                        selectedButton = clickedButton; // Select the clicked piece
                        pieceToMove = clickedPiece;

                    }
                } else {
                    // If a piece is already selected (second click)
                    if (clickedPiece != null
                            && clickedPiece.getSide() == theModel.getCurrentPlayer().getIsYellowSide()) {
                        selectedButton = clickedButton; // Select the new clicked piece
                        pieceToMove = clickedPiece;
                        // theView.getMessageContent().setText("Replaced selection with piece at row: "
                        // + clickedButton.getRow() + " column: " + clickedButton.getCol());
                    } else {
                        // Move the selected piece to the new location (if valid according to piece
                        // movement rules)
                        // You would need to implement the logic that checks if the move is valid
                        // For now, let's assume the move is always valid
                        // Update the model (move the piece in the board)
                        if (pieceToMove.allowedMove(
                                theModel.getBoard().getSquares()[selectedButton.getRow()][selectedButton.getCol()],
                                theModel.getBoard().getSquares()[clickedButton.getRow()][clickedButton.getCol()],
                                theModel.getBoard())) {
                            Piece pieceCaptured = theModel.getBoard().getSquares()[clickedButton.getRow()][clickedButton
                                    .getCol()].getPiece();
                            theModel.getBoard().getSquares()[clickedButton.getRow()][clickedButton.getCol()].setPiece(
                                    theModel.getBoard().getSquares()[selectedButton.getRow()][selectedButton.getCol()]
                                            .getPiece());
                            theModel.getBoard().getSquares()[selectedButton.getRow()][selectedButton.getCol()]
                                    .removePiece();
                            // if yellow point reach row = 0, isEnd = true && reach row = 6, isEnd = false
                            // if blue point reach row = 6, isEnd = true && reach row = 0, isEnd = false

                            clickedButton.removeIcon();
                            clickedButton.insertIcon(selectedButton.getPieceRoute());
                            // clickedButton.insertExistedIcon(selectedButton.getPieceRoute(),
                            //         selectedButton.getScaledIcon());
                            selectedButton.removeIcon(); // Remove icon from the old button
                            selectedButton = null; // Deselect
                            theModel.addMoveNum();
                            if (pieceToMove instanceof PointPiece) {
                                if (clickedButton.getRow() == 0 || clickedButton.getRow() == 5) {
                                    ((PointPiece) pieceToMove).setIsEnd();
                                }
                                if (((PointPiece) pieceToMove).getIsEnd() == true) {
                                    clickedButton.rotateIcon();
                                }
                            }
                            theModel.switchPlayer(); // Switch turns
                            theView.flipBoard();
                            theView.changeIsBoardFlippedState();
                            //System.out.println(theView.getIsBoardFlipped());
                            if (pieceCaptured instanceof SunPiece) {
                                isGameEnd = true;
                                ((SunPiece) pieceCaptured).setIsCaptured();
                                if (pieceCaptured.getSide() == true) {
                                    System.out.println("blue wins");
                                    int confirmed = CustomConfirmDialog.showCustomConfirmDialog(null,
                                            "Exit Confirmation", "BLUE wins the Game. Start new game?");
                                    if (confirmed == JOptionPane.YES_OPTION) {
                                        // User selected "Yes"
                                        theModel.resetGame();
                                        theView.resetBoardView();
                                        theView.setUpMessagePanel();
                                        theView.addButtonListener(new ButtonListener());
                                        theView.revalidate();
                                        theView.repaint();
                                    } else if (confirmed == JOptionPane.NO_OPTION) {
                                        // User selected "No"
                                        theModel.resetGame();
                                        theView.resetView();
                                    }
                                } else {
                                    System.out.println("yellow wins");
                                    int confirmed = CustomConfirmDialog.showCustomConfirmDialog(null,
                                            "Exit Confirmation", "Yellow wins the Game. Start new game?");
                                    if (confirmed == JOptionPane.YES_OPTION) {
                                        // User selected "Yes"
                                        theModel.resetGame();
                                        theView.resetBoardView();
                                        theView.setUpMessagePanel();
                                        theView.addButtonListener(new ButtonListener());
                                        theView.revalidate();
                                        theView.repaint();
                                    } else if (confirmed == JOptionPane.NO_OPTION) {
                                        // User selected "No"
                                        theModel.resetGame();
                                        theView.resetView();
                                    }
                                }
                            }

                            if (isGameEnd == false) {
                                if (theModel.getMoveNum() % 4 == 0) {
                                    theModel.getBoard().changeBehaviourTimeAndPlus();
                                    for (Component component : theView.panelForBoard.getComponents()) {
                                        if (component instanceof ChessButton) {
                                            ChessButton chessButton = (ChessButton) component;
                                            String pieceRoute = chessButton.getPieceRoute();
                                            if (pieceRoute != null) {
                                                if (pieceRoute.contains("Blue") && pieceRoute.contains("Time")) {
                                                    chessButton.insertIcon("../ImageComponent/Blue/bluePlus.png");
                                                } else if (pieceRoute.contains("Blue") && pieceRoute.contains("Plus")) {
                                                    chessButton.insertIcon("../ImageComponent/Blue/blueTime.png");
                                                } else if (pieceRoute.contains("Yellow")
                                                        && pieceRoute.contains("Time")) {
                                                    chessButton.insertIcon("../ImageComponent/Yellow/yellowPlus.png");
                                                } else if (pieceRoute.contains("Yellow")
                                                        && pieceRoute.contains("Plus")) {
                                                    chessButton.insertIcon("../ImageComponent/Yellow/yellowTime.png");
                                                }
                                            }

                                        }
                                    }
                                }
                            }

                            isGameEnd = false;
                            String turns = theModel.getCurrentPlayer().getIsYellowSide() ? "Yellow Player"
                                    : "Blue Player";
                            theView.getMessageContent()
                                    .setText("Turns: " + turns + "     Move numbers: " + theModel.getMoveNum());

                        } else {
                            selectedButton = null;
                        }
                    }
                }
            }
        }
    }

    class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            theView.remove(theView.titlePanel);
            theView.remove(theView.buttonOptionMenu);
            theView.setUpSaveExit();
            theView.initializePieceIconPosition();
            theView.initializeBoard();
            theView.setUpMessagePanel();
            theView.addButtonListener(new ButtonListener());
            theView.revalidate();
            theView.repaint();

        }
    }

    class LoadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // JOptionPane.showMessageDialog(theView, "Load button clicked!");
            theView.resetLoadView();
            loadData();

            theView.remove(theView.titlePanel);
            theView.remove(theView.buttonOptionMenu);
            theView.setUpSaveExit();

            // theView.initializeLoadBoard();
            theView.setUpMessagePanel();

            theView.addButtonListener(new ButtonListener());
            if (theView.getIsBoardFlipped() == true)
                theView.flipBoard();
            theView.revalidate();
            theView.repaint();

        }
    }

    public void loadData() {
        // Load data from a specific file
        JFileChooser loadChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        loadChooser.setFileFilter(filter);

        int loadResult = loadChooser.showOpenDialog(null);

        if (loadResult == JFileChooser.APPROVE_OPTION) {
            String loadFileName = loadChooser.getSelectedFile().getAbsolutePath();
            // theModel.loadGame(loadFileName);

            LoadedData loadedData = loadGame(loadFileName);

            // Access the loaded arrays if needed
            int[] rowsArray = loadedData.rowsArray;
            int[] colsArray = loadedData.colsArray;
            String[] pieceArray = loadedData.pieceArray;
            // for(String i: loadedData.pieceArray) System.out.println(i);
            setSpecialBehaviour(pieceArray);

            theView.initializeLoadPieceIconPositions(pieceArray);
            theView.initializeLoadBoard(rowsArray, colsArray, pieceArray);

        }
        String turns = theModel.getCurrentPlayer().getIsYellowSide() ? "Yellow Player" : "Blue Player";
        theView.getMessageContent().setText("Turns: " + turns + "     Move numbers: " + theModel.getMoveNum());
    }

    class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to exit?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                System.exit(0); // Exit the application
            }
        }
    }

    class ExitToMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to exit to menu? Make sure to save the current game progress",
                    "Exit To Menu Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                theModel.resetGame();
                theView.resetView();
            }
        }
    }

    class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // JOptionPane.showMessageDialog(theView, "Save button clicked!");
            saveGame();
        }
    }

    public void saveGame() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            // save move number
            writer.write("Move number: " + Integer.toString(theModel.getMoveNum()));
            writer.newLine();
            writer.write("Player turn: " + theModel.getCurrentPlayer());
            writer.newLine();
            writer.write("FlipBoard: " + theView.getIsBoardFlipped());
            writer.newLine();
            // save all squares
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    writer.write("" + Board.getSquares(i, j));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class LoadedData {
        public int[] rowsArray;
        public int[] colsArray;
        public String[] pieceArray;

        public LoadedData(int[] rowsArray, int[] colsArray, String[] pieceArray) {
            this.rowsArray = rowsArray;
            this.colsArray = colsArray;
            this.pieceArray = pieceArray;
        }
    }

    public LoadedData loadGame(String fileName) {
        int[] rowsArray = new int[6 * 7];
        int[] colsArray = new int[6 * 7];
        String[] pieceArray = new String[6 * 7];
        theModel.setdefaultPlayer();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Col:")) {
                    String[] parts = line.split(", ");
                    int col = Integer.parseInt(parts[0].substring(parts[0].lastIndexOf(' ') + 1).trim());
                    int row = Integer.parseInt(parts[1].substring(parts[1].lastIndexOf(' ') + 1).trim());
                    String piece = parts[2].substring(6).trim();

                    colsArray[index] = col;
                    rowsArray[index] = row;
                    pieceArray[index] = piece;
                    index++;
                } else if (line.contains("Move")) {
                    int moveCount = Integer.parseInt(line.substring(13).trim());
                    // System.out.println(moveCount);
                    theModel.setMoveNum(moveCount);
                } else if (line.contains("Player")) {
                    String cuPlayer = line.substring(7).trim();
                    if (cuPlayer.contains("Yellow")) {
                        theModel.setCurrentYellow();
                    } else {
                        theModel.setCurrentBlue();
                    }
                } else if (line.contains("FlipBoard:")) {
                    String flipstate = line.substring(11).trim();
                    if (flipstate.equals("false")) {
                        theView.setIsBoardFlipped(false);
                    } else {
                        theView.setIsBoardFlipped(true);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Board.loadBoard(rowsArray, colsArray, pieceArray);

        return new LoadedData(rowsArray, colsArray, pieceArray);

    }

    public void setSpecialBehaviour(String[] pieceArray) {
        int index = 0;
        int rows = 6;
        int columns = 7;
    
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String curPiece = pieceArray[index];
                String[] indexPiece = curPiece.split(" ");
    
                if (indexPiece != null && indexPiece.length >= 3) {
                    if (!indexPiece[0].equals("null") && indexPiece[0].equals("Point")) {
                        PointPiece pointPiece = (PointPiece) theModel.getBoard().getSquares()[i][j].getPiece();
                        if (indexPiece[2].equals("true")) {
                            pointPiece.loadIsEnd(true);
                        } else if (indexPiece[2].equals("false")) {
                            pointPiece.loadIsEnd(false);
                        }
                    } else if (!indexPiece[0].equals("null") && indexPiece[0].equals("Time")) {
                        TimePiece timePiece = getTimePiece(i, j);
                        if (indexPiece[2].equals("TimeBehaviour")) {
                            timePiece.setBehaviour(true);
                        } else if (indexPiece[2].equals("PlusBehaviour")) {
                            timePiece.setBehaviour(false);
                        }
                        System.out.println(timePiece);
                    } else if (!indexPiece[0].equals("null") && indexPiece[0].equals("Plus")) {
                        PlusPiece plusPiece = getPlusPiece(i, j);
                        if (indexPiece[2].equals("PlusBehaviour")) {
                            plusPiece.setBehaviour(true);
                        } else if (indexPiece[2].equals("TimeBehaviour")) {
                            plusPiece.setBehaviour(false);
                        }
                        System.out.println(plusPiece);
                    }
                } else {
                    // Handle the case when indexPiece is null or doesn't have enough elements
                }
                
                index++;
            }
        }
    }
    

    public TimePiece getTimePiece(int row, int col) {
        Square square = theModel.getBoard().getSquares()[row][col];
        if (square.getPiece() instanceof TimePiece) {
            return (TimePiece) square.getPiece();
        } else {
            return null; // or throw an exception, depending on your requirements
        }
    }

    public PlusPiece getPlusPiece(int row, int col) {
        Square square = theModel.getBoard().getSquares()[row][col];
        if (square.getPiece() instanceof PlusPiece) {
            return (PlusPiece) square.getPiece();
        } else {
            // Handle the case when the piece is not a PlusPiece
            return null; // or throw an exception, depending on your requirements
        }
    }

}
