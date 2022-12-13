package ru.pxlhack.snake_game;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    Snake(int dx) {
        x = 160;
        setY(160);
        setDx(dx);
        setDy(0);
        setLength(4);
        setCells(new ArrayList<>());

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    private int x;
    private int y;
    private int dx;
    private int dy;
    private int length;
    private List<int[]> cells;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<int[]> getCells() {
        return cells;
    }

    public void setCells(List<int[]> cells) {
        this.cells = cells;
    }

    public void increaseX() {
        this.x += this.dx;
    }

    public void increaseY() {
        this.y += this.dy;
    }

    public void move() {
        increaseX();
        increaseY();
    }

    public void check(int width, int height) {
        if (this.x < 0) {
            this.x = width;
        } else if (this.x >= width) {
            this.x = 0;
        }

        if (this.y < 0) {
            this.y = height;
        } else if (this.y >= height) {
            this.y = 0;
        }
    }
}
