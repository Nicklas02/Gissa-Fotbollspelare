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
        startGenerating();
    }

    private void startGenerating() {
        while(true) {
            fetchDataFromDatabase.questionsAboutAge();
            String question = "Vem är äldst?";
            String alternatives = fetchDataFromDatabase.getAlternatives();
            automaticQuestionsGUI.gui(question, alternatives);
            fetchDataFromDatabase.questionsAboutAge();
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
