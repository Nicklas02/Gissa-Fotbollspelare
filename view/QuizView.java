package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import controller.Controller;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class QuizView extends JPanel{
    private int currentQuestionNum = 1;
    private final int totalQuestionNum = 10;
    private JTextArea playerNameJTextField;
    private Controller controller;
    private JLabel questionLabel;
    private JLabel questionNumber;
    JLabel rightOrWrong;
    private String playerName;
    private JButton[] optionButtons;
    private int selectedOptionIndex = -1;

    private JButton nextButton;
    private String[] questions;
    private String[][] alt;
    private String[] answers;
    private int score = 0;
    private Timer timer;
    private JProgressBar progressBar;



    public void FillQuestions(String[] questions, String[][] alt, String[] answers) {
        this.questions = questions;
        this.alt = alt;
        this.answers = answers;
    }

    public QuizView(Controller controller) {
        this.controller = controller;
        this.setLayout(null);
        ImageIcon imageIcon = new ImageIcon("images/purplequiz.jpg");
        Image image = imageIcon.getImage();
        Image scaled = image.getScaledInstance(800, 800,Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaled);
        JLabel background = new JLabel(imageIcon);
        int height = 800;
        int width = 800;
        background.setBounds(0, 0, width, height);
        playerNameJTextField = new JTextArea();
        Font font = new Font("Ariel", Font.BOLD, 20);
        playerNameJTextField.setFont(font);
        playerNameJTextField.setEditable(false);
        playerNameJTextField.setLineWrap(true);
        playerNameJTextField.setWrapStyleWord(true);
        playerNameJTextField.setBackground(Color.BLACK);
        playerNameJTextField.setForeground(Color.WHITE);
        playerNameJTextField.setBounds(0, 0, 200, 50);
        this.add(playerNameJTextField);

        JLabel titleLabel = new JLabel("Gissa Fotbollsspelare");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(250, 15, 500, 40);
        this.add(titleLabel);
        questionNumber = new JLabel();
        questionNumber.setBounds(width - 130, 110, 200, 40);
        questionNumber.setFont(font);
        //colour for fonts
        questionNumber.setForeground(Color.WHITE);
        this.add(questionNumber);

        rightOrWrong = new JLabel();
        rightOrWrong.setBounds(50, 500, 300, 100);
        rightOrWrong.setForeground(Color.RED);
        rightOrWrong.setFont(font);
        this.add(rightOrWrong);

        questionLabel = new JLabel();
        questionLabel.setBounds(200, 140, width, 50);
        questionLabel.setFont(font);
        questionLabel.setForeground(Color.WHITE);
        this.add(questionLabel);

        optionButtons = new JButton[4];
        optionButtons[0] = new JButton();
        optionButtons[0].setBounds(100, 200, 270, 30);
        Font font2 = new Font("Ariel", Font.PLAIN, 15);
        optionButtons[0].setFont(font2);
        optionButtons[0].setForeground(Color.BLACK);
        optionButtons[0].addActionListener(new OptionButtonListener(0));
        this.add(optionButtons[0]);

        optionButtons[1] = new JButton();
        optionButtons[1].setBounds(400, 200, 270, 30);
        optionButtons[1].setFont(font2);
        optionButtons[1].setForeground(Color.BLACK);
        optionButtons[1].addActionListener(new OptionButtonListener(1));
        this.add(optionButtons[1]);

        optionButtons[2] = new JButton();
        optionButtons[2].setBounds(100, 300, 270, 30);
        optionButtons[2].setFont(font2);
        optionButtons[2].setForeground(Color.BLACK);
        optionButtons[2].addActionListener(new OptionButtonListener(2));

        this.add(optionButtons[2]);

        optionButtons[3] = new JButton();
        optionButtons[3].setBounds(400, 300, 270, 30);
        optionButtons[3].setFont(font2);
        optionButtons[3].setForeground(Color.BLACK);
        optionButtons[3].addActionListener(new OptionButtonListener(3));
        this.add(optionButtons[3]);

        progressBar = new JProgressBar(0, 15);
        progressBar.setBounds(250, 50, 300, 100);
        progressBar.setStringPainted(false);

        this.add(progressBar);

        timer = new Timer(1500, new ActionListener() {
            int count = 15;
            @Override
            public void actionPerformed(ActionEvent e) {
                count--;
                if (count <= 0) {
                    showRightOrWrong();
                    gameOver();
                    currentQuestionNum++;
                    updateQuestion();
                    clearSelection();
                    count = 15;
                }
                progressBar.setValue(15 - count);
            }
        });


        nextButton = new JButton("Nästa");
        nextButton.setBounds(400, 400, 100, 30);
        nextButton.setFont(font2);
        nextButton.addActionListener(e -> {

            if(getUserAnswer().equals("")){
                showError("Du måste välja ett alternativ!");
                return;
            }
            timer.start();
            showRightOrWrong();
            gameOver();
            currentQuestionNum++;
            updateQuestion();
            clearSelection();
        });
        JButton startAgain = new JButton("Starta Om");
        startAgain.setBounds(220, 400, 150, 30);
        startAgain.setFont(font2);
        startAgain.addActionListener(e -> controller.startGame());
        this.add(nextButton);
        this.add(startAgain);
        this.add(background);

    }
    private class OptionButtonListener implements ActionListener {
        private int optionIndex;

        public OptionButtonListener(int optionIndex) {
            this.optionIndex = optionIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            selectedOptionIndex = optionIndex;
            // Disable all option buttons
            disableOptionButtons();
        }
    }
    public void disableOptionButtons() {
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setEnabled(false);
        }
    }

    public String getUserAnswer() {
        if (selectedOptionIndex != -1) {
            return optionButtons[selectedOptionIndex].getText();
        }
        return "";
    }

    public void showRightOrWrong() {
        String userAnswer = getUserAnswer();
        if (answers[currentQuestionNum - 1].contains(userAnswer)) {
            String s = "Rätt!";
            rightOrWrong.setText(s);
            rightOrWrong.setForeground(Color.GREEN);
            score++;
        } else {
            rightOrWrong.setText("Fel!");
            rightOrWrong.setForeground(Color.RED);
        }
        if (userAnswer.isEmpty()) {
            rightOrWrong.setText("Du valde ej ett alternativ!");
            rightOrWrong.setForeground(Color.RED);
            score--;
        }
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setBackground(null);
        }
        repaint();
    }


    public void enableOptionButtons() {
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setEnabled(true);
        }
    }

    public void updateQuestion() {
        timer.stop();

        if (currentQuestionNum > totalQuestionNum) {
            gameOver();
            return;
        }
        questionNumber.setText("Quiz: " + currentQuestionNum + "/" + totalQuestionNum);
        questionLabel.setText(questions[currentQuestionNum-1]);
        String[] options = alt[currentQuestionNum-1];
        for (int i = 0; i < 4; i++) {
            String[] parts = options[i].split("null");
            optionButtons[i].setText(parts[1]);
        }
        clearSelection();

        progressBar.setMinimum(0);
        progressBar.setMaximum(15);
        progressBar.setValue(15);

        timer = new Timer(1500, new ActionListener() {
            int count = 15;
            @Override
            public void actionPerformed(ActionEvent e) {
                count--;
                if (count <= 0) {
                    showRightOrWrong();
                    gameOver();
                    currentQuestionNum++;
                    updateQuestion();
                    count = 15;
                }
                progressBar.setValue(count);

            }
        });
        timer.start();
        enableOptionButtons();
    }

    public void gameOver(){
        if (currentQuestionNum == totalQuestionNum){
            //playFinitoSound();
            showGameOverMessage();
            controller.sendScoreToDatabase(playerName, score);
            controller.startGame();
        }
    }

    public void clearSelection() {
        selectedOptionIndex = -1;
    }

    public void disableNextButton() {
        nextButton.setEnabled(false);
    }


    public void showGameOverMessage() {
        disableNextButton();
        JOptionPane.showMessageDialog(null, "Game Over! Ditt resultat: " + score + "/" + totalQuestionNum , "Fotbollsquiz", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }


    public void setPlayerName(String playerName) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        playerNameJTextField.setText(playerName + "\n"+ currentDate.format(formatter));
        this.playerName = playerName;
    }



    public void playFinitoSound() {
        Clip clip;
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("mus/finito.wav"));
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

