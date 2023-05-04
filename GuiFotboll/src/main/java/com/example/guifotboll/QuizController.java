package com.example.guifotboll;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class QuizController {

        @FXML
        public Label question;

        @FXML
        public Button opt1, opt2, opt3, opt4;

        int counter = 0;
        static int correct = 0;
        static int wrong = 0;
        public Button nextBtn;

        public ProgressBar timerProgress;

        private Timeline timeline;

        private int maxTime = 10;

        @FXML
        private void initialize(){
            loadQuestions();
            nextBtn.setDisable(true);
            timerProgress.setStyle("-fx-accent: #00FF00;");

            timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                maxTime--;
                timerProgress.setProgress((double)maxTime / 10);
                if (maxTime == 0) {
                    timeline.stop();
                    opt1.setDisable(true);
                    opt2.setDisable(true);
                    opt3.setDisable(true);
                    opt4.setDisable(true);
                    nextBtn.setDisable(false);
                }
            }));

            // Set the cycle count of the timeline to indefinite
            timeline.setCycleCount(Timeline.INDEFINITE);
        }


        @FXML
        private void loadQuestions() {
            if(counter == 0){
                question.setText("Question 1");
                opt1.setText("Rätt");
                opt2.setText("Fel");
                opt3.setText("Fel");
                opt4.setText("Fel");

            }
            else if(counter == 1){
                question.setText("Question 2");
                opt1.setText("Fel");
                opt2.setText("Fel");
                opt3.setText("Rätt");
                opt4.setText("Fel");
            }
            else if(counter == 2){
                question.setText("Question 3");
                opt1.setText("Fel");
                opt2.setText("Fel");
                opt3.setText("Fel");
                opt4.setText("Rätt");
            }
            else if(counter == 3){
                question.setText("Question 4");
                opt1.setText("Fel");
                opt2.setText("Rätt");
                opt3.setText("Fel");
                opt4.setText("Fel");
            }
            else if(counter == 4){
                question.setText("Question 5");
                opt1.setText("Fel");
                opt2.setText("Fel");
                opt3.setText("Rätt");
                opt4.setText("Fel");


            }
            // Reset the button styles
            opt1.setStyle("");
            opt2.setStyle("");
            opt3.setStyle("");
            opt4.setStyle("");


            maxTime = 10;
            timerProgress.setProgress(1);


        }

        @FXML
        public void opt1Clicked(ActionEvent actionEvent) {
            maxTime = 10;
            timeline.playFromStart();

            checkAnswer(opt1.getText().toString());
            // Change the button style based on the answer
            if (checkAnswer(opt1.getText().toString())) {
                opt1.setStyle("-fx-background-color: #00FF00;");
                correct++;
            } else {
                opt1.setStyle("-fx-background-color: #FF0000;");
                wrong++;
            }

            // Disable all the buttons
            opt1.setDisable(true);
            opt2.setDisable(true);
            opt3.setDisable(true);
            opt4.setDisable(true);

            nextBtn.setDisable(false);

        }

        private boolean checkAnswer(String answer) {

            if (counter == 0) {
                if (answer.equals("Rätt")) {
                    return true;
                } else {
                    return false;
                }
            }
            if (counter == 1) {
                if (answer.equals("Rätt")) {
                    return true;
                } else {
                    return false;
                }
            }
            if (counter == 2) {
                if (answer.equals("Rätt")) {
                    return true;
                } else {
                    return false;
                }
            }
            if (counter == 3) {
                if (answer.equals("Rätt")) {
                    return true;
                } else {
                    return false;
                }
            }

            if (counter == 4) {
                if (answer.equals("Rätt")) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }


        @FXML
        public void opt2Clicked(ActionEvent actionEvent) {
            maxTime = 10;
            timeline.playFromStart();
            checkAnswer(opt2.getText().toString());

            // Change the button style based on the answer
            if (checkAnswer(opt2.getText().toString())) {
                opt2.setStyle("-fx-background-color: #00FF00;");
                correct++;
            } else {
                opt2.setStyle("-fx-background-color: #FF0000;");
                wrong++;
            }

            // Disable all the buttons
            opt1.setDisable(true);
            opt2.setDisable(true);
            opt3.setDisable(true);
            opt4.setDisable(true);

            nextBtn.setDisable(false);


        }
        @FXML
        public void opt3Clicked(ActionEvent actionEvent) {
            maxTime = 10;
            timeline.playFromStart();
            checkAnswer(opt3.getText().toString());

            // Change the button style based on the answer
            if (checkAnswer(opt3.getText().toString())) {
                opt3.setStyle("-fx-background-color: #00FF00;");
                correct++;
            } else {
                opt3.setStyle("-fx-background-color: #FF0000;");
                wrong++;
            }

            // Disable all the buttons
            opt1.setDisable(true);
            opt2.setDisable(true);
            opt3.setDisable(true);
            opt4.setDisable(true);

            nextBtn.setDisable(false);

        }
        @FXML
        public void opt4Clicked(ActionEvent actionEvent) {
            maxTime = 10;
            timeline.playFromStart();
            checkAnswer(opt4.getText().toString());

            // Change the button style based on the answer
            if (checkAnswer(opt4.getText().toString())) {
                opt4.setStyle("-fx-background-color: #00FF00;");
                correct++;
            } else {
                opt4.setStyle("-fx-background-color: #FF0000;");
                wrong++;
            }

            // Disable all the buttons
            opt1.setDisable(true);
            opt2.setDisable(true);
            opt3.setDisable(true);
            opt4.setDisable(true);

            nextBtn.setDisable(false);

        }
        @FXML
        public void nextbtnClicked(ActionEvent event) {
            if(counter == 4){
                try{
                    Stage thisStage = (Stage)((Button) event.getSource()).getScene().getWindow();
                    thisStage.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("result.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    scene.setFill(Color.TRANSPARENT);
                    stage.show();

                } catch(Exception e){
                    e.printStackTrace();
                }
                // Display the score or move to the next scene
            } else {
                counter++;
                loadQuestions();
                opt1.setDisable(false);
                opt2.setDisable(false);
                opt3.setDisable(false);
                opt4.setDisable(false);
                nextBtn.setDisable(true); // Disable the next button again after loading the next question
            }


        }
    }
