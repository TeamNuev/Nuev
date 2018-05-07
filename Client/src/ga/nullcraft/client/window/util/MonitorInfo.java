package ga.nullcraft.client.window.util;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class MonitorInfo {

    public final String name;

    public final int xPosition;
    public final int yPosition;

    public final float xScale;
    public final float yScale;

    public final int widthPhysical;
    public final int heightPhysical;

    public MonitorInfo(long monitor){
        this.name = GLFW.glfwGetMonitorName(monitor);

        IntBuffer xBuffer = BufferUtils.createIntBuffer(1);
        IntBuffer yBuffer = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetMonitorPos(monitor, xBuffer, yBuffer);

        this.xPosition = xBuffer.get();
        this.yPosition = yBuffer.get();

        FloatBuffer xScale = BufferUtils.createFloatBuffer(1);
        FloatBuffer yScale = BufferUtils.createFloatBuffer(1);
        GLFW.glfwGetMonitorContentScale(monitor, xScale, yScale);

        this.xScale = xScale.get();
        this.yScale = yScale.get();

        IntBuffer widthPhysical = BufferUtils.createIntBuffer(1);
        IntBuffer heightPhysical = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetMonitorPhysicalSize(monitor, widthPhysical, heightPhysical);

        this.widthPhysical = widthPhysical.get();
        this.heightPhysical = heightPhysical.get();
    }
}
