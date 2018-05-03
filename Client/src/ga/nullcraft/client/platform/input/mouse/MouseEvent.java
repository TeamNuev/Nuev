package ga.nullcraft.client.platform.input.mouse;

import ga.nullcraft.client.platform.input.InputEvent;
import ga.nullcraft.client.platform.input.InputManager;

public class MouseEvent implements InputEvent {

    private InputManager input;

    public MouseEvent(InputManager input){

    }

    @Override
    public InputManager getInput() {
        return input;
    }
}
