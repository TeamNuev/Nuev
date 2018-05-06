package ga.nullcraft.util;

import java.util.ArrayList;
import java.util.List;

public class EventSet<A> {
    public abstract class EventRunnable<A> {
        public abstract void on(A args);
    }

    private List<EventRunnable<A>> listenerList;

    public EventSet(){
        this.listenerList = new ArrayList<>();
    }

    public boolean add(EventRunnable<A> eventRunnable){
        return listenerList.add(eventRunnable);
    }

    public boolean remove(EventRunnable<A> eventRunnable){
        return listenerList.remove(eventRunnable);
    }

    public void invoke(A args){
        for (EventRunnable eventRunnable : listenerList){
            eventRunnable.on(args);
        }
    }
}
