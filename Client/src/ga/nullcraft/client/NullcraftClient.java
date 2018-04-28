package ga.nullcraft.client;

import ga.nullcraft.client.audio.AudioManager;
import ga.nullcraft.client.graphics.WindowManager;
import ga.nullcraft.client.local.LocalGameDirectory;
import ga.nullcraft.client.model.ModelManager;
import ga.nullcraft.client.storage.TempStorage;
import ga.nullcraft.global.IGameDirectory;
import ga.nullcraft.global.mod.ModLoader;

import java.nio.file.Path;

public class NullcraftClient {
    private static LocalGameDirectory gameDirectory;
    private static ModLoader modLoader;

    private WindowManager windowManager;
    private ModelManager modelManager;
    private AudioManager audioManager;

    private TempStorage tempStorage;

    public NullcraftClient(Path dataDir){
        this.gameDirectory = new LocalGameDirectory(dataDir);
        this.tempStorage = new TempStorage();
    }

    public LocalGameDirectory getGameDirectory() {
        return gameDirectory;
    }

    public WindowManager getWindowManager() {
        return windowManager;
    }

    public ModelManager getModelManager() {
        return modelManager;
    }

    public AudioManager getAudioManager() {
        return audioManager;
    }

    public TempStorage getTempStorage() {
        return tempStorage;
    }

    public static void main(String[] args){
    	modLoader = new ModLoader(gameDirectory.getModStorage());
    	modLoader.loadMods();
    }
}
