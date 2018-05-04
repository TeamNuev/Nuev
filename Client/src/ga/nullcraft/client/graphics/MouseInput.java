package ga.nullcraft.client.graphics;

import org.joml.Vector2d;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

public class MouseInput {
	
	private final Vector2d previousPos;
	private final Vector2d currentPos;
	private final Vector2f displVec;
	private boolean inWindow = false;
	
	public MouseInput() {
		previousPos = new Vector2d(-1, -1);
		currentPos = new Vector2d(0, 0);
		displVec = new Vector2f();
	}
	
	public void init(NuevWindow window) {
        GLFW.glfwSetCursorPosCallback(window.getWindowHandle(), (windowHandle, xpos, ypos) -> {
            currentPos.x = xpos;
            currentPos.y = ypos;
        });
        GLFW.glfwSetCursorEnterCallback(window.getWindowHandle(), (windowHandle, entered) -> {
            inWindow = entered;
        });
	}
	
	public void input(NuevWindow window) {
        displVec.x = 0;
        displVec.y = 0;
        if (previousPos.x > 0 && previousPos.y > 0 && inWindow) {
            double deltax = currentPos.x - previousPos.x;
            double deltay = currentPos.y - previousPos.y;
            boolean rotateX = deltax != 0;
            boolean rotateY = deltay != 0;
            if (rotateX) {
                displVec.y = (float) deltax;
            }
            if (rotateY) {
                displVec.x = (float) deltay;
            }
        }
        previousPos.x = currentPos.x;
        previousPos.y = currentPos.y;
	}
	
	public Vector2f getDisplVec() {
		return displVec;
	}
}
