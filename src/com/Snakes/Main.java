package com.Snakes;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        
        List<Player> players = new ArrayList<>();
        Terminal terminal = getTerminal();
        Scan scanMenu = new Scan();
        
        welcomeScreen(terminal, scanMenu);
        
        terminal.clearScreen();
        
        gameRun(terminal, players);
        
        gameOver(terminal, players);
    }
    
    private static void welcomeScreen(Terminal terminal, Scan scanMenu) throws FileNotFoundException, InterruptedException {
        scanMenu.scanText("menuSplash", terminal);
        
        Key key;
        do {
            Thread.sleep(5);
            key = terminal.readInput();
        } while (key == null);
    }
    
    private static void gameOver(Terminal terminal, List<Player> players) throws InterruptedException, FileNotFoundException {
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

        // Add level
        Scan scanLevel = new Scan();

        // Make apples
        List<Apple> apples = new ArrayList<>();
        Apple apple = new Apple();
        int counter = 0;
        
        // Play mp3
        MP3Player m = new MP3Player();
        m.play("Snakes.mp3");

        while (true) {
            
            terminal.clearScreen();
            
            // Create Apples
            createApples(terminal, apples, apple, counter);

            // Print level
            scanLevel.scanText("levelOne-2", terminal);

            // Put player on terminal
            putPlayerOnTerminal(terminal, players);
            
            // Sleep
            Thread.sleep(150);
            
            // Move
            movePlayers(terminal, players);
            
            // Check for crash and for apples
            Player.checkForCrash(players, counter);
            Player.checkForApples(players, apples);
            
            // Check for players death
            boolean playersDead = isPlayersDead(players);
            
            // Game Over screen
            if (playersDead) {
                m.stopAll(); // Stops mp3
                Scan scanGameOver = new Scan();
                scanGameOver.scanText("gameOver", terminal);
                Scan.printScore(players,terminal);
                break;
            }

            counter++;
        }
    }
    
    private static boolean isPlayersDead(List<Player> players) {
        int death = 0;
        for (Player player : players) {
            if (!(player.isAlive())) {
                ++death;
            }
        }
        boolean playersDead = false;
        if (death == players.size()) {
            playersDead = true;
        }
        return playersDead;
    }
    
    private static void movePlayers(Terminal terminal, List<Player> players) {
        Key key = terminal.readInput();
        for (int i = 0; i < players.size(); i++) {
            players.get(i).keyListen(key);
            players.get(i).move();
        }
    }
    
    private static void createApples(Terminal terminal, List<Apple> apples, Apple apple, int counter) {
        apple.createApples(counter, apples);
        
        // Write Apples
        for (int i = 0; i < apples.size(); i++) {
            terminal.moveCursor(apples.get(i).getX(), apples.get(i).getY());
            terminal.putCharacter('A');
        }
    }
    
    private static void putPlayerOnTerminal(Terminal terminal, List<Player> players) {
        for (int j = 0; j < players.size(); j++) {
            for (int i = 0; i < players.get(j).getTail().size(); i++) {
                terminal.moveCursor(players.get(j).getTail().get(i).getX(), players.get(j).getTail().get(i).getY());
                if (i != 0)
                    terminal.putCharacter('\u25E9');
                else
                    terminal.putCharacter('♥');
            }
        }
    }
    
    private static Terminal getTerminal() {
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();
        terminal.setCursorVisible(false);
        return terminal;
    }
}
