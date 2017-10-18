package com.Snakes;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;

import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        Terminal terminal = getTerminal();

        List<Player> players = new ArrayList<>();
        Scan scanMenu = new Scan();
        scanMenu.scanText("menuSplash", terminal);
        Thread.sleep(10000);
        
        // Menu, deciding how many players/AIs to participate. Level
        
        // Count down splash and music begins playing
        
        
        gameRun(terminal, players);
    
        // Splash screen
        // Print stuff on screen


        Key key;
        while (true) {
            do {
                Thread.sleep(100);
                key = terminal.readInput();
                
            } while (key == null);
            
            switch (key.getKind()) {
                case Escape:
                    terminal.exitPrivateMode();
                    System.exit(1337);
                    break;
                case Enter:
                    players.clear();
                    gameRun(terminal, players);
                    break;
            }
        }
    }
    
    private static void gameRun(Terminal terminal, List<Player> players) throws InterruptedException, FileNotFoundException {
        // Add players
        players.add(new Player('w', 's', 'a', 'd'));
        players.add(new Player('i', 'k', 'j', 'l'));
        List<Apple> apples = new ArrayList<>();
        Apple apple = new Apple();
        int counter = 0;

        // Play mp3
        MP3Player m = new MP3Player();
        m.play("Snakes.mp3");

        while (true) {

            terminal.clearScreen();

            // Create Apples
            apple.createApples(counter, apples);

            // Write Apples
            for (int i = 0; i < apples.size(); i++) {
                terminal.moveCursor(apples.get(i).getX(),apples.get(i).getY());
                terminal.putCharacter('A');
            }

            // Put player on terminal
            for (int j = 0; j < players.size(); j++) {
                for (int i = 0; i < players.get(j).getTail().size(); i++) {
                    terminal.moveCursor(players.get(j).getTail().get(i).getX(), players.get(j).getTail().get(i).getY());
                    if (i != 0)
                        terminal.putCharacter('\u25E9');
                    else
                        terminal.putCharacter('♥');
                }
            }
            
            // Sleep
            Thread.sleep(150);
            
            // Move
            Key key = terminal.readInput();
            for (int i = 0; i < players.size(); i++) {
                players.get(i).keyListen(key);
                players.get(i).move();
            }
            
            // Check for crash
            Player.checkForCrash(players);
            
            // Check for end
            int death = 0;
            for (Player player : players) {
                if (!(player.isAlive())) {
                    ++death;
                }
            }
            if (death == players.size()) {
                m.stopAll(); // Stops mp3
                Scan scanGameOver = new Scan();
                scanGameOver.scanText("gameOver", terminal);
                break;
            }
            counter++;
        }
    }
    
    private static Terminal getTerminal() {
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();
        terminal.setCursorVisible(false);
        return terminal;
    }
}
