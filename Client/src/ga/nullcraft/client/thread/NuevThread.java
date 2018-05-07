package ga.nullcraft.client.thread;

public abstract class NuevThread extends Thread implements Runnable {

    private Runnable handler;

    public NuevThread(Runnable handler){
        this.handler = handler;
    }

    @Override
    public void run(){
        runInternal();
    }

    protected final void runInternal(){
        this.handler.run();
    }
}
