package model;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MarioButton extends Button {


    public MarioButton(String text, Scene parent) {
        Scene scene = parent;
        double scenesWidth = scene.getWidth();
        this.setPrefWidth(scenesWidth / 6);
        this.setPrefHeight(this.getWidth() / 2);
        this.setText(text);
        this.setStyle("-fx-background-color: linear-gradient(#f0ff35, #a9ff00), radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%); -fx-background-radius: 6, 5; -fx-background-insets: 0, 1; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 ); -fx-text-fill: #395306;");
        setListeners();
    }

    private void setListeners() {
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    setButtonPressedStyle();
                }

            }
        });
    }

    private void setButtonPressedStyle() {
        this.setStyle("-fx-background-color: linear-gradient(#f0ff35, #a9ff00), radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%); -fx-background-radius: 6, 5; -fx-background-insets: 0, 1; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 ); -fx-text-fill: #395306;");
    }


}