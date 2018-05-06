package ga.nullcraft.client.thread;

public abstract class NuevThread extends Thread {

    private Runnable handler;

    public NuevThread(Runnable handler){
        this.handler = handler;
    }

    @Override
    public void run(){
        this.handler.run();
    }
}
