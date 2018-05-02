package ga.nullcraft.client;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import com.sun.scenario.effect.impl.Renderer;

import ga.nullcraft.client.audio.AudioManager;
import ga.nullcraft.client.graphics.Mesh;
import ga.nullcraft.client.graphics.NuevMeshItem;
import ga.nullcraft.client.graphics.NuevRenderer;
import ga.nullcraft.client.graphics.PlayerCamera;
import ga.nullcraft.client.local.LocalGameDirectory;
import ga.nullcraft.client.model.ModelManager;
import ga.nullcraft.client.storage.TempStorage;
import ga.nullcraft.client.window.WindowManager;
import ga.nullcraft.global.game.entity.EntityPlayer;
import ga.nullcraft.global.mod.LocalModLoader;
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.NonOptionArgumentSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class NullcraftClient {
    private LocalGameDirectory gameDirectory;
    private LocalModLoader modLoader;

    private WindowManager windowManager;
    private ModelManager modelManager;
    private AudioManager audioManager;

    private TempStorage tempStorage;
    
    private NuevWindow testWindow;
    private NuevGameLoop gameLoop;
    
    private NuevRenderer renderer;
    private PlayerCamera camera;
    
    private float dx;
    private float dy;
    private float dz;
	private NuevMeshItem[] meshItems;

    public NullcraftClient(Path dataDir){
        this.gameDirectory = new LocalGameDirectory(dataDir);
        this.tempStorage = new TempStorage();
    }
    
    public void start() throws Exception {
		LaunchManager launchManager = new LaunchManager();
    	NullcraftClient client = launchManager.getClient();
    	EntityPlayer player = new EntityPlayer(0, 0, 0);
    	
		testWindow = launchManager.getWindow();
		testWindow.init();
    	gameLoop = new NuevGameLoop(client);
    	modLoader = new LocalModLoader(client.gameDirectory.getModStorage());
    	modLoader.loadMods();
    	renderer = new NuevRenderer();
    	camera = new PlayerCamera(player);
    	gameLoop.run();
    	testWindow.close();
    }
	
	public void init() throws Exception {
		renderer.init(testWindow);
		
        float[] positions = new float[]{
            -0.5f,  0.5f, -1.0f,
            -0.5f, -0.5f, -1.0f,
             0.5f, -0.5f, -1.0f,
             0.5f,  0.5f, -1.0f,
        };
        float[] colours = new float[]{
            0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f,
        };
        int[] indices = new int[]{
            0, 1, 3, 3, 1, 2,
        };
        Mesh mesh = new Mesh(positions, colours, indices);
        NuevMeshItem item = new NuevMeshItem(mesh);
        item.setPosition(0.0f, 0.0f, 0.0f);
        meshItems = new NuevMeshItem[] { item };
	}
	
	public void input() {
		dx = 0;
		dy = 0;
		dz = 0;
		if (testWindow.isKeyPressed(GLFW.GLFW_KEY_F11)) {
			testWindow.setScreenMode(!testWindow.isFullScreen());
		}
		if (testWindow.isKeyPressed(GLFW.GLFW_KEY_W)) {
			dz = -1;
		}
		else if (testWindow.isKeyPressed(GLFW.GLFW_KEY_S)) {
			dz = 1;
		}
		if (testWindow.isKeyPressed(GLFW.GLFW_KEY_A)) {
			dx = -1;
		}
		else if (testWindow.isKeyPressed(GLFW.GLFW_KEY_D)) {
			dx = 1;
		}
		if (testWindow.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT)) {
			dy = -1;
		}
		else if (testWindow.isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
			dy = 1;
		}
	}
	
	public void update() {
		camera.movePosition(dx * 0.05f, dy * 0.05f, dz * 0.05f);
	}
	
	public void render() {
        if (testWindow.isResized()) {
            GL11.glViewport(0, 0, testWindow.getWidth(), testWindow.getHeight());
            testWindow.setResized(false);
        }
        testWindow.clear();
        renderer.render(testWindow, camera, meshItems);
	}

	public void cleanup() {
		renderer.cleanup();
		for(NuevMeshItem item : meshItems) {
			item.getMesh().cleanup();
		}
	}
	 
    public NuevWindow getWindow() {
    	return testWindow;
    }
    
    public LocalModLoader getModLoader() {
    	return modLoader;
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

}
