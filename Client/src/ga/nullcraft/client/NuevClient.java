package ga.nullcraft.client;

import java.nio.file.Path;

import ga.nullcraft.client.audio.AudioManager;
import ga.nullcraft.client.graphics.Mesh;
import ga.nullcraft.client.graphics.NuevMeshItem;
import ga.nullcraft.client.graphics.NuevRenderer;
import ga.nullcraft.client.graphics.NuevTexture;
import ga.nullcraft.client.graphics.ObjLoader;
import ga.nullcraft.client.graphics.PlayerCamera;
import ga.nullcraft.client.listener.KeyboardListener;
import ga.nullcraft.client.listener.MouseListener;
import ga.nullcraft.client.local.LocalGameDirectory;
import ga.nullcraft.client.model.ModelManager;
import ga.nullcraft.client.storage.TempStorage;
import ga.nullcraft.client.window.WindowManager;
import ga.nullcraft.global.game.entity.EntityPlayer;
import ga.nullcraft.global.mod.loader.LocalFullModLoader;
import ga.nullcraft.global.mod.loader.LocalHalfModLoader;

public class NuevClient {
	private static NuevClient client;
	
    private LocalGameDirectory gameDirectory;
    private LocalFullModLoader fullModLoader;
    private LocalHalfModLoader halfModLoader;

    private WindowManager windowManager;
    private ModelManager modelManager;
    private AudioManager audioManager;

    private TempStorage tempStorage;
    
    private NuevRenderer renderer;
    private PlayerCamera camera;
    
	private NuevMeshItem[] meshItems;
	
	private MouseListener mouseListener;
	private KeyboardListener keyboardListener;
	
	private EntityPlayer player;

    public NuevClient(Path dataDir){
    	client = this;
        this.gameDirectory = new LocalGameDirectory(dataDir);
        this.tempStorage = new TempStorage();
    }
    
    public static NuevClient getClient() {
    	return client;
    }
    
    
    public void start(WindowManager windowManager) throws Exception {
    	this.windowManager = windowManager;

		LaunchManager launchManager = new LaunchManager();
    	player = new EntityPlayer(null, 0, 0, 0);

    	fullModLoader = new LocalFullModLoader(this.getGameDirectory().getFullModStorage());
    	fullModLoader.loadMods();
    	halfModLoader = new LocalHalfModLoader(this.getGameDirectory().getHalfModStorage());
    	halfModLoader.loadMods();

    	renderer = new NuevRenderer();
    	camera = new PlayerCamera(player);
    	
    	mouseListener = new MouseListener(camera);
    	keyboardListener = new KeyboardListener();

        windowManager.getInput().getMouse().addListener(mouseListener);
        windowManager.getInput().getKeyboard().addListener(keyboardListener);
    }
	
	public void init() throws Exception {

        renderer.init(getWindowManager().getWindow());
		
        Mesh mesh = ObjLoader.loadMesh("/testObj.obj");
        NuevTexture texture = new NuevTexture("/textures/grassblock.png");
        mesh.setTexture(texture);
        NuevMeshItem item = new NuevMeshItem(mesh);
        item.setPosition(0.0f, 0.0f, 0.0f);
        meshItems = new NuevMeshItem[] { item };
	}
	
	public void render() {
        renderer.render(getWindowManager().getWindow(), camera, meshItems);
	}

	public void cleanup() {
		renderer.cleanup();
		for(NuevMeshItem item : meshItems) {
			item.getMesh().cleanUp();
		}
	}
    
    public LocalFullModLoader getModLoader() {
    	return fullModLoader;
    }
    
    public LocalHalfModLoader getHalfModLoader() {
    	return halfModLoader;
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
    
    public MouseListener getMouseListener() {
    	return mouseListener;
    }
    
    public KeyboardListener getKeyboardListener() {
    	return keyboardListener;
    }
    
    public EntityPlayer getPlayer() {
    	return player;
    }

    public void update() {

    }
}