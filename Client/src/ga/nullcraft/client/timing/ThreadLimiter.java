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
        elapsed = Math.max((lastTime = System.nanoTime()) - lastTime, interval);
        return lastTime + interval;
    }

}
