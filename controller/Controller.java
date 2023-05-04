package controller;

import model.HighScore;
import view.QuizView;
import model.Question;
import model.Quiz;
import java.awt.Font;
import javax.swing.*;
import model.HighScore;
import view.Frame;



/**
 * controller class that control our project.
 *
 * @author Group 29
 */
public class Controller {/*

    private Quiz quiz;
    private QuizView quizView;
    private Frame frame;
    private int score;
    private int lastScore;
    private HighScore list;
    private String[] highScoreList;
    private Font font = new Font("Arial", Font.BOLD, 24);





    public Controller(String questionsFileName,String topScoresFileName) {
        quiz = new Quiz(questionsFileName);
        list = new HighScore(topScoresFileName);
        // call getList here to get list of 10 top scores
        highScoreList = getList();
        // create object of Fram to display start menu
        //this.frame = new Frame(this,font,600,600);
        //frame.addStartPanel(highScoreList);
        //this.quizView = new QuizView(this,600,600);
//        this.quizView.updateQuestion(quiz.getCurrentQuestion(), quiz.getCurrentQuestionNum(), quiz.getTotalNumberQuestions());
        this.score = 0;
//        this.quizView.display();
    }


    public void displayQuestions(){
        if(frame.getPlayerName().isBlank()){
            JOptionPane.showMessageDialog(frame, "Error: you must enter your name", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.quizView.setPlayerName(frame.getPlayerName());
        frame.addQuestionsPanel(quizView);
        this.quizView.updateQuestion(quiz.getCurrentQuestion(), quiz.getCurrentQuestionNum(), quiz.getTotalNumberQuestions());

    }


    public void prevQuestion() {
        if (this.quiz.hasPrevQuestion()) {
            this.quizView.clearSelection();
            this.quizView.setNextButtonTextToNext();
            this.score -= lastScore;
            this.quiz.prevQuestion();
            this.quizView.updateQuestion(quiz.getCurrentQuestion(), quiz.getCurrentQuestionNum(), quiz.getTotalNumberQuestions());
        }
        if (this.quiz.getCurrentQuestionNum() == 1) {
            this.quizView.disablePrevButton();
        }
    }

    public void nextQuestion() {
        if (this.quizView.getUserAnswer().equals("")) {
            this.quizView.showError("Must select one option first");
            return;
        }
        int currQuestion= this.quiz.getCurrentQuestionNum();

        if (currQuestion == this.quiz.getTotalNumberQuestions() - 1) {
            this.quizView.setNextButtonTextToFinish();
        }
        if (this.quiz.getCurrentQuestion().isCorrect(this.quizView.getUserAnswer())) {
            lastScore = 1;////presentag
            this.quizView.showRightOrWrong("You answered correct!");
        } else {
            lastScore = 0;
            this.quizView.showRightOrWrong("You answered wrong!");
        }
        this.score += lastScore;
        if (!(this.quiz.hasNextQuestion())) {
            int percent = 100;
//presentag
            this.quizView.showGameOverMessage("game over", this.score * percent / this.quiz.getTotalNumberQuestions());
            onLeaderboard();
            return;
        }
        this.quizView.clearSelection();
        this.quiz.nextQuestion();
        this.quizView.enablePrevButton();
        this.quizView.updateQuestion(quiz.getCurrentQuestion(), quiz.getCurrentQuestionNum(), quiz.getTotalNumberQuestions());

    }

   /*
    public void onLeaderboard() {
        getList();
        int[] listScore = list.getScore();
        String[] listName = list.getName();

        if (score > listScore[9]) {
            String name = JOptionPane.showInputDialog(null, "You are in the Top10! \n" + "Enter name");
            listScore[9] = score;
            listName[9] = name;
        }
        for (int i = 0; i < listScore.length; i++) {
            for (int j = i + 1; j < listScore.length; j++) {
                int tmp;
                String temp;
                if (listScore[i] < listScore[j]) {
                    tmp = listScore[i];
                    listScore[i] = listScore[j];
                    listScore[j] = tmp;
                    temp = listName[i];
                    listName[i] = listName[j];
                    listName[j] = temp;
                }
            }
        }
        list.writeToList(listName, listScore);
    }
    *//*


    public String[] getList() {
        return list.readList();
    }
    */
}

