package ga.nullcraft.global;

import ga.nullcraft.global.mod.IMod;
import ga.nullcraft.global.storage.ConfigStorage;
import ga.nullcraft.global.storage.ModStorage;
import ga.nullcraft.global.storage.WorldStorage;

public interface IGameDirectory {
    ConfigStorage getConfigStorage(IMod mod);

    WorldStorage getWorldStorage();

    ModStorage getModStorage();
}

