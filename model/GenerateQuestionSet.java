package model;

import java.util.Scanner;

public class GenerateQuestionSet {
    Player[] sample;


    public GenerateQuestionSet(Player[] sample) {
        this.sample=sample;
        ageQuestion();
    }

    private void ageQuestion() {
        System.out.println("Who is the olderst?");
        for(int i =0; i<4; i++){
            System.out.println(sample[i].toString());
        }

        Scanner scanner = new Scanner(System.in);

    }


}
