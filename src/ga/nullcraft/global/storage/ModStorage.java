package ga.nullcraft.global.storage;

import ga.nullcraft.global.mod.IMod;

import java.io.IOException;
import java.nio.file.Path;

public class ModStorage extends Storage<IMod> {

    private Path path;

    public ModStorage(Path path){
        this.path = path;
    }

    @Override
    public boolean saveSync(IMod mod, String name) throws IOException {
        throw new IOException();
    }

    @Override
    public IMod getSync(String name) {
        return null;
    }
}
