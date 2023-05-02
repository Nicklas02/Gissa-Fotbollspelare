package model;

import java.util.ArrayList;
import java.util.Random;

public class GenerateQuestionSet {
     //Förbindelse till sample objektet. 1:1 förbindelse
    private Player[] sample;
    private final Random random = new Random(); //Används för slumpmässiga frågor liksom för slumpmässiga svarsalternativ
    private static final int NUMBER_OF_QUESTIONS = 10; //antalet frågor settet innehåller, dvs antalet frågor användaren får per omgång
    private final GameType gameType;
    private final Difficulty difficulty;
    ArrayList<Player> corrAnswers = new ArrayList<>();


    public GenerateQuestionSet(GameType gameType, Difficulty difficulty) {
        this.gameType = gameType;
        this.difficulty = difficulty;
    }

    public QuestionAutomatic[] buildNewQuestionSet() {
        GetSample getSample = new GetSample(gameType, difficulty);
        sample = getSample.getSample();
        QuestionAutomatic[] questionSet = new QuestionAutomatic[NUMBER_OF_QUESTIONS];
        for (int i = 0; i < questionSet.length; i++) {
            questionSet[i] = randomQuestion();
        }
        return questionSet;
    }

    private QuestionAutomatic randomQuestion() {
<<<<<<< Updated upstream
        int localRandom = random.nextInt(4);

=======
        int localRandom;
        if(difficulty==Difficulty.Easy){
            localRandom = random.nextInt(2);
        } else {
            localRandom = random.nextInt(4);
        }
>>>>>>> Stashed changes

        if (localRandom == 0) {
            return ageQuestion();
        }
        if (localRandom == 1) {
            return heightQuestion();
        }
        if (localRandom == 2) {
            return weakFootQuestion();
        }
        if (localRandom == 3) {
            return kitNum10();
        }
        if(localRandom==4){
            //Matyin
        }
        if(localRandom==5){
            //Matyin
        }
        if(localRandom==6){
            //Matyin
        }
        if(localRandom==7){
            //Adam
        }
        if(localRandom==8){
            //Adam
        }
        if(localRandom==9){
            //Adam
        }
        if(localRandom==10){
            //Adam
        }
        System.out.println("Error, no question was generated, random was: " + localRandom);
        return null;
    }

    quesitonsABoutSPecificPlayer{
        random attribute
                Random fake alternatives
                Create question
    }


    //Generell metod som tar ut fyra slumpmässigt valda (=alternatives) spelare utifrån urvalet (=sample)
    private Player[] randomAlternatives() {
        //svarsalternativ
        int nbrOfAlt = 4;
        Player[] alternatives = new Player[nbrOfAlt];
        for (int i = 0; i < nbrOfAlt; i++) {
            int pos = random.nextInt(sample.length);
            alternatives[i] = sample[pos];

        }
        return alternatives;
    }

    //lagrar metodens data i ett questionObject så att controllerklasserna sedermera kan hämta alla QuestionsObject
    private QuestionAutomatic ageQuestion() {
        Player[] alternatives = randomAlternatives();
        int firstAlternative = 0;
        Player correctAnswer = alternatives[firstAlternative];
        for (Player p : alternatives) {
            if (p.getAge() > correctAnswer.getAge()) {
                correctAnswer = p;
            }
        }
        corrAnswers.clear();
        corrAnswers.add(correctAnswer);
        for (Player p : alternatives) {
            if (p.getAge() == correctAnswer.getAge()) {
                corrAnswers.add(p);
            }
        }

        String localQuestion = "Vilken spelare är äldst?";
        return new QuestionAutomatic(alternatives, corrAnswers, localQuestion);
    }

    private QuestionAutomatic heightQuestion() {
        Player[] alternatives = randomAlternatives();
        int firstAlternative = 0;
        Player correctAnswer = alternatives[firstAlternative];
        for (Player p : alternatives) {
            if (p.getHeight() > correctAnswer.getHeight()) {
                correctAnswer = p;
            }
        }
        corrAnswers.clear();
        corrAnswers.add(correctAnswer);
        for (Player p : alternatives) {
            if (p.getHeight() == correctAnswer.getHeight()) {
                corrAnswers.add(p);
            }
        }
        String localQuestion = "Vilken spelare är längst?";
        return new QuestionAutomatic(alternatives, corrAnswers, localQuestion);
    }

    private QuestionAutomatic weakFootQuestion() {
        Player[] alternatives = null;
        boolean badWeakFoot = false;
        while (!badWeakFoot) {
            alternatives = randomAlternatives();
            for (Player alternative : alternatives) {
                if (alternative.getWeakFoot() <= 2) {
                    badWeakFoot = true;
                    break;
                }
            }
        }
        int firstAlternative = 0;
        Player correctAnswer = alternatives[firstAlternative];
        for (Player p : alternatives) {
            if (p.getWeakFoot() < correctAnswer.getWeakFoot()) {
                correctAnswer = p;
            }
        }
        corrAnswers.clear();
        corrAnswers.add(correctAnswer);
        for (Player p : alternatives) {
            if (p.getWeakFoot() == correctAnswer.getWeakFoot()) {
                corrAnswers.add(p);
            }
        }
        String localQuestion = "Vilken spelare är mest enfotad?";
        return new QuestionAutomatic(alternatives, corrAnswers, localQuestion);

    }


    private QuestionAutomatic kitNum10() {
        Player[] alternatives = null;
        boolean kitNum10 = false;
        while (!kitNum10) {
            alternatives = randomAlternatives();
            for (Player alternative : alternatives) {
                if (alternative.getKitNumber() == 10) {
                    kitNum10 = true;
                    break;
                }
            }
        }
        corrAnswers.clear();
        for (Player p : alternatives) {
            if (p.getKitNumber() == 10) {
                corrAnswers.add(p);
            }
        }
        String localQuestion = "Vilken spelare har tröjnummer 10?";
        return new QuestionAutomatic(alternatives, corrAnswers, localQuestion);
    }

}
