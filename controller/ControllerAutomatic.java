package controller;

import model.*;

import java.util.Scanner;

public class ControllerAutomatic {
    private QuestionAutomatic[] listQuestions;
    private HighScoreFromDatabase highScoreList;


    public ControllerAutomatic() {
        highScoreList = new HighScoreFromDatabase();
        getQuestions(); //skapar och hämtar dataset utifrån premisserna 10 frågor och premierleague-kategori
        startQuiz(); //Konsolbaserat lokalt gui
    }

    //maximum number of questions is 50-4 = 46;
    private void getQuestions() {
        GenerateQuestionSet generateQuestionSet = new GenerateQuestionSet(10, GameType.PremierLeague);
        listQuestions = generateQuestionSet.getQuestionSet();
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
            if (q.getCorrectAnswer().contains(q.getAlternatives()[answer])) {
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

}
