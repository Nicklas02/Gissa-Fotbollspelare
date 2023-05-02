package model;

import java.util.ArrayList;
import java.util.Random;

public class GenerateQuestionSet {
     //Förbindelse till sample objektet. 1:1 förbindelse
    private Player[] sample;
    private final Random random = new Random(); //Används för slumpmässiga frågor liksom för slumpmässiga svarsalternativ
    private static final int NUMBER_OF_QUESTIONS = 10; //antalet frågor settet innehåller, dvs antalet frågor användaren får per omgång
    private static final int NBR_OF_ALT = 4;
    private static final int HIGH_RATED_PLAYER = 15;
    private static final int DIFFERENCE_IN_RATING = 5; //Difference between a famous and non famous player
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
            corrAnswers = new ArrayList<>();
        }
        return questionSet;
    }

    private QuestionAutomatic randomQuestion() {
        int localRandom = random.nextInt(11);

        localRandom = 4; //test

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
            return kitNum();
        }
        if(localRandom==4){
            return Overall();
        }
        if(localRandom==5){
            return Nationality(); //Adam
        }
        if(localRandom==6){
            return Club(); //Adan
        }
        if(localRandom==7){
            return Position(); //ADam
        }
        if(localRandom==8){
            return Value(); //Bytte ut denna mot Weight för weight känns inte helt vettigt att ha med på en utställning
        }
        if(localRandom==9){
            return SkillMoves(); //ADam
        }
        if(localRandom==10){
            return Wage();
        }
        System.out.println("Error, no question was generated, random was: " + localRandom);
        return null;
    }



    private QuestionAutomatic SkillMoves() {
        return null;
    }



    private QuestionAutomatic Position() {
        return null;
    }

    private QuestionAutomatic Club() {
        return null;
    }

    private QuestionAutomatic Nationality() {
        return null;
    }

    private QuestionAutomatic Overall() {
        Player correctAnswer = sample[random.nextInt(HIGH_RATED_PLAYER)];
        corrAnswers.add(correctAnswer);

        Player[] alternatives = randomAlternativesLowestQuartile();
        alternatives[random.nextInt(4)] = correctAnswer;

        return new QuestionAutomatic(alternatives, corrAnswers, "Vem är den bästa spelaren?");
    }

    private QuestionAutomatic Wage() {
        
        return null;
    }

    private QuestionAutomatic Value() {
        return null;
    }



    //Generell metod som tar ut fyra slumpmässigt valda (=alternatives) spelare utifrån urvalet (=sample)
    private Player[] randomAlternatives() {
        //svarsalternativ
        Player[] alternatives = new Player[NBR_OF_ALT];
        for (int i = 0; i < NBR_OF_ALT; i++) {
            int pos = random.nextInt(sample.length);
            alternatives[i] = sample[pos];

        }
        return alternatives;
    }

    private Player[] randomAlternativesLowestQuartile() {
        Player[] alternatives = new Player[NBR_OF_ALT];
        int high = sample.length;
        int low = sample.length/4*3; //Quartile
        for (int i = 0; i < NBR_OF_ALT; i++) {
            int lowestQuartilePosition = random.nextInt(high-low) + low; //Players with worst overall rating
            alternatives[i] = sample[lowestQuartilePosition];
        }
        return alternatives;
    }


    private Player randomElement(){
        return sample[randomElementNbr()];
    }

    private int randomElementNbr(){
        return random.nextInt(sample.length);
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
        corrAnswers.add(correctAnswer);
        for (Player p : alternatives) {
            if (p.getWeakFoot() == correctAnswer.getWeakFoot()) {
                corrAnswers.add(p);
            }
        }
        String localQuestion = "Vilken spelare är mest enfotad?";
        return new QuestionAutomatic(alternatives, corrAnswers, localQuestion);

    }


    private QuestionAutomatic kitNum() {
        Player[] alternatives = randomAlternatives();
        int correctAnswer = random.nextInt(4);
        corrAnswers.add(alternatives[correctAnswer]);
        String localQuestion = "Vilken spelare har tröjnummer " + alternatives[correctAnswer].getKitNumber() + "?";
        return new QuestionAutomatic(alternatives, corrAnswers, localQuestion);
    }

}
