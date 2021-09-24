package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.Params;

public class Character extends ImageView {




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


    public Character(){
        super(new Image("/resources/mario-red-walk1.png"));
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
        switch (stat){
            case JUMPING:
                if(up&&this.getLayoutY()>Params.CHARACTER_POS- Params.JUMP){
                    setLayoutY(this.getLayoutY()-5);}
                else if(up2&&this.getLayoutY()>Params.CHARACTER_POS-Params.JUMP-100){
                    setLayoutY(this.getLayoutY()-5);}
                else if(this.getLayoutY()<Params.CHARACTER_POS){
                    up=false;
                    up2=false;
                    setLayoutY(this.getLayoutY()+5);
                    System.out.println("from JUMPING to" +stat);
                }
                else
                    stat=Status.START;
                break;



    }}
    public void stop(){
            switch (stat){
                case DOWN:
                    stat=Status.JUMPING;
                    break;
            }

        }

}
