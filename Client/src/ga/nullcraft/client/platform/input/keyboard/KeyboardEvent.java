package ga.nullcraft.client.platform.input.keyboard;

import ga.nullcraft.client.platform.input.InputEvent;
import ga.nullcraft.client.platform.input.InputManager;

public class KeyboardEvent implements InputEvent {

    private KeyboardInput input;

    protected boolean userTriggered;

    public KeyboardEvent(KeyboardInput input){
        this.input = input;
    }

    public KeyboardInput getInput() {
        return input;
    }

    @Override
    public boolean isTriggeredByUser() {
        return userTriggered;
    }
}
