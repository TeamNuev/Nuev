package ga.nullcraft.client.resource;

import ga.nullcraft.global.mod.ModLoader;
import ga.nullcraft.global.storage.ModStorage;
import ga.nullcraft.server.resource.ServerModLoader;

public class LocalModLoader extends ModLoader {
    public LocalModLoader(ModStorage modStorage) {
        super(modStorage);
    }
}
