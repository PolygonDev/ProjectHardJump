package de.polygondev.hjp.ctrl;

import de.polygondev.hjp.timers.TestTimer;

public class Main {

    public static void main(String[] args) {

        Resources.as_player.load();

        TimerController tc = new TimerController();
        tc.addTimer(new TestTimer(8000));

	    GameController gc = new GameController();
    }
}
