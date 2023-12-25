import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class Board extends JFrame {

    public Board() {
        // System.out.println("null");
        super("Talabia Chess");
        setLayout(new BorderLayout());
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JPanel jp = new JPanel(new GridLayout(6, 7));
        JButton[] btn = new JButton[42];
        for (int i = 0; i < 42; i++) {
            final int buttonIndex = 0;
            btn[i] = new JButton();
            jp.add(btn[i]);
            btn[i].setBackground(Color.WHITE);
            
            btn[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Button  pressed");
                }
            });
        }
        add(jp);

    }

    public static void main(String[] args) {
        new Board();
    }
}
