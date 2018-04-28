package ga.nullcraft.client.local;

import ga.nullcraft.client.storage.AssetStorage;
import ga.nullcraft.global.IGameDirectory;
import ga.nullcraft.global.mod.IMod;
import ga.nullcraft.global.storage.ConfigStorage;
import ga.nullcraft.global.storage.ModStorage;
import ga.nullcraft.global.storage.WorldStorage;

import java.nio.file.Path;

public class LocalGameDirectory implements IGameDirectory {

    private Path path;

    public LocalGameDirectory(Path path){
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    public AssetStorage getAssetStorage() {
        return new AssetStorage(path.resolve("assets"));
    }

    @Override
    public WorldStorage getWorldStorage() {
        return new WorldStorage(path.resolve("saves"));
    }

    @Override
    public ModStorage getModStorage() {
        return new ModStorage(path.resolve("mods"));
    }

    @Override
    public ConfigStorage getConfigStorage(IMod mod) {
        return new ConfigStorage(path.resolve("config"), mod);
    }
}
