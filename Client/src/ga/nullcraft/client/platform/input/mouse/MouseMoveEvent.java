package ga.nullcraft.client.platform.input.mouse;

public class MouseMoveEvent extends MouseEvent {

    private double x;
    private double y;

    private double deltaX;
    private double deltaY;

    public MouseMoveEvent(MouseInput input, double x, double y, double deltaX, double deltaY) {
        super(input);

        this.x = x;
        this.y = y;

        this.deltaX = deltaX;
        this.deltaY = deltaY;
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
}
