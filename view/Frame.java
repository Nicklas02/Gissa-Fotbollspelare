package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame{

    JButton start;
    JLabel label;
    public Frame(){
        this.setTitle("Gissa Fotbollsspelare");
        this.setSize(800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);


       JPanel panel = new JPanel();
        // Lägg till en bild i panelen
         ImageIcon icon = new ImageIcon("football.jpg");
         JLabel label = new JLabel();
         label.setPreferredSize(new Dimension(800, 500));
        panel.add(label);

        //this.getContentPane().setBackground(Color.RED);
        start = new JButton("Starta spelet");
        start.setFont(new Font("Arial", Font.PLAIN, 18));
        start.setBackground(Color.ORANGE);
        start.setForeground(Color.WHITE);
        start.setFocusPainted(false);
        start.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        start.setBounds(320, 200, 160, 40);
        panel.add(start);

        // Skapa en label och en knapp
        label = new JLabel("Välkommen till Gissa Fotbollsspelare!");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.BLACK);
        label.setBounds(180, 100, 500, 50);
        panel.add(label);

       this.add(panel);
        panel.setBounds(0,0,100,100);

        this.add(label);
        label.setBounds(230, 0, 480, 40);

        this.add(start);
        start.setBounds(315, 200, 180, 40);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              new QuizView(new Controller("files/questions.txt"));
            }
        });

    }
}
