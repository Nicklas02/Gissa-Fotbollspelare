package controller;

import model.GenerateQuestionSet;
import model.GetSample;
import model.Player;
import model.QuestionAutomatic;

import java.util.Scanner;

/**
 * Main class in our project.
 * @author Group 29
 *
 */
public class MainProgram {

    /**
     * Main function where the program start.
     * @param args main parameter.
     */
    public static void main(String[] args) {
        // create object of controller
        //Controller controller = new Controller("files/questions.txt");

        testGetSample_testGenerateQuetsionSet_testQuestionObject();

    }

    private static void testGetSample_testGenerateQuetsionSet_testQuestionObject() {
        GetSample getSample = new GetSample();
        GenerateQuestionSet generateQuestionSet = getSample.getGenerateQuestionSet();
        QuestionAutomatic[] listQuestions = generateQuestionSet.getQuestionSet();
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
