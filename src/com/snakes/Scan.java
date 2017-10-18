package com.snakes;

import com.googlecode.lanterna.terminal.Terminal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Scan {
    private Scanner scanner;

    public void scanText(String file, Terminal terminal) throws FileNotFoundException {

        if (file == null) {
            System.out.println("Error");
        } else {
            scanner = new Scanner(new File(file));
        }
        printScan(terminal);
        scanner.close();
    }

    private void printScan(Terminal terminal) {
        String s = "";
        int x = 0;
        int y = 0;
        while (scanner.hasNextLine()) {
            s = scanner.nextLine();
            for (int i = 0; i < s.length() ; i++) {
                terminal.moveCursor(i+x,y);
                terminal.putCharacter(s.charAt(i));
            }
            y++;
        }
    }

    public static void printScore(List<Player> players, Terminal terminal) {
        String s;
        int x = 10;
        int y = 5;

        for (int i = 0; i < players.size(); i++) {
            s = "Player " + (i + 1) + ": " + players.get(i).getPoint();
            for (int j = 0; j < s.length(); j++) {
                terminal.moveCursor(j + x, y);
                terminal.putCharacter(s.charAt(j));
            }
            y++;
        }
    }
}
