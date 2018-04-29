package ga.nullcraft.client.local;

import java.nio.file.Path;

import ga.nullcraft.client.storage.AssetStorage;
import ga.nullcraft.global.GameDirectory;

public class LocalGameDirectory extends GameDirectory {

    public LocalGameDirectory(Path path){
        super(path);
    }

    public AssetStorage getAssetStorage() {
        return new AssetStorage(path.resolve("assets"));
    }
}
