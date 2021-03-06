package util;

public interface Params {
    //WIDTHS AND HEIGHTS
    int WIDTH=600;
    int HEIGHT=600;
    int GROUND_HEIGHT =100 ;
    int GROUND_WIDTH = 100;

    //LOCATIONS
    double SCORE_LOCATIONX = WIDTH/15;
    double SCORE_LOCATIONY = HEIGHT/15;
    int CHARACTER_POS = HEIGHT-GROUND_HEIGHT-75;
    int STAR_POS = HEIGHT-GROUND_HEIGHT-50;


    //SPACES AND LENGTHS
      int CLOUDS_SPACE=110;
    int CLOUDS_SPACEX=250;
    int STAR_SPACE=110;
      int JUMP =200 ;
    int CLOUDS_SPACEY = 100;
    int RAMP_MAX_WIDTH = 4;
    int STAR_SPACEY = 250;
    double STAR_SPACEX = 220;

    //QUANTITIES
    int CLOUDS_NUM = 6;
    int RAMPS_NUM = 6;
    int STAR_NUM = 6;


    public enum LEVEL{
        LEVEL_ONE(1.5),
        LEVEL_TWO(2),
        LEVEL_THREE(3),
        LEVEL_FOUR(3.5),
        LEVEL_FIVE(4);

        public final double value;

        LEVEL(final double newvalue){
            value=newvalue;
        }


    }
}
