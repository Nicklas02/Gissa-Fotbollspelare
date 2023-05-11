package view;
import controller.Controller2;
import model.Difficulty;
import model.GameType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {

    private JButton start, showlist;
    private JScrollPane scrollPane;
    private boolean show = false;
    private JLabel label, playerNameLabel, backgroundLabel;
    private JTextField playerNameJTextField;
    private JPanel startPanel;
    private int width = 800;
    private int height = 800;
    private Font font = new Font("Arial", Font.BOLD, 24);
    private JTextArea scoresJTextArea;
    private JRadioButton[] difficultyOptions;

    private ButtonGroup difficultyOptionsGroup;
    private JRadioButton[] gameTypeOptions;
    private ButtonGroup gameTypeOptionsGroup;
    private Controller2 controller;

    public Frame(Controller2 controller) {
        this.controller = controller;
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
        createLabels();
        createTextField();
        createButtons();
        createTextArea (highScoreList);
        createRadioButtons();
        startPanel.add(playerNameLabel);
        startPanel.add(playerNameJTextField);
        startPanel.add(start);
        startPanel.add(label);
        startPanel.add(scrollPane);
        startPanel.add(showlist);
        for(JRadioButton option : difficultyOptions){
            startPanel.add(option);
        }
        for(JRadioButton option : gameTypeOptions){
            startPanel.add(option);
        }
        startPanel.add(backgroundLabel);
        startPanel.setBounds(0, 0, width, height);
        this.add(startPanel);
        this.setVisible(true);
    }


    private void createLabels(){
        // Lägg till en bild i panelen
        ImageIcon imageIcon = new ImageIcon("images/start.jpg");
        Image image = imageIcon.getImage();
        Image scaled = image.getScaledInstance(800, 800,Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaled);
        backgroundLabel = new JLabel(imageIcon);
        backgroundLabel.setBounds(0, 0, width, height);
        playerNameLabel = new JLabel("Player Name: ");
        playerNameLabel.setFont(font);
        playerNameLabel.setForeground(Color.WHITE);
        playerNameLabel.setBounds(50, height/2-125, 200, 50);
        // Skapa en label och en knapp
        label = new JLabel("Välkommen till Gissa Fotbollsspelare!");
        label.setFont(font);
        label.setForeground(Color.WHITE);
        label.setBounds(170, 50, width-100, 50);
    }
    private void createTextField(){
        playerNameJTextField = new JTextField("");
        playerNameJTextField.setFont(font);
        playerNameJTextField.setBackground(Color.LIGHT_GRAY);
        playerNameJTextField.setForeground(Color.WHITE);
        playerNameJTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        playerNameJTextField.setBounds(220, height/2-115, 230, 30);
    }
    private void createTextArea(String[] highScoreList){
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
        scrollPane.setBounds(10,  height/2-70, 200,  height/2+30);
    }
    private void createButtons(){
        //this.getContentPane().setBackground(Color.RED);
        start = new JButton("  Starta spelet  ");
        start.setFont(font);
        start.setBackground(Color.LIGHT_GRAY);
        start.setForeground(Color.WHITE);
        start.setFocusPainted(false);
        start.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        start.setBounds(220, height/2-1, 180, 40);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.displayQuestions();
            }
        });
        showlist = new JButton("  show list  ");
        showlist.setFont(font);
        showlist.setBackground(Color.LIGHT_GRAY);
        showlist.setForeground(Color.WHITE);
        showlist.setFocusPainted(false);
        showlist.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        showlist.setBounds(220, height/2-70, 180, 40);
        showlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show = !show;
                scrollPane.setVisible(show);
            }
        });
    }
    public void addQuestionsPanel(JPanel questionPanel){
        this.remove(startPanel);
        questionPanel.setBounds(0, 0, width, height);
        this.add(questionPanel);
        repaint();
    }

    private void createRadioButtons(){
        difficultyOptions = new JRadioButton[2];
        difficultyOptionsGroup = new ButtonGroup();
        difficultyOptions[0] = new JRadioButton("Normal");
        difficultyOptions[0].setBounds(640, height/2+1, 120, 30);
        difficultyOptions[0].setFont(font);
        difficultyOptions[0].setSelected(true);
        difficultyOptions[0].setBackground(Color.LIGHT_GRAY);
        difficultyOptions[0].setForeground(Color.WHITE);
        difficultyOptionsGroup.add(difficultyOptions[0]);

        difficultyOptions[1] = new JRadioButton("Hard");
        difficultyOptions[1].setBounds(640, height/2+50, 100, 30);
        difficultyOptions[1].setFont(font);
        difficultyOptions[1].setBackground(Color.LIGHT_GRAY);
        difficultyOptions[1].setForeground(Color.WHITE);
        difficultyOptionsGroup.add(difficultyOptions[1]);
        /////////////////////////////////////////////////
        gameTypeOptions = new JRadioButton[5];
        gameTypeOptionsGroup = new ButtonGroup();
        gameTypeOptions[0] = new JRadioButton("PremierLeague");
        gameTypeOptions[0].setBounds(420, height/2+1, 200, 30);
        gameTypeOptions[0].setFont(font);
        gameTypeOptions[0].setSelected(true);
        gameTypeOptions[0].setBackground(Color.LIGHT_GRAY);
        gameTypeOptions[0].setForeground(Color.WHITE);
        gameTypeOptionsGroup.add(gameTypeOptions[0]);

        gameTypeOptions[1] = new JRadioButton("LaLiga");
        gameTypeOptions[1].setBounds(420, height/2+170, 120, 30);
        gameTypeOptions[1].setFont(font);
        gameTypeOptions[1].setBackground(Color.LIGHT_GRAY);
        gameTypeOptions[1].setForeground(Color.WHITE);
        gameTypeOptionsGroup.add(gameTypeOptions[1]);

        gameTypeOptions[2] = new JRadioButton("Bundesliga");
        gameTypeOptions[2].setBounds(420, height/2+50, 170, 30);
        gameTypeOptions[2].setFont(font);
        gameTypeOptions[2].setBackground(Color.LIGHT_GRAY);
        gameTypeOptions[2].setForeground(Color.WHITE);
        gameTypeOptionsGroup.add(gameTypeOptions[2]);

        gameTypeOptions[3] = new JRadioButton("Ligue1");
        gameTypeOptions[3].setBounds(420, height/2+90, 120, 30);
        gameTypeOptions[3].setFont(font);
        gameTypeOptions[3].setBackground(Color.LIGHT_GRAY);
        gameTypeOptions[3].setForeground(Color.WHITE);
        gameTypeOptionsGroup.add(gameTypeOptions[3]);

        gameTypeOptions[4] = new JRadioButton("SerieA");
        gameTypeOptions[4].setBounds(420, height/2+130, 120, 30);
        gameTypeOptions[4].setFont(font);
        gameTypeOptions[4].setBackground(Color.LIGHT_GRAY);
        gameTypeOptions[4].setForeground(Color.WHITE);
        gameTypeOptionsGroup.add(gameTypeOptions[4]);
    }
    public GameType getGameType(){
        if(gameTypeOptions[0].isSelected()){
            return GameType.PremierLeague;
        }
        else if(gameTypeOptions[1].isSelected()){
            return GameType.LaLiga;
        }
        else if(gameTypeOptions[2].isSelected()){
            return GameType.Bundesliga;
        }
        else if(gameTypeOptions[3].isSelected()){
            return GameType.Ligue1;
        }
        return GameType.SerieA;
    }


    public Difficulty getDifficulty(){
        if(difficultyOptions[0].isSelected()){
            return Difficulty.Normal;
        }
        return Difficulty.Hard;
    }

    public String getPlayerName(){
        return playerNameJTextField.getText().trim();
    }


}
