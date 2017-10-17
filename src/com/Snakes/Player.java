package com.Snakes;

import com.googlecode.lanterna.input.Key;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Player {
    
    private List<Tail> tail = new LinkedList<>();
    private boolean up, down, right, left;
    private char dir;
    final char UP = 'w', DOWN = 's', LEFT = 'a', RIGHT = 'D';
    
    public Player() {
        Random random = new Random();
        
        this.tail.add(new Tail(random.nextInt(20), random.nextInt(20)));
        this.right = true;
    }
    
    public Tail getHead() {
        return tail.get(tail.size() - 1);
    }
    
    public void keyListen(Key key) {
        // Listen to where to go next
        dir = key.getCharacter();
    }
    
    
    public void move() {
        // Update direction
        switch (dir) {
            case UP:
                if (!up && !down) {
                    up = true;
                    down = false;
                    left = false;
                    right = false;
                }
                break;
            case DOWN:
                if (!up && !down) {
                    up = false;
                    down = true;
                    left = false;
                    right = false;
                }
                break;
            case RIGHT:
                if (!right && !left) {
                    up = false;
                    down = false;
                    left = false;
                    right = true;
                }
                break;
            case LEFT:
                if (!right && !left) {
                    up = false;
                    down = false;
                    left = true;
                    right = false;
                }
                break;
        }
        
        // Add new Tail object in list tail, in direction bool
        
    }
}

