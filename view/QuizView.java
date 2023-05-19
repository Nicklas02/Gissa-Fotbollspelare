package view;

//package com.example.footballquiz;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


import java.awt.*;
import java.awt.event.ActionEvent;

import controller.Controller;
import model.QuestionAutomatic;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class QuizView extends JPanel{

    private int currentQuestionNum = 1;
    private final int totalQuestionNum = 10;

    private ImageIcon imageIcon;
    private JLabel background,  titleLabel;
    private JTextArea playerNameJTextField;

    private Controller controller;
    private JFrame frame;
    private JLabel questionLabel;
    private JLabel questionNumber;
    JLabel rightOrWrong;
    private String playerName;


    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;
    private JButton nextButton;
    private JButton startAgain;
    private JButton prevButton;
    private Font font = new Font("Ariel", Font.BOLD, 20);
    private Font font2 = new Font("Ariel", Font.PLAIN, 15);
    private int width = 800;
    private int height = 800;
    private LocalDate currentDate;
    private DateTimeFormatter formatter;
    private String[] questions;
    private String[][] alt;
    private String[] answers;
    private int score = 0;
    private JLabel countdownLabel;
    private Timer timer;
    private AudioInputStream questionSound ;
    private static Clip questionSoundClip;
    private AudioInputStream correctAnswerSound ;
    private static Clip correctAnswerSoundClip;
    private AudioInputStream wrongAnswerSound ;
    private static Clip wrongAnswerSoundClip;



    public void FillQuestions(String[] questions, String[][] alt, String[] answers) {
        this.questions = questions;
        this.alt = alt;
        this.answers = answers;
    }

    public QuizView(Controller controller) {
        this.controller = controller;
        //question = controller.getQuestionsList();

        this.setLayout(null);
        imageIcon = new ImageIcon("images/background.jpg");
        Image image = imageIcon.getImage();
        Image scaled = image.getScaledInstance(800, 800,Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaled);
        background = new JLabel(imageIcon);
        background.setBounds(0, 0, width, height);
        playerNameJTextField = new JTextArea();
        playerNameJTextField.setFont(font);
        playerNameJTextField.setEditable(false);
        playerNameJTextField.setLineWrap(true);
        playerNameJTextField.setWrapStyleWord(true);
        playerNameJTextField.setBackground(Color.BLACK);
        playerNameJTextField.setForeground(Color.WHITE);
        playerNameJTextField.setBounds(0, 0, 200, 50);
        this.add(playerNameJTextField);

        titleLabel = new JLabel("Gissa Fotbollsspelare");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(150, 60, 500, 40);
        this.add(titleLabel);
        questionNumber = new JLabel();
        questionNumber.setBounds(width - 120, 110, 100, 40);
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
        questionLabel.setBounds(50, 140, width, 50);
        questionLabel.setFont(font);
        //colour fonts
        questionLabel.setForeground(Color.WHITE);
        this.add(questionLabel);

        optionButtons = new JRadioButton[4];
        optionGroup = new ButtonGroup();
        optionButtons[0] = new JRadioButton();
        optionButtons[0].setBounds(10, 200, 270, 30);
        optionButtons[0].setFont(font2);
        optionButtons[0].setForeground(Color.BLACK);
        optionGroup.add(optionButtons[0]);
        this.add(optionButtons[0]);

        optionButtons[1] = new JRadioButton();
        optionButtons[1].setBounds(310, 200, 270, 30);
        optionButtons[1].setFont(font2);
        optionButtons[1].setForeground(Color.BLACK);
        optionGroup.add(optionButtons[1]);
        this.add(optionButtons[1]);

        optionButtons[2] = new JRadioButton();
        optionButtons[2].setBounds(10, 300, 270, 30);
        optionButtons[2].setFont(font2);
        optionButtons[2].setForeground(Color.BLACK);
        optionGroup.add(optionButtons[2]);
        this.add(optionButtons[2]);

        optionButtons[3] = new JRadioButton();
        optionButtons[3].setBounds(310, 300, 270, 30);
        optionButtons[3].setFont(font2);
        optionButtons[3].setForeground(Color.BLACK);
        optionGroup.add(optionButtons[3]);
        this.add(optionButtons[3]);

        //Timer
        countdownLabel = new JLabel("10");
        countdownLabel.setBounds(750, 200, 50, 20);
        countdownLabel.setFont(font);
        countdownLabel.setForeground(Color.BLACK);
        this.add(countdownLabel);

        timer = new Timer(1000, new ActionListener() {
            int count = 10;
            @Override
            public void actionPerformed(ActionEvent e) {
                count--;
                if (count <= 0) {
                    showRightOrWrong();
                    gameOver();
                    currentQuestionNum++;
                    updateQuestion();
                    clearSelection();
                    count = 10;
                }
                countdownLabel.setText(String.valueOf(count));
            }
        });


        nextButton = new JButton("Next");
        nextButton.setBounds(350, 400, 100, 30);
        nextButton.setFont(font2);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(getUserAnswer().equals("")){
                    showError("Must select one option first!");
                    return;
                }
                timer.start();
                showRightOrWrong();
                gameOver();
                currentQuestionNum++;
                updateQuestion();
                clearSelection();
            }
        });
        this.add(nextButton);
        startAgain = new JButton("Starta Om");
        startAgain.setBounds(180, 400, 150, 30);
        startAgain.setFont(font2);
        startAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopAllSound();
                controller.startGame();
            }
        });
        this.add(startAgain);
        this.add(background);

    }
    public void stopAllSound() {
        if (questionSoundClip != null && questionSoundClip.isRunning()) {
            questionSoundClip.stop();
            questionSoundClip.close();
        }
        if (correctAnswerSoundClip != null && correctAnswerSoundClip.isRunning()) {
            correctAnswerSoundClip.stop();
            correctAnswerSoundClip.close();
        }
        if (wrongAnswerSoundClip != null && wrongAnswerSoundClip.isRunning()) {
            wrongAnswerSoundClip.stop();
            wrongAnswerSoundClip.close();
        }
    }
    public void playQuestionSound() {
        try {
            stopAllSound();
            questionSound = AudioSystem.getAudioInputStream(new File("question sound.wav"));
            questionSoundClip = AudioSystem.getClip();
            questionSoundClip.open(questionSound);
            questionSoundClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playWrongAnswerSound() {
        try {
            stopAllSound();
            wrongAnswerSound = AudioSystem.getAudioInputStream(new File("wrong answer.wav"));
            wrongAnswerSoundClip = AudioSystem.getClip();
            wrongAnswerSoundClip.open(wrongAnswerSound);
            wrongAnswerSoundClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playCorrectAnswerSound() {
        try {
            stopAllSound();
            correctAnswerSound = AudioSystem.getAudioInputStream(new File("true answer.wav"));
            correctAnswerSoundClip = AudioSystem.getClip();
            correctAnswerSoundClip.open(correctAnswerSound);
            correctAnswerSoundClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getUserAnswer() {
        JRadioButton userChoice = null;
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                userChoice = optionButtons[i];
            }
        }
        if (userChoice != null) {
            return userChoice.getText();
        }
        return "";
    }

    public void showRightOrWrong() {
       String userAnswer = getUserAnswer();
        if (answers[currentQuestionNum - 1].contains(userAnswer)) {
           String s = "You answered correct!";
            playCorrectAnswerSound();
            rightOrWrong.setText(s);
            rightOrWrong.setForeground(Color.GREEN);
           score++;
        } else {
           playWrongAnswerSound();
            rightOrWrong.setText("Wrong");
           rightOrWrong.setForeground(Color.RED);
       }
        if (userAnswer == null || userAnswer.isEmpty()) {
            rightOrWrong.setText("You didn't select an answer");
            rightOrWrong.setForeground(Color.RED);
            score--;
        }
        repaint();
        timer = new Timer(1000, new ActionListener() {
            int count = 10;
            @Override
            public void actionPerformed(ActionEvent e) {
                count--;
                if (count <= 0) {
                    timer. stop();
                }

            }
        });
        timer . start();


    }




        public void display() {
        frame.setVisible(true);
    }

    public void updateQuestion() {
        // stop the current timer
        timer.stop();

        if (currentQuestionNum > totalQuestionNum) {
            gameOver();
            return;
        }

        questionNumber.setText("Quiz: " + currentQuestionNum + "/" + totalQuestionNum);
        questionLabel.setText(questions[currentQuestionNum-1]);
        String[] options = alt[currentQuestionNum-1];
        clearSelection();

        for (int i = 0; i < 4; i++) {
            String[] parts = options[i].split("null");
            optionButtons[i].setText(parts[1]);
        }
        // clear the selection
        clearSelection();
        playQuestionSound();


        // start a new timer
        timer = new Timer(1000, new ActionListener() {
            int count = 10;
            @Override
            public void actionPerformed(ActionEvent e) {
                count--;
                if (count <= 0) {
                    showRightOrWrong();
                    gameOver();
                    currentQuestionNum++;
                    updateQuestion();
                    count = 10;
                }
                countdownLabel.setText(String.valueOf(count));
            }
        });
        timer.start();
    }

    public void gameOver(){
        stopAllSound();
        if (currentQuestionNum == totalQuestionNum){
            showGameOverMessage();
            controller.sendScoreToDatabase(playerName, score);
            System.exit(0);
        }
    }

    public void clearSelection() {
        optionGroup.clearSelection();
    }

    public void disableNextButton() {
        this.nextButton.setEnabled(false);
    }



    public void showGameOverMessage() {
        disableNextButton();
        JOptionPane.showMessageDialog(frame, "Game Over! Your score is: " + score + "/" + totalQuestionNum , "Football Quiz", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        currentDate = LocalDate.now();
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        playerNameJTextField.setText(playerName + "\n"+currentDate.format(formatter));
        this.playerName = playerName;
    }
    public void displayQuestions(StartPanel startPanel) {
        setPlayerName(startPanel.getPlayerName());
        startPanel.addQuestionsPanel(this);
        updateQuestion();
    }
}

