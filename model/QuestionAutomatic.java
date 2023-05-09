package model;

import java.util.ArrayList;

public class QuestionAutomatic {
    private Player[] alternatives;
    private ArrayList<Player> correctAnswers;
    private String articulatedQuestion;

    public QuestionAutomatic(Player[] alternatives, ArrayList<Player> correctAnswers, String articulatedQuestion) {
        this.alternatives = alternatives;
        this.correctAnswers = correctAnswers;
        this.articulatedQuestion = articulatedQuestion;
    }

    public Player[] getAlternatives() {
        return alternatives;
    }


    public ArrayList<Player> getCorrectAnswers() {
        return correctAnswers;
    }


    public String getArticulatedQuestion() {
        return articulatedQuestion;
    }

}
