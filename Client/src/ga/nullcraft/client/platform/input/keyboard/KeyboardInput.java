package ga.nullcraft.client.platform.input.keyboard;

import ga.nullcraft.client.platform.input.DeviceInput;
import ga.nullcraft.client.platform.input.mouse.IMouseListener;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

import java.util.ArrayList;
import java.util.List;

public class KeyboardInput extends DeviceInput {

    private KeyboardInputHandler handler;

    private List<Integer> pressedKeyList;

    public KeyboardInput(){
        this.pressedKeyList = new ArrayList<>();

        this.handler = new KeyboardInputHandler();
    }

    public void addListener(IKeyboardListener listener) {
        handler.listenerList.add(listener);
    }

    public void removeListener(IKeyboardListener listener) {
        handler.listenerList.remove(listener);
    }

    @Override
    protected void initializeDevice(long windowHandle) {
        GLFW.glfwSetKeyCallback(windowHandle, this.handler);
    }

    private class KeyboardInputHandler extends GLFWKeyCallback {

        private List<IKeyboardListener> listenerList;

        public KeyboardInputHandler(){
            this.listenerList = new ArrayList<>();
        }

        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
            KeyboardKeyEvent.Type type;

            switch (action){
                case GLFW.GLFW_PRESS:
                    if (!pressedKeyList.contains(key))
                        pressedKeyList.add(key);

                    type = KeyboardKeyEvent.Type.PRESS;
                    break;

                case GLFW.GLFW_RELEASE:
                    if (pressedKeyList.contains(key))
                        pressedKeyList.remove((Integer) key);

                    type = KeyboardKeyEvent.Type.RELEASE;
                    break;

                default:
                    return;
            }

            callEventInternal(new KeyboardKeyEvent(KeyboardInput.this, type, new ArrayList<>(pressedKeyList)), true);
        }

        private void callEventInternal(KeyboardKeyEvent event, boolean userPressed){
            event.userTriggered = userPressed;

            switch (event.getType()) {
                case PRESS:
                    for (IKeyboardListener listener : listenerList)
                        listener.onKeyDown(event);
                    break;

                case RELEASE:
                    for (IKeyboardListener listener : listenerList)
                        listener.onKeyUp(event);
                    break;

                default:
                    return;
            }
        }

        public void callEvent(KeyboardKeyEvent event){
            handler.callEventInternal(event, false);
        }
    }
}
