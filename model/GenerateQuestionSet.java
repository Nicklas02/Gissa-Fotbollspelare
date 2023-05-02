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
        int localRandom = random.nextInt(4);

        if(difficulty==Difficulty.Normal){
            localRandom = random.nextInt(2);
        } else {
            localRandom = random.nextInt(4);
        }

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
            return Overall();
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

<<<<<<< HEAD
    private QuestionAutomatic Overall() {
        int randomBeast = random.nextInt(10); //a random player bound 10 means a very good player
        corrAnswers.clear();
        Player correctAnswer = sample[randomBeast];
        corrAnswers.add(correctAnswer);
        Player[] alternatives  = randomAlternatives();
        alternatives[random.nextInt(alternatives.length)] = correctAnswer;
        return new QuestionAutomatic(alternatives, corrAnswers, "Vem är den bästa spelaren?");
    }
=======
>>>>>>> 0c76e4edbc8860b82c7feda18df7812446d451f0



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
        Player[] alternatives = randomAlternatives();
       /* boolean kitNum10 = false;
        while (!kitNum10) {
            alternatives = randomAlternatives();
            for (Player alternative : alternatives) {
                if (alternative.getKitNumber() == 10) {
                    kitNum10 = true;
                    break;
                }
            }
        }

        */
        corrAnswers.clear();
        int correctAnswer = random.nextInt(4);
        corrAnswers.add(alternatives[correctAnswer]);

        /*corrAnswers.clear();
        for (Player p : alternatives) {
            if (p.getKitNumber() == 10) {
                corrAnswers.add(p);
            }
        }

         */
        String localQuestion = "Vilken spelare har tröjnummer " + alternatives[correctAnswer].getKitNumber() + "?";
        return new QuestionAutomatic(alternatives, corrAnswers, localQuestion);
    }

}
