package com.Snakes;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

public class Listener {
    private Terminal terminal;

    public Key listenKey() {
        Key key = terminal.readInput();

        return key;
    }
}


