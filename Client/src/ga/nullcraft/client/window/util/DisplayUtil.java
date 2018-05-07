package ga.nullcraft.client.window.util;

import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class DisplayUtil {

    public static GLFWVidMode.Buffer getDisplayModeList(long monitor){
        return GLFW.glfwGetVideoModes(monitor);
    }

    public static GLFWVidMode getCurrentDisplayMode(long monitor){
        return GLFW.glfwGetVideoMode(monitor);
    }

    public static GLFWVidMode getCurrentDisplayMode(){
        return getCurrentDisplayMode(GLFW.glfwGetPrimaryMonitor());
    }

    public static long getWindowMonitor(long windowHandle){
        return GLFW.glfwGetWindowMonitor(windowHandle);
    }

    public static MonitorInfo getMonitorInfo(long monitor){
        return new MonitorInfo(monitor);
    }

    public static long getPrimaryMonitor(){
        return GLFW.glfwGetPrimaryMonitor();
    }

    public static long[] getMonitors(){
        PointerBuffer buffer = GLFW.glfwGetMonitors();
        return GLFW.glfwGetMonitors().getLongBuffer(buffer.position()).array();
    }
}
