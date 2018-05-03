package ga.nullcraft.client.local;

import java.nio.file.Path;

import ga.nullcraft.client.storage.AssetStorage;
import ga.nullcraft.client.storage.CacheStorage;
import ga.nullcraft.global.GameDirectory;

public class LocalGameDirectory extends GameDirectory {

    public LocalGameDirectory(Path path){
        super(path);
    }

    public CacheStorage getCacheStorage() {
        return new CacheStorage(path.resolve(".data"));
    }

    public AssetStorage getAssetStorage() {
        return new AssetStorage(path.resolve("assets"));
    }
}
