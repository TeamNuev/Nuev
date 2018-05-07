package ga.nullcraft.client.thread;

import ga.nullcraft.client.timing.ThreadLimiter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class LoopThread extends NuevThread {

    private ThreadLimiter limiter;

    public LoopThread(Runnable handler) {
        super(handler);

        this.limiter = new ThreadLimiter(0);
    }

    public double getHz(){
        return 1000 / limiter.getInterval();
    }

    public void setHz(double hz){
        limiter.setInterval(1000 / hz);
    }

    public double getElapsed(){
        return limiter.getElapsed();
    }

    public void initialize(){

    }

    @Override
    public void run(){
        initialize();

        while(!isInterrupted()) {
            runInternal();

            try {
                double next = limiter.next();
                while(System.nanoTime() < next) {
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {

            }
        }
    }
}
