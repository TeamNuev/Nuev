package ga.nullcraft.server.resource;

import ga.nullcraft.global.mod.loader.LocalFullModLoader;
import ga.nullcraft.global.storage.ModStorage;

public class ServerModLoader extends LocalFullModLoader {
    public ServerModLoader(ModStorage modStorage) {
        super(modStorage);
    }
}
