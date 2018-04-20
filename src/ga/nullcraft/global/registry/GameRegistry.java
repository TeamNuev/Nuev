package ga.nullcraft.global.registry;

public class GameRegistry implements IRegistry {

    private String namespace;

    public GameRegistry(String namespace){
        this.namespace = namespace;
    }

    public String getNamespace() {
        return namespace;
    }
}
