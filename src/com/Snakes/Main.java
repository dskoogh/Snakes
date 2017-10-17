package com.Snakes;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;
import com.googlecode.lanterna.input.Key;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Terminal terminal = getTerminal();
        Player playerOne = new Player();
        Key key = terminal.readInput();
        
        // Menu, deciding how many players/AIs to participate. Level

        // Playerklass avgör längd, kollisionsavkänning och sparar koordinat och riktning

        // Count down
        while(true) {

            terminal.clearScreen();
            // Put player on terminal
            terminal.moveCursor(playerOne.getHead().getX(), playerOne.getHead().getY());
            terminal.putCharacter('O');
            
            // Sleep
            Thread.sleep(100);
            
            // Move
            playerOne.keyListen(key);
            playerOne.move();
            
            

            // Movementklass som översätter key till ett riktningsbyte - en per spelare

            // Utskrivningsklass

            // Väntar X länge
        }
    }
    
    private static Terminal getTerminal() {
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();
        terminal.setCursorVisible(false);
        return terminal;
    }
}
