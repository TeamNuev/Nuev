package ga.nullcraft.client.platform.input.mouse;

public class WheelEvent extends MouseEvent {

    private double wheelX;
    private double wheelY;

    public WheelEvent(MouseInput input, double wheelX, double wheelY){
        super(input);
        this.wheelX = wheelX;
        this.wheelY = wheelY;
    }

    public double getWheelX() {
        return wheelX;
    }

    public double getWheelY() {
        return wheelY;
    }
}
