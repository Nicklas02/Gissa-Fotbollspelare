package model;

import java.util.HashSet;

public class QuestionAutomatic {
    private Player[] alternatives;
    private HashSet<Player> correctAnswers;
    private String articulatedQuestion;

    public QuestionAutomatic(Player[] alternatives, HashSet<Player> correctAnswers, String articulatedQuestion) {
        this.alternatives = alternatives;
        this.correctAnswers = correctAnswers;
        this.articulatedQuestion = articulatedQuestion;
    }

    public Player[] getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(Player[] alternatives) {
        this.alternatives = alternatives;
    }

    public HashSet<Player> getCorrectAnswer() {
        return correctAnswers;
    }

    public void setCorrectAnswer(HashSet<Player> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public String getArticulatedQuestion() {
        return articulatedQuestion;
    }

    public void setArticulatedQuestion(String articulatedQuestion) {
        this.articulatedQuestion = articulatedQuestion;
    }
}
