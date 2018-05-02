package ga.nullcraft.client.resource;

import ga.nullcraft.global.mod.loader.LocalFullModLoader;
import ga.nullcraft.global.storage.ModStorage;

public class ClientModLoader extends LocalFullModLoader {
    public ClientModLoader(ModStorage modStorage) {
        super(modStorage);
    }
}
