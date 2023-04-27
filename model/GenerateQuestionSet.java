package model;

import java.util.Random;
import java.util.Scanner;

public class GenerateQuestionSet {
    private Player[] sample;
    private Player[] alternatives;
    private Random random = new Random();
    private int numberOfQuestions = 10;
    private QuestionObject[] questionSet;


    public GenerateQuestionSet(Player[] sample) {
        this.sample=sample;
        //ta bort buildQuestionSet senare, anrop sker utifrån
        buildQuestionSet(numberOfQuestions); //mängd frågor kan eventuellt lägga till spelets längd som in-parameter senare
    }

    private void buildQuestionSet(int numberOfQuestions) {
        questionSet= new QuestionObject[numberOfQuestions]; //En alternativ lösning är att skapa en lista med QuestionObjects
        //för att sedan skicka till controller
        for (int i=0; i<questionSet.length; i++){
            questionSet[i] = randomQuestion();
        }
     /*   int localCounter = 0;
        while(localCounter<questionSet.length) {
            questionSet[i] = qu

            if (randomQuestion()) {
                System.out.println("Success!");
            } else {
                System.out.println("Better luck next time!");
            }
            localCounter++;
        }*/
    }

    private QuestionObject randomQuestion() {
        int localRandom = random.nextInt(3);
        if (localRandom==0) {
            return ageQuestion();
        }
        if (localRandom==1) {
            return heightQuestion();
        }
        if (localRandom==1) {
            return weakFootQuestion();
        }
        System.out.println("Error, no question was generated, random was: " + localRandom );
        return null;

    }

    //Generell metod som tar ut fyra slumpmässigt valda (=alternatives) spelare utifrån urvalet (=sample)
    private Player[] randomAlternatives() {
        int startingPos = random.nextInt(20);
        alternatives = new Player[4];
        for (int i=0; i<4; i++){
            alternatives[i] = sample[startingPos++];
        }
        return alternatives;
    }

    private QuestionObject ageQuestion() {
        //Alternativ sätt att göra det på är att lagra metodens data i ett questionObject och sedermera skicka vidare
        //detta till Controllern
        //t ex
        Player[] alternatives = randomAlternatives();
        int firstAlternative = 0;
        Player correctAnswer = alternatives[firstAlternative];
        for (Player p : alternatives){
            if(p.getAge()>correctAnswer.getAge()){
                correctAnswer = p;
            }
        }
        String localQuestion = "vem är äldst?";

        return new QuestionObject(alternatives, correctAnswer, localQuestion );

        /*System.out.println("Who is the oldest? Answer 1-4");
        for(int i =0; i<4; i++){
            System.out.println(alternatives[i].getName() + alternatives[i].getAge());
        }
        int answer = scanner.nextInt()-1;
        for (Player p : alternatives){
            if (alternatives[answer].getAge() < p.getAge()){
                return false;
            }
        }*/

    }
    
    private QuestionObject heightQuestion(){
        System.out.println("Who is the tallest? Answer 1-4");
        return null;
    }

    private QuestionObject weakFootQuestion() {
        System.out.println("Vem är mest enfotad?");
        return null;
    }

    public QuestionObject[] getQuestionSet() {
        return questionSet;
    }
}
