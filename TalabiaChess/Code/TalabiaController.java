import java.awt.event.*;
import javax.swing.JOptionPane;

public class TalabiaController {
    
    TalabiaView theView;
    TalabiaModel theModel;
    private ChessButton selectedButton;

    public TalabiaController(TalabiaView theView, TalabiaModel theModel) {

        this.theView = theView;
        this.theModel = theModel;

        theView.startButtonListener(new StartListener());
        theView.loadButtonListener(new LoadListener());
        theView.exitButtonListener(new ExitListener());
        theView.exitToMenuButtonListener(new ExitToMenuListener());
        theView.flipButtonListener(new FlipListener());
    }

    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() instanceof ChessButton) {

                ChessButton clickedButton = (ChessButton) e.getSource();                
                theView.getMessageContent().setText("row: " + clickedButton.getRow() + " column: " + clickedButton.getCol() + " clicked");
                if(theModel.getBoard().getSquares()[clickedButton.getRow() ][clickedButton.getCol() ].getPiece() != null) System.out.println(theModel.getBoard().getSquares()[clickedButton.getRow()][clickedButton.getCol()].getPiece().toString());

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

    class FlipListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            theView.flipBoard();
        }
    }

}
