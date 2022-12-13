package ru.pxlhack.snake_game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(GameApplication.class.getResource("game-view.fxml"));
        Scene scene = new Scene(loader.load());
        GameController controller = loader.getController();
        scene.setOnKeyPressed(controller::keyPressed);

        stage.setOnCloseRequest(we -> System.exit(0));
        stage.setResizable(false);
        stage.setTitle("Snake game");
        stage.setScene(scene);
        stage.show();
    }

    public void run() {
        launch();
    }

}