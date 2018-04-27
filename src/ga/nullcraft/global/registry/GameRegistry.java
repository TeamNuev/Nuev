package ga.nullcraft.global.registry;

/**
 * Has information of items, blocks, or entities and so on
 * in all mods which uses same namespace.
 *
 * @author TNuev
 */
public class GameRegistry implements IRegistry {

    private String namespace;

    public GameRegistry(String namespace){
        this.namespace = namespace;
    }

    public String getNamespace() {
        return namespace;
    }
}
