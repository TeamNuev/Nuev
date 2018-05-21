package ga.nullcraft.client.window;

import ga.nullcraft.client.NuevClient;
import ga.nullcraft.client.audio.AudioManager;
import ga.nullcraft.client.platform.input.InputManager;
import ga.nullcraft.client.thread.*;
import ga.nullcraft.client.window.util.DisplayUtil;
import ga.nullcraft.client.window.util.MonitorInfo;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
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

    private NuevClient client;

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
    private volatile boolean renderUpdate;

    private VsyncMode vsyncMode = VsyncMode.DISABLED;

    private static GameWindow currentWindow;

    protected static void setCurrentWindow(GameWindow window){
        if (window == null) {
            GLFW.glfwMakeContextCurrent(GLFW.GLFW_FALSE);
            return;
        }

        long handle = window.getHandle();

        if (GLFW.glfwGetCurrentContext() == handle)
            return;

        currentWindow = window;
        GLFW.glfwMakeContextCurrent(handle);
    }

    public static GameWindow getCurrentWindow(){
        return currentWindow;
    }

    public WindowManager(String name){
        this.name = name;

        this.input = new InputManager(this);
        this.audio = new AudioManager(this);

        this.isStarted = false;
        this.renderUpdate = false;
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

    public void setVSync(VsyncMode mode){
        if (this.vsyncMode == mode)
            return;

        this.vsyncMode = mode;
        renderUpdate = true;
    }

    public VsyncMode getVsyncMode() {
        return vsyncMode;
    }

    public UpdateThread getUpdateThread() {
        return updateThread;
    }

    public RenderThread getRenderThread() {
        return renderThread;
    }

    public InputThread getInputThread() {
        return inputThread;
    }

    public AudioThread getAudiothread() {
        return audiothread;
    }

    public void run(NuevClient client) throws Exception {
        if (isStarted)
            throw new Exception("Client already started with this window");
        this.isStarted = true;

        this.client = client;

        if (!GLFW.glfwInit())
            throw new Exception("Unable to initialize GLFW");

        GLFWErrorCallback.createPrint(System.err).set();

        hintWindow();
        this.window = new GameWindow(getName(), DEFAULT_WIDTH, DEFAULT_HEIGHT);

        this.updateThread = new UpdateThread(this.updateTask());
        this.renderThread = new RenderThread(this.renderTask()){
          @Override
          public void initialize(){
              renderInitialize();
          }
        };
        this.audiothread = new AudioThread(this.audio);
        this.inputThread = new InputThread(this.getInput());

        this.updateThread.start();
        this.renderThread.start();
        this.audiothread.start();
        this.inputThread.start();

        client.start(this);

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

            if (getWindow().windowShouldClose()){
                exit();
                return;
            }
        }
    }

    public void cycleMode(){
        //temp_code
        if (getWindow().getWindowMode() == WindowMode.RESIZABLE){
            getWindow().setWindowMode(WindowMode.BORDERLESS);
            getWindow().setWindowState(WindowState.MAXIMIZED);

            GLFWVidMode mode = DisplayUtil.getCurrentDisplayMode();
            getWindow().setClientLocation(0, 0);
            getWindow().setSize(mode.width(), mode.height());
        }
        else{
            getWindow().setWindowMode(WindowMode.RESIZABLE);
            getWindow().setWindowState(WindowState.NORMAL);
        }

        getWindow().focus();
    }

    public void renderInitialize(){
        setCurrentWindow(getWindow());
        GL.createCapabilities();
        renderUpdate = true;

        //temp_code
        try {
            client.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateRenderSettings(){
        GLFW.glfwSwapInterval(vsyncMode.getMode());
    }

    public Runnable renderTask(){
        return () -> {
            if (renderUpdate) {
                updateRenderSettings();
                renderUpdate = false;
            }

            //temp_code
            client.render();

            synchronized (renderLock) {
                if (isStarted)
                    GLFW.glfwSwapBuffers(getWindow().getHandle());
            }
        };
    }

    public Runnable updateTask(){
        return () -> {

            //temp_code
            client.update();
        };
    }

    public void stop(){
        if (!isStarted)
            return;
        isStarted = false;

        this.renderThread.interrupt();
        this.updateThread.interrupt();
        this.inputThread.interrupt();
        this.audiothread.interrupt();
    }

    public void exit(){
        if (isStarted) {
            stop();

            synchronized (renderLock) {
                isStarted = false;

                getWindow().destroy();
            }
        }
    }
}
