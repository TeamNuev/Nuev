package ga.nullcraft.client;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import ga.nullcraft.client.audio.AudioManager;
import ga.nullcraft.client.graphics.WindowManager;
import ga.nullcraft.client.local.LocalGameDirectory;
import ga.nullcraft.client.model.ModelManager;
import ga.nullcraft.client.storage.TempStorage;
import ga.nullcraft.global.mod.ModLoader;
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.NonOptionArgumentSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class NullcraftClient {
    private LocalGameDirectory gameDirectory;
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
		String defaultPath = System.getProperty("user.home") + File.separator + "Nuev";
		
		//Parsing arguments
    	OptionParser parser = new OptionParser();
    	parser.allowsUnrecognizedOptions();
    	parser.accepts("width");
    	parser.accepts("height");
    	parser.accepts("fullscreen");
    	parser.accepts("userToken");
    	parser.accepts("gameDir");
    	ArgumentAcceptingOptionSpec<Integer> width = parser.accepts("width").withOptionalArg().ofType(Integer.class).defaultsTo(800);
    	ArgumentAcceptingOptionSpec<Integer> height = parser.accepts("height").withOptionalArg().ofType(Integer.class).defaultsTo(500);
    	ArgumentAcceptingOptionSpec<Boolean> isFullScreen = parser.accepts("fullscreen").withOptionalArg().ofType(Boolean.class).defaultsTo(false);
    	ArgumentAcceptingOptionSpec<String> userToken = parser.accepts("userToken").withOptionalArg().ofType(String.class);
    	ArgumentAcceptingOptionSpec<String> gameDir = parser.accepts("gameDir").withOptionalArg().ofType(String.class).defaultsTo(defaultPath);
    	NonOptionArgumentSpec<String> nonOptions = parser.nonOptions();
    	OptionSet options = parser.parse(args);
    	List<String> nonOptionList = options.valuesOf(nonOptions);

    	NullcraftClient client = new NullcraftClient(Paths.get((String)options.valueOf(gameDir)));
    	NuevWindow testWindow = new NuevWindow((int)options.valueOf(width), (int)options.valueOf(height), (boolean)options.valueOf(isFullScreen));
    	testWindow.run();
    	modLoader = new ModLoader(client.gameDirectory.getModStorage());
    	modLoader.loadMods();
    }
}
