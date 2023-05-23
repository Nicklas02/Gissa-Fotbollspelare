package model;

import java.util.*;

/**
 * Syftet med GenerateQuestionSet-klassen är att utifrån ett urval (sample) av spelar-objekt generera frågor.
 * Frågorna genereras utifrån ett dussin metoder och ordningen bestäms genom en slumpmässig Random-klass.
 */
public class GenerateQuestionSet {
    private Player[] sample;
    private final Random random = new Random();
    private static final int NUMBER_OF_QUESTIONS = 10;
    private static final int NORMAL_SAMPLE = 80;
    private static final int HARD_SAMPLE = 180;
    private static final int NBR_OF_ALT = 4;
    private final GameType gameType;
    private final Difficulty difficulty;
    private ArrayList<Player> corrAnswers = new ArrayList<>();
    private int prevQuestion = 100;


    public GenerateQuestionSet(GameType gameType, Difficulty difficulty) {
        this.gameType = gameType;
        this.difficulty = difficulty;
    }

    /**
     * Hämtar ett urval av spelare beroende på användarens självvalda spelinställningar.
     * @return
     */
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

    /**
     * Slumpar fram en metod som returnerar ett frågeobjekt.
     * @return Denna metoden returnerar i sin tur frågeobjektet till huvudmetoden.
     */
    private QuestionAutomatic randomQuestion() {
        int nbrOfRdmQuestions= 10;
        int localRandom = random.nextInt(nbrOfRdmQuestions);

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
            return ValueOrWage();
        }
        if(localRandom==9){
            return SkillMoves();
        }
        System.out.println("Fel, ingen fråga genererades: " + localRandom);
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

    /**
     * Fråga om vem som har en specifik position
     * @return ett frågeobjekt med fråga, rätt svar och alternativ
     */
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

        String localQuestion = "Vilken spelare spelar på positionen " + corrAnswers.get(0).getPosition() +"?";
        return new QuestionAutomatic(alternatives, corrAnswers, localQuestion);
    }

    /**
     * Fråga om vem som spelar för en specifik klubb
     * @return ett frågeobjekt med fråga, rätt svar och alternativ
     */
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

    /**
     * Fråga om vilken nationalitet spelaren kommer från
     * @return ett frågeobjekt med fråga, rätt svar och alternativ
     */
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
        return new QuestionAutomatic(alternatives, corrAnswers, "Vem är den bästa spelaren? (Enligt FIFA23)");
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
        }
        Arrays.sort(wageOrValue);
        int lowestQuartile = sample.length/4;
        int highestQuartile = sample.length/4*3;
        int[] lowValuesOrWages = new int[NBR_OF_ALT];
        for (int i=0; i< lowValuesOrWages.length;i++){
            lowValuesOrWages[i] = wageOrValue[random.nextInt(lowestQuartile)];
        }
        int highValueOrWage = wageOrValue[random.nextInt(lowestQuartile) + highestQuartile];
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
            question="Vilken spelare tjänar mest med" +
                    " en månadslön på " + correctAnswer.getWage() + "?";
        }
        return new QuestionAutomatic(alternatives, corrAnswers, question);
    }

    /**
     * Tar fram fyra alternativ och kolla så att de inte är samma spelare på två platser
     * @return en array av spelare som alternativ
     */
    private Player[] randomAlternatives() {
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
                    }
                }
            }
        }
        return alternatives;
    }

    /**
     * Fråga om vem som är yngst eller äldst
     * @return ett frågeobjekt med fråga, rätt svar och alternativ
     */
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

    /**
     * Fråga om vem som är längst eller kortast
     * @return ett frågeobjekt med fråga, rätt svar och alternativ
     */
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

    /**
     * Fråga om vem som är mest enfotad
     * @return ett frågeobjekt med fråga, rätt svar och alternativ
     */
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

    /**
     * Fråga om vem som har ett specifikt tröjnummer
     * @return ett frågeobjekt med fråga, rätt svar och alternativ
     */
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
        String localQuestion;
        if(random.nextBoolean()){
            localQuestion = "Vilken spelare har tröjnummer ";
        } else {
            localQuestion = "Vem bär tröja nummer ";
        }

        localQuestion += corrAnswers.get(0).getKitNumber() + "?";
        return new QuestionAutomatic(alternatives, corrAnswers, localQuestion);
    }

    public int getPrevQuestion() {
        return prevQuestion;
    }

    public void setPrevQuestion(int prevQuestion) {
        this.prevQuestion = prevQuestion;
    }
}
