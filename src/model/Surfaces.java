package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import util.Params;

import java.util.ArrayList;

public class Surfaces extends ArrayList<Surfaces.Surface> {

    public Surfaces(){
        Surface surface1=new Surface(Params.WIDTH/Params.GROUND_WIDTH,0);
        surface1.setLayoutX(0);
        this.add(surface1);
        Surface surface2=new Surface(Params.WIDTH/Params.GROUND_WIDTH,0);
        surface2.setLayoutX(Params.WIDTH);
        this.add(surface2);
    }

    public void moveGround(){
        for(Surface surface:this){
            surface.setLayoutX(surface.getLayoutX()-1);
        if(surface.getLayoutX()<=(-1*Params.WIDTH))
            surface.setLayoutX(Params.WIDTH);}
    }



protected static class Surface extends HBox{
    private static Image rampground= new Image("/resources/rampground.png");
    private static Image ground= new Image("/resources/ground.png");

    public Surface(double num, int mode){

        int i=(int)(num);
        if(mode==1){
        for(int j=0;j<i;j++) {
            ImageView groundView= new ImageView(rampground);
            this.getChildren().add(groundView);
        }}
        else{
            for(int j=0;j<i;j++) {
                ImageView groundView= new ImageView(ground);
                this.getChildren().add(groundView);
            }
        }

        this.setLayoutY(Params.HEIGHT-Params.GROUND_HEIGHT);




    }}



}
