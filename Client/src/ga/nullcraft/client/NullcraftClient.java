package ga.nullcraft.client;

import ga.nullcraft.client.local.LocalGameDirectory;
import ga.nullcraft.global.IGameDirectory;
import ga.nullcraft.global.mod.ModLoader;

import java.nio.file.Path;

public class NullcraftClient {
    private static LocalGameDirectory gameDirectory;
    private static ModLoader modLoader;

    public NullcraftClient(Path dataDir){
        this.gameDirectory = new LocalGameDirectory(dataDir);
    }

    public LocalGameDirectory getGameDirectory() {
        return gameDirectory;
    }

    public static void main(String[] args){
    	modLoader = new ModLoader(gameDirectory.getModStorage());
    	modLoader.loadMods();
    }
}
