package controller;

import view.QuizView;
import model.Question;
import model.Quiz;

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


    /**
     * Parameter constrictor that used to create object of Controller.
     * @param fileName file name that contains questions.
     */
    public Controller(String fileName) {
        quiz = new Quiz(fileName);
        this.quizView = new QuizView(this);
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
        if(this.quiz.getCurrentQuestionNum() == 1){
            this.quizView.disablePrevButton();
        }
    }

    public void nextQuestion() {
        if(this.quizView.getUserAnswer().equals("")){
            this.quizView.showError("Must select one option first");
            return;
        }
        if (this.quiz.getCurrentQuestionNum() == this.quiz.getTotalNumberQuestions() - 1) {
            this.quizView.setNextButtonTextToFinish();
        }
        if (this.quiz.getCurrentQuestion().isCorrect(this.quizView.getUserAnswer())) {
            lastScore = 1;////presentag
            this.quizView.showRightOrWrong("You answered correct!");
        }
        else{
            lastScore = 0;
            this.quizView.showRightOrWrong("You answered wrong!");
        }
        this.score += lastScore;
        if (!(this.quiz.hasNextQuestion())) {
//presentag
            this.quizView.showGameOverMessage("game over", this.score * 100/ this.quiz.getTotalNumberQuestions());
            return;
        }
        this.quizView.clearSelection();
        this.quiz.nextQuestion();
        this.quizView.enablePrevButton();
        this.quizView.updateQuestion(quiz.getCurrentQuestion(), quiz.getCurrentQuestionNum(), quiz.getTotalNumberQuestions());

    }
}
