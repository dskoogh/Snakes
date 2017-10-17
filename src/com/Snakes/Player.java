package com.Snakes;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Player {

    private Queue<Tail> tail = new LinkedList<>();
    private boolean up, down, right, left;

    public Player() {
        Random random = new Random();

        this.tail.add(new Tail(random.nextInt(20), random.nextInt(20)));
        this.right = true;

    }

    public Queue<Tail> getTail() {
        return tail;
    }
}

