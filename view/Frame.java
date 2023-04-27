package view;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{

    JButton button;
    JLabel label;
    public Frame(){
        this.setTitle("Gissa Fotbollsspelare");
        this.setSize(800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(new FlowLayout());

        button = new JButton("Starta spelet");
        label = new JLabel("Hej");

        this.add(label);
        this.add(button);
    }





    public static void main(String[] args){
        new Frame();
    }
}
