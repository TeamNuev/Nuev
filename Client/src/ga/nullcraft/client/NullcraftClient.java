package ga.nullcraft.client;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import ga.nullcraft.client.audio.AudioManager;
import ga.nullcraft.client.local.LocalGameDirectory;
import ga.nullcraft.client.model.ModelManager;
import ga.nullcraft.client.storage.TempStorage;
import ga.nullcraft.client.window.WindowManager;
import ga.nullcraft.global.mod.LocalModLoader;
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.NonOptionArgumentSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class NullcraftClient {
    private LocalGameDirectory gameDirectory;
    private static LocalModLoader modLoader;

    private WindowManager windowManager;
    private ModelManager modelManager;
    private AudioManager audioManager;

    private TempStorage tempStorage;
    
    private static NuevWindow testWindow;

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
		Path defaultPath = Paths.get(System.getProperty("user.home"), "Nuev");
		
		//Parsing arguments
    	OptionParser parser = new OptionParser();
    	parser.allowsUnrecognizedOptions();
    	parser.accepts("width");
    	parser.accepts("height");
    	parser.accepts("fullscreen");
    	parser.accepts("userToken");
    	parser.accepts("gameDir");

    	ArgumentAcceptingOptionSpec<Integer> width = parser.accepts("width").withOptionalArg().ofType(Integer.class).defaultsTo(-1);
    	ArgumentAcceptingOptionSpec<Integer> height = parser.accepts("height").withOptionalArg().ofType(Integer.class).defaultsTo(-1);
    	ArgumentAcceptingOptionSpec<Boolean> isFullScreen = parser.accepts("fullscreen").withOptionalArg().ofType(Boolean.class).defaultsTo(false);
    	ArgumentAcceptingOptionSpec<String> userToken = parser.accepts("userToken").withOptionalArg().ofType(String.class);
    	ArgumentAcceptingOptionSpec<String> gameDir = parser.accepts("gameDir").withOptionalArg().ofType(String.class).defaultsTo(defaultPath.toString());
    	NonOptionArgumentSpec<String> nonOptions = parser.nonOptions();

    	OptionSet options = parser.parse(args);
    	List<String> nonOptionList = options.valuesOf(nonOptions);

    	NullcraftClient client = new NullcraftClient(Paths.get(options.valueOf(gameDir)));

    	testWindow = new NuevWindow(options.valueOf(width), options.valueOf(height), options.valueOf(isFullScreen));
        testWindow.init();
    	modLoader = new LocalModLoader(client.gameDirectory.getModStorage());
    	modLoader.loadMods();
    	client.gameLoop();
    	testWindow.close();
    }
	
	private void gameLoop() {
		double secsPerUpdate = 1000000000.0d / 30.0d;
		long previous = System.nanoTime();
		long steps = 0;
		while (!testWindow.windowShouldClose()) {
			long loopStartTime = System.nanoTime();
			long elapsed = loopStartTime - previous;
			previous = loopStartTime;
			steps += elapsed;

			// handleInput();

			while (steps >= secsPerUpdate) {
				// updateGameState();
				steps -= secsPerUpdate;
			}
			testWindow.loop();
			sync(loopStartTime);
		}
	}
	private void sync(long loopStartTime) {
		   float loopSlot = 1f / 50;
		   double endTime = loopStartTime + loopSlot; 
		   while(System.nanoTime() < endTime) {
		       try {
		           Thread.sleep(1);
		       } catch (InterruptedException ie) {}
		   }
		}
}
