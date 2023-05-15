package model;

import java.util.*;

public class GenerateQuestionSet {
     //Förbindelse till sample objektet. 1:1 förbindelse
    private Player[] sample;
    private final Random random = new Random(); //Används för slumpmässiga frågor liksom för slumpmässiga svarsalternativ
    private static final int NUMBER_OF_QUESTIONS = 10; //antalet frågor settet innehåller, dvs antalet frågor användaren får per omgång
    private static final int NORMAL_SAMPLE = 80;
    private static final int HARD_SAMPLE = 180;
    private static final int NBR_OF_ALT = 4;
    private final GameType gameType;
    private final Difficulty difficulty;
    ArrayList<Player> corrAnswers = new ArrayList<>();
    private int prevQuestion = 100;


    public GenerateQuestionSet(GameType gameType, Difficulty difficulty) {
        this.gameType = gameType;
        this.difficulty = difficulty;
    }

    public QuestionAutomatic[] buildNewQuestionSet() {
        GetSample getSample;
        if(difficulty==Difficulty.Normal){
            getSample = new GetSample(gameType, NORMAL_SAMPLE);
        } else {
            getSample = new GetSample(gameType, HARD_SAMPLE);
        }
        sample = getSample.getSample();
        QuestionAutomatic[] questionSet = new QuestionAutomatic[NUMBER_OF_QUESTIONS];
        for (int i = 0; i < questionSet.length; i++) {
            questionSet[i] = randomQuestion();
            corrAnswers = new ArrayList<>();
        }
        return questionSet;
    }

    private QuestionAutomatic randomQuestion() {
        int nbrOfRdmQuestions= 10;
        int localRandom = random.nextInt(nbrOfRdmQuestions);

        //Test specific value
        //localRandom = 8;
        while(localRandom == getPrevQuestion()) {
            localRandom = random.nextInt(nbrOfRdmQuestions);
        }

        setPrevQuestion(localRandom);
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
        Player[] alternatives = bottomQuartilePlayers();
        Player correctAnswer = upperQuartilePlayer();
        corrAnswers.add(correctAnswer);
        alternatives[random.nextInt(NBR_OF_ALT)] = correctAnswer;
        return new QuestionAutomatic(alternatives, corrAnswers, "Vem är den bästa spelaren?");
    }

    private Player[] bottomQuartilePlayers() {
        int lowestQuartile = sample.length/4*3;
        int highestQuartile = sample.length/4;
        Player[] players = new Player[NBR_OF_ALT];
        for (int i=0; i< players.length;i++){
            players[i] = sample[random.nextInt(highestQuartile)+lowestQuartile];
        }
        return players;
    }

    private Player upperQuartilePlayer() {
        int highestQuartile = sample.length/4;
        return sample[random.nextInt(highestQuartile)];
    }




    private int formattingValueOrWage(Player player, boolean isValueQuestion){
        if(isValueQuestion){
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
        boolean isValueQuestion = random.nextBoolean();
        int[] wageOrValue = new int[sample.length];
        for (int i=0; i< wageOrValue.length; i++){
            wageOrValue[i] = formattingValueOrWage(sample[i], isValueQuestion);
            //Test formatting the entire column of either wage or value
            //System.out.println("Formatted wage/value: " + wageOrValue[i] + " Non-formatted wage: " + sample[i].getWage() + " Non-formatted value: " + sample[i].getValue());
        }
        Arrays.sort(wageOrValue);
        int lowestQuartile = sample.length/4;
        int highestQuartile = sample.length/4*3;
        int[] lowValuesOrWages = new int[NBR_OF_ALT];
        for (int i=0; i< lowValuesOrWages.length;i++){
            lowValuesOrWages[i] = wageOrValue[random.nextInt(lowestQuartile)];
        }
        int highValueOrWage = wageOrValue[random.nextInt(lowestQuartile) + highestQuartile];
        //Test randomizing one highValueOrWage value (top quartile) and four low values (bottom quartile)
        //System.out.println(highValueOrWage + "-"+ lowValuesOrWages[0]+"-"+ lowValuesOrWages[1]+"-"+ lowValuesOrWages[2]+ "-"+ lowValuesOrWages[3]);
        Player[] alternatives = new Player[NBR_OF_ALT];
        Player correctAnswer = null;
        int count=0;
        for(Player p : sample){
            int currWageOrValue = formattingValueOrWage(p, isValueQuestion);
            for (int low : lowValuesOrWages) {
                if (currWageOrValue == low && count < NBR_OF_ALT) {
                    alternatives[count] = p;
                    count++;
                }
                if (currWageOrValue == highValueOrWage) {
                    correctAnswer = p;
                }
            }
        }
        alternatives[random.nextInt(NBR_OF_ALT)] = correctAnswer;
        corrAnswers.add(correctAnswer);
        String question="Default Question";
        if(isValueQuestion && correctAnswer!=null){
            question = "Vilken spelare har högst marknadsvärde på "
                    + correctAnswer.getValue() + "?";
        } if(!isValueQuestion && correctAnswer!=null) {
            question="Vilken spelare har tjänar mest med" +
                    "en månadslön på " + correctAnswer.getWage() + "?";
        }
        return new QuestionAutomatic(alternatives, corrAnswers, question);
    }

    //Generell metod som tar ut fyra slumpmässigt valda (=alternatives) spelare utifrån urvalet (=sample)
    private Player[] randomAlternatives() {
        //svarsalternativ
        boolean samePlayerTwice = true;
        Player[] alternatives = new Player[NBR_OF_ALT];
        while(samePlayerTwice) {
            samePlayerTwice = false;
            for (int i = 0; i < NBR_OF_ALT; i++) {
                int pos = random.nextInt(sample.length);
                alternatives[i] = sample[pos];
            }
            for (int i = 0; i < alternatives.length; i++) {
                for (int j = i + 1 ; j < alternatives.length; j++) {
                    if (alternatives[i].equals(alternatives[j])) {
                        samePlayerTwice = true;
                        break;
                    }
                }
            }
        }
        return alternatives;
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
        int lowWeakFoot = 2;
        while (!badWeakFoot) {
            alternatives = randomAlternatives();
            for (Player alternative : alternatives) {
                if (alternative.getWeakFoot() <= lowWeakFoot) {
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
        int alternative = random.nextInt(NBR_OF_ALT);
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

    public int getPrevQuestion() {
        return prevQuestion;
    }

    public void setPrevQuestion(int prevQuestion) {
        this.prevQuestion = prevQuestion;
    }
}
