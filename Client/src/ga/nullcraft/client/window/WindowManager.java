package ga.nullcraft.client.window;

import ga.nullcraft.client.NullcraftClient;
import ga.nullcraft.client.platform.input.InputManager;
import ga.nullcraft.client.thread.NuevThread;
import org.lwjgl.glfw.GLFW;

/**
 * Manage game window
 *
 * @author TNuev
 */
public class WindowManager {

    public static final int GL_VERSION = 3;
    public static final int GL_VERSION_MINOR = 2;

    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 500;

    private NullcraftClient client;

    private NuevThread renderThread;
    private NuevThread updateThread;
    private NuevThread audiothread;
    private NuevThread inputThread;

    private InputManager input;

    private boolean isStarted;

    private GameWindow window;

    private boolean vsync = false;

    private static GameWindow currentWindow;

    protected static void setCurrentWindow(GameWindow window){
        currentWindow = window;
        GLFW.glfwMakeContextCurrent(window.getHandle());
    }

    public static GameWindow getCurrentWindow(){
        return currentWindow;
    }

    public WindowManager(String title){
        this.window = new GameWindow(title, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public WindowManager(GameWindow window){
        this.window = window;
        this.input = new InputManager(this);
        this.isStarted = false;
    }

    public GameWindow getWindow() {
        return window;
    }

    public InputManager getInput() {
        return input;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setVSync(boolean flag){
        if (this.vsync == flag)
            return;

        this.vsync = flag;

        getWindow().makeCurrent();
        GLFW.glfwSwapInterval(1);
    }

    public boolean isVsync() {
        return vsync;
    }

    public void run(NullcraftClient client) throws Exception {
        if (isStarted)
            throw new Exception("Client already started with this window");

        this.client = client;

        client.start(this);
    }

    public void cycleMode(){

    }

    public void exit(){
        if (!isStarted)
            return;

        getWindow().destroy();

    }
}
