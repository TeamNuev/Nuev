package ga.nullcraft.global.storage;

import ga.nullcraft.global.mod.IMod;

import java.io.IOException;

public class OnlineModStorage extends Storage<IMod> {

    @Override
    public boolean saveSync(IMod object, String name) throws IOException {
        throw new IOException();
    }

    @Override
    public IMod getSync(String name) throws IOException {
        return null;
    }
}
