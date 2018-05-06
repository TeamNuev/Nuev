package ga.nullcraft.client.platform.input;

import ga.nullcraft.client.platform.input.keyboard.KeyboardInput;
import ga.nullcraft.client.platform.input.mouse.MouseInput;
import ga.nullcraft.client.window.GameWindow;
import ga.nullcraft.client.window.WindowManager;

public class InputManager implements Runnable {

    private WindowManager windowManager;

    private MouseInput mouse;
    private KeyboardInput keyboard;

    public InputManager(WindowManager windowManager){
        this.windowManager = windowManager;

        this.mouse = new MouseInput();
        this.keyboard = new KeyboardInput();
    }

    public WindowManager getWindowManager() {
        return windowManager;
    }

    public GameWindow getWindow(){
        return getWindowManager().getWindow();
    }

    public MouseInput getMouse() {
        return mouse;
    }

    public KeyboardInput getKeyboard() {
        return keyboard;
    }

    @Override
    public void run() {
        long windowHandle = getWindow().getHandle();

        ((DeviceInput) this.mouse).initializeDevice(windowHandle);
        ((DeviceInput) this.keyboard).initializeDevice(windowHandle);
    }
}
