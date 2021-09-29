package model;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.Params;

public class Score extends Label {
    private int scoreCount=0;

    public Score(){
        this.setFont(Font.loadFont(getClass().getResourceAsStream("/Resources/Minecraft.ttf"),35));
        this.setTextFill(Color.DARKORANGE);
        DropShadow shadow=new DropShadow();
        shadow.setColor(Color.BLUE);
        shadow.setOffsetX(-4);
        shadow.setOffsetY(4);
        shadow.setRadius(2);
        setEffect(shadow);
        this.setText(Integer.toString(scoreCount));
        this.setLayoutX(Params.SCORE_LOCATIONX);
        this.setLayoutY(Params.SCORE_LOCATIONY);

    }

    public void updateScore(Boolean bool){
        if(bool){
            scoreCount++;
            this.setText(Integer.toString(scoreCount));
        }
    }


}
