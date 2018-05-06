package ga.nullcraft.client.platform.input.keyboard;

import java.util.List;

public class KeyboardKeyEvent extends KeyboardEvent {

    private Type type;
    private List<Integer> keyList;

    public KeyboardKeyEvent(KeyboardInput input, Type type, List<Integer> keyList) {
        super(input);

        this.type = type;
        this.keyList = keyList;
    }

    public Type getType() {
        return type;
    }

    public boolean isKeyPressed(int button) {
        return keyList.contains(button);
    }

    public enum Type {
        RELEASE, PRESS
    }
}
