package ga.nullcraft.client.window;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;

public class GameWindow {

    private final long handle;

    private String title;

    private WindowState windowState;
    private WindowMode windowMode;

    public GameWindow(String title, int width, int height){
        this(createWindow(title, width, height), title);

        setSize(width, height);
    }

    public GameWindow(String title, int clientX, int clientY, int width, int height){
        this(title, width, height);

        setClientLocation(clientX, clientY);
    }

    public GameWindow(long handle, String title){
        if (handle == 0)
            throw new RuntimeException("Failed to create the GLFW window");

        this.handle = handle;
        this.title = title;
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
        return getClientSize()[0];
    }

    public int getClientY(){
        return getClientSize()[1];
    }

    public int[] getClientSize(){
        IntBuffer x = BufferUtils.createIntBuffer(1);
        IntBuffer y = BufferUtils.createIntBuffer(1);

        GLFW.glfwGetWindowPos(getHandle(), x, y);

        return new int[]{ x.get(), y.get() };
    }

    public int getWidth(){
        return getWindowSize()[0];
    }

    public int getHeight(){
        return getWindowSize()[1];
    }

    public int[] getWindowSize(){
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);

        GLFW.glfwGetWindowSize(getHandle(), width, height);

        return new int[]{ width.get(), height.get() };
    }

    public String getTitle(){
        return title;
    }

    public boolean isVisible() {
        return GLFW.glfwGetWindowAttrib(getHandle(), GLFW.GLFW_VISIBLE) == GLFW.GLFW_TRUE;
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
                setWindowAttrib(GLFW.GLFW_MAXIMIZED, false);
                break;

            case MAXIMIZED:
                setWindowAttrib(GLFW.GLFW_MAXIMIZED, true);
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
                setWindowAttrib(GLFW.GLFW_DECORATED, true);
                setWindowAttrib(GLFW.GLFW_RESIZABLE, true);
                break;

            case FIXED:
                setWindowAttrib(GLFW.GLFW_DECORATED, true);
                setWindowAttrib(GLFW.GLFW_RESIZABLE, false);
                break;

            case BORDERLESS:
                setWindowAttrib(GLFW.GLFW_DECORATED, false);
                break;

            default:
                break;
        }
    }

    public void setVisible(boolean flag) {
        if (flag)
            GLFW.glfwShowWindow(getHandle());
        else
            GLFW.glfwHideWindow(getHandle());
    }

    protected void setWindowAttrib(int hint, boolean flag){
        GLFW.glfwSetWindowAttrib(getHandle(), hint, flag ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
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


    }

    public void setSize(int width, int height){
        GLFW.glfwSetWindowSize(getHandle(), width, height);
    }

    public void makeCurrent(){
        if (WindowManager.getCurrentWindow() != this)
            WindowManager.setCurrentWindow(this);
    }

    public boolean windowShouldClose(){
        return GLFW.glfwWindowShouldClose(getHandle());
    }

    public void destroy(){
        GLFW.glfwDestroyWindow(getHandle());
    }

    public static long createWindow(String title, int width, int height){
        return GLFW.glfwCreateWindow(640, 480, title, MemoryUtil.NULL, MemoryUtil.NULL);
    }
}
