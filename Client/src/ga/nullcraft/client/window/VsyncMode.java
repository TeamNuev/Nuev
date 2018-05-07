package ga.nullcraft.client.window;

public enum VsyncMode {
    VSYNC(1),
    ADAPTIVE(-1),
    DISABLED(0);

    private int mode;

    VsyncMode(int mode){
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }
}
