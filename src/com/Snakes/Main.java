package com.Snakes;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Terminal terminal = getTerminal();
        List<Player> players = new ArrayList<>();

        System.out.println('\u262d');
        
        // Menu, deciding how many players/AIs to participate. Level
        
        // Count down splash
        
        
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
    
    private static void gameRun(Terminal terminal, List<Player> players) throws InterruptedException {
        // Add players
        players.add(new Player('w', 's', 'a', 'd'));
        players.add(new Player('i', 'k', 'j', 'l'));
    
    
        while (true) {
            
            terminal.clearScreen();
            // Put player on terminal
            for (int j = 0; j < players.size(); j++) {
                for (int i = 0; i < players.get(j).getTail().size(); i++) {
                    terminal.moveCursor(players.get(j).getTail().get(i).getX(), players.get(j).getTail().get(i).getY());
                    if (i != 0)
                        terminal.putCharacter('Z');
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
                break;
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
