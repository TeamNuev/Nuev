package ga.nullcraft.global.mod;

import java.util.UUID;

/**
 * A basic mod class.
 * You can declare mod by extending this class.
 * 
 * @author TNuev
 *
 */
public abstract class NullMod implements IMod {
    private final String name;

    private final UUID guid;
    private final String namespace;

    private final String version;

    public NullMod(String name, UUID uuid, String namespace, String version){
        this.name = name;
        this.guid = uuid;
        this.namespace = namespace;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public UUID getGuid() {
        return guid;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getVersion() {
        return version;
    }
}
