package view;

//package com.example.footballquiz;

import controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;

import model.Question;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class QuizView extends JPanel{

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
    private JButton prevButton;
    private Font font = new Font("Arial", Font.BOLD, 20);
    private Font font2 = new Font("Arial", Font.PLAIN, 15);
    private int width;
    private int height;
    private LocalDate currentDate;
    private DateTimeFormatter formatter;

    public QuizView(Controller controller, int width, int height) {
        this.controller = controller;
        this.width = width;
        this.height = height;
        this.setLayout(null);
        imageIcon = new ImageIcon("images/background.jpg");
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
        titleLabel.setBounds(width / 2, 30, width / 2, 30);
        this.add(titleLabel);
        questionNumber = new JLabel();
        questionNumber.setBounds(width - 120, 110, 100, 40);
        questionNumber.setFont(font);
        //colour for fonts
        questionNumber.setForeground(Color.WHITE);
        this.add(questionNumber);

        rightOrWrong = new JLabel();
        rightOrWrong.setBounds(50, height - 150, 300, 100);
        rightOrWrong.setForeground(Color.black);
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
        optionGroup.add(optionButtons[0]);
        this.add(optionButtons[0]);

        optionButtons[1] = new JRadioButton();
        optionButtons[1].setBounds(310, 200, 270, 30);
        optionButtons[1].setFont(font2);
        optionGroup.add(optionButtons[1]);
        this.add(optionButtons[1]);

        optionButtons[2] = new JRadioButton();
        optionButtons[2].setBounds(10, 300, 270, 30);
        optionButtons[2].setFont(font2);
        optionGroup.add(optionButtons[2]);
        this.add(optionButtons[2]);

        optionButtons[3] = new JRadioButton();
        optionButtons[3].setBounds(310, 300, 270, 30);
        optionButtons[3].setFont(font2);
        optionGroup.add(optionButtons[3]);
        this.add(optionButtons[3]);
        nextButton = new JButton("Next");
        nextButton.setBounds(350, 400, 100, 30);
        nextButton.setFont(font2);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.nextQuestion();
            }
        });
        this.add(nextButton);

        prevButton = new JButton("Previous");
        prevButton.setBounds(50, 400, 100, 30);
        prevButton.setFont(font2);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.prevQuestion();
            }
        });
        disablePrevButton();
        this.add(prevButton);
        this.add(background);
    }
    public String getUserAnswer() {
        JRadioButton userChoice = null;
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                userChoice = optionButtons[i];
                break;
            }
        }
        if (userChoice != null) {
            return userChoice.getText();
        }
        return "";
    }

    public void showRightOrWrong(String s) {
        if(s.equals("You answered correct!")){
            rightOrWrong.setForeground(Color.WHITE);
        }
        else{
            rightOrWrong.setForeground(Color.RED);
        }
        rightOrWrong.setText(s);
    }
    public void display() {
        frame.setVisible(true);
    }

    public void updateQuestion(Question question, int currentQuestionNum, int totalQuestionNum) {
        questionNumber.setText("Quiz: " + currentQuestionNum + "/" + totalQuestionNum);
        questionLabel.setText(question.getQuestion());
        String[] options = question.getOptions();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[i]);
        }
    }

    public void clearSelection() {
        optionGroup.clearSelection();
    }

    public void enablePrevButton() {
        this.prevButton.setEnabled(true);
    }

    public void disablePrevButton() {
        this.prevButton.setEnabled(false);
    }

    public void enableNextButton() {
        this.nextButton.setEnabled(true);
    }

    public void disableNextButton() {
        this.nextButton.setEnabled(false);
    }

    public void setNextButtonTextToFinish() {
        this.nextButton.setText("Finish");
    }

    public void setNextButtonTextToNext() {
        this.nextButton.setText("Next");
    }

    //presentag
    public void showGameOverMessage(String message, int scorePresentage) {
        disablePrevButton();
        disableNextButton();
        JOptionPane.showMessageDialog(frame, "Game Over! Your score is: " + scorePresentage + "%", "Football Quiz", JOptionPane.INFORMATION_MESSAGE);
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
}

