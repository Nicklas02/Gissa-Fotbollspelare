package view;

import controller.Controller2;
import model.Difficulty;
import model.GameType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
    private JComboBox<String> difficultyOptions;
    private JComboBox<String> gameTypeOptions;
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

    public void addStartPanel(String[] highScoreList) {
        startPanel = new JPanel();
        startPanel.setLayout(null);
        createLabels();
        createTextField();
        createButtons();
        createTextArea(highScoreList);
        createRadioButtons();
        //   createGameTypePanel();
        startPanel.add(playerNameLabel);
        startPanel.add(playerNameJTextField);
        startPanel.add(start);
        startPanel.add(label);
        startPanel.add(scrollPane);
        startPanel.add(showlist);
        startPanel.add(difficultyOptions);
        startPanel.add(gameTypeOptions);
        startPanel.add(backgroundLabel);
        startPanel.setBounds(0, 0, width, height);
        this.add(startPanel);
        this.setVisible(true);
    }

    private void createLabels() {
        // Lägg till en bild i panelen
        ImageIcon imageIcon = new ImageIcon("images/start.jpg");
        Image image = imageIcon.getImage();
        Image scaled = image.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaled);
        backgroundLabel = new JLabel(imageIcon);
        backgroundLabel.setBounds(0, 0, width, height);
        playerNameLabel = new JLabel("Player Name: ");
        playerNameLabel.setFont(font);
        playerNameLabel.setForeground(Color.WHITE);
        playerNameLabel.setBounds(170, height / 2 - 125, 200, 50);
        // Skapa en label och en knapp
        label = new JLabel("Välkommen till Gissa Fotbollsspelare!");
        label.setForeground(Color.WHITE);
        label.setBounds(170, 50, width - 100, 50);
        Font labelFont = new Font(Font.SANS_SERIF,Font.BOLD, 26);
        label.setFont(labelFont);

    }

    private void createTextField() {
        playerNameJTextField = new JTextField("");
        playerNameJTextField.setFont(font);
        playerNameJTextField.setBackground(Color.LIGHT_GRAY);
        playerNameJTextField.setForeground(Color.WHITE);
        playerNameJTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        playerNameJTextField.setBounds(330, height / 2 - 115, 230, 30);

        /*
        Denna kod förbjuder användaren att skriva siffror i med sitt namn. 
        playerNameJTextField.addKeyListener(new KeyAdapter() {

            @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isLetter(c)) {
                e.consume(); // Ignorera inmatningen om det inte är en bokstav
            }
        }
    });
    */

    }

    private void createTextArea(String[] highScoreList) {
        String scores = "";
        for (String s : highScoreList) {
            scores += s + "\n";
        }
        scoresJTextArea = new JTextArea("Top 10 Scores:\n" + scores);
        scoresJTextArea.setFont(font);
        scoresJTextArea.setEditable(false);
        scoresJTextArea.setLineWrap(true);
        scoresJTextArea.setWrapStyleWord(true);
        scoresJTextArea.setBackground(Color.LIGHT_GRAY);
        scoresJTextArea.setForeground(Color.WHITE);
//        scoresJTextArea.setBounds(10,  height/2, 200,  height/2);
        scrollPane = new JScrollPane(scoresJTextArea);
        scrollPane.setVisible(false);
        scrollPane.setBounds(10, height / 2 - 70, 200, height / 2 + 30);
    }

    private void createButtons() {
        //this.getContentPane().setBackground(Color.RED);
        start = new JButton("  Starta spelet  ");
        start.setFont(font);
        start.setBackground(Color.LIGHT_GRAY);
        start.setForeground(Color.WHITE);
        start.setFocusPainted(false);
        start.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        start.setBounds(330, 380, 180, 40);
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
        showlist.setBounds(330, height / 2 - 70, 180, 40);
        showlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show = !show;
                scrollPane.setVisible(show);
            }
        });
    }

    public void addQuestionsPanel(JPanel questionPanel) {
        this.remove(startPanel);
        questionPanel.setBounds(0, 0, width, height);
        this.add(questionPanel);
        repaint();
    }

    private void createRadioButtons() {
        String[] diffOptions = {"Normal", "Hard"};
        difficultyOptions = new JComboBox<>(diffOptions);
        difficultyOptions.setBounds(550, 430, 120, 30);
        difficultyOptions.setFont(font);
        difficultyOptions.setSelectedIndex(0);
        difficultyOptions.setBackground(Color.LIGHT_GRAY);
        difficultyOptions.setForeground(Color.WHITE);
        /////////////////////////////////////////////////
        String[] gameOptions = {"PremierLeague", "LaLiga", "Bundesliga", "Ligue1", "SerieA"};
        gameTypeOptions = new JComboBox<>(gameOptions);
        gameTypeOptions.setBounds(333, 430, 200, 30);
        gameTypeOptions.setFont(font);
        gameTypeOptions.setSelectedIndex(0);
        gameTypeOptions.setBackground(Color.LIGHT_GRAY);
        gameTypeOptions.setForeground(Color.WHITE);

    }

    public GameType getGameType() {
        String selectedGameType = (String) gameTypeOptions.getSelectedItem();
        switch (selectedGameType) {
            case "PremierLeague":
                return GameType.PremierLeague;
            case "LaLiga":
                return GameType.LaLiga;
            case "Bundesliga":
                return GameType.Bundesliga;
            case "Ligue1":
                return GameType.Ligue1;
            case "SerieA":
                return GameType.SerieA;
            default:
                return null;
        }
    }

    public Difficulty getDifficulty() {
        String selectedDifficulty = (String) difficultyOptions.getSelectedItem();
        if (selectedDifficulty.equals("Normal")) {
            return Difficulty.Normal;
        } else {
            return Difficulty.Hard;
        }
    }

    public String getPlayerName() {
        return playerNameJTextField.getText().trim();
    }

}
