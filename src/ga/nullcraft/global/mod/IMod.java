package ga.nullcraft.global.mod;

import java.util.UUID;

/**
 * A basic mod interface.
 * 
 * @author TNuev
 * 
 */
public interface IMod {
    String getName();

    UUID getGuid();

    String getNamespace();

    String getVersion();

    String getDescription();
}
