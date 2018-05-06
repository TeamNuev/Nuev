package ga.nullcraft.client.platform.input.mouse;

import java.util.List;

public class MouseButtonEvent extends MouseEvent {

    private Type type;
    private List<Integer> mouseButtonList;

    public MouseButtonEvent(MouseInput input, Type type, List<Integer> mouseButtonList) {
        super(input);

        this.type = type;
        this.mouseButtonList = mouseButtonList;
    }

    public Type getType() {
        return type;
    }

    public boolean isButtonPressed(int button) {
        return mouseButtonList.contains(button);
    }

    public enum Type {
        RELEASE, PRESS
    }
}
