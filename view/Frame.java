package view;
import controller.Controller;
import controller.Controller2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {

    private JButton start, showlist;
    private JScrollPane scrollPane;
    private boolean show = false;
    private JLabel label, playerNameLabel;
    private JTextField playerNameJTextField;
    private Controller2 controller;
    private JPanel startPanel;
    private int width = 800;
    private int height = 800;
    private Font font = new Font("Arial", Font.BOLD, 24);
    private JTextArea scoresJTextArea;
    private QuizView quizView;

    public Frame(Controller2 controller, QuizView quizView) {
        this.controller = controller;
        this.quizView = quizView;
        this.setTitle("Gissa Fotbollsspelare");
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

    }

    public void addStartPanel(String[] highScoreList){
        startPanel = new JPanel();
        startPanel.setLayout(null);
        // Lägg till en bild i panelen
        ImageIcon icon = new ImageIcon("images/start.jpg");
        JLabel backgroundLabel = new JLabel(icon);
        backgroundLabel.setBounds(0, 0, width, height);

        playerNameLabel = new JLabel("Player Name: ");
        playerNameLabel.setFont(font);
        playerNameLabel.setForeground(Color.WHITE);
        playerNameLabel.setBounds(50, height/2-100, 200, 50);

        playerNameJTextField = new JTextField("");
        playerNameJTextField.setFont(font);
        playerNameJTextField.setBackground(Color.LIGHT_GRAY);
        playerNameJTextField.setForeground(Color.WHITE);
        playerNameJTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        playerNameJTextField.setBounds(220, height/2-90, 230, 30);

        // Skapa en label och en knapp
        label = new JLabel("Välkommen till Gissa Fotbollsspelare!");
        label.setFont(font);
        label.setForeground(Color.WHITE);
        label.setBounds(100, 50, width-100, 50);

        //this.getContentPane().setBackground(Color.RED);
        start = new JButton("  Starta spelet  ");
        start.setFont(font);
        start.setBackground(Color.LIGHT_GRAY);
        start.setForeground(Color.WHITE);
        start.setFocusPainted(false);
        start.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        start.setBounds(220, height/2-20, 180, 40);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quizView.displayQuestions(Frame.this);
            }
        });

        showlist = new JButton("  show list  ");
        showlist.setFont(font);
        showlist.setBackground(Color.LIGHT_GRAY);
        showlist.setForeground(Color.WHITE);
        showlist.setFocusPainted(false);
        showlist.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        showlist.setBounds(220, height/2+40, 180, 40);

        showlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show = !show;
                scrollPane.setVisible(show);

            }
        });
        String scores = "";
        for(String s : highScoreList){
            scores += s+"\n";
        }
        scoresJTextArea = new JTextArea("Top 10 Scores:\n"+scores);
        scoresJTextArea.setFont(font);
        scoresJTextArea.setEditable(false);
        scoresJTextArea.setLineWrap(true);
        scoresJTextArea.setWrapStyleWord(true);
        scoresJTextArea.setBackground(Color.LIGHT_GRAY);
        scoresJTextArea.setForeground(Color.WHITE);
//        scoresJTextArea.setBounds(10,  height/2, 200,  height/2);
        scrollPane = new JScrollPane(scoresJTextArea);
        scrollPane.setVisible(false);
        scrollPane.setBounds(10,  height/2-50, 200,  height/2+30);
        startPanel.add(playerNameLabel);
        startPanel.add(playerNameJTextField);
        startPanel.add(start);
        startPanel.add(label);
        startPanel.add(scrollPane);
        startPanel.add(showlist);
        startPanel.add(backgroundLabel);
        startPanel.setBounds(0, 0, width, height);
        this.add(startPanel);
        this.setVisible(true);
    }

    public void addQuestionsPanel(JPanel questionPanel){
        this.remove(startPanel);
        questionPanel.setBounds(0, 0, width, height);
        this.add(questionPanel);
        repaint();
    }

    public String getPlayerName(){
        return playerNameJTextField.getText().trim();
    }


}
