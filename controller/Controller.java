package controller;

import model.HighScore;
import view.QuizView;
import model.Quiz;

import javax.swing.*;

/**
 * controller class that control our project.
 *
 * @author Group 29
 */
public class Controller {

    private Quiz quiz;
    private QuizView quizView;
    private int score;
    private int lastScore;
    private HighScore highScoreList;
    private static final int TOP_TEN = 10;
    //the HUNDRED_PERCENT constant is used in the nextQuestion() method while calculating the percentage.
    private static final int HUNDRED_PERCENT = 100;

    /**
     * Parameter constrictor that used to create object of Controller.
     *
     * @param fileName file name that contains questions.
     */
    public Controller(String fileName) {
        quiz = new Quiz(fileName);
        this.quizView = new QuizView(this);
        highScoreList = new HighScore();
        this.quizView.updateQuestion(quiz.getCurrentQuestion(), quiz.getCurrentQuestionNum(), quiz.getTotalNumberQuestions());
        this.score = 0;
        this.quizView.display();
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
            lastScore = 1;
            this.quizView.showRightOrWrong("You answered correct!");
        } else {
            lastScore = 0;
            this.quizView.showRightOrWrong("You answered wrong!");
        }
        this.score += lastScore;
        if (!(this.quiz.hasNextQuestion())) {
            this.quizView.showGameOverMessage("game over", this.score * HUNDRED_PERCENT / this.quiz.getTotalNumberQuestions());
            onLeaderboard();
            return;
        }
        this.quizView.clearSelection();
        this.quiz.nextQuestion();
        this.quizView.enablePrevButton();
        this.quizView.updateQuestion(quiz.getCurrentQuestion(), quiz.getCurrentQuestionNum(), quiz.getTotalNumberQuestions());

    }

    /**
     * Checks if the score is better than the last person on the leaderboard
     * and if it is it asks for your name and puts it in the leaderboard and the sorts it.
     */
    public void onLeaderboard() {
        getHighScoreList();
        int[] highScores = highScoreList.getScore();
        String[] highScoreNames = highScoreList.getName();

        if (score > highScores[TOP_TEN - 1]) {
            String name = JOptionPane.showInputDialog(null, "You are in the Top 10! \n" + "Enter name");
            highScores[TOP_TEN - 1] = score;
            highScoreNames[TOP_TEN - 1] = name;
        }
        for (int i = 0; i < highScores.length; i++) {
            for (int j = i + 1; j < highScores.length; j++) {
                int tempScore;
                String tempName;
                if (highScores[i] < highScores[j]) {
                    tempScore = highScores[i];
                    highScores[i] = highScores[j];
                    highScores[j] = tempScore;
                    tempName = highScoreNames[i];
                    highScoreNames[i] = highScoreNames[j];
                    highScoreNames[j] = tempName;
                }
            }
        }
        highScoreList.writeToList(highScoreNames, highScores);
    }


    /**
     * @return the highscorelist to the mainframe.
     */
    public String[] getHighScoreList() {
        return highScoreList.readList();
    }
}
