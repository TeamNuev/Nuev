package ga.nullcraft.global.storage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import ga.nullcraft.util.AsyncTask;

/**
 * Does I/O tasks.
 * key should be a path(local path, file name, web address, etc).
 * Must return same value if key is same.
 *
 * @author TNuev
 */
public abstract class Storage<T> {
	
	/**
	 * Saves an object into file.
	 * 
	 * @param object to be saved
	 * @param name
	 * @return true if succeed
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
    public abstract boolean saveSync(T object, String name) throws IOException;

    /**
     * Saves an object into file.
     * Calls {@link Storage#saveSync(Object, String)} asynchronously.
     * 
     * @param object to be saved.
     * @param name
     * @return
     * @throws IOException
     */
    public AsyncTask<Boolean> saveAsync(T object, String name) throws IOException{
        return new AsyncTask<>(new AsyncTask.AsyncCallable<Boolean>() {
            @Override
            public Boolean get() {
                try {
                    return saveSync(object, name);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        });
    }

    /**
     * Get objects from file.
     * 
     * @param name
     * @return
     * @throws IOException
     */
    public abstract T getSync(String name) throws IOException;

    /**
     * Get objects from file.
     * Calls {@link Storage#getSync(String)} asynchronously.
     * 
     * @param name
     * @return
     */
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
