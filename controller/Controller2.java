package controller;

import model.*;
import view.Frame;
import view.QuizView;

import java.util.ArrayList;

public class Controller2 {

    private String[] questionsAndAnswer;
    private HighScoreFromDatabase highScoreList;
    private Frame frame;
    private GameType gameType = GameType.PremierLeague;
    private Difficulty difficulty = Difficulty.Normal;

    public Controller2() {
        this.highScoreList = new HighScoreFromDatabase();
        //String [] questionsandanswer=  hänmta questions
        startGame();
    }

    //skicka till GUI
    private void startGame() {
        String[] highscorelist = highScoreList.readList();
        frame = new Frame(this);
        frame.addStartPanel(highscorelist);
        fetchQuestions();
        //frame.metodföratttaEMotfrågor(Formatterad lista strings[]  och strings[] hoghscore)

    }


    //Hämta automatisk
    private void fetchQuestions() {
        GenerateQuestionSet generateQuestionSet = new GenerateQuestionSet(gameType, difficulty);
        QuestionAutomatic[] questionsList = generateQuestionSet.buildNewQuestionSet();
        String[] questions = new String[questionsList.length];
        String[] answers = new String[questions.length];
        String[][] alt = new String[questions.length][4];
        for (int i=0; i< questionsList.length;i++){
            questions[i] = questionsList[i].getArticulatedQuestion();
            ArrayList<Player> temp = new ArrayList<>();
            temp = questionsList[i].getCorrectAnswers();
            for(Player p : temp){
                answers[i] += p.getName();
            }
            Player[] temp2 = questionsList[i].getAlternatives();
            for(int j=0; j< 4; j++){
                alt[i][j] += temp2[j].getName();
            }
        }
        QuizView quizView = new QuizView(this);
        quizView.FillQuestions(questions, alt, answers);

        //senare hämta manuella
    }


}
