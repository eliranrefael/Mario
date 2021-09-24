package app;

import javafx.application.Application;
import javafx.stage.Stage;
import view.GameView;
import view.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            GameView gameView=new GameView();
            primaryStage= gameView.getMainStage();
            primaryStage.show();

        }catch(Exception e){
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
