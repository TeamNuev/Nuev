package ga.nullcraft.client.platform.input.keyboard;

import ga.nullcraft.client.platform.input.InputEvent;
import ga.nullcraft.client.platform.input.InputManager;

public class KeyboardEvent implements InputEvent {

    private InputManager input;

    public KeyboardEvent(InputManager input){

    }

    @Override
    public InputManager getInput() {
        return input;
    }
}
