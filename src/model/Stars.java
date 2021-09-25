package model;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import util.Params;

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

     if(star.getLayoutY()<Params.HEIGHT-Params.JUMP)
         ramps.activate(star.getLayoutX(), star.getLayoutY()+50);


 }


 public void relocate(){
     for(Star star:this){
         star.setLayoutX(star.getLayoutX()-1);

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

    public Star(){
        super(new Image("/resources/star.png"));
    }
}}
