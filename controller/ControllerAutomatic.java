package controller;

import model.*;
import java.util.Scanner;


public class ControllerAutomatic {
    private QuestionAutomatic[] listQuestions;
    private HighScoreFromDatabase highScoreList;
    private int numOfQuestions;
    private GameType gameType;


    public ControllerAutomatic() {
        setGameConditions();
        displayLeaderboard();
        getQuestions(); //skapar och hämtar dataset utifrån premisserna 10 frågor och premierleague-kategori
        startQuiz(); //Konsolbaserat lokalt gui
    }

    private void setGameConditions() {
        numOfQuestions=10;
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
        Scanner scanner = new Scanner(System.in);
        while(gameType==null) {
            int choice = scanner.nextInt();
            if (choice>=0 && choice<=5) {
                gameType = GameType.valueOf(enumValues[choice]);
            }
        }


        System.out.println(gameType);
    }

    //maximum number of questions is 50-4 = 46;
    private void getQuestions() {
        if(gameType!=null && numOfQuestions>5) {
            GenerateQuestionSet generateQuestionSet = new GenerateQuestionSet(numOfQuestions, gameType);
            listQuestions = generateQuestionSet.buildNewQuestionSet();
            //listQuestions = generateQuestionSet.getQuestionSet();
        } else {
            System.out.println("Failure caused by game setup parameters");
        }
    }

    private void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        int answer = 0;
        int score = 0;
        String name;
        for (QuestionAutomatic q : listQuestions) {
            System.out.println(q.getArticulatedQuestion());

            int nbr = 1;
            for (Player p : q.getAlternatives()) {
                System.out.println(nbr + "---" + p.getName() + "---" + p.getAge() + "---" + p.getHeight() + "---" + p.getWeakFoot()
                        + "---" + p.getKitNumber());
                nbr++;
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

    private void displayLeaderboard() {
        highScoreList = new HighScoreFromDatabase();
    }

}
