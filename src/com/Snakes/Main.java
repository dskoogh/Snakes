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
        MP3Player m = new MP3Player();
    
        welcomeScreen(terminal, scanMenu);

        gameRun(terminal, players, m, scanMenu);
    }

    private static Terminal getTerminal() {
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();
        terminal.setCursorVisible(false);
        return terminal;
    }

    private static String selectMode(Terminal terminal, Scan scanMenu) throws FileNotFoundException, InterruptedException {
        scanMenu.scanText("selectPlayers", terminal);

        // Put red heart at first option

        terminal.moveCursor(40,13);
        terminal.applyForegroundColor(Terminal.Color.RED);
        terminal.putCharacter('♥');
        Key key;

        int x = 40;
        int y = 13;
        boolean modeChosen = false;
        while (!modeChosen) {
            do {
                Thread.sleep(5);
                key = terminal.readInput();
            } while (key == null);

            switch (key.getKind()) {
                case ArrowUp:
                    if (y != 13) {
                        // Put white heart-char in old position
                        terminal.moveCursor(x, y);
                        terminal.applyForegroundColor(Terminal.Color.WHITE);
                        terminal.putCharacter('♥');
                        // Put red heart
                        y -= 2;
                        terminal.moveCursor(x, y);
                        terminal.applyForegroundColor(Terminal.Color.RED);
                        terminal.putCharacter('♥');
                    }
                    break;
                case ArrowDown:
                    if (y != 17) {
                        // Put white heart-char in old position
                        terminal.moveCursor(x, y);
                        terminal.applyForegroundColor(Terminal.Color.WHITE);
                        terminal.putCharacter('♥');
                        // Put red heart
                        y += 2;
                        terminal.moveCursor(x, y);
                        terminal.applyForegroundColor(Terminal.Color.RED);
                        terminal.putCharacter('♥');
                    }
                    break;
                case Enter:
                    modeChosen = true;
                    break;
            }
        }

        terminal.clearScreen();
        terminal.applyForegroundColor(Terminal.Color.WHITE);

        // Läs ut Game mode ur y
        String mode = "single";
        switch (y) {
            case 13:
                return mode = "single";
            case 15:
                return mode = "double";
            case 17:
                return mode = "triple";
        }
        return mode;
    }

    private static void welcomeScreen(Terminal terminal, Scan scanMenu) throws FileNotFoundException, InterruptedException {
        scanMenu.scanText("menuSplash", terminal);

        Key key;
        do {
            Thread.sleep(5);
            key = terminal.readInput();
        } while (key == null);
        terminal.clearScreen();
    }

    private static int selectSpeed(Terminal terminal, Scan scanMenu) throws FileNotFoundException, InterruptedException {
        scanMenu.scanText("selectSpeed", terminal);
    
        terminal.moveCursor(40, 13);
        terminal.applyForegroundColor(Terminal.Color.RED);
        terminal.putCharacter('♥');
        Key key;
    
        int x = 40;
        int y = 13;
        
        boolean speedChosen = false;
        while (!speedChosen) {
            do {
                Thread.sleep(5);
                key = terminal.readInput();
            } while (key == null);

            switch (key.getKind()) {
                case ArrowUp:
                    if (y != 13) {
                        // Put white heart-char in old position
                        terminal.moveCursor(x, y);
                        terminal.applyForegroundColor(Terminal.Color.WHITE);
                        terminal.putCharacter('♥');
                        // Put red heart
                        y -= 2;
                        terminal.moveCursor(x, y);
                        terminal.applyForegroundColor(Terminal.Color.RED);
                        terminal.putCharacter('♥');
                    }
                    break;
                case ArrowDown:
                    if (y != 19) {
                        // Put white heart-char in old position
                        terminal.moveCursor(x, y);
                        terminal.applyForegroundColor(Terminal.Color.WHITE);
                        terminal.putCharacter('♥');
                        // Put red heart
                        y += 2;
                        terminal.moveCursor(x, y);
                        terminal.applyForegroundColor(Terminal.Color.RED);
                        terminal.putCharacter('♥');
                    }
                    break;
                case Enter:
                    speedChosen = true;
                    break;
            }
        }

        terminal.clearScreen();
        terminal.applyForegroundColor(Terminal.Color.WHITE);

        // Läs ut Game mode ur y
        int speed = 0;
        switch (y) {
            case 13:
                return speed = 300;
            case 13+2:
                return speed = 150;
            case 13+4:
                return speed = 75;
            case 13+6:
                return speed = 25;
        }
        return 150;
    }

    private static void countDown(Terminal terminal, Scan scanMenu, MP3Player m) throws FileNotFoundException, InterruptedException{
        // Start playing music
        m.play("Snakes.mp3");
    
        // Load Graphics
        terminal.clearScreen();
        scanMenu.scanText("tre", terminal);
        Thread.sleep(1000);
        terminal.clearScreen();
        scanMenu.scanText("tva", terminal);
        Thread.sleep(1000);
        terminal.clearScreen();
        scanMenu.scanText("ett", terminal);
        Thread.sleep(1000);
        terminal.clearScreen();
        scanMenu.scanText("noll", terminal);
        Thread.sleep(500);
    }

    private static void gameRun(Terminal terminal, List<Player> players, MP3Player m, Scan scanMenu) throws InterruptedException, FileNotFoundException {
    
        // Select mode
        String mode = selectMode(terminal, scanMenu);
        
        // Select speed
        int speed = selectSpeed(terminal, scanMenu);
        
        // Load countDown
        countDown(terminal,scanMenu, m);
        
        // Add players
        switch (mode) {
            case "triple":
                players.add(new Player('8','5','4','6', '\u265e', 3, Terminal.Color.BLUE));
            case "double":
                players.add(new Player('i', 'k', 'j', 'l', '\u265a', 2, Terminal.Color.CYAN));
            case "single":
                players.add(new Player('w', 's', 'a', 'd','\u2764', 1, Terminal.Color.GREEN));
                break;
        }

        // Add level
        Scan scanLevel = new Scan();

        // Make apples
        List<Apple> apples = new ArrayList<>();
        Apple apple = new Apple();
        int counter = 0;

        while (true) {

            terminal.clearScreen();

            // Create Apples
            createApples(terminal, apples, apple, counter);

            // Print level
            scanLevel.scanLevel("levelOne", terminal);

            // Put player on terminal
            putPlayerOnTerminal(terminal, players);

            // Sleep
            Thread.sleep(speed);

            // Move
            movePlayers(terminal, players);

            // Check for crash and for apples
            Player.checkForCrash(players, counter);
            Player.checkForApples(players, apples);

            // Check for players death
            boolean playersDead = isPlayersDead(players);

            // Check for game over
            if (playersDead) {
                m.stopAll(); // Stops mp3
                Scan scanGameOver = new Scan();
                scanGameOver.scanText("gameOver", terminal);
                Scan.printScore(players, terminal);
                break;
            }
            
            counter++;
        }
        
        gameOver(terminal, players, m, scanMenu);
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
            terminal.applyForegroundColor(Terminal.Color.RED);
            terminal.putCharacter('\u2689');
        }
    }

    private static void putPlayerOnTerminal(Terminal terminal, List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < players.get(i).getTail().size(); j++) {
                terminal.moveCursor(players.get(i).getTail().get(j).getX(), players.get(i).getTail().get(j).getY());
                terminal.applyForegroundColor(players.get(i).getColor());
                if (j != 0)
                    terminal.putCharacter('\u25E9');
                else
                    terminal.putCharacter(players.get(i).getHeadChar());
            }
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

    private static void gameOver(Terminal terminal, List<Player> players, MP3Player m, Scan scanMenu) throws InterruptedException, FileNotFoundException {
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
                    gameRun(terminal, players, m, scanMenu);
                    break;
            }
        }
    }

}