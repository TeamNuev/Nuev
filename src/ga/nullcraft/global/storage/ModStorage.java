package ga.nullcraft.global.storage;

import ga.nullcraft.global.mod.IMod;

import java.io.IOException;

public class ModStorage extends Storage<IMod> {

    @Override
    public boolean saveSync(IMod mod, String name) throws IOException {
        throw new IOException();
    }

    @Override
    public IMod getSync(String name) {
        return null;
    }
}
