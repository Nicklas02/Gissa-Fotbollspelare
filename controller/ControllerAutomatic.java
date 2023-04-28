package controller;

import model.*;
import java.util.Scanner;

/**
 * Målet med klassen är att sköta det logiska flödet i anrop till model-klasserna för att få fram frågor.
 */
public class ControllerAutomatic {
    private QuestionAutomatic[] questionsList;
    private HighScoreFromDatabase highScoreList;
    private GameType gameType;
    private Difficulty difficulty;


    public ControllerAutomatic() {
        selectGameType();
        displayLeaderboard();
        fetchQuestions();
        startQuiz(); //Detta är ett temporärt konsolbaserat GUI
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
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        gameType = GameType.valueOf(enumValues[choice]);
        System.out.println("Select difficulty: 1 for easy or 2 for hard");
        choice = scanner.nextInt();
        if (choice==1){
            difficulty = Difficulty.Easy;
        } else {
            difficulty = Difficulty.Hard;
        }
        System.out.println("You started a " + gameType + " game " + "on "+ difficulty);
    }

    private void fetchQuestions() {
        if(gameType!=null && difficulty!=null) {
            GenerateQuestionSet generateQuestionSet = new GenerateQuestionSet(gameType, difficulty);
            questionsList = generateQuestionSet.buildNewQuestionSet();
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
        for (QuestionAutomatic q : questionsList) {
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
