package ga.nullcraft.client.local;

import ga.nullcraft.client.NullcraftClient;
import ga.nullcraft.global.IGameDirectory;
import ga.nullcraft.server.NullcraftServer;

public class LocalServer extends NullcraftServer {

    private NullcraftClient client;

    public LocalServer(NullcraftClient client){
        this.client = client;
    }

    public NullcraftClient getClient() {
        return client;
    }

    @Override
    public IGameDirectory getGameDirectory() {
        return null;
    }
}
