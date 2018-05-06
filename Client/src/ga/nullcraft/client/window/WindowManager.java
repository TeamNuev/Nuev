package ga.nullcraft.client.window;

import ga.nullcraft.client.NullcraftClient;
import ga.nullcraft.client.audio.AudioManager;
import ga.nullcraft.client.platform.input.InputManager;
import ga.nullcraft.client.thread.*;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

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

    private UpdateThread updateThread;
    private RenderThread renderThread;
    private AudioThread audiothread;
    private InputThread inputThread;

    private InputManager input;
    private AudioManager audio;

    private boolean isStarted;

    private String name;

    private GameWindow window;

    private final Object renderLock = new Object();

    private boolean vsync = false;

    private static GameWindow currentWindow;

    protected static void setCurrentWindow(GameWindow window){
        currentWindow = window;
        GLFW.glfwMakeContextCurrent(window.getHandle());
    }

    public static GameWindow getCurrentWindow(){
        return currentWindow;
    }

    public WindowManager(String name){
        this.name = name;

        this.input = new InputManager(this);
        this.audio = new AudioManager(this);

        this.isStarted = false;
    }

    public String getName() {
        return name;
    }

    public GameWindow getWindow() {
        return window;
    }

    public InputManager getInput() {
        return input;
    }

    public AudioManager getAudio() {
        return audio;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setVSync(boolean flag){
        if (this.vsync == flag)
            return;

        this.vsync = flag;

        getWindow().makeCurrent();
        GLFW.glfwSwapInterval(flag ? 1 : 0);
    }

    public boolean isVsync() {
        return vsync;
    }

    public void run(NullcraftClient client) throws Exception {
        if (isStarted)
            throw new Exception("Client already started with this window");
        this.isStarted = true;

        this.client = client;

        if (!GLFW.glfwInit())
            throw new Exception("Unable to initialize GLFW");

        GLFWErrorCallback.createPrint(System.err).set();

        hintWindow();
        this.window = new GameWindow(getName(), DEFAULT_WIDTH, DEFAULT_HEIGHT);

        this.updateThread = new UpdateThread();
        this.renderThread = new RenderThread(this.renderTask());
        this.audiothread = new AudioThread(this.audio);
        this.inputThread = new InputThread(this.getInput());

        client.start(this);

        this.updateThread.start();
        this.renderThread.start();
        this.audiothread.start();
        this.inputThread.start();

        listenEvent();
    }

    private void hintWindow() {
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, GL_VERSION);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, GL_VERSION_MINOR);
    }

    private void listenEvent(){
        while(isStarted) {
            GLFW.glfwPollEvents();
        }
    }

    public void cycleMode(){

    }

    public void renderInitialize(){
        getWindow().makeCurrent();
        GL.createCapabilities();

        //temp_code
        try {
            client.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Runnable renderTask(){
        return () -> {
            renderInitialize();

            while(isStarted) {

                //temp_code
                client.render();

                synchronized (renderLock) {
                    if (isStarted)
                        GLFW.glfwSwapBuffers(getWindow().getHandle());
                }
            }
        };
    }

    public void update(){

    }

    public void exit(){
        if (!isStarted)
            return;

        synchronized (renderLock){
            isStarted = false;

            getWindow().destroy();
        }
    }
}
