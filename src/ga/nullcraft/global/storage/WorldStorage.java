package ga.nullcraft.global.storage;

public class WorldStorage extends Storage<Object> {

    @Override
    public boolean saveSync(Object object, String name) {
        return false;
    }

    @Override
    public Object getSync(String name) {
        return null;
    }
}
