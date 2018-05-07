package ga.nullcraft.client.timing;

public class ThreadLimiter {

    private double interval;
    private double elapsed;
    private long lastTime;

    public ThreadLimiter(double interval){
        this.interval = interval;
        this.lastTime = System.nanoTime();
    }

    public double getInterval() {
        return interval;
    }

    public double getElapsed() {
        return elapsed;
    }

    public void setInterval(double interval) {
        this.interval = interval;
    }

    public double next(){
        long now = System.nanoTime();
        elapsed = Math.max((now - lastTime) / 1000000d, interval);
        lastTime = now;

        return lastTime + Math.min(interval, Math.max((interval * 2 - elapsed), 0)) * 1000000d;
    }

}
