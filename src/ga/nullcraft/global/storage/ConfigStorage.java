package ga.nullcraft.global.storage;

import ga.nullcraft.global.mod.IMod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigStorage extends Storage<File> {

    private Path path;
    private IMod mod;

    public ConfigStorage(Path path, IMod mod){
        this.path = path;
        this.mod = mod;
    }

    /**
     * Saves a file in a config path of the mod.
     *
     * @param name A name of a file which is going to be saved
     * @return true if succeed
     */
    @Override
    public boolean saveSync(File object, String name) throws IOException {
        return false;
    }

    /**
     * Loads a file in a config path of the mod.
     *
     * @param name A name of a file which is going to be loaded
     * @return A file named name. null if doesn't exist.
     */
    @Override
    public File getSync(String name) throws IOException {
        return null;
    }
}
