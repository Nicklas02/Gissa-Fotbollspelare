package controller;

import model.*;
import view.StartPanel;
import view.QuizView;

import java.util.ArrayList;

public class Controller {

    private HighScoreFromDatabase highScoreList;
    private GameType gameType = GameType.PremierLeague;
    private Difficulty difficulty = Difficulty.Normal;
    private QuizView quizView;

    public Controller() {
        this.highScoreList = new HighScoreFromDatabase();
        //String [] questionsandanswer=  hänmta questions
        startGame();
    }

    //skicka till GUI
    private void startGame() {
        String[] highscorelist = highScoreList.readList();
        fetchQuestions();

        StartPanel startPanel = new StartPanel(this, quizView);
        startPanel.addStartPanel(highscorelist);
        //frame.metodföratttaEMotfrågor(Formatterad lista strings[]  och strings[] hoghscore)

    }


    //Hämta automatisk
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
        quizView.FillQuestions(questions, alt, answers);

        //senare hämta manuella
    }

    public void sendScoreToDatabase(String playerName, int score) {
        highScoreList.newScoreToDatabase(playerName, score);
    }
}
