package ga.nullcraft.global.game.event;

public interface ICancellable {
    boolean isCancelled();

    void setCancelled(boolean flag);
}
