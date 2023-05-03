package controller;

import view.Frame;

/**
 * Main class in our project.
 * @author Group 29
 * Testttttt
 *
 */
public class MainProgram {

    /**
     * Main function where the program start.
     * @param args main parameter.
     */
    public static void main(String[] args) {
        // create object of controller
        new Controller("files/questions.txt","files/HighScoreList.txt");

    }


}
