package model;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import util.Params;
import view.GameView;

import java.util.ArrayList;
import java.util.Random;

public class Stars extends ArrayList<Stars.Star> {

   private Random random=new Random();
   private Ramps ramps;
   private Pane main;

    public Ramps getRamps() {
        return ramps;
    }

    public Stars(Pane mainPane){
     main=mainPane;
     ramps=new Ramps();
     Star temp;
     for(int i = 0; i< Params.STAR_NUM; i++){
         temp=new Star();
         relocate(temp);
         this.add(temp);
     }

 }

 public void relocate(Star star){
     star.setLayoutY(Params.STAR_POS-random.nextInt(Params.STAR_SPACEY));
     double start=Params.WIDTH;
     for(Star tempStar:this){
         if(tempStar.getLayoutX()>start)
             start=tempStar.getLayoutX();
     }

     int temp=random.nextInt(Params.STAR_SPACE);
     star.setLayoutX(start+Params.STAR_SPACEX+temp);

     if(star.getLayoutY()<Params.CHARACTER_POS-Params.JUMP+75)
         ramps.activate(star.getLayoutX(), star.getLayoutY()+100);


 }


 public void relocate(){
     for(Star star:this){
         star.setLayoutX(star.getLayoutX()- GameView.level.value);

         if(star.getLayoutX()<=(-1*star.getLayoutBounds().getWidth()))
             relocate(star);
             }

    }

    public Boolean doesTouch(Node node){
     for(Star star:this){
         if(star.getBoundsInParent().intersects(node.getBoundsInParent()))
            {
             relocate(star);
             return true;}
         }

     return false;
    }





protected class Star extends ImageView {

    RotateTransition rotateTransition;

    public Star(){
        super(new Image("/resources/star.png"));

        rotateTransition=new RotateTransition(Duration.millis(2000), this);
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setFromAngle(-50);
        rotateTransition.setToAngle(50);
        rotateTransition.setAutoReverse(true);
        rotateTransition.setInterpolator(Interpolator.LINEAR);

//        rotateTransition.setByAngle(360f);
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        rotateTransition.play();

    }
}}
