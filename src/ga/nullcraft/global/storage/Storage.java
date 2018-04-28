package ga.nullcraft.global.storage;

import ga.nullcraft.util.AsyncTask;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Does I/O tasks.
 * key should be a path(local path, file name, web address, etc).
 * Must return same value if key is same.
 *
 * @author TNuev
 */
public abstract class Storage<T> {
    public abstract boolean saveSync(T object, String name) throws IOException, NoSuchAlgorithmException;

    public AsyncTask<Boolean> saveAsync(T object, String name) throws IOException{
        return new AsyncTask<>(new AsyncTask.AsyncCallable<Boolean>() {
            @Override
            public Boolean get() {
                try {
                    return saveSync(object, name);
                } catch (IOException | NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                return null;
            }
        });
    }

    public abstract T getSync(String name) throws IOException;

    public AsyncTask<T> getAsync(String name){
        return new AsyncTask<>(new AsyncTask.AsyncCallable<T>() {
            @Override
            public T get() {
                try {
                    return getSync(name);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        });
    }
}
