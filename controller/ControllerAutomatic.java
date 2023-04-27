package controller;

import model.GameType;
import model.GenerateQuestionSet;
import model.Player;
import model.QuestionAutomatic;

import java.util.Scanner;

public class ControllerAutomatic {
    private QuestionAutomatic[] listQuestions;


    public ControllerAutomatic() {
        getQuestions(); //skapar och hämtar dataset utifrån premisserna 10 frågor och premierleague-kategori
        startQuiz(); //Konsolbaserat lokalt gui
    }

    //maximum number of questions is 50-4 = 46; 
    private void getQuestions() {
        GenerateQuestionSet generateQuestionSet = new GenerateQuestionSet(10, GameType.PremierLeague);
        listQuestions = generateQuestionSet.getQuestionSet();
    }

    private void startQuiz() {
        int answer =0;
        int score=0;
        for(QuestionAutomatic q : listQuestions){
            System.out.println(q.getArticulatedQuestion());

            int nbr =1;
            for (Player p : q.getAlternatives()) {
                System.out.println(nbr + p.getName() + p.getAge());
                nbr++;
            }

            Scanner scanner = new Scanner(System.in);
            answer = scanner.nextInt()-1;
            if (q.getAlternatives()[answer]==q.getCorrectAnswer()){
                System.out.println("Success!");
                score++;
            } else {
                System.out.println("Better luck next time!");
            }
        }
        System.out.println("Your score was: "+score+"/10");
    }

}
