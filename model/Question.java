package model;

import java.util.Arrays;
import java.util.Collections;


public class Question {
    private String question;
    private String[] options;
    private String correctAnswer;

    public Question(String questionText, String[] options, String correctAnswer) {
        this.question = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return this.question;
    }

    public String[] getOptions() {
        // shuffle options to appear each time with different order
        Collections.shuffle(Arrays.asList(this.options));
        return this.options;
    }

    public boolean isCorrect(String answer) {
        return this.correctAnswer.equalsIgnoreCase(answer);
    }
}
