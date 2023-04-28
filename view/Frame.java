package view;

import javax.swing.*;
import java.awt.*;

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


      //  JPanel panel = new JPanel();
        // Lägg till en bild i panelen
         //ImageIcon icon = new ImageIcon("football.jpg");
         //JLabel label = new JLabel(icon);
        // panel.add(label);
        //this.getContentPane().setBackground(Color.RED);

        start = new JButton("Starta spelet");
        label = new JLabel("Välkommen till Gissa Fotbollsspelare!");


        this.add(label);
        label.setBounds(300, 0, 280, 40);


        this.add(start);
        start.setBounds(315, 200, 180, 40);




    }





    public static void main(String[] args){
        new Frame();
    }
}
