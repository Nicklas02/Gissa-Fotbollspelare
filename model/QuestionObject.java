package model;

public class QuestionObject {
    private Player[] alternatives;
    private Player correctAnswer;
    private String articulatedQuestion;

    public QuestionObject(Player[] alternatives, Player correctAnswer, String articulatedQuestion) {
        this.alternatives = alternatives;
        this.correctAnswer = correctAnswer;
        this.articulatedQuestion = articulatedQuestion;
    }
}
