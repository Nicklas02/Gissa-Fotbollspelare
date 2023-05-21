package controller;

import model.*;
import view.StartPanel;
import view.QuizView;

import javax.swing.*;
import java.util.ArrayList;

public class Controller {

    private HighScoreFromDatabase highScoreList;
    private GameType gameType;
    private Difficulty difficulty;
    private QuizView quizView;
    private StartPanel startPanel;

    public Controller() {
        this.highScoreList = new HighScoreFromDatabase();
        startGame();
        new MusicPlayer().start();
    }

    public void startGame() {
        String[] highscorelist = highScoreList.readList();
        startPanel = new StartPanel(this);
        startPanel.addStartPanel(highscorelist);
    }



    private void fetchQuestions() {
        GenerateQuestionSet generateQuestionSet = new GenerateQuestionSet(gameType, difficulty);
        QuestionAutomatic[] questionsList = generateQuestionSet.buildNewQuestionSet();
        String[] questions = new String[questionsList.length];
        String[] answers = new String[questions.length];
        int alternatives = 4;
        String[][] alt = new String[questions.length][alternatives];
        for (int i=0; i< questionsList.length;i++){
            questions[i] = questionsList[i].getArticulatedQuestion();
            ArrayList<Player> temp;
            temp = questionsList[i].getCorrectAnswers();
            for(Player p : temp){
                answers[i] += p.getName();
            }
            Player[] temp2 = questionsList[i].getAlternatives();
            for(int j=0; j< alternatives; j++){
                alt[i][j] += temp2[j].getName();
            }
        }
        quizView = new QuizView(this);
        quizView.fillQuestions(questions, alt, answers);
    }

    /**
     * Skickar ett nytt resultat till topplistan
     * @param playerName användarnamnen på spelaren
     * @param score resultatet på ronden
     */
    public void sendScoreToDatabase(String playerName, int score) {
        highScoreList.newScoreToDatabase(playerName, score, gameType, difficulty);
    }

    public void displayQuestions(){
        if(startPanel.getPlayerName().isBlank()){
            JOptionPane.showMessageDialog(startPanel, "Fel: Skriv in ditt namn!", "Fel", JOptionPane.ERROR_MESSAGE);
            return;
        }
        gameType = startPanel.getGameType();
        difficulty = startPanel.getDifficulty();
        fetchQuestions();
        this.quizView.setPlayerName(startPanel.getPlayerName());
        startPanel.addQuestionsPanel(this.quizView);
        this.quizView.updateQuestion();

    }

}
