package ga.nullcraft.client.storage;

import ga.nullcraft.global.storage.Storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

/**
 * TempStorage is temp storage
 * you can create or remove temp files
 *
 * @author TNuev
 */
public class TempStorage extends Storage<byte[]> {

    private Path tempPath;

    public TempStorage(){
        this(UUID.randomUUID().toString());
    }

    public TempStorage(String prefix){
        try {
            this.tempPath = Files.createTempDirectory(prefix);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
