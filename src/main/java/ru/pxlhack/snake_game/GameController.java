package ru.pxlhack.snake_game;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {

    @FXML
    AnchorPane gameBoxPane;
    private final int grid = 16;


    private Timer mTimer;
    private Snake snake;
    private Apple apple;
    private int speed;
    private int dSpeed;

    private Game game;

    private ArrayList<Rectangle> cellsList;

    @FXML
    private Button startGameBtn;
    @FXML
    private AnchorPane menuPane;


    @FXML
    Label scoreLabel;

    public GameController() {
    }

    private void startGame() {
        game = new Game();

        cellsList = new ArrayList<>();
        mTimer = new Timer();


        snake = new Snake(grid);

        speed = 100;
        dSpeed = 2;
        apple = new Apple(320, 320);
        resumeGame();
        switchStartStopBtn();
    }

    private void resumeGame() {
        scoreLabel.setText("Score: " + game.getScore());
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> loop());
            }
        }, 0, speed);
    }


    private void loop() {
        gameBoxPane.getChildren().clear();

        Rectangle appleRectangle = new Rectangle(apple.x, apple.y, grid, grid);
        appleRectangle.setFill(Color.RED);
        gameBoxPane.getChildren().add(appleRectangle);

        snake.move();
        int width = (int) gameBoxPane.getWidth();
        int height = (int) gameBoxPane.getHeight();
        snake.check(width, height);


        int[] cell = {snake.getX(), snake.getY()};
        snake.getCells().add(0, cell);
        if (snake.getCells().size() > snake.getLength()) {
            snake.getCells().remove(snake.getCells().size() - 1);
        }

        for (int[] c : snake.getCells()) {
            Rectangle r = new Rectangle(c[0], c[1], grid, grid);
            r.setFill(Color.GREEN);
            cellsList.add(r);
        }

        for (Rectangle r : cellsList) {
            gameBoxPane.getChildren().add(r);
        }

        //fix
        if (isSnakeEatsApple()) {
            snake.setLength(snake.getLength() + 1);
            getNewRandomApple();
            increaseSpeed();
        }

        if (ifSnakeEatsSnake()) {
            stopGame();
        }

        cellsList.clear();

    }

    @FXML
    private Button stopGameBtn;

    private void switchStartStopBtn() {
        stopGameBtn.setDisable(!stopGameBtn.isDisable());
        startGameBtn.setDisable(!startGameBtn.isDisable());
    }

    private void stopGame() {
        mTimer.cancel();
        menuPane.setEffect(new BoxBlur());
        showScore();
        gameBoxPane.getChildren().clear();
        menuPane.setEffect(null);
        switchStartStopBtn();
    }

    private void showScore() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("You lose!");
        alert.setHeaderText("Your score: " + game.getScore());
        alert.showAndWait();
    }

    private boolean ifSnakeEatsSnake() {
        for (int i = 4; i < snake.getLength() - 1; i++) {
            if (equaulsCells(snake.getX(), snake.getY(), snake.getCells().get(i)[0], snake.getCells().get(i)[1])) {
                return true;
            }
        }
        return false;
    }

    private void getNewRandomApple() {
        int xCount = (int) ((Math.random() * 21));
        int yCount = (int) ((Math.random() * 21));
        apple.x = xCount * grid + grid;
        apple.y = yCount * grid + grid;
    }


    private boolean equaulsCells(int x1, int y1, int x2, int y2) {
        return x1 == x2 && y1 == y2;
    }

    private boolean isSnakeEatsApple() {
        return equaulsCells(snake.getX(), snake.getY(), apple.x, apple.y);
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getCode()) {
            case W -> movedUp();
            case S -> movedDown();
            case A -> movedLeft();
            case D -> movedRight();
        }
    }

    private void movedUp() {
        if (snake.getDy() == 0) {
            snake.setDy(-grid);
            snake.setDx(0);
        }
    }

    private void increaseSpeed() {
        mTimer.cancel();
        mTimer = new Timer();
        if (speed >= dSpeed) speed -= dSpeed;
        game.increaseScore();
        resumeGame();
    }

    private void movedRight() {
        if (snake.getDx() == 0) {
            snake.setDx(grid);
            snake.setDy(0);
        }
    }

    private void movedLeft() {
        if (snake.getDx() == 0) {
            snake.setDx(-grid);
            snake.setDy(0);
        }
    }

    private void movedDown() {
        if (snake.getDy() == 0) {
            snake.setDy(grid);
            snake.setDx(0);
        }
    }


    public void stopGameBtnOnClick() {
        stopGame();
    }

    public void startGameBtnOnClick() {
        startGame();
    }
}