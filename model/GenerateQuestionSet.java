package model;

import java.util.*;

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

        localRandom = 8; //test

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
            return Nationality();
        }
        if(localRandom==6){
            return Club();
        }
        if(localRandom==7){
            return Position();
        }
        if(localRandom==8){
            return ValueOrWage(); //Bytte ut denna mot Weight för weight känns inte helt vettigt att ha med på en utställning
        }
        if(localRandom==9){
            return SkillMoves(); 
        }
        System.out.println("Error, no question was generated, random was: " + localRandom);
        return null;
    }



    private QuestionAutomatic SkillMoves() {
        Player[] alternatives = null;
        boolean skiller = false;
        while (!skiller) {
            alternatives = randomAlternatives();
            for (Player alternative : alternatives) {
                if (alternative.getSkillMoves() >= 4) {
                    skiller = true;
                    break;
                }
            }
        }
        int firstAlternative = 0;
        Player correctAnswer = alternatives[firstAlternative];
        for (Player p : alternatives) {
            if (p.getSkillMoves() > correctAnswer.getSkillMoves()) {
                correctAnswer = p;
            }
        }
        corrAnswers.add(correctAnswer);
        for (Player p : alternatives) {
            if (p.getSkillMoves() == correctAnswer.getSkillMoves()) {
                corrAnswers.add(p);
            }
        }
        String localQuestion = "Vilken spelare är mest teknisk?";
        return new QuestionAutomatic(alternatives, corrAnswers, localQuestion);
    }



    private QuestionAutomatic Position() {
        Player[] alternatives = randomAlternatives();
        int alternative = random.nextInt(4);
        boolean sub = true;

        while(sub) {
            sub = false;
            for (Player p : alternatives) {
                if (p.getPosition().equals("SUB") || p.getPosition().equals("RES")) {
                    alternatives = randomAlternatives();
                    sub = true;
                }
            }
        }

        Player correctAnswer = alternatives[alternative];
        corrAnswers.add(correctAnswer);
        for (Player p : alternatives) {
            if (p.getPosition().equals(correctAnswer.getPosition())) {
                corrAnswers.add(p);
            }
        }

        String localQuestion = "Vilken spelare har positionen " + corrAnswers.get(0).getPosition() +"?";
        return new QuestionAutomatic(alternatives, corrAnswers, localQuestion);
    }

    private QuestionAutomatic Club() {
        Player[] alternatives = randomAlternatives();
        int alternative = random.nextInt(4);
        Player correctAnswer = alternatives[alternative];

        corrAnswers.add(correctAnswer);
        for (Player p : alternatives) {
            if (p.getClub().equals(correctAnswer.getClub())) {
                corrAnswers.add(p);
            }
        }

        String localQuestion = "Vilken spelare spelar i " + corrAnswers.get(0).getClub() +"?";
        return new QuestionAutomatic(alternatives, corrAnswers, localQuestion);
    }

    private QuestionAutomatic Nationality() {
        Player[] alternatives = randomAlternatives();
        int alternative = random.nextInt(4);
        Player correctAnswer = alternatives[alternative];

        corrAnswers.add(correctAnswer);
        for (Player p : alternatives) {
            if (p.getNationality().equals(correctAnswer.getNationality())) {
                corrAnswers.add(p);
            }
        }

        String localQuestion = "Vilken spelare kommer från " + corrAnswers.get(0).getNationality() +"?";
        return new QuestionAutomatic(alternatives, corrAnswers, localQuestion);
    }

    private QuestionAutomatic Overall() {
        Player correctAnswer = sample[random.nextInt(HIGH_RATED_PLAYER)];
        corrAnswers.add(correctAnswer);

        Player[] alternatives = randomAlternativesLowestQuartile();
        alternatives[random.nextInt(4)] = correctAnswer;

        return new QuestionAutomatic(alternatives, corrAnswers, "Vem är den bästa spelaren?");
    }

    private int formattingValueOrWage(Player player, boolean value){
        if(value){
            if(player.getValue().contains(".")){
                String[] parts = player.getValue().split("\\.");
                String currValue = parts[0];
                return Integer.parseInt(currValue.substring(1));
            } else{
                return Integer.parseInt(player.getValue().substring(1, player.getValue().length() - 1));
            }
        } else {
            return Integer.parseInt(player.getWage().substring(1, player.getWage().length() - 1));
        }
    }

    private QuestionAutomatic ValueOrWage() {
        boolean isValue = random.nextBoolean();
        int[] wageOrValue = new int[sample.length];
        for (int i=0; i< wageOrValue.length; i++){
            wageOrValue[i] = formattingValueOrWage(sample[i], isValue);
            //Test formatting the entire column of either wage or value
            //System.out.println("Formatted wage/value: " + wageOrValue[i] + " Non-formatted wage: " + sample[i].getWage() + " Non-formatted value: " + sample[i].getValue());
        }
        Arrays.sort(wageOrValue);
        int[] lows = new int[NBR_OF_ALT];
        int high;
        int lowestQuartile = sample.length/4;
        int highestQuartile = sample.length/4*3;
        for (int i=0; i< lows.length;i++){
            lows[i] = wageOrValue[random.nextInt(lowestQuartile)];
        }
        high = wageOrValue[random.nextInt(lowestQuartile)+highestQuartile];
        //Test randomizing one high value (top quartile) and four low values (bottom quartile)
        //System.out.println(high + "-"+ lows[0]+"-"+ lows[1]+"-"+ lows[2]+ "-"+ lows[3]);
        Player[] alternatives = new Player[NBR_OF_ALT];
        Player correctAnswer = null;
        int count=0;
        int currWageOrValue;
        for(Player p : sample){
            currWageOrValue = formattingValueOrWage(p, isValue);
            for (int i=0; i<lows.length; i++){
                if(currWageOrValue==lows[i] && count<NBR_OF_ALT){
                    alternatives[count] = p;
                    count++;
                }
                if(currWageOrValue==high){
                    correctAnswer=p;
                }
            }
        }
        alternatives[random.nextInt(NBR_OF_ALT)] = correctAnswer;
        corrAnswers.add(correctAnswer);
        String question;
        if(isValue){
            question = "Vilken spelare har högst marknadsvärde med ett uppskattat värde på "
                    + correctAnswer.getValue() + "?";
        } else {
            question="Vilken spelare har tjänar mest med" +
                    "en månadslön på " + correctAnswer.getWage() + "?";
        }
        return new QuestionAutomatic(alternatives, corrAnswers, question);
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
        String localQuestion;
        boolean oldest = random.nextBoolean();
        if(oldest) {
            for (Player p : alternatives) {
                if (p.getAge() > correctAnswer.getAge()) {
                    correctAnswer = p;
                }
            }
            localQuestion = "Vilken spelare är äldst?";
        } else {
            for (Player p : alternatives) {
                if (p.getAge() < correctAnswer.getAge()) {
                    correctAnswer = p;
                }
            }
            localQuestion = "Vilken spelare är yngst?";
        }
        corrAnswers.add(correctAnswer);
        for (Player p : alternatives) {
            if (p.getAge() == correctAnswer.getAge()) {
                corrAnswers.add(p);
            }
        }

        return new QuestionAutomatic(alternatives, corrAnswers, localQuestion);
    }

    private QuestionAutomatic heightQuestion() {
        Player[] alternatives = randomAlternatives();
        int firstAlternative = 0;
        Player correctAnswer = alternatives[firstAlternative];
        String localQuestion;
        boolean tallest = random.nextBoolean();
        if(tallest) {
            for (Player p : alternatives) {
                if (p.getHeight() > correctAnswer.getHeight()) {
                    correctAnswer = p;
                }
            }
            localQuestion = "Vilken spelare är längst?";
        } else {
            for (Player p : alternatives) {
                if (p.getHeight() < correctAnswer.getHeight()) {
                    correctAnswer = p;
                }
            }
            localQuestion = "Vilken spelare är kortast?";
        }
        corrAnswers.add(correctAnswer);
        for (Player p : alternatives) {
            if (p.getHeight() == correctAnswer.getHeight()) {
                corrAnswers.add(p);
            }
        }
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
        int alternative = random.nextInt(4);
        Player correctAnswer = alternatives[alternative];
        corrAnswers.add(correctAnswer);
        for (Player p : alternatives) {
            if (p.getKitNumber() == correctAnswer.getKitNumber()) {
                corrAnswers.add(p);
            }
        }
        String localQuestion = "Vilken spelare har tröjnummer " + corrAnswers.get(0).getKitNumber() + "?";
        return new QuestionAutomatic(alternatives, corrAnswers, localQuestion);
    }

}
