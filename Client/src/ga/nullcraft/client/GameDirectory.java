package ga.nullcraft.client;

import ga.nullcraft.global.IGameDirectory;
import ga.nullcraft.global.mod.IMod;
import ga.nullcraft.global.storage.ConfigStorage;
import ga.nullcraft.global.storage.ModStorage;
import ga.nullcraft.global.storage.WorldStorage;

import java.nio.file.Path;

public class GameDirectory implements IGameDirectory {

    private Path path;

    public GameDirectory(Path path){
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    @Override
    public WorldStorage getWorldStorage() {
        return null;
    }

    @Override
    public ModStorage getModStorage() {
        return null;
    }

    @Override
    public ConfigStorage getConfigStorage(IMod mod) {
        return null;
    }
}
