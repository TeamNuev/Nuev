package ga.nullcraft.client.platform.input.mouse;

import ga.nullcraft.client.NuevClient;
import ga.nullcraft.client.graphics.base.PlayerCamera;

public class MouseListener implements IMouseListener {

    public float MOUSE_SENSITIVITY = 0.2f;
    
    private NuevClient client = NuevClient.getClient();
    private PlayerCamera camera;
    
    public MouseListener(PlayerCamera camera) {
    	this.camera = camera;
    }
    
    @Override
    public void onMouseMove(MouseMoveEvent e) {
    	camera.moveRotation((float) e.getDeltaY() * MOUSE_SENSITIVITY, (float) e.getDeltaX() * MOUSE_SENSITIVITY, 0);
    }

    @Override
    public void onMouseDown(MouseButtonEvent e) {
        System.out.println("update: " + client.getWindowManager().getUpdateThread().getElapsed());
        System.out.println("render: " + client.getWindowManager().getRenderThread().getElapsed());
    }

    @Override
    public void onMouseUp(MouseButtonEvent e) {

    }

    @Override
    public void onScroll(WheelEvent e) {

    }

}
