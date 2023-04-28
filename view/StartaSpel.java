package view;

import java.awt.*;
import javax.swing.*;

public class StartaSpel extends JFrame {

    public StartaSpel() {
        setTitle("My GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));


        // Skapa en panel för att placera komponenter
        JPanel panel = new JPanel();
        panel.setLayout(new OverlayLayout(panel)); // OverlayLayout för att lägga knappen över bilden

        //Lägg till en bild i panelen
        ImageIcon icon = new ImageIcon("football.jpg");
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(800, 600));

        panel.add(label);

        // Skapa en panel för knappen och placera den i mitten av bilden
        JPanel knappPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton knapp = new JButton("Starta spel");
        knapp.setBackground(Color.WHITE); // sätt bakgrundsfärgen till blå
        knappPanel.add(knapp, gbc);
        panel.add(knappPanel); // lägg till knapppanelen i panelen med bilden
        getContentPane().add(panel); // lägg till panelen i JFrame

        setLocationRelativeTo(null); // centrerar JFrame på skärmen
        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        // SwingUtilities.invokeLater() används för att köra skapandet av fönstret på huvudtråden i Swing-komponenten.
        // Detta garanterar att Swing-komponenten skapas och visas på ett säkert sätt, utan risk för trådkonflikter.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StartaSpel();
            }
        });
    }
}