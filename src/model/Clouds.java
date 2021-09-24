package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import util.Params;

import java.util.ArrayList;
import java.util.Random;

public class Clouds extends ArrayList<Clouds.Cloud> {
    private static Image cloud=new Image("/resources/cloud.png");
    private Random random=new Random();

 public Clouds(){
     Cloud temp;
     for(int i=0; i<Params.CLOUDS_NUM; i++){
         temp=new Cloud();
         this.add(temp);
         relocate(temp);
     }

 }

 public void relocate(Cloud cloud){

     cloud.setLayoutY(random.nextInt(Params.CLOUDS_SPACEY));
     double start= Params.WIDTH;
     for(Cloud tempCloud:this){

         if(tempCloud.getLayoutX()>start)
             start=tempCloud.getLayoutX();
     }

     int temp=random.nextInt(Params.CLOUDS_SPACE);
     cloud.setLayoutX(start+Params.CLOUDS_SPACEX+temp);

 }


 public void relocate(){
     for(Cloud cloud:this){
         cloud.setLayoutX(cloud.getLayoutX()-1);

         if(cloud.getLayoutX()<=(-1*cloud.getLayoutBounds().getWidth()))
             relocate(cloud);
             }
    }





protected class Cloud extends ImageView {
    public Cloud(){
        super(cloud);
    }
}}
