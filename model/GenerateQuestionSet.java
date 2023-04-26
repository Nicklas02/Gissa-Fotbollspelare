package model;

import java.util.Random;
import java.util.Scanner;

public class GenerateQuestionSet {
    private Player[] sample;
    private Player[] alternatives;
    private Random random = new Random();


    public GenerateQuestionSet(Player[] sample) {
        this.sample=sample;
        while(true) {
            prepareQuestion();
            if (ageQuestion()) {
                System.out.println("Success!");
            } else {
                System.out.println("Better luck next time!");
            }
        }
    }

    //Generell metod som tar ut fyra slumpmässigt valda (=alternatives) spelare utifrån urvalet (=sample)
    private void prepareQuestion() {
        int startingPos = random.nextInt(20);
        alternatives = new Player[4];
        for (int i=0; i<4; i++){
            alternatives[i] = sample[startingPos++];
        }

    }

    private boolean ageQuestion() {
        System.out.println("Who is the olderst? Answer 1-4");
        for(int i =0; i<4; i++){
            System.out.println(alternatives[i].getName() + alternatives[i].getAge());
        }

        Scanner scanner = new Scanner(System.in);
        int answer = scanner.nextInt()-1;
        for (Player p : alternatives){
            if (alternatives[answer].getAge() < p.getAge()){
                return false;
            }
        }
        return true;
    }




}
