package ga.nullcraft.client;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import ga.nullcraft.client.window.NuevWindow;
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.NonOptionArgumentSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class LaunchManager {
	
	private static OptionSet options;
	private static NullcraftClient client;
	private static NuevWindow window;
	
	public static void main(String[] args) throws Exception {
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

    	options = parser.parse(args);
    	List<String> nonOptionList = options.valuesOf(nonOptions);
    	
    	client = new NullcraftClient(Paths.get(options.valueOf(gameDir)));
		window = new NuevWindow(options.valueOf(width), options.valueOf(height), options.valueOf(isFullScreen));
		
		client.start();
	}
	
	public NullcraftClient getClient() {
		return client;
	}
	
	public NuevWindow getWindow() {
		return window;
	}
	
	public OptionSet getOptions() {
		return options;
	}
}
