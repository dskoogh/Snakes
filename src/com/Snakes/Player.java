package com.Snakes;

import com.googlecode.lanterna.input.Key;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static com.googlecode.lanterna.input.Key.Kind.ArrowUp;


public class Player {
    
    private List<Tail> tail = new LinkedList<>();
    private boolean up, down, right, left;
    private char dir = 'd';
    final char UP = 'w', DOWN = 's', LEFT = 'a', RIGHT = 'd';
    
    public Player() {
        Random random = new Random();
        
        this.tail.add(new Tail(random.nextInt(20), random.nextInt(20)));
        this.right = true;
    }
    
    public Tail getHead() {
        return tail.get(0);
    }
    
    public void keyListen(Key key) {
        // Listen to where to go next
        try {
            dir = key.getCharacter();
            
        } catch (NullPointerException e) {
        }
    }
    
    
    public void move() {
        // Update direction
        updateDirection();
        
        // Add new Tail object in list tail, in direction bool
        addTail();
        
        // Remove last tail
        
    }
    
    private void addTail() {
        if (up) {
            tail.add(0, new Tail(tail.get(0).getX(), tail.get(0).getY() - 1));
        } else if (down) {
            tail.add(0, new Tail(tail.get(0).getX(), tail.get(0).getY() + 1));
        } else if (right) {
            tail.add(0, new Tail(tail.get(0).getX() + 1, tail.get(0).getY()));
        } else if (left) {
            tail.add(0, new Tail(tail.get(0).getX() - 1, tail.get(0).getY()));
        }
    }
    
    private void updateDirection() {
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
    }
}

