package com.example.guifotboll;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartController {

        @FXML
        private Button playquizbtn;


        @FXML
        private void initialize(){
            playquizbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try{
                        Stage thisStage = (Stage)((Button) event.getSource()).getScene().getWindow();
                        thisStage.close();

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("quiz.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        scene.setFill(Color.TRANSPARENT);
                        stage.show();

                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }

    }

