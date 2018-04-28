package ga.nullcraft.global.storage;

import java.nio.file.Path;

public class WorldStorage extends Storage<Object> {

    private Path path;

    public WorldStorage(Path path){
        this.path = path;
    }

    @Override
    public boolean saveSync(Object object, String name) {
        return false;
    }

    @Override
    public Object getSync(String name) {
        return null;
    }
}
