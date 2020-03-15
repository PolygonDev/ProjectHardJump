package de.polygondev.hjp.ctrl;

import java.util.ArrayList;

public class TimerController {

    private ArrayList<Timer> timers = new ArrayList<>();

    public boolean running = true;

    public TimerController() {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                threadAction();
            }
        });

        t.start();
    }

    private void threadAction() {
        while(running) {
            check();
        }
    }

    private void check() {
        long currentTime = System.currentTimeMillis();
        //Loop through the timers
        for (int i = 0; i<timers.size(); i++) {
            Timer timer = timers.get(i);

            //If the timer is elapsed
            if (currentTime - timer.getStartTime() >= timer.getDuration()) {
                timer.action();
                timers.remove(timer);
                break;
            }
        }
    }

    public void addTimer(Timer timer) {
        this.timers.add(timer);
    }

}
