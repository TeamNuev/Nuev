package ga.nullcraft.server;

import ga.nullcraft.global.IGameDirectory;

public class DedicatedServer extends NullcraftServer {

    public DedicatedServer(){

    }

    @Override
    public IGameDirectory getGameDirectory() {
        return null;
    }

    public static void main(String[] args){

    }
}
