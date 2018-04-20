package ga.nullcraft.global.storage;

import java.io.File;
import java.io.IOException;

public class ConfigStorage extends Storage<File> {

    @Override
    public boolean saveSync(File object, String name) throws IOException {
        return false;
    }

    @Override
    public File getSync(String name) throws IOException {
        return null;
    }
}
