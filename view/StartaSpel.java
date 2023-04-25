package view;
import java.awt.*;
import javax.swing.*;

public class StartaSpel extends JFrame {

    public StartaSpel() {
        setTitle("Start Fotbolls quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));


        //ImageIcon icon = new ImageIcon("football.jpg");
        //JLabel label = new JLabel(icon);
        //add(label, BorderLayout.CENTER);

        getContentPane().setBackground(Color.BLACK); // sätter bakgrundsfärgen till svart
        setLocationRelativeTo(null); // centrerar JFrame på skärmen
        setVisible(true);
        pack();
        setLocationRelativeTo(null);

    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StartaSpel();
            }
        });

    }
}
