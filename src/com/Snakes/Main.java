package com.Snakes;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.input.Key;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Terminal terminal = getTerminal();
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            players.add(new Player());
        }
//        Player playerOne = new Player();
//        Player playerTwo = new Player();
        boolean isAlive = true;

        // Menu, deciding how many players/AIs to participate. Level

        // Count down
        while (isAlive) {

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

        }
        System.out.println("GAME OVER");
    }

//    private static boolean checkForCrash(List<Player> players, boolean isAlive) {
//        for (int i = 0; i < players.size(); i++) {
//            for (int j = 1; j < players.get(i).getTail().size(); j++) {
//                if (players.get(i).getHead().getX() == players.get(i).getTail().get(j).getX() && players.get(i).getHead().getY() == players.get(i).getTail().get(j).getY()) {
//                    isAlive = false;
//                    break;
//                }
//            }
//        }
//        return isAlive;
//    }

    private static Terminal getTerminal() {
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();
        terminal.setCursorVisible(false);
        return terminal;
    }
}
