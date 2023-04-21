package controller;

import view.StartScreen;

/**
 * Main class in our project.
 * @author Group 29
 */
public class MainProgram {

    /**
     * Main function where the program start.
     * @param args main parameter.
     */
    public static void main(String[] args) {
        // create object of controller
        StartScreen startScreen = new StartScreen(new Controller("files/questions.txt"));
        //Controller controller = new Controller("files/questions.txt");
    }
}
