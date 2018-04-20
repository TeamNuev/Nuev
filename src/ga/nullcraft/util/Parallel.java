package ga.nullcraft.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Parallel {
    private static final int NUM_CORES = Runtime.getRuntime().availableProcessors();

    private static final ExecutorService forPool = Executors.newFixedThreadPool(NUM_CORES * 2);

    public static <T> void forEach(Iterable<T> elements, Operation<T> operation) {
        try {
            forPool.invokeAll(createCallable(elements, operation));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static <T> Collection<Callable<Void>> createCallable(final Iterable<T> elements, final Operation<T> operation) {
        List<Callable<Void>> callables = new LinkedList<>();

        for (final T element : elements) {
            callables.add(new Callable<Void>() {
                @Override
                public Void call() {
                    operation.run(element);
                    return null;
                }
            });
        }

        return callables;
    }

    public interface Operation<T> {
        void run(T param);
    }
}