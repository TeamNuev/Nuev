package ga.nullcraft.client.platform.input.mouse;

public interface IMouseListener {

    void onMouseMove(MouseMoveEvent e);

    void onMouseDown(MouseButtonEvent e);
    void onMouseUp(MouseButtonEvent e);

    void onScroll(WheelEvent e);

}
