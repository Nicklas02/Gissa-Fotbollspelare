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

    public QuestionObject() {
    }

    public Player[] getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(Player[] alternatives) {
        this.alternatives = alternatives;
    }

    public Player getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Player correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getArticulatedQuestion() {
        return articulatedQuestion;
    }

    public void setArticulatedQuestion(String articulatedQuestion) {
        this.articulatedQuestion = articulatedQuestion;
    }
}
