package de.polygondev.hjp.timers;

import de.polygondev.hjp.ctrl.Timer;

public class TestTimer extends Timer {
    public TestTimer(long ms) {
        super(ms);
    }

    @Override
    public void action() {
        System.out.println("Test");
    }
}
