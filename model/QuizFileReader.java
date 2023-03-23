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

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            // read questions from file one by one
            // each question consists of 5 lines one for question itself and other 4 lines
            // for 4 answer options
            String question;
            String[] options;
            String correctAnswer;
            while ((line = br.readLine()) != null) {
                // read question
                question = line.trim();
                options = new String[4];
                // read option 1 (correct option)
                line = br.readLine();
                correctAnswer = line.trim();
                options[0] = line.trim();
                // read option 2
                line = br.readLine();
                options[1] = line.trim();
                // read option 3
                line = br.readLine();
                options[2] = line.trim();
                // read option 4
                line = br.readLine();
                options[3] = line.trim();
                questions.add(new Question(question, options, correctAnswer));

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }

}
