package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen {
    private JFrame frame;
    private Controller controller;
    private JLabel label;
    private JList<Object> highScoreList;
    private JButton play;

    public StartScreen(Controller controller) {
        this.controller = controller;
        createFrame();
    }

    private void createFrame() {
        frame = new JFrame("StartScreen");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null);

        ImageIcon imageIcon = new ImageIcon("files/soccer.jpg");
        Image image = imageIcon.getImage();

        Image scaled = image.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaled);

        label = new JLabel(imageIcon);
        label.setBounds(0,0, 600,600);
        frame.add(label);

        JLabel scoreLbl = new JLabel("Top 10 High scores");
        scoreLbl.setBounds(50,0,300,100);
        highScoreList = new JList<>();
        createHighScoreList(controller.getList());
        highScoreList.setBounds(50, 60, 135, 200);
        highScoreList.setBackground(Color.WHITE);
        highScoreList.setOpaque(true);
        label.add((scoreLbl));
        label.add(highScoreList);

        play = new JButton("Start game");
        play.setBounds(200,300,200,30);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.play();
                frame.setVisible(false);
            }
        });
        label.add(play);

        frame.setVisible(true);
    }

    public void createHighScoreList(String[] scores){highScoreList.setListData(scores);}

}
