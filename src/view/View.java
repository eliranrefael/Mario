package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.MarioButton;

import java.util.ArrayList;

public class View {

    private Stage mainStage;
    private Scene mainScene;
    private AnchorPane mainPane;
    private String[] buttonsLabels;
    private ArrayList<MarioButton> buttons;
    private ArrayList<SubScene> subScenes;

    public Stage getMainStage() {
        return mainStage;
    }

    public View() {
        mainPane=new AnchorPane();
        mainStage=new Stage();
        mainScene=new Scene(mainPane,768, 1024);
        mainStage.setScene(mainScene);
        buttons=new ArrayList<>();
        createButtonsLabels();
        createButtons();
        subScenes=new ArrayList<>();
        createSubScenes();
        createButtonsListeners();


    }

    private void createButtonsListeners() {
        for (MarioButton button:buttons)
              {
                  button.setOnAction(new EventHandler<ActionEvent>() {
                      @Override
                      public void handle(ActionEvent actionEvent) {
                          SubScene temp=subScenes.get(buttons.indexOf(button));
                          if(!temp.isVisible())
                          temp.setVisible(true);
                          else
                          temp.setVisible(false);
                      }
                  });

        }
    }

    private void createSubScenes() {
        int i=buttonsLabels.length;
        for(int j=0;j<i;j++){
            MarioButton temp=buttons.get(j);
            SubScene subScene=new SubScene(new AnchorPane(),200,temp.getPrefHeight()+100);
            subScene.setLayoutX(temp.getLayoutX()+temp.getPrefWidth()+100);
            subScene.setLayoutY(temp.getLayoutY());
            AnchorPane temp2=(AnchorPane) subScene.getRoot();
            temp2.setStyle("-fx-background-color: black");
            subScenes.add(subScene);
            mainPane.getChildren().add(subScene);
        }
    }



    private void createButtonsLabels() {
        buttonsLabels=new String[] {"play", "scores","help","exit"};


    }

    private void createButtons() {

        int i=buttonsLabels.length-1;
        for(;i>=0;i--){
            MarioButton button=new MarioButton(buttonsLabels[i], this.mainScene);
            button.setLayoutX(50);
            button.setLayoutY(50+i*100);
            buttons.add(button);
            mainPane.getChildren().add(button);

        }
    }
}
