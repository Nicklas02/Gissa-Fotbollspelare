package controller;

import model.GenerateQuestionSet;
import model.Player;
import model.QuestionAutomatic;

import java.util.Scanner;

public class ControllerAutomatic {
    private GenerateQuestionSet generateQuestionSet;
    private QuestionAutomatic[] listQuestions;


    public ControllerAutomatic() {
        generateQuestionSet = new GenerateQuestionSet();
        startController();
    }

    private void startController() {
        listQuestions = generateQuestionSet.getQuestionSet();



        int answer =0;
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
            } else {
                System.out.println("Better luck next time!");
            }
        }
    }
}
