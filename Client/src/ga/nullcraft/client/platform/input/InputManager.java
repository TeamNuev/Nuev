package ga.nullcraft.client.platform.input;

import ga.nullcraft.client.platform.input.keyboard.KeyboardInput;
import ga.nullcraft.client.platform.input.mouse.MouseInput;
import ga.nullcraft.client.window.GameWindow;
import ga.nullcraft.client.window.WindowManager;

public class InputManager {

    private WindowManager windowManager;

    private MouseInput mouse;
    private KeyboardInput keyboard;

    public InputManager(WindowManager windowManager){
        this.windowManager = windowManager;

        this.mouse = new MouseInput(this);
        this.keyboard = new KeyboardInput(this);
    }

    public WindowManager getWindowManager() {
        return windowManager;
    }

    public GameWindow getWindow(){
        return getWindowManager().getWindow();
    }
}
