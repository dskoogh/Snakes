package com.Snakes;

import com.googlecode.lanterna.input.Key;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Player {
    
    private List<Tail> tail = new LinkedList<>();
    private int point = 0;
    private boolean up, down, right, left;
    private char nextUp, nextDown, nextLeft, nextRight;
    private char dir = nextRight;
    private int countR = 0;
    private boolean isAlive = true;

    public List<Tail> getTail() {
        return tail;
    }
    
    public Player(char up, char down, char left, char right) {
        Random random = new Random();
        this.nextUp = up;
        this.nextDown = down;
        this.nextLeft = left;
        this.nextRight = right;
        this.tail.add(new Tail(random.nextInt(20), random.nextInt(20)));
        this.right = true;
    }
    
    public Tail getHead() {
        if (tail.size() == 0) return null;
        return tail.get(0);
    }

    public int getPoint() {
        return point;
    }

    public void keyListen(Key key) {
        // Listen to where to go next
        try {
            char c = key.getCharacter();
            // If its one of my keys, ändra direction
            if (c == nextUp || c == nextDown || c == nextLeft || c == nextRight) {
                dir = c;
            }
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
        if (!isAlive) {
            removeTail();
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
        if (dir == nextUp) {
            if (!up && !down) {
                up = true;
                down = false;
                left = false;
                right = false;
            }
            
        } else if (dir == nextDown) {
            if (!up && !down) {
                up = false;
                down = true;
                left = false;
                right = false;
            }
            
        } else if (dir == nextRight) {
            if (!right && !left) {
                up = false;
                down = false;
                left = false;
                right = true;
            }
            
        } else if (dir == nextLeft) {
            if (!right && !left) {
                up = false;
                down = false;
                left = true;
                right = false;
            }
            
        }
    }
    
    private void removeTail() {
        countR++;
        if (tail.size() == 0) return;
        
        if (isAlive && countR % 10 == 0) {
            return;
        } else if (!isAlive && countR % 50 == 0) {
            return;
        }
        tail.remove(tail.size() - 1);
    }
    
    public boolean isAlive() {
        return isAlive;
    }
    
    public static void checkForCrash(List<Player> players, int counter) {
        
        for (Player thisPlayer : players) {
            for (Player otherPlayers : players) {
                for (Tail bodypart : otherPlayers.getTail()) {
                    // Check for crash
                    if (thisPlayer.getHead() == null) continue;
                    if (thisPlayer.getHead().getX() == bodypart.getX() && thisPlayer.getHead().getY() == bodypart.getY()) {
                        if (thisPlayer.getHead() == bodypart) {
                            continue;
                        }
                        thisPlayer.point += counter;
                        thisPlayer.isAlive = false;
                    }
                }
            }
        }
    }

    public static void checkForApples(List<Player> players, List<Apple> apples) {
        for (int i = 0; i < players.size(); i++) {
            if (!players.get(i).isAlive) {
                break;
            }
            for (int j = 0; j < apples.size(); j++) {
                if (players.get(i).getHead().getX() == apples.get(j).getX() && players.get(i).getHead().getY() == apples.get(j).getY()) {
                    apples.remove(j);
                    players.get(i).point = players.get(i).point + 100;
                }
            }
        }
    }
}