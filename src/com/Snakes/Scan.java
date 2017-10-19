package com.Snakes;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Scan {
    private Scanner scanner;

    public void scanText(String file, Terminal terminal, Terminal.Color color) throws FileNotFoundException {

        if (file == null) {
            System.out.println("Error");
        } else {
            scanner = new Scanner(new File(file));
        }
        printScan(terminal, color);
        scanner.close();
    }

    public void scanSplash(String file, Terminal terminal) throws FileNotFoundException, InterruptedException {

        if (file == null) {
            System.out.println("Error");
        } else {
            scanner = new Scanner(new File(file));
        }
        printSplash(terminal);
        scanner.close();
    }

    private void printSplash(Terminal terminal) throws FileNotFoundException, InterruptedException {
        String s = "";
        Random r = new Random();
        int color = 0;
        int x = 0;
        int y = 0;

        while (scanner.hasNextLine()) {
            s = scanner.nextLine();
            for (int i = 0; i < s.length(); i++) {
                color = r.nextInt(5);
                switch (color) {
                    case 0:
                        terminal.applyForegroundColor(Terminal.Color.BLUE);
                        break;
                    case 1:
                        terminal.applyForegroundColor(Terminal.Color.RED);
                        break;
                    case 2:
                        terminal.applyForegroundColor(Terminal.Color.GREEN);
                        break;
                    case 3:
                        terminal.applyForegroundColor(Terminal.Color.CYAN);
                        break;
                    case 4:
                        terminal.applyForegroundColor(Terminal.Color.MAGENTA);
                        break;
                    case 5:
                        terminal.applyForegroundColor(Terminal.Color.YELLOW);
                }
                terminal.moveCursor(i + x, y);
                terminal.putCharacter(s.charAt(i));
            }
            y++;
        }
        Thread.sleep(150);
    }

    private void printScan(Terminal terminal, Terminal.Color color) {
        String s = "";
        terminal.applyForegroundColor(color);
        int x = 0;
        int y = 0;
        while (scanner.hasNextLine()) {
            s = scanner.nextLine();
            for (int i = 0; i < s.length(); i++) {
                terminal.moveCursor(i + x, y);
                terminal.putCharacter(s.charAt(i));
            }
            y++;
        }
    }

    public void scanLevel(String file, Terminal terminal) throws FileNotFoundException {

        if (file == null) {
            System.out.println("Error");
        } else {
            scanner = new Scanner(new File(file));
        }
        printLevel(terminal);
        scanner.close();
    }

    private void printLevel(Terminal terminal) {
        String s = "";
        int x = 0;
        int y = 0;
        while (scanner.hasNextLine()) {
            s = scanner.nextLine();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '█') {
                    terminal.moveCursor(i + x, y);
                    terminal.applyBackgroundColor(Terminal.Color.WHITE);
                    terminal.applyForegroundColor(Terminal.Color.WHITE);
                    terminal.putCharacter(s.charAt(i));
                }
            }
            y++;
        }
        terminal.applyBackgroundColor(Terminal.Color.BLACK);
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
