package model;

import java.util.Timer;
import java.util.TimerTask;

public class TimerForParse {
    public void timer(Runnable task) {
        int minutes = 10;
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                task.run();
            }
        }, 0, 1000 * 60 * minutes);
    }
}
