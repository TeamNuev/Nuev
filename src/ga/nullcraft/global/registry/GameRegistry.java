package ga.nullcraft.global.registry;

/**
 * Saves game objects like blocks, items, etc.
 * 
 * @author TNuev
 *
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
