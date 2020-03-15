package de.polygondev.hjp.ctrl;

public class Timer {

    private long duration = 0L;
    private long startTime = 0L;

    public Timer(long ms) {
        duration = ms;
        startTime = System.currentTimeMillis();
    }

    public void action() {

    }

    public long getDuration() {
        return this.duration;
    }

    public long getStartTime() {
        return startTime;
    }
}
