package ga.nullcraft.server;

import ga.nullcraft.global.IGameDirectory;
import ga.nullcraft.global.mod.IMod;
import ga.nullcraft.global.storage.ConfigStorage;
import ga.nullcraft.global.storage.ModStorage;
import ga.nullcraft.global.storage.WorldStorage;

import java.nio.file.Path;

public class ServerGameDirectory implements IGameDirectory {

    private Path path;

    public ServerGameDirectory(Path path){
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    @Override
    public WorldStorage getWorldStorage() {
        return new WorldStorage(path.resolve("world"));
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
