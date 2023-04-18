package model;

import java.io.*;
import java.util.Scanner;


/**
 * Class that reads the text file HighScore and changes it if there is a new one.
 * @author Adam Leijman
 */
public class HighScore {

    private String[] name = new String[10];
    private int[] score = new int[10];
    private String[] highScoreList = new String[10];
    private int place = 1;

    /**
     * Inputs a new person to the highScoreList.
     * @param name  the name of the person.
     * @param moves how many moves it took to win.
     */
    public void writeToList(String[] name, int[] moves) {
        try {
            FileWriter myWriter = new FileWriter("files/HighScoreList.txt");
            for(int i = 0; i < name.length; i++) {
                myWriter.write(name[i] + "\n" + moves[i] + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Could not write to HighScoreList");
            e.printStackTrace();
        }
    }

    /**
     * Prints the text from HighScoreList.
     * @return the highscorelist
     */
    public String[] readList() {
        int i = 0;
        try {
            File myFile = new File("files/HighScoreList.txt");
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                name[i] = myReader.nextLine();
                score[i] = Integer.parseInt(myReader.nextLine());
                if (name[i] != null) {
                    highScoreList[i] = String.format(place + " " + name[i] + " " + score[i]);
                    i++;
                    place++;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        return highScoreList;
    }

    /**
     * @return The scores from the highscorelist
     */
    public int[] getScore() {
        return score;
    }

    /**
     * @return The names from the highscorelist
     */
    public String[] getName() {
        return name;
    }
}
