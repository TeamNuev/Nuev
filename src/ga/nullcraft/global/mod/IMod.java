package ga.nullcraft.global.mod;

import java.util.UUID;

public interface IMod {
    String getName();

    UUID getGuid();

    String getNamespace();

    String getVersion();

    String getDescription();
}
