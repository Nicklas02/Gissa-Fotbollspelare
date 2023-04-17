package controller;

import model.HighScore;
import view.QuizView;
import model.Question;
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
    private HighScore list;


    /**
     * Parameter constrictor that used to create object of Controller.
     *
     * @param fileName file name that contains questions.
     */
    public Controller(String fileName) {
        quiz = new Quiz(fileName);
        this.quizView = new QuizView(this);
        list = new HighScore();
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
        if (this.quiz.getCurrentQuestionNum() == this.quiz.getTotalNumberQuestions() - 1) {
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
//presentag
            this.quizView.showGameOverMessage("game over", this.score * 100 / this.quiz.getTotalNumberQuestions());
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

    /**
     * @return the highscorelist to the mainframe.
     */
    public String[] getList() {
        return list.readList();
    }
}
