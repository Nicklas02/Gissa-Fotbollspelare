package model;

import java.util.Random;
import java.util.Scanner;

public class GenerateQuestionSet {
    private Player[] sample;
    private Player[] alternatives;
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);
    private int numberOfQuestions = 10;
    private Question[] questionSet;

    public GenerateQuestionSet(Player[] sample) {
        this.sample=sample;
        questionSet= new Question[numberOfQuestions]; //En alternativ lösning är att skapa en lista med QuestionObjects
        //för att sedan skicka till controller
        startRound(numberOfQuestions); //mängd frågor kan eventuellt lägga till spelets längd som in-parameter senare
    }

    private void startRound(int numberOfQuestions) {
        int localCounter = 0;
        while(localCounter<numberOfQuestions) {
            if (randomQuestion()) {
                System.out.println("Success!");
            } else {
                System.out.println("Better luck next time!");
            }
            localCounter++;
        }
    }

    private boolean randomQuestion() {
        int localRandom = random.nextInt(3);
        if (localRandom==0) {
            return ageQuestion();

            //Alternativ sätt att göra det på är att lagra metodens data i ett questionObject och sedermera skicka vidare
            //detta till Controllern
            //t ex
            int nonLocalIndex = 0;
            questionSet[nonLocalIndex] = new Question(randomAlternatives(), ageQuestion(), "Vem är äldst?" );
        }
        if (localRandom==1) {
            return heightQuestion();
        }
        if(localRandom==2){
            return weakFootQuestion();
        }
        System.out.println("Error, no question was generate");
        return false;
    }

    //Generell metod som tar ut fyra slumpmässigt valda (=alternatives) spelare utifrån urvalet (=sample)
    private void randomAlternatives() {
        int startingPos = random.nextInt(20);
        alternatives = new Player[4];
        for (int i=0; i<4; i++){
            alternatives[i] = sample[startingPos++];
        }
    }

    private boolean ageQuestion() {
        randomAlternatives();
        System.out.println("Who is the oldest? Answer 1-4");
        for(int i =0; i<4; i++){
            System.out.println(alternatives[i].getName() + alternatives[i].getAge());
        }
        int answer = scanner.nextInt()-1;
        for (Player p : alternatives){
            if (alternatives[answer].getAge() < p.getAge()){
                return false;
            }
        }
        return true;
    }
    
    private boolean heightQuestion(){
        System.out.println("Who is the tallest? Answer 1-4");
        return true;
    }

    private boolean weakFootQuestion() {
        System.out.println("Vem är mest enfotad?");
        return true;
    }


}
