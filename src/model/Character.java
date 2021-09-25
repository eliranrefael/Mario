package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import util.Params;

public class Character extends AnchorPane {




    enum Status {
        START,
        JUMPING,
        JUMPING2,
        STOP,
        DOWN

    }



    private Status stat=Status.START;


    private boolean up=false;
    private boolean up2=false;
    private ImageView walk1;
    private ImageView walk2;
    private int count=0;


    public Character(){

        walk1=new ImageView(new Image("/resources/mario-red-walk1.png"));
        walk2=new ImageView(new Image("/resources/mario-red-walk2.png"));
        walk1.setLayoutY(0);
        walk2.setLayoutY(0);
        walk1.setLayoutX(0);
        walk2.setLayoutX(0);
        this.getChildren().addAll(walk1,walk2);
        walk2.setVisible(false);


        this.setLayoutX(25);

        this.setLayoutY(Params.HEIGHT-Params.GROUND_HEIGHT-Params.CHARACTER_POS);

    }

    public Status getStat() {
        return stat;
    }

    public boolean isUp() {
        return up;
    }



    public void setUp(boolean up) {
        this.up = up;
    }

    public void keyPressedChangeStatus(){
        if(!up){
        switch (stat){
            case START:
                up=true;
                stat=Status.JUMPING;
                System.out.println("from STARAT to" +stat);
                break;
            case STOP:
                up2=true;
                stat=Status.JUMPING;
                System.out.println("from STARAT to" +stat);
                break;


        }}
    }

    public void setStat(Status stat) {
        this.stat=stat;
    }

    public boolean isUp2() {
        return up2;
    }

    public void jump() {
        if(stat!=Status.JUMPING){
        count++;
        count%=25;
        if(count/5==4){
            walk1.setVisible(false);
            walk2.setVisible(true);
        }
        else if(count/5==2){
            walk1.setVisible(true);
            walk2.setVisible(false);
        }}
        else {
            if (up && this.getLayoutY() > Params.CHARACTER_POS - Params.JUMP) {
                setLayoutY(this.getLayoutY() - 5);
            } else if (up2 && this.getLayoutY() > Params.CHARACTER_POS - Params.JUMP - 100) {
                setLayoutY(this.getLayoutY() - 5);
            } else if (this.getLayoutY() < Params.CHARACTER_POS) {
                up = false;
                up2 = false;
                setLayoutY(this.getLayoutY() + 5);
                System.out.println("from JUMPING to" + stat);
            } else
                stat = Status.START;
        }



    }
    public void stop(){
            switch (stat){
                case DOWN:
                    stat=Status.JUMPING;
                    break;
            }

        }

}
