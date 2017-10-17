package com.Snakes;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();
        terminal.setCursorVisible(false);

        Player playerOne = new Player();

        
        // Menu, deciding how many players/AIs to participate. Level

        // Playerklass avgör längd, kollisionsavkänning och sparar koordinat och riktning

        // Count down
        while(true) {

            terminal.clearScreen();

            terminal.moveCursor(playerOne.getTail(playerOne.getTail().size()));

            //skriv ut
            //sov
            //key

            // Game starting

            // Keylyssnarklass som avslyssnar vilka keys som trycks

            // Movementklass som översätter key till ett riktningsbyte - en per spelare

            // Utskrivningsklass

            // Väntar X länge
        }
    }
}
