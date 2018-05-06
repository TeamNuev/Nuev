package ga.nullcraft.client.platform.input.mouse;

import ga.nullcraft.client.platform.input.DeviceInput;
import org.lwjgl.glfw.*;

import java.util.ArrayList;
import java.util.List;

public class MouseInput extends DeviceInput {

    private double deltaX;
    private double deltaY;

    private double x;
    private double y;

    private List<Integer> pressedButtonList;

    private double wheelX;
    private double wheelY;

    private MouseInputHandler handler;

    public MouseInput() {
        this.x = 0;
        this.y = 0;

        this.deltaX = 0;
        this.deltaY = 0;

        this.pressedButtonList = new ArrayList<>();

        this.handler = new MouseInputHandler();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDeltaX() {
        return deltaX;
    }

    public double getDeltaY() {
        return deltaY;
    }

    public double getWheelX() {
        return wheelX;
    }

    public double getWheelY() {
        return wheelY;
    }

    public boolean isPressed(int button){
        return pressedButtonList.contains(button);
    }

    @Override
    protected void initializeDevice(long windowHandle) {
        GLFW.glfwSetCursorPosCallback(windowHandle, handler.cursorPosListener);
        GLFW.glfwSetMouseButtonCallback(windowHandle, handler.mouseButtonListener);
        GLFW.glfwSetScrollCallback(windowHandle, handler.scrollListener);
    }

    public void addListener(IMouseListener listener) {
        handler.listenerList.add(listener);
    }

    public void removeListener(IMouseListener listener) {
        handler.listenerList.remove(listener);
    }

    private class MouseInputHandler {

        private List<IMouseListener> listenerList;

        private CursorPosListener cursorPosListener;
        private MouseButtonListener mouseButtonListener;
        private ScrollListener scrollListener;

        public MouseInputHandler(){
            this.cursorPosListener = new CursorPosListener();
            this.mouseButtonListener = new MouseButtonListener();
            this.scrollListener = new ScrollListener();

            this.listenerList = new ArrayList<>();
        }

        private class CursorPosListener extends GLFWCursorPosCallback {

            @Override
            public void invoke(long window, double xpos, double ypos) {
                deltaX = xpos - x;
                deltaY = ypos - y;

                x = xpos;
                y = ypos;

                callEventInternal(new MouseMoveEvent(MouseInput.this, getX(), getY(), getDeltaX(), getDeltaY()), true);
            }
        }

        private class MouseButtonListener extends GLFWMouseButtonCallback {

            @Override
            public void invoke(long window, int button, int action, int mods) {
                MouseButtonEvent.Type type;

                switch (action){
                    case GLFW.GLFW_PRESS:
                        if (!pressedButtonList.contains(button))
                            pressedButtonList.add(button);

                        type = MouseButtonEvent.Type.PRESS;
                        break;

                    case GLFW.GLFW_RELEASE:
                        if (pressedButtonList.contains(button))
                            pressedButtonList.remove((Integer) button);

                        type = MouseButtonEvent.Type.RELEASE;
                        break;

                    default:
                        return;
                }

                callEventInternal(new MouseButtonEvent(MouseInput.this, type, new ArrayList<>(pressedButtonList)), true);
            }
        }

        private class ScrollListener extends GLFWScrollCallback {

            @Override
            public void invoke(long window, double xoffset, double yoffset) {
                wheelX = xoffset;
                wheelY = xoffset;

                callEventInternal(new WheelEvent(MouseInput.this, xoffset, yoffset), true);
            }
        }

        private void callEventInternal(MouseButtonEvent event, boolean userPressed){
            event.userTriggered = userPressed;

            if (event.getType() == MouseButtonEvent.Type.PRESS) {
                for (IMouseListener listener : listenerList)
                    listener.onMouseDown(event);
            }
            else if (event.getType() == MouseButtonEvent.Type.RELEASE) {
                for (IMouseListener listener : listenerList)
                    listener.onMouseUp(event);
            }
        }

        private void callEventInternal(WheelEvent event, boolean userPressed){
            event.userTriggered = userPressed;

            for (IMouseListener listener : listenerList)
                listener.onScroll(event);
        }

        private void callEventInternal(MouseMoveEvent event, boolean userPressed){
            event.userTriggered = userPressed;

            for (IMouseListener listener : listenerList)
                listener.onMouseMove(event);
        }
    }

    public void callEvent(MouseButtonEvent event){
        handler.callEventInternal(event, false);
    }

    public void callEvent(WheelEvent event){
        handler.callEventInternal(event, false);
    }

    public void callEvent(MouseMoveEvent event){
        handler.callEventInternal(event, false);
    }
}
