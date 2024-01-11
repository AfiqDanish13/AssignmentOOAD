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
    JButton flipButton = new JButton("Flip Button");

    JLabel messageInfo = new JLabel("Message: ");
    JLabel messageContent = new JLabel();
    
    private Map<Point, PieceIcon> initialPieceIconPositions = new HashMap<>();
    private boolean isBoardFlipped = false;

    public TalabiaView() {
        super("Talabia Chess");
        setLayout(new BorderLayout());
        menu();
        // setUpSaveExit();
        // initializePieceIconPosition();
        // initializeBoard();
        // setUpMessagePanel();
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
        panelForInfo.add(flipButton);
        add(panelForInfo, BorderLayout.NORTH);
        
    }

    public void initializePieceIconPosition() {
        
        // initial blue PieceIcons
        initialPieceIconPositions.put(new Point(0, 0), new PieceIcon("../ImageComponent/Blue/bluePlus.png"));
        initialPieceIconPositions.put(new Point(0, 1), new PieceIcon("../ImageComponent/Blue/blueHourglass.png"));
        initialPieceIconPositions.put(new Point(0, 2), new PieceIcon("../ImageComponent/Blue/blueTime.png"));
        initialPieceIconPositions.put(new Point(0, 3), new PieceIcon("../ImageComponent/Blue/blueSun.png"));
        initialPieceIconPositions.put(new Point(0, 4), new PieceIcon("../ImageComponent/Blue/blueTime.png"));
        initialPieceIconPositions.put(new Point(0, 5), new PieceIcon("../ImageComponent/Blue/blueHourGlass.png"));
        initialPieceIconPositions.put(new Point(0, 6), new PieceIcon("../ImageComponent/Blue/bluePlus.png"));
        initialPieceIconPositions.put(new Point(1, 0), new PieceIcon("../ImageComponent/Blue/blueArrow.png"));
        initialPieceIconPositions.put(new Point(1, 1), new PieceIcon("../ImageComponent/Blue/blueArrow.png"));
        initialPieceIconPositions.put(new Point(1, 2), new PieceIcon("../ImageComponent/Blue/blueArrow.png"));
        initialPieceIconPositions.put(new Point(1, 3), new PieceIcon("../ImageComponent/Blue/blueArrow.png"));
        initialPieceIconPositions.put(new Point(1, 4), new PieceIcon("../ImageComponent/Blue/blueArrow.png"));
        initialPieceIconPositions.put(new Point(1, 5), new PieceIcon("../ImageComponent/Blue/blueArrow.png"));
        initialPieceIconPositions.put(new Point(1, 6), new PieceIcon("../ImageComponent/Blue/blueArrow.png"));

        // initial yellow PieceIcons
        initialPieceIconPositions.put(new Point(5, 0), new PieceIcon("../ImageComponent/Yellow/yellowPlus.png"));
        initialPieceIconPositions.put(new Point(5, 1), new PieceIcon("../ImageComponent/Yellow/yellowHourglass.png"));
        initialPieceIconPositions.put(new Point(5, 2), new PieceIcon("../ImageComponent/Yellow/yellowTime.png"));
        initialPieceIconPositions.put(new Point(5, 3), new PieceIcon("../ImageComponent/Yellow/yellowSun.png"));
        initialPieceIconPositions.put(new Point(5, 4), new PieceIcon("../ImageComponent/Yellow/yellowTime.png"));
        initialPieceIconPositions.put(new Point(5, 5), new PieceIcon("../ImageComponent/Yellow/yellowHourGlass.png"));
        initialPieceIconPositions.put(new Point(5, 6), new PieceIcon("../ImageComponent/Yellow/yellowPlus.png"));
        initialPieceIconPositions.put(new Point(4, 0), new PieceIcon("../ImageComponent/Yellow/yellowArrow.png"));
        initialPieceIconPositions.put(new Point(4, 1), new PieceIcon("../ImageComponent/Yellow/yellowArrow.png"));
        initialPieceIconPositions.put(new Point(4, 2), new PieceIcon("../ImageComponent/Yellow/yellowArrow.png"));
        initialPieceIconPositions.put(new Point(4, 3), new PieceIcon("../ImageComponent/Yellow/yellowArrow.png"));
        initialPieceIconPositions.put(new Point(4, 4), new PieceIcon("../ImageComponent/Yellow/yellowArrow.png"));
        initialPieceIconPositions.put(new Point(4, 5), new PieceIcon("../ImageComponent/Yellow/yellowArrow.png"));
        initialPieceIconPositions.put(new Point(4, 6), new PieceIcon("../ImageComponent/Yellow/yellowArrow.png"));

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
                    PieceIcon PieceIcon = initialPieceIconPositions.get(new Point(i, j));
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
        // int rows = 6;
        // int columns = 7;
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
            // Flip the row and column indices for each button
            // int flippedRow = rows - 1 - button.getRow();
            // int flippedCol = columns - 1 - button.getCol();
            // button.setRow(flippedRow);
            // button.setCol(flippedCol);

            if (button.getIcon() != null) {
                String iconPath = button.getIconRouting().iconPath;
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
        panelForBoard.removeAll();
        titlePanel.remove(gameName);
        buttonOptionMenu.remove(startButton);
        buttonOptionMenu.remove(loadButton);
        buttonOptionMenu.remove(exitButton);
        menu(); // Call the menu setup again
        revalidate();
        repaint();
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

    public void flipButtonListener(ActionListener flipListener) {
        flipButton.addActionListener(flipListener);
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
    private PieceIcon iconRouting;
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

    public void setIconRouting(PieceIcon iconRouting) {
        this.iconRouting = iconRouting;
    }

    public PieceIcon getIconRouting() {
        return iconRouting;
    }

    public ImageIcon getScaledIcon() {
        return scaledIcon;
    }

    public void insertIcon(PieceIcon pieceIcon) {
        iconRouting = pieceIcon;
        ImageIcon icon = new ImageIcon(pieceIcon.iconPath);
        Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.scaledIcon = new ImageIcon(scaledImage);
        setIcon(scaledIcon);
    }

    public void removeIcon() {
        this.iconRouting = null;
        this.scaledIcon = null;
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

class PieceIcon {
    String iconPath;

    public PieceIcon(String iconPath) {
        this.iconPath = iconPath;
    }
}