package com.Snakes;

import com.googlecode.lanterna.terminal.Terminal;

import java.io.File;
import java.io.FileNotFoundException;
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
        int x = 2;
        int y = 7;
        while (scanner.hasNextLine()) {
            s = scanner.nextLine();
            for (int i = 0; i < s.length() ; i++) {
                terminal.moveCursor(i+x,y);
                terminal.putCharacter(s.charAt(i));

            }
            y++;
        }
    }

}
