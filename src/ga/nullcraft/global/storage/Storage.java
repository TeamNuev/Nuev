package ga.nullcraft.global.storage;

import ga.nullcraft.util.AsyncTask;

import java.io.IOException;

/**
 * Storage는 문자열을 key로 받아 io 작업을 처리 하게
 * 됩니다. key로 받을수 있는 문자열에는 규칙성이 있어야
 * 하며(경로, 파일 이름, 웹 주소 등등), 같은 key 값은 무조건 같은 반환값을 반환해야 합니다.
 *
 * @author      storycraft
 */
public abstract class Storage<T> {
    public abstract boolean saveSync(T object, String name) throws IOException;

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
