import java.awt.Component;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class TalabiaController {
    
    TalabiaView theView;
    TalabiaModel theModel;
    private ChessButton selectedButton;
    private Piece clickedPiece;
    private Piece pieceToMove;
    private boolean isGameEnd = false;

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
                clickedPiece = theModel.getBoard().getSquares()[clickedButton.getRow()][clickedButton.getCol()].getPiece();
                
                if (selectedButton == null) {
                    if (clickedPiece != null && clickedPiece.getSide() == theModel.getCurrentPlayer().getIsYellowSide()) {
                        selectedButton = clickedButton; // Select the clicked piece
                        pieceToMove = clickedPiece;

                    }
                } else {
                    // If a piece is already selected (second click)
                    if (clickedPiece != null && clickedPiece.getSide() == theModel.getCurrentPlayer().getIsYellowSide()) {
                        selectedButton = clickedButton; // Select the new clicked piece
                        pieceToMove = clickedPiece;
                        // theView.getMessageContent().setText("Replaced selection with piece at row: " + clickedButton.getRow() + " column: " + clickedButton.getCol());
                    } else {
                        // Move the selected piece to the new location (if valid according to piece movement rules)
                        // You would need to implement the logic that checks if the move is valid
                        // For now, let's assume the move is always valid
                        // Update the model (move the piece in the board)
                        if(pieceToMove.allowedMove(theModel.getBoard().getSquares()[selectedButton.getRow()][selectedButton.getCol()], theModel.getBoard().getSquares()[clickedButton.getRow()][clickedButton.getCol()], theModel.getBoard())) {
                            Piece pieceCaptured = theModel.getBoard().getSquares()[clickedButton.getRow()][clickedButton.getCol()].getPiece();
                            theModel.getBoard().getSquares()[clickedButton.getRow()][clickedButton.getCol()].setPiece(theModel.getBoard().getSquares()[selectedButton.getRow()][selectedButton.getCol()].getPiece());
                            theModel.getBoard().getSquares()[selectedButton.getRow()][selectedButton.getCol()].removePiece();
                            // if yellow point reach row = 0, isEnd = true && reach row = 6, isEnd = false
                            // if blue point reach row = 6, isEnd = true && reach row = 0, isEnd = false
                            
                            clickedButton.removeIcon();
                            clickedButton.insertIcon(selectedButton.getPieceRoute());
                            clickedButton.insertExistedIcon(selectedButton.getPieceRoute(), selectedButton.getScaledIcon());
                            selectedButton.removeIcon(); // Remove icon from the old button
                            selectedButton = null; // Deselect
                            theModel.addMoveNum();
                            if(pieceToMove instanceof PointPiece) {
                                if(clickedButton.getRow() == 0 || clickedButton.getRow() == 5) {
                                    ((PointPiece)pieceToMove).setIsEnd();

                                }
                                if(((PointPiece)pieceToMove).getIsEnd() == true) {
                                    clickedButton.rotateIcon();
                                }
                            }
                            theModel.switchPlayer(); // Switch turns
                            theView.flipBoard();
                            if(pieceCaptured instanceof SunPiece) {
                                isGameEnd = true;
                                ((SunPiece)pieceCaptured).setIsCaptured();
                                if(pieceCaptured.getSide() == true) {
                                    System.out.println("blue wins");
                                    int confirmed = CustomConfirmDialog.showCustomConfirmDialog(null, "Exit Confirmation", "BLUE wins the Game. Start new game?");
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
                                    int confirmed = CustomConfirmDialog.showCustomConfirmDialog(null, "Exit Confirmation", "Yellow wins the Game. Start new game?");
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

                            if(isGameEnd == false) {
                                if(theModel.getMoveNum() % 4 == 0) {
                                    theModel.getBoard().changeBehaviourTimeAndPlus();
                                    for(Component component: theView.panelForBoard.getComponents()) {
                                        if(component instanceof ChessButton) {
                                            ChessButton chessButton = (ChessButton) component;
                                            String pieceRoute = chessButton.getPieceRoute();
                                            if(pieceRoute != null) {
                                                if(pieceRoute.contains("Blue") && pieceRoute.contains("Time")) {
                                                    chessButton.insertIcon("../ImageComponent/Blue/bluePlus.png");
                                                } else if(pieceRoute.contains("Blue") && pieceRoute.contains("Plus")) {
                                                    chessButton.insertIcon("../ImageComponent/Blue/blueTime.png");
                                                } else if(pieceRoute.contains("Yellow") && pieceRoute.contains("Time")) {
                                                    chessButton.insertIcon("../ImageComponent/Yellow/yellowPlus.png");
                                                } else if(pieceRoute.contains("Yellow") && pieceRoute.contains("Plus")) {
                                                    chessButton.insertIcon("../ImageComponent/Yellow/yellowTime.png");
                                                }
                                            }
                                            
                                        }
                                    }
                                }
                            }          
                            
                            isGameEnd = false;
                            String turns = theModel.getCurrentPlayer().getIsYellowSide() ? "Yellow Player" : "Blue Player";
                            theView.getMessageContent().setText("Turns: " + turns + "     Move numbers: " + theModel.getMoveNum());

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
            JOptionPane.showMessageDialog(theView, "Load button clicked!");
        }
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
            JOptionPane.showMessageDialog(theView, "Save button clicked!");
        }
    }

}
