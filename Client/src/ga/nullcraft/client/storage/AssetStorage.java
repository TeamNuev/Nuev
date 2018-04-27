package ga.nullcraft.client.storage;

import ga.nullcraft.global.storage.Storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Mainly saves resource files.
 * key is the path of a resource file.
 * The file is saved in [first two letters of the hash value of key]/[the hash value of key] 
 *
 * @see Storage
 * @author TNuev
 */
public class AssetStorage extends Storage<File> {

    private Path path;

    public AssetStorage(Path path){
        this.path = path;
    }

    /**
     * Saves a file in the game assets directory.
     *
     * @param name A name with a path of a file which is going to be saved.
     * @return true if succeed
     */
    @Override
    public boolean saveSync(File object, String name) throws IOException {
        return false;
    }

    /**
     * Load a file in game assets directory.
     *
     * @param A name with a path of a file which is going to be saved. 
     * @return asset file. null if not exists.
     */
    @Override
    public File getSync(String name) throws IOException {
        return null;
    }
}
