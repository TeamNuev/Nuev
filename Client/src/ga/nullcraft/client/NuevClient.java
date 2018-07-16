package ga.nullcraft.client;

import java.nio.file.Path;

import ga.nullcraft.client.audio.AudioManager;
import ga.nullcraft.client.graphics.base.ICamera;
import ga.nullcraft.client.graphics.base.IHud;
import ga.nullcraft.client.graphics.base.Mesh;
import ga.nullcraft.client.graphics.base.NuevMeshItem;
import ga.nullcraft.client.graphics.base.NuevRenderer;
import ga.nullcraft.client.graphics.base.PlayerCamera;
import ga.nullcraft.client.local.LocalGameDirectory;
import ga.nullcraft.client.model.ModelManager;
import ga.nullcraft.client.platform.input.keyboard.KeyboardListener;
import ga.nullcraft.client.platform.input.mouse.MouseListener;
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
	
	private IHud hud;

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
		
        float[] positions = new float[]{
            -0.5f,  0.5f, -1.0f,
            -0.5f, -0.5f, -1.0f,
             0.5f, -0.5f, -1.0f,
             0.5f,  0.5f, -1.0f,
        };
        float[] colours = new float[]{
            0.3f, 0.5f, 0.5f,
            0.4f, 0.5f, 0.5f,
            0.5f, 0.5f, 0.5f,
            0.6f, 0.5f, 0.5f,
        };
        int[] indices = new int[]{
            0, 1, 3, 3, 1, 2,
        };
        Mesh mesh = new Mesh(positions, colours, indices);
        NuevMeshItem item = new NuevMeshItem(mesh);
        item.setPosition(0.0f, 0.0f, 0.0f);
        meshItems = new NuevMeshItem[] { item };
	}
	
	public void render() {
        renderer.renderScene(getWindowManager().getWindow(), camera, meshItems);
        if(hud != null) renderer.renderHud(getWindowManager().getWindow(), hud);
	}

	public void cleanUp() {
		renderer.cleanUp();
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
    
    public ICamera getCamera() {
    	return camera;
    }

    public void update() {

    }
}