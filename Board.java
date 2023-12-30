import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class Board extends JFrame {

    public Board() {
        // System.out.println("null");
        super("Talabia Chess");
        setLayout(new BorderLayout());
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jr = new JPanel();
        JLabel title = new JLabel("Talabia Chess");
        jr.add(title);
        title.setFont(new Font("Arial", Font.BOLD, 36)); // Set font size to 36
        add(jr, BorderLayout.NORTH);

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

        JPanel jq = new JPanel();
        JButton save = new JButton("Save");
        JButton menu = new JButton("Return to Menu");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button  pressed");
            }
        });
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to main menu?",
                        "Main menu",
                        JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    new Menu();
                    dispose();
                }
            }
        });
        jq.add(save);
        jq.add(menu);
        add(jq, BorderLayout.SOUTH);

        setVisible(true);

    }

    public static void main(String[] args) {
        new Board();
    }
}
