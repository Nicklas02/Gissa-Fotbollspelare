//package controller;

import model.*;
import view.Frame;
import view.QuizView;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
/*
/**
 * Målet med klassen är att sköta det logiska flödet i anrop till model-klasserna för att få fram frågor.
 */
/*public class ControllerAutomatic {
    private QuestionAutomatic[] questionsList;
  /*  private HighScoreFromDatabase highScoreList;
    private GameType gameType = GameType.PremierLeague;
    private Difficulty difficulty = Difficulty.Normal;
    private final Scanner scanner = new Scanner(System.in);
    private Frame frame;
    private QuizView quizView;
    private Font font = new Font("Arial", Font.BOLD, 24);
    private int score = 0;

/*
    public ControllerAutomatic() {
        displayLeaderboard();
        fetchQuestions();
        this.frame = new Frame(this,font,600,600);
        frame.addStartPanel(highScoreList.readList());
        this.quizView = new QuizView(this,600,600);
        selectGameType();
        fetchQuestions();
        startQuiz(); //Detta är ett temporärt konsolbaserat GUI
    }

    public void displayQuestions(){
        if(frame.getPlayerName().isBlank()){
            JOptionPane.showMessageDialog(frame, "Error: you must enter your name", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.quizView.setPlayerName(frame.getPlayerName());
        frame.addQuestionsPanel(quizView);
        this.quizView.updateQuestion();
    }

    private void selectGameType() {
        String[] enumValues = {"None",
                "PremierLeague",
                "LaLiga",
                "Bundesliga",
                "Ligue1",
                "SerieA"};
        System.out.println("What quiz category do you choose?");
        for(int i=0; i<enumValues.length; i++){
            System.out.println(i + ". " + enumValues[i]);
        }
        int choice = scanner.nextInt();
        gameType = GameType.valueOf(enumValues[choice]);
        System.out.println("Select difficulty: 1 for normal or 2 for hard");
        choice = scanner.nextInt();
        if (choice==1){
            difficulty = Difficulty.Normal;
        } else {
            difficulty = Difficulty.Hard;
        }
        System.out.println("You started a " + gameType + " game " + "on "+ difficulty);
    }

    private void fetchQuestions() {
        if(gameType!=null && difficulty!=null) {
            GenerateQuestionSet generateQuestionSet = new GenerateQuestionSet(gameType, difficulty);
            questionsList = generateQuestionSet.buildNewQuestionSet();
        } else {
            System.out.println("Failure caused by game setup parameters");
        }

    }
    
    //hämta frågeset(EPL och normal)
    //hämta highscorelitsa
    //skapa gui klass
    //skicka över string[] och svar int[] och high score
            
    
    private void startQuiz() {
        int answer;
        int score = 0;
        String name;
        for (QuestionAutomatic q : questionsList) {
            System.out.println(q.getArticulatedQuestion());
            int nbrAlternative = 1;
            for (Player p : q.getAlternatives()) {
                // System.out.println(nbrAlternative + "---" + p.getName() + "---" + p.getAge() + "---" + p.getHeight() + "---" + p.getWeakFoot()
                    //    + "---" + p.getKitNumber() + " Ovr:" + p.getOverall() + " Wage: "+p.getWage() +" Value:" +p.getValue());
                System.out.println(nbrAlternative + ". " + p.getName());
                nbrAlternative++;
            }
            answer = scanner.nextInt() - 1;
            if (q.getCorrectAnswers().contains(q.getAlternatives()[answer])) {
                System.out.println("Success!");
                score++;
            } else {
                System.out.println("Better luck next time!");
            }
        }
        System.out.println("Please enter your name: ");
        name = scanner.next();
        highScoreList.newScoreToDatabase(name, score);
        System.out.println("Game over");
        System.out.println("Your score was: " + score + "/10");
    }

    public void verifyAnswer(){
        QuestionAutomatic q = quizView.getQurrentAnswer();

        String answer = quizView.getUserAnswer();
        Player p = null;
        for(Player player : q.getAlternatives()){
            if(player.getName().equals(answer)){
                p=player;
            }
        }
        
        if (q.getCorrectAnswers().contains(p)) {
            quizView.showRightOrWrong(true);
            score++;
        } else {
            quizView.showRightOrWrong(false);
            System.out.println("Better luck next time!");
        }
    }

    private void displayLeaderboard() {
        highScoreList = new HighScoreFromDatabase();
    }

    public QuestionAutomatic[] getQuestionsList() {
        return questionsList;
    }
}*/
