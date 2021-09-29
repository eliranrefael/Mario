package view;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.*;
import model.Character;
import util.Params;

import java.util.Random;

public class GameView {

    private Stage mainStage;
    private Scene mainScene;
    private AnchorPane mainPane;
    private Surfaces surfaces;
    private AnchorPane skyPane;
    private Random random;
    private Score score;
    private boolean onRamp=false;


    public static Params.LEVEL level= Params.LEVEL.LEVEL_FIVE;
    //private String[] buttonsLabels;
    //private ArrayList<MarioButton> buttons;
    //private ArrayList<SubScene> subScenes;
    private Character character;


    private boolean isKeyPressed=false;
    private boolean isHeUP=false;
    private AnimationTimer gameTimer;
    Clouds clouds;
    Stars stars;



    public Stage getMainStage() {
        return mainStage;
    }


    public GameView() {
        random=new Random();
        mainPane=new AnchorPane();
        mainStage=new Stage();
        mainScene=new Scene(mainPane, Params.WIDTH, Params.HEIGHT);
        mainScene.setCamera(new PerspectiveCamera());
        mainStage.setScene(mainScene);
        setListeners();
        character=new Character();
        surfaces=new Surfaces();
        score=new Score();
        stars=new Stars(mainPane);
        clouds=new Clouds();
        addToPane();
        createGameLoop();




    }
    private void addToPane(){
        mainPane.getChildren().addAll(stars);
        for(Ramps.Ramp ramp: stars.getRamps())
            mainPane.getChildren().addAll(ramp.getRamp());
        mainPane.getChildren().add(character);
        mainPane.getChildren().addAll(surfaces);
        skyPane=new AnchorPane();
        skyPane.setStyle("-fx-background-color: linear-gradient(to bottom, #1a001a,   #99bbff);");
        skyPane.setPrefSize(Params.WIDTH,Params.HEIGHT-Params.GROUND_HEIGHT);
        mainPane.getChildren().add(skyPane);
        skyPane.toBack();
        skyPane.getChildren().addAll(clouds);
        skyPane.getChildren().add(score);

    }


    private void setListeners() {
        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode()==KeyCode.UP){
                    character.keyPressedChangeStatus();
                }
            }
        });

    }


    private void createGameLoop(){

        gameTimer=new AnimationTimer(){


            @Override
            public void handle(long l) {
                if(!stars.getRamps().getStop()){
                    surfaces.moveGround();
                    clouds.relocate();
                stars.relocate();
                stars.getRamps().relocate();}
                character.jump();
                stars.getRamps().doesTouch(character);
                score.updateScore(stars.doesTouch(character));


            }
        };
        gameTimer.start();
    }

    /*private void jump() {
        if(isKeyPressed&&!onRamp){

            if(character.getLayoutY()>=(mainPane.getHeight()-200)&&!isHeUP)
                character.setLayoutY(character.getLayoutY()-1.5);
            else if(character.getLayoutY()<=(mainPane.getHeight()-200)){
                isHeUP=true;
                character.setLayoutY(character.getLayoutY()+1.5);
            }
            else if(character.getLayoutY()<(mainPane.getHeight()-100)){
                if(stars.getRamps().getOnRamp()&&stars.getRamps().getRamp()!=null){
                    if(character.getLayoutY()>=stars.getRamps().getRamp().getLayoutY()-50)
                        onRamp=true;
                }
                else
                character.setLayoutY(character.getLayoutY()+1.5);
                }
            else if(character.getLayoutY()>=(mainPane.getHeight()-100)){
                isHeUP=false;
                isKeyPressed=false;
            }




            }


        }*/
    }


/*
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
    }*/

