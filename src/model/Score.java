package model;

import javafx.scene.control.Label;
import util.Params;

public class Score extends Label {
    private int scoreCount=0;

    public Score(){
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
