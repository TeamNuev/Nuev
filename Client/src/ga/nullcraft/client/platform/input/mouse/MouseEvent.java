package ga.nullcraft.client.platform.input.mouse;

import ga.nullcraft.client.platform.input.InputEvent;

public class MouseEvent implements InputEvent {

    private MouseInput input;

    protected boolean userTriggered;

    public MouseEvent(MouseInput input){
        this.input = input;

        this.userTriggered = false;
    }

    public MouseInput getInput(){
        return input;
    }

    @Override
    public boolean isTriggeredByUser() {
        return userTriggered;
    }
}
