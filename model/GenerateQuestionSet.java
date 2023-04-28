package model;

import java.util.ArrayList;
import java.util.Random;

public class GenerateQuestionSet {
    private GetSample getSample =null; //Förbindelse till sample objektet. 1:1 förbindelse
    private Player[] sample;
    private Random random = new Random(); //Används för slumpmässiga frågor liksom för slumpmässiga svarsalternativ
    private QuestionAutomatic[] questionSet;
    private final int nbrOfAlt = 4; //svarsalternativ
    private int numberOfQuestions; //antalet frågor settet innehåller, dvs antalet frågor användaren får per omgång
    private GameType gameType;


    public GenerateQuestionSet(int numberOfQuestions, GameType gameType) {
        this.numberOfQuestions = numberOfQuestions;
        this.gameType = gameType;
        //ta bort buildQuestionSet senare, anrop sker utifrån
        //buildNewQuestionSet(); //mängd frågor kan eventuellt lägga till spelets längd som in-parameter senare
    }

    public QuestionAutomatic[] buildNewQuestionSet() {
        if (getSample == null) {
            getSample = new GetSample();
        }

        try{Thread.sleep(3000);} catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        sample = getSample.getSample(gameType);
        questionSet = new QuestionAutomatic[numberOfQuestions]; //En alternativ lösning är att skapa en lista med QuestionObjects
        //för att sedan skicka till controller
        for (int i = 0; i < questionSet.length; i++) {
            questionSet[i] = randomQuestion();
        }
        return questionSet;

    }

    private QuestionAutomatic randomQuestion() {
        int localRandom = random.nextInt(4);
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
        System.out.println("Error, no question was generated, random was: " + localRandom);
        return null;
    }

    //Generell metod som tar ut fyra slumpmässigt valda (=alternatives) spelare utifrån urvalet (=sample)
    private Player[] randomAlternatives() {
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
        /*for (Player p : alternatives) {
            if (p.getAge() > correctAnswer.getAge()) {
                correctAnswer = p;
            }
        }*/
        ArrayList<Player> corrAnswers = new ArrayList<>();
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
        ArrayList<Player> corrAnswers = new ArrayList<>();
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
        ArrayList<Player> corrAnswers = new ArrayList<>();
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
        ArrayList<Player> corrAnswers = new ArrayList<>();
        for (Player p : alternatives) {
            if (p.getKitNumber() == 10) {
                corrAnswers.add(p);
            }
        }
        String localQuestion = "Vilken spelare har tröjnummer 10?";
        return new QuestionAutomatic(alternatives, corrAnswers, localQuestion);
    }

    public QuestionAutomatic[] getQuestionSet() {
        return questionSet;
    }
}
