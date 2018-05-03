package ga.nullcraft.client.window;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.system.MemoryUtil;

public class GameWindow {

    private final long handle;

    private String title;

    private WindowState windowState;
    private WindowMode windowMode;

    private int clientX;
    private int clientY;

    private int width;
    private int height;

    private boolean visible;

    public GameWindow(String title, int width, int height){
        this(title, 0, 0, width, height);
    }

    public GameWindow(String title, int clientX, int clientY, int width, int height){
        this(createWindow(title, width, height), title, clientX, clientY, width, height);
    }

    public GameWindow(long handle, String title, int clientX, int clientY, int width, int height){
        this.handle = handle;

        this.title = title;

        this.visible = false;

        this.clientX = clientX;
        this.clientY = clientY;

        this.width = width;
        this.height = height;
    }

    {
        setWindowHint(GLFW.GLFW_VISIBLE, false);
        setWindowHint(GLFW.GLFW_RESIZABLE, false);

        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, WindowManager.GL_VERSION);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, WindowManager.GL_VERSION_MINOR);

        GLFW.glfwSetFramebufferSizeCallback(getHandle(), new GLFWFramebufferSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                if (window != getHandle())
                    return;

                GameWindow.this.width = width;
                GameWindow.this.height = height;
            }
        });
    }

    public long getHandle(){
        return handle;
    }

    public WindowState getWindowState(){
        return windowState;
    }

    public WindowMode getWindowMode(){
        return windowMode;
    }

    public int getClientX(){
        return clientX;
    }

    public int getClientY(){
        return clientY;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public String getTitle(){
        return title;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isFocused(){
        return GLFW.glfwGetWindowAttrib(getHandle(), GLFW.GLFW_FOCUSED) == GLFW.GLFW_TRUE;
    }

    public void setWindowState(WindowState windowState) {
        if (this.windowState == windowState)
            return;

        this.windowState = windowState;

        switch(windowState){
            case NORMAL:
                setWindowHint(GLFW.GLFW_MAXIMIZED, false);
                break;

            case MAXIMIZED:
                setWindowHint(GLFW.GLFW_MAXIMIZED, true);
                break;

            case MINIMIZED:
                GLFW.glfwIconifyWindow(getHandle());
                break;

            default:
                break;
        }
    }

    public void setWindowMode(WindowMode windowMode){
        if (this.windowMode == windowMode)
            return;

        this.windowMode = windowMode;

        switch(windowMode){
            case RESIZABLE:
                setWindowHint(GLFW.GLFW_DECORATED, true);
                setWindowHint(GLFW.GLFW_RESIZABLE, true);
                break;

            case FIXED:
                setWindowHint(GLFW.GLFW_DECORATED, true);
                setWindowHint(GLFW.GLFW_RESIZABLE, false);
                break;

            case BORDERLESS:
                setWindowHint(GLFW.GLFW_DECORATED, false);
                break;

            default:
                break;
        }
    }

    public void setVisible(boolean flag) {
        if (this.visible == flag)
            return;

        this.visible = flag;

        makeCurrent();
        if (flag)
            GLFW.glfwShowWindow(getHandle());
        else
            GLFW.glfwHideWindow(getHandle());
    }

    protected void setWindowHint(int hint, boolean flag){
        makeCurrent();

        GLFW.glfwWindowHint(hint, flag ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
    }

    public void setClientX(int clientX){
        setClientLocation(clientX, getClientY());
    }

    public void setClientY(int clientY){
        setClientLocation(getClientX(), clientY);
    }

    public void setClientLocation(int clientX, int clientY){
        GLFW.glfwSetWindowPos(getHandle(), clientX, clientY);
    }

    public void setWidth(int width){
        setSize(width, getHeight());
    }

    public void setHeight(int height){
        setSize(getWidth(), height);
    }

    public void setTitle(String title){
        if (title == null || title.equals(getTitle()))
            return;

        this.title = title;

        makeCurrent();
    }

    public void setSize(int width, int height){
        if (this.width != width)
            this.width = width;

        if (this.height != height)
            this.height = height;

        makeCurrent();
        GLFW.glfwSetWindowSize(getHandle(), width, height);
    }

    public void makeCurrent(){
        if (WindowManager.getCurrentWindow() != this)
            WindowManager.setCurrentWindow(this);
    }

    public void destroy(){
        GLFW.glfwDestroyWindow(getHandle());
    }

    public static long createWindow(String title, int width, int height){
        return GLFW.glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL);
    }
}
