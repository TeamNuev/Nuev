package ga.nullcraft.client.local;

import ga.nullcraft.client.NuevClient;
import ga.nullcraft.global.IGameDirectory;
import ga.nullcraft.server.NullcraftServer;

public class LocalServer extends NullcraftServer {

    private NuevClient client;

    public LocalServer(NuevClient client){
        this.client = client;
    }

    public NuevClient getClient() {
        return client;
    }

    @Override
    public IGameDirectory getGameDirectory() {
        return null;
    }
}
