package com.Snakes;

import java.util.List;
import java.util.Random;

public class Apple {
    private int X;
    private int Y;

    public Apple() {
    }

    public Apple(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public boolean createApples(int counter, List<Apple> apples) {
        Random rand = new Random();
        if (counter % 70 == 0) {
            return apples.add(new Apple(rand.nextInt(99)+1, rand.nextInt(29)+1));
        }
        return false;
    }
}