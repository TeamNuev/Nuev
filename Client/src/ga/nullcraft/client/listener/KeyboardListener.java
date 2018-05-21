package ga.nullcraft.client.listener;

import org.lwjgl.glfw.GLFW;

import ga.nullcraft.client.NuevClient;
import ga.nullcraft.client.platform.input.keyboard.IKeyboardListener;
import ga.nullcraft.client.platform.input.keyboard.KeyboardKeyEvent;

public class KeyboardListener implements IKeyboardListener {

    private float dx;
    private float dy;
    private float dz;
    
	private NuevClient client = NuevClient.getClient();
	private MouseListener mouseListener = client.getMouseListener();

	@Override
	public void onKeyDown(KeyboardKeyEvent e) {
		dx = 0;
		dy = 0;
		dz = 0;
		if (e.isKeyPressed(GLFW.GLFW_KEY_F11)) {
			client.getWindowManager().cycleMode();
		}
		if (e.isKeyPressed(GLFW.GLFW_KEY_W)) {
			dz = -1;
		}
		if (e.isKeyPressed(GLFW.GLFW_KEY_S)) {
			dz = 1;
		}
		if (e.isKeyPressed(GLFW.GLFW_KEY_A)) {
			dx = -1;
		}
		if (e.isKeyPressed(GLFW.GLFW_KEY_D)) {
			dx = 1;
		}
		if (e.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT)) {
			dy = -1;
		}
		if (e.isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
			dy = 1;
		}
		if (e.isKeyPressed(GLFW.GLFW_KEY_UP)) {
			mouseListener.MOUSE_SENSITIVITY += 0.02f;
		}
		if (e.isKeyPressed(GLFW.GLFW_KEY_DOWN)) {
			mouseListener.MOUSE_SENSITIVITY -= 0.02f;
		}
		if (e.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
			mouseListener.mouseLocked = !mouseListener.mouseLocked;
		}
	}

	@Override
	public void onKeyUp(KeyboardKeyEvent e) {

	}

}
