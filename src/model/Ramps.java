package model;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import util.Params;
import view.GameView;

import java.util.ArrayList;
import java.util.Random;

public class Ramps extends ArrayList<Ramps.Ramp> {

    private PositionStat stat=null;
    Random random=new Random();
    private Boolean onRamp=false;
    private Boolean stop=false;
    private Ramp ramp=null;

    enum PositionStat{
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }


    public Boolean getOnRamp() {
        return onRamp;
    }

    public Ramp getRamp() {
        return ramp;
    }

    public Ramps() {
        for (int i = 0; i < Params.RAMPS_NUM; i++)
            this.addRamp(random.nextInt(Params.RAMP_MAX_WIDTH) + 1);


    }

    public void addRamp(int len){
        Ramp tempRamp=new Ramp(len);
        tempRamp.getRamp().setLayoutX(800);
        this.add(tempRamp);

    }



    public void relocate(){
        if(!this.isEmpty()){
        for(Ramp ramp:this){
            if(ramp.active)
                ramp.getRamp().setLayoutX(ramp.getRamp().getLayoutX()- GameView.level.value);

            if(ramp.getRamp().getLayoutX()<=(-1*ramp.getRamp().getLayoutBounds().getWidth()))
                ramp.setActive(false);
        }}
        else
            return;
    }

    public void activate(double layoutX, double layoutY) {
        for(Ramp ramp:this){
            if(!ramp.active){
                System.out.println("star x: "+layoutX+" ramp x: "+(layoutX-ramp.getRamp().getWidth())+" "+ramp.getRamp().getBoundsInParent().getWidth());
                ramp.getRamp().setLayoutX(layoutX-(((ramp.getLength()-1)*50)/2));
                ramp.getRamp().setLayoutY(layoutY);
                ramp.setActive(true);
                return;
            }

        }
        return;
    }

    public Boolean getStop() {
        return stop;
    }

    public void doesTouch(Character character) {
        if(ramp==null){
            if(stop)
                stop=false;
        for(Ramp ramp:this) {
            if (character.getBoundsInParent().intersects(ramp.getRamp().getBoundsInParent())){
                this.ramp=ramp;
                touchAct(character);
                break;
            }
            this.ramp=null;
            }
            }
        else{
            Bounds chara=character.getBoundsInParent();
            Bounds ram=ramp.getRamp().getBoundsInParent();

            if(chara.intersects(ram))
                return;
            else {
                character.setStat(Character.Status.DOWN);
                character.stop();
                this.ramp = null;
                stat=null;
            }
        }
    }


    public void touchAct(Character character){
        if(stop)
            stop=false;
        PositionStat positionStat=null;
        if(ramp!=null){
             positionStat = whereTouched(character);
             if(stat!=positionStat){
            switch(positionStat){
                case RIGHT:
                    if(character.getStat()==Character.Status.STOP||character.getStat()==Character.Status.JUMPING2){
                        character.setStat(Character.Status.DOWN);
                        character.stop();
                        break;
                    }
                case BOTTOM:
                    if(character.isUp()){
                        character.setUp(false);
                    character.setStat(Character.Status.DOWN);
                    character.stop();}
                    break;
                case TOP:
                    character.setStat(Character.Status.STOP);
                    break;
                case LEFT:
                    stop=true;

            }
            stat=positionStat;
        }}}





    private PositionStat whereTouched(Character character) {
        double charYMax=character.getBoundsInParent().getMaxY();
        double charYMin=character.getBoundsInParent().getMinY();
        double rampYMin=ramp.getRamp().getBoundsInParent().getMinY();
        double rampYMax=ramp.getRamp().getBoundsInParent().getMaxY();

        double upSpace=rampYMin-charYMax;
        double bottomSpace=charYMin-rampYMax;
        if(upSpace<=-4&&bottomSpace<=-4){
        if(character.getBoundsInParent().getMaxX()-4<=ramp.getRamp().getBoundsInParent().getMinX()){
            System.out.println("left- charmaxx: "+charYMax+" rampminx: "+ rampYMin);
            return PositionStat.LEFT;
        }

        else if(character.getBoundsInParent().getMinX()+4>=ramp.getRamp().getBoundsInParent().getMaxX()){
            System.out.println("right- charminx: "+character.getBoundsInParent().getMinX()+" rampmaxx: "+ ramp.getRamp().getBoundsInParent().getMaxX());
        return PositionStat.RIGHT;}}

        if(upSpace>=-4&&upSpace<=3){
            System.out.println("up- charY: "+charYMax+" rampY: "+ rampYMin);
            return PositionStat.TOP;
        }
        else if(bottomSpace>=-4&&bottomSpace<=3){
            System.out.println("down- charY: "+charYMin+" rampY: "+ charYMax);
            return PositionStat.BOTTOM;

        }
        System.out.println("intersected without action"+ stat);
        System.out.println("upspace: "+upSpace+" bottomspace: "+ bottomSpace);



        return null;
    }


    public class Ramp {

        private boolean active;
        private Surfaces.Surface ramp;

        public void setActive(boolean active) {
            this.active = active;
        }

        public Surfaces.Surface getRamp() {
            return ramp;
        }

        public int getLength() {
            return length;
        }

        private int length;

        public Ramp(int len){
            active=false;
            length=len;
            ramp=new Surfaces.Surface(len,1);
            ramp.setPadding(new Insets(5));



        }



    }}

