package model;

import java.util.Random;

public class GenerateQuestionSet {
    private GetSample sampleObject;
    private Player[] sample;
    private Random random = new Random();
    private QuestionAutomatic[] questionSet;
    private final int nbrOfAlt = 4;


    public GenerateQuestionSet() {
        sampleObject = new GetSample();
        //ta bort buildQuestionSet senare, anrop sker utifrån
        //buildQuestionSet(10); //mängd frågor kan eventuellt lägga till spelets längd som in-parameter senare
    }

    private QuestionAutomatic[] buildQuestionSet(int numberOfQuestions) {
        sample = sampleObject.getSample(GameType.PremierLeague, numberOfQuestions); //hårdkodad

        questionSet= new QuestionAutomatic[numberOfQuestions]; //En alternativ lösning är att skapa en lista med QuestionObjects
        //för att sedan skicka till controller
        for (int i=0; i<questionSet.length; i++){
            questionSet[i] = randomQuestion();
        }
        return questionSet;
    }

    private QuestionAutomatic randomQuestion() {
        int localRandom = random.nextInt(3);
        localRandom = 0; //temporär
        if (localRandom==0) {
            return ageQuestion();
        }
        if (localRandom==1) {
            return heightQuestion();
        }
        if (localRandom==2) {
            return weakFootQuestion();
        }
        System.out.println("Error, no question was generated, random was: " + localRandom );
        return null;
    }

    //Generell metod som tar ut fyra slumpmässigt valda (=alternatives) spelare utifrån urvalet (=sample)
    private Player[] randomAlternatives() {
        int startingPos = random.nextInt(sample.length-nbrOfAlt);
        Player[] alternatives = new Player[nbrOfAlt];
        for (int i=0; i<nbrOfAlt; i++){
            alternatives[i] = sample[startingPos++];
        }
        return alternatives;
    }

    //lagrar metodens data i ett questionObject så att controllerklasserna sedermera kan hämta alla QuestionsObject
    private QuestionAutomatic ageQuestion() {
        Player[] alternatives = randomAlternatives();
        int firstAlternative = 0;
        Player correctAnswer = alternatives[firstAlternative];
        for (Player p : alternatives){
            if(p.getAge()>correctAnswer.getAge()){
                correctAnswer = p;
            }
        }
        String localQuestion = "vem är äldst?";
        return new QuestionAutomatic(alternatives, correctAnswer, localQuestion );
    }
    
    private QuestionAutomatic heightQuestion(){
        Player[] alternatives = randomAlternatives();
        int firstAlternative = 0;
        Player correctAnswer = alternatives[firstAlternative];
        for (Player p : alternatives){
            if(p.getHeight()>correctAnswer.getHeight()){
                correctAnswer = p;
            }
        }
        String localQuestion = "Who is the tallest? Answer 1-4";
        return new QuestionAutomatic(alternatives, correctAnswer, localQuestion );
    }

    private QuestionAutomatic weakFootQuestion() {
        Player[] alternatives = new Player[nbrOfAlt];
        for (Player p : sample){
            //om weakfoot == 1 eller 2 adda till alternatives
            //om weakfoot == 5 adda till alternatives och correct answer
        }
        String localQuestion = "Vilken spelare är mest enfotad?";
        return new QuestionAutomatic(null, null, localQuestion );
    }

}
