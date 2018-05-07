package ga.nullcraft.client;

import java.nio.file.Path;

import ga.nullcraft.client.audio.AudioManager;
import ga.nullcraft.client.graphics.Mesh;
import ga.nullcraft.client.graphics.NuevMeshItem;
import ga.nullcraft.client.graphics.NuevRenderer;
import ga.nullcraft.client.graphics.PlayerCamera;
import ga.nullcraft.client.local.LocalGameDirectory;
import ga.nullcraft.client.model.ModelManager;
import ga.nullcraft.client.platform.input.keyboard.IKeyboardListener;
import ga.nullcraft.client.platform.input.keyboard.KeyboardEvent;
import ga.nullcraft.client.platform.input.keyboard.KeyboardKeyEvent;
import ga.nullcraft.client.platform.input.mouse.IMouseListener;
import ga.nullcraft.client.platform.input.mouse.MouseButtonEvent;
import ga.nullcraft.client.platform.input.mouse.MouseMoveEvent;
import ga.nullcraft.client.platform.input.mouse.WheelEvent;
import ga.nullcraft.client.storage.TempStorage;
import ga.nullcraft.client.window.VsyncMode;
import ga.nullcraft.client.window.WindowManager;
import ga.nullcraft.client.window.util.DisplayUtil;
import ga.nullcraft.client.window.util.MonitorInfo;
import ga.nullcraft.global.game.entity.EntityPlayer;
import ga.nullcraft.global.mod.loader.LocalFullModLoader;
import ga.nullcraft.global.mod.loader.LocalHalfModLoader;
import org.lwjgl.glfw.GLFW;

public class NullcraftClient implements IKeyboardListener, IMouseListener {
    private LocalGameDirectory gameDirectory;
    private LocalFullModLoader fullModLoader;
    private LocalHalfModLoader halfModLoader;

    private WindowManager windowManager;
    private ModelManager modelManager;
    private AudioManager audioManager;

    private TempStorage tempStorage;
    
    private NuevRenderer renderer;
    private PlayerCamera camera;
    
    private float dx;
    private float dy;
    private float dz;
    private float MOUSE_SENSITIVITY = 0.2f;
	private NuevMeshItem[] meshItems;

    public NullcraftClient(Path dataDir){
        this.gameDirectory = new LocalGameDirectory(dataDir);
        this.tempStorage = new TempStorage();
    }
    
    public void start(WindowManager windowManager) throws Exception {
    	this.windowManager = windowManager;

        windowManager.getInput().getMouse().addListener(this);
        windowManager.getInput().getKeyboard().addListener(this);

		LaunchManager launchManager = new LaunchManager();
    	EntityPlayer player = new EntityPlayer(0, 0, 0);

    	fullModLoader = new LocalFullModLoader(this.getGameDirectory().getFullModStorage());
    	fullModLoader.loadMods();
    	halfModLoader = new LocalHalfModLoader(this.getGameDirectory().getHalfModStorage());
    	halfModLoader.loadMods();

    	renderer = new NuevRenderer();
    	camera = new PlayerCamera(player);
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
	
	public void render() {
        renderer.render(getWindowManager().getWindow(), camera, meshItems);
	}

	public void cleanup() {
		renderer.cleanup();
		for(NuevMeshItem item : meshItems) {
			item.getMesh().cleanup();
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

    @Override
    public void onKeyDown(KeyboardKeyEvent e) {
        dx = 0;
        dy = 0;
        dz = 0;
        if (e.isKeyPressed(GLFW.GLFW_KEY_F11)) {
            getWindowManager().cycleMode();
        }
        if (e.isKeyPressed(GLFW.GLFW_KEY_W)) {
            dz = -1;
        }
        else if (e.isKeyPressed(GLFW.GLFW_KEY_S)) {
            dz = 1;
        }
        if (e.isKeyPressed(GLFW.GLFW_KEY_A)) {
            dx = -1;
        }
        else if (e.isKeyPressed(GLFW.GLFW_KEY_D)) {
            dx = 1;
        }
        if (e.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT)) {
            dy = -1;
        }
        else if (e.isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
            dy = 1;
        }
        if (e.isKeyPressed(GLFW.GLFW_KEY_UP)) {
            MOUSE_SENSITIVITY += 0.02f;
        }
        else if (e.isKeyPressed(GLFW.GLFW_KEY_DOWN)) {
            MOUSE_SENSITIVITY -= 0.02f;
        }
    }

    @Override
    public void onKeyUp(KeyboardKeyEvent e) {

    }

    @Override
    public void onMouseMove(MouseMoveEvent e) {
        camera.movePosition(dx * 0.05f, dy * 0.05f, dz * 0.05f);

        e.getInput().setPosition(getWindowManager().getWindow().getWidth()/2, getWindowManager().getWindow().getHeight()/2);

        camera.moveRotation((float) e.getDeltaX() * MOUSE_SENSITIVITY, (float) e.getDeltaY() * MOUSE_SENSITIVITY, 0);
    }

    @Override
    public void onMouseDown(MouseButtonEvent e) {
        System.out.println("update: " + getWindowManager().getUpdateThread().getElapsed());
        System.out.println("render: " + getWindowManager().getRenderThread().getElapsed());
    }

    @Override
    public void onMouseUp(MouseButtonEvent e) {

    }

    @Override
    public void onScroll(WheelEvent e) {

    }

    public void update() {

    }
}