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
    private char UP = 'w', DOWN = 's', LEFT = 'a', RIGHT = 'd';
    private int countR = 0;
    private boolean isAlive = true;

    public List<Tail> getTail() {
        return tail;
    }

    public Player(char up, char down, char left, char right) {
        Random random = new Random();
        this.UP = up;
        this.DOWN = down;
        this.LEFT = left;
        this.RIGHT = right;
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
        if(!isAlive){
//            removeTail();
            return;
        }
        if (up) {
            tail.add(0, new Tail(tail.get(0).getX(), tail.get(0).getY() - 1));
        } else if (down) {
            tail.add(0, new Tail(tail.get(0).getX(), tail.get(0).getY() + 1));
        } else if (right) {
            tail.add(0, new Tail(tail.get(0).getX() + 1, tail.get(0).getY()));
        } else if (left) {
            tail.add(0, new Tail(tail.get(0).getX() - 1, tail.get(0).getY()));
        }
        removeTail();
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

    private void removeTail() {
        countR++;
        if (countR % 10 == 0) {
            return;
        }
        tail.remove(tail.size()-1);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public static void checkForCrash(List<Player> players) {
        // For each player
        for (int i = 0; i < players.size(); i++) {
            // For all players
            for (int j = 0; j < players.size(); j++) {
                // For all snake parts
                for (int k = 0; k < players.get(j).getTail().size(); k++) {
                    // Check if comparing tail is own head
                    if (players.get(i).getHead() == players.get(j).getHead()) {
                        continue;
                    }
                    // Check crash
                    if (players.get(i).getHead().getX() == players.get(j).getTail().get(k).getX() &&
                            players.get(i).getHead().getY() == players.get(j).getTail().get(k).getY()) {
                        players.get(i).isAlive = false;
                        System.out.println("Player " + i + "dies");
                    }
                }
            }
        }


//        for (int i = 0; i < players.size(); i++) {
//            for (int j = 1; j < players.get(i).getTail().size(); j++) {
//                if (players.get(i).getHead().getX() == players.get(i).getTail().get(j).getX() && players.get(i).getHead().getY() == players.get(i).getTail().get(j).getY()) {
//                    isAlive = false;
//                    break;
//                }
//            }
//        }
//        return isAlive;
    }
}