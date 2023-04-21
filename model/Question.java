package model;

import java.util.Arrays;
import java.util.Collections;

/**
 * The Question class represents a question in a quiz game.
 */
public class Question {
    private String question;
    private String[] options;
    private String correctAnswer;

    /**
     * Creates a new question with given text, answer options and correct answer.
     * @param questionText the text of the question.
     * @param options an array of answer options for the question.
     * @param correctAnswer correctAnswer the correct answer to the question.
     */
    public Question(String questionText, String[] options, String correctAnswer) {
        this.question = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Returns the text of the question.
     * @return the text of the question.
     */
    public String getQuestion() {
        return this.question;
    }

    /**
     * Returns a random order of the answer options.
     * @return an array of response options in random order.
     */
    public String[] getOptions() {
        // shuffle options to appear each time with different order
        Collections.shuffle(Arrays.asList(this.options));
        return this.options;
    }

    /**
     * Checks if the given answer option is the correct answer to the question.
     * @param answer answer options to check.
     * @return true if the answer option is the correct answer, false otherwise.
     */
    public boolean isCorrect(String answer) {
        return this.correctAnswer.equalsIgnoreCase(answer);
    }
}
