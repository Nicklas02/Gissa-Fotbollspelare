package controller;

import model.FetchDataFromDatabase;
import view.AutomaticQuestionsGUI;

import java.sql.SQLException;

public class DatabaseController {
    private FetchDataFromDatabase fetchDataFromDatabase;
    private AutomaticQuestionsGUI automaticQuestionsGUI;

    public DatabaseController() {
        fetchDataFromDatabase = new FetchDataFromDatabase(this);
        automaticQuestionsGUI = new AutomaticQuestionsGUI(this);
        fetchDataFromDatabase.addPlayersToArray();
        startGenerating();
    }

    private void startGenerating() {
        while (true) {
            String question = "Vem är äldst?";
            String alternatives = fetchDataFromDatabase.getAlternatives();
            automaticQuestionsGUI.gui(question, alternatives);
        }
    }

    public void answer(int nextInt) {
        String userOutput = fetchDataFromDatabase.verifyAnswer(nextInt);
        System.out.println(userOutput);
    }


    public static void main(String[] args) {
        DatabaseController databaseController = new DatabaseController();
    }

}
