import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    Menu() {
        super("Main Menu");
        setLayout(new BorderLayout());
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        // Set Talabia Chess title
        JPanel jp = new JPanel();
        JLabel title = new JLabel("Talabia Chess");
        jp.add(title);
        title.setFont(new Font("Arial", Font.BOLD, 36)); // Set font size to 36
        add(jp, BorderLayout.NORTH);

        // Add menu buttons
        JPanel jq = new JPanel();

        jq.setLayout(new FlowLayout());

        JButton start = new JButton("Start");
        JButton load = new JButton("Load");
        JButton exit = new JButton("Exit");

        // Add action listeners to buttons
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your code for "Start" button action here
                JOptionPane.showMessageDialog(Menu.this, "Start button clicked!");
                new Board();
            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your code for "Load" button action here
                JOptionPane.showMessageDialog(Menu.this, "Load button clicked!");
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your code for "Exit" button action here
                System.exit(0);
            }
        });

        // Add buttons to panel
        jq.add(start);
        jq.add(load);
        jq.add(exit);

        add(jq, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Menu();
    }
}
