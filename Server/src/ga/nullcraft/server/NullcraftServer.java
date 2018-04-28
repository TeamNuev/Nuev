package ga.nullcraft.server;

import ga.nullcraft.global.IGameDirectory;

/**
 * an abstract server class.
 * ServerGameDirectory which is used in server
 * can be set by overriding getGameDirectory method.
 *
 * @author TNuev
 */
public abstract class NullcraftServer {
    public abstract IGameDirectory getGameDirectory();
}
