package view;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JFrame {

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
    private QuizView quizView;

    public StartPanel(Controller controller, QuizView quizView) {
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
        createLabels();
        createTextField();
        createButtons();
        createTextArea (highScoreList);
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
    private void createLabels(){
        // Lägg till en bild i panelen
        ImageIcon imageIcon = new ImageIcon("images/bluestart.jpg");
        Image image = imageIcon.getImage();
        Image scaled = image.getScaledInstance(800, 800,Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaled);
        backgroundLabel = new JLabel(imageIcon);
        backgroundLabel.setBounds(0, 0, width, height);
        playerNameLabel = new JLabel("Player Name: ");
        playerNameLabel.setFont(font);
        playerNameLabel.setForeground(Color.WHITE);
        playerNameLabel.setBounds(50, height/2-100, 200, 50);
        // Skapa en label och en knapp
        label = new JLabel("Välkommen till Gissa Fotbollsspelare!");
        label.setFont(font);
        label.setForeground(Color.WHITE);
        label.setBounds(100, 50, width-100, 50);
    }
    private void createTextField(){
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
                quizView.displayQuestions(StartPanel.this);
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
        scrollPane.setBounds(10,  height/2-50, 200,  height/2+30);
    }
    private void createButtons(){
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
                quizView.displayQuestions(StartPanel.this);
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
