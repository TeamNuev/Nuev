package ga.nullcraft.global.registry;

import java.util.HashMap;
import java.util.Map;

public class RegistryManager {

    private Map<String, IRegistry> registryMap;

    public RegistryManager(){
        this.registryMap = new HashMap<>();
    }

    public boolean hasRegistry(String namespace){
        return registryMap.containsKey(namespace);
    }

    public IRegistry addRegistry(String namespace){
        if (hasRegistry(namespace))
            return getRegistry(namespace);

        IRegistry registry = new GameRegistry(namespace);
        registryMap.put(namespace, registry);

        return registry;
    }

    public boolean removeRegistry(String namespace){
        if (!hasRegistry(namespace))
            return false;

        registryMap.remove(namespace);
        return true;
    }

    public IRegistry getRegistry(String namespace){
        return registryMap.get(namespace);
    }
}
