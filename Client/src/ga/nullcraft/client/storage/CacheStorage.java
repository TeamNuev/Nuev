package ga.nullcraft.client.storage;

import ga.nullcraft.global.storage.Storage;

import java.io.IOException;
import java.nio.file.Path;

/**
 * CacheStorage is client cache directory
 * which can save server mods, online textures, etc
 *
 * @author TNuev
 */
public class CacheStorage extends Storage<byte[]> {

    private Path path;

    public CacheStorage(Path path) {
        this.path = path;
    }

    @Override
    public boolean saveSync(byte[] object, String name) throws IOException {
        return false;
    }

    @Override
    public byte[] getSync(String name) throws IOException {
        return new byte[0];
    }
}
