package model;

import java.util.List;

/**
 * this class contains all data about quiz.
 * @author author
 */
public class Quiz {
    private List<Question> questions;
    private int currentQuestionIndex;

    /**
     * parameter constrictor that used to create object of Quiz.
     *
     * @param fileName file name that contains questions.
     */
    public Quiz(String fileName) {
        QuizFileReader quizFileReader = new QuizFileReader(fileName);
        this.questions = quizFileReader.getQuestions();
        this.currentQuestionIndex = 0;
    }

    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public boolean hasNextQuestion() {
        return currentQuestionIndex < questions.size()-1;
    }

    public boolean hasPrevQuestion() {
        return currentQuestionIndex > 0;
    }

    public void nextQuestion() {
        currentQuestionIndex++;
    }

    public void prevQuestion() {
        currentQuestionIndex--;
    }

    public int getCurrentQuestionNum(){
        return currentQuestionIndex+1;
    }

    public int getTotalNumberQuestions(){
        return this.questions.size();
    }
}
