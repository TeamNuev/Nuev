package ga.nullcraft.client;

import ga.nullcraft.client.local.GameDirectory;
import ga.nullcraft.global.mod.ModLoader;

public class NullcraftClient {
    private static GameDirectory gameDirectory;
    private static ModLoader modLoader;

    public NullcraftClient(){

    }

    public GameDirectory getGameDirectory() {
        return gameDirectory;
    }

    public static void main(String[] args){
    	modLoader = new ModLoader(gameDirectory.getModStorage());
    	modLoader.loadMods();
    }
}
