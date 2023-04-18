package model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * this file read questions file as .txt file.
 *
 * @author Group 29
 */
public class QuizFileReader {

    private final String fileName;
    private List<Question> questions;

    /**
     * parameter constrictor that used to create object of QuizFileReader.
     *
     * @param fileName file name that contains questions.
     */
    public QuizFileReader(String fileName) {
        this.fileName = fileName;
        questions = new ArrayList<>();
        readQuestions();
    }

    /**
     * this function read all questions from input file.
     */
    public void readQuestions() {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String question = line.trim();
                    String[] options = new String[4];
                    String correctAnswer = "";
                    for (int i = 0; i < 4; i++) {
                        line = br.readLine();
                        options[i] = line.trim();
                        if (i == 0) {
                            correctAnswer = options[i];
                        }
                    }
                    questions.add(new Question(question, options, correctAnswer));
                }
            } catch (IOException e) {
                System.err.println("Error reading questions from file: " + e.getMessage());
                System.exit(1);
            }
        }

    public List<Question> getQuestions() {
        return questions;
    }

}
