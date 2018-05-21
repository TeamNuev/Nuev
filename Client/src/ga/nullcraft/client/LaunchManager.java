package ga.nullcraft.client;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import ga.nullcraft.client.window.GameWindow;
import ga.nullcraft.client.window.WindowManager;
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.NonOptionArgumentSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class LaunchManager {

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

		OptionSet options = parser.parse(args);
    	List<String> nonOptionList = options.valuesOf(nonOptions);

		NuevClient client = new NuevClient(Paths.get(options.valueOf(gameDir)));
		WindowManager windowManager = new WindowManager("Nuev");

		windowManager.run(client);
	}
}
