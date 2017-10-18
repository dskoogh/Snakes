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
        if (counter % 100 == 0) {
            return apples.add(new Apple(rand.nextInt(10), rand.nextInt(15)));
        }
        return false;
    }
}
