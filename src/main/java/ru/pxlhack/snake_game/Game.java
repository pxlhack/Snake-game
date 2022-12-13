package ru.pxlhack.snake_game;

public class Game {
    private int score;

    public Game() {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        this.score++;
    }

    public void resetScore() {
        this.score = 0;
    }

}
