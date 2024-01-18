import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;

public class TalabiaView extends JFrame {

    // panel and button used on menu
    JLabel gameName = new JLabel("Talabia Chess");
    JPanel titlePanel = new JPanel();
    JPanel buttonOptionMenu = new JPanel();
    JButton startButton = new JButton("Start");
    JButton loadButton = new JButton("Load");
    JButton exitButton = new JButton("Exit");
    
    // panel used when the game started
    JPanel panelForBoard = new JPanel();
    JPanel panelForInfo = new JPanel();
    JPanel panelForMessage = new JPanel();
    JButton saveButton = new JButton("Save");
    JButton exitToMenu = new JButton("Exit to menu");

    JLabel messageInfo = new JLabel("Message: ");
    JLabel messageContent = new JLabel("Turns: Yellow Player     Move numbers: 0");
    
    private Map<Point, String> initialPieceIconPositions = new HashMap<>();
    public static boolean isBoardFlipped = false;

    public TalabiaView() {
        super("Talabia Chess");
        setLayout(new BorderLayout());
        menu();
        setSize(700,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void menu() {
        gameName.setFont(new Font("Arial", Font.BOLD, 36));
        titlePanel.add(gameName);
        buttonOptionMenu.add(startButton);
        buttonOptionMenu.add(loadButton);
        buttonOptionMenu.add(exitButton);
        add(titlePanel, BorderLayout.NORTH);
        add(buttonOptionMenu, BorderLayout.CENTER);
    }

    public void setUpSaveExit() {

        panelForInfo.add(saveButton);
        panelForInfo.add(exitToMenu);
        // panelForInfo.add(flipButton);
        add(panelForInfo, BorderLayout.NORTH);
        
    }

    public void initializePieceIconPosition() {
        
        // initial blue PieceIcons
        initialPieceIconPositions.put(new Point(0, 0), "../ImageComponent/Blue/bluePlus.png");
        initialPieceIconPositions.put(new Point(0, 1), "../ImageComponent/Blue/blueHourglass.png");
        initialPieceIconPositions.put(new Point(0, 2), "../ImageComponent/Blue/blueTime.png");
        initialPieceIconPositions.put(new Point(0, 3), "../ImageComponent/Blue/blueSun.png");
        initialPieceIconPositions.put(new Point(0, 4), "../ImageComponent/Blue/blueTime.png");
        initialPieceIconPositions.put(new Point(0, 5), "../ImageComponent/Blue/blueHourGlass.png");
        initialPieceIconPositions.put(new Point(0, 6), "../ImageComponent/Blue/bluePlus.png");
        initialPieceIconPositions.put(new Point(1, 0), "../ImageComponent/Blue/blueArrow.png");
        initialPieceIconPositions.put(new Point(1, 1), "../ImageComponent/Blue/blueArrow.png");
        initialPieceIconPositions.put(new Point(1, 2), "../ImageComponent/Blue/blueArrow.png");
        initialPieceIconPositions.put(new Point(1, 3), "../ImageComponent/Blue/blueArrow.png");
        initialPieceIconPositions.put(new Point(1, 4), "../ImageComponent/Blue/blueArrow.png");
        initialPieceIconPositions.put(new Point(1, 5), "../ImageComponent/Blue/blueArrow.png");
        initialPieceIconPositions.put(new Point(1, 6), "../ImageComponent/Blue/blueArrow.png");

        // initial yellow PieceIcons
        initialPieceIconPositions.put(new Point(5, 0), "../ImageComponent/Yellow/yellowPlus.png");
        initialPieceIconPositions.put(new Point(5, 1), "../ImageComponent/Yellow/yellowHourglass.png");
        initialPieceIconPositions.put(new Point(5, 2), "../ImageComponent/Yellow/yellowTime.png");
        initialPieceIconPositions.put(new Point(5, 3), "../ImageComponent/Yellow/yellowSun.png");
        initialPieceIconPositions.put(new Point(5, 4), "../ImageComponent/Yellow/yellowTime.png");
        initialPieceIconPositions.put(new Point(5, 5), "../ImageComponent/Yellow/yellowHourGlass.png");
        initialPieceIconPositions.put(new Point(5, 6), "../ImageComponent/Yellow/yellowPlus.png");
        initialPieceIconPositions.put(new Point(4, 0), "../ImageComponent/Yellow/yellowArrow.png");
        initialPieceIconPositions.put(new Point(4, 1), "../ImageComponent/Yellow/yellowArrow.png");
        initialPieceIconPositions.put(new Point(4, 2), "../ImageComponent/Yellow/yellowArrow.png");
        initialPieceIconPositions.put(new Point(4, 3), "../ImageComponent/Yellow/yellowArrow.png");
        initialPieceIconPositions.put(new Point(4, 4), "../ImageComponent/Yellow/yellowArrow.png");
        initialPieceIconPositions.put(new Point(4, 5), "../ImageComponent/Yellow/yellowArrow.png");
        initialPieceIconPositions.put(new Point(4, 6), "../ImageComponent/Yellow/yellowArrow.png");

    }

    public void initializeBoard() {

        int rows = 6;
        int columns = 7;
        panelForBoard.setLayout(new GridLayout(rows, columns));
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                ChessButton square = new ChessButton(i, j);
                square.setBackground(Color.WHITE);
                square.setPreferredSize(new Dimension(50, 50));
                if(i == 0 || i == 1 || i == 4 || i == 5) {
                    String PieceIcon = initialPieceIconPositions.get(new Point(i, j));
                    if (PieceIcon != null) {
                        square.insertIcon(PieceIcon);
                        
                    }
                }
                
                panelForBoard.add(square);
            }
        }
        add(panelForBoard, BorderLayout.CENTER);
    }

    public void flipBoard() {
        ArrayList<ChessButton> buttons = new ArrayList<>();
    
        // Remove all buttons and store them in a list
        for (Component comp : panelForBoard.getComponents()) {
            if (comp instanceof ChessButton) {
                buttons.add((ChessButton) comp);
            }
        }
        panelForBoard.removeAll(); // Remove all components from the panel
    
        // Flip the order of the buttons in the list
        Collections.reverse(buttons);
    
        // Update the grid and add the buttons back in the flipped order
        for (ChessButton button : buttons) {

            if (button.getIcon() != null) {
                String iconPath = button.getPieceRoute();
                if (iconPath.contains("Arrow")) {
                    button.rotateIcon();
                }
            }
    
            // Re-add the button to the panel
            panelForBoard.add(button);
        }

        isBoardFlipped = !isBoardFlipped;
    
        // Refresh the panel
        panelForBoard.revalidate();
        panelForBoard.repaint();
    }

    public void setUpMessagePanel() {

        panelForMessage.add(messageInfo);
        panelForMessage.add(messageContent);
        add(panelForMessage, BorderLayout.SOUTH);

    }

    public JLabel getMessageContent() {
        return messageContent;
    }

    public void resetView() {
        remove(panelForBoard);
        remove(panelForInfo);
        remove(panelForMessage);
        panelForInfo.removeAll();
        panelForMessage.removeAll();
        messageContent.setText("Turns: Yellow Player     Move numbers: 0");
        panelForBoard.removeAll();
        titlePanel.remove(gameName);
        buttonOptionMenu.remove(startButton);
        buttonOptionMenu.remove(loadButton);
        buttonOptionMenu.remove(exitButton);
        isBoardFlipped = false;
        menu(); // Call the menu setup again
        revalidate();
        repaint();
    }

    public void resetBoardView() {
        panelForBoard.removeAll();
        initializeBoard();
        messageContent.setText("Turns: Yellow Player     Move numbers: 0");
        isBoardFlipped = false;
    }

    public boolean getIsBoardFlipped() {
        return isBoardFlipped;
    }


    public void addButtonListener(ActionListener listenForButton) {
        for (Component component : panelForBoard.getComponents()) {
            if (component instanceof ChessButton) {
                ((ChessButton) component).addActionListener(listenForButton);
            }
        }
    }

    public void saveButtonListener(ActionListener saveListener) {
        saveButton.addActionListener(saveListener);
    }

    public void exitToMenuButtonListener(ActionListener exitInGameListener) {
        exitToMenu.addActionListener(exitInGameListener);
    }

    public void startButtonListener(ActionListener startListener) {
        startButton.addActionListener(startListener);
    }

    public void loadButtonListener(ActionListener loadListener) {
        loadButton.addActionListener(loadListener);
    }

    public void exitButtonListener(ActionListener exitListener) {
        exitButton.addActionListener(exitListener);
    }
}

class ChessButton extends JButton {
    private int row;
    private int col;
    String pieceRoute;
    private ImageIcon scaledIcon;

    public ChessButton(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setPieceRoute(String pieceRouting) {
        this.pieceRoute = pieceRouting;
    }

    public String getPieceRoute() {
        return pieceRoute;
    }

    public ImageIcon getScaledIcon() {
        return scaledIcon;
    }

    public void insertIcon(String pieceRoute) {
        this.pieceRoute = pieceRoute;
        ImageIcon icon = new ImageIcon(pieceRoute);
        Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.scaledIcon = new ImageIcon(scaledImage);
        setIcon(scaledIcon);
    }

    public void insertExistedIcon(String pieceRoute, ImageIcon scaledImage) {
        this.pieceRoute = pieceRoute;
        setIcon(scaledImage);
        if(TalabiaView.isBoardFlipped == true) rotateIcon();
    }

    public void removeIcon() {
        this.scaledIcon = null;
        this.pieceRoute = null;
        setIcon(null);
    }

    public void rotateIcon() {
        if (scaledIcon != null) {
            Image rotatedImage = new BufferedImage(scaledIcon.getIconWidth(), scaledIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D) rotatedImage.getGraphics();
            g2d.rotate(Math.toRadians(180), scaledIcon.getIconWidth() / 2.0, scaledIcon.getIconHeight() / 2.0);
            g2d.drawImage(scaledIcon.getImage(), 0, 0, null);
            g2d.dispose();

            scaledIcon = new ImageIcon(rotatedImage);
            setIcon(scaledIcon);
        }
    }
}

class CustomConfirmDialog extends JDialog {
    private int confirmed = JOptionPane.CLOSED_OPTION;

    public CustomConfirmDialog(Frame owner, String title, String message) {
        super(owner, title, true);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // Disable the close button
        setLayout(new BorderLayout());

        // Add a message label
        JLabel messageLabel = new JLabel(message);
        add(messageLabel, BorderLayout.CENTER);

        // Add Yes and No buttons
        JPanel buttonPanel = new JPanel();
        JButton yesButton = new JButton("Yes");
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmed = JOptionPane.YES_OPTION;
                dispose();
            }
        });
        buttonPanel.add(yesButton);

        JButton noButton = new JButton("No");
        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmed = JOptionPane.NO_OPTION;
                dispose();
            }
        });
        buttonPanel.add(noButton);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(owner);
    }

    public int getConfirmed() {
        return confirmed;
    }

    public static int showCustomConfirmDialog(Frame owner, String title, String message) {
        CustomConfirmDialog dialog = new CustomConfirmDialog(owner, title, message);
        dialog.setVisible(true);
        return dialog.getConfirmed();
    }
}