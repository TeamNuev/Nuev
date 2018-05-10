package ga.nullcraft.global.registry;

/**
 * Registers registries in the mod.
 * You just register registries to this registry to register something.
 * 
 * @author TNuev
 *
 */
public class RegistryMain extends RegistryBase<RegistryBase<?>> {

	RegistryMain(String namespace) {
		this.setNamespace(namespace);
	}
	
	public boolean addRegistry(String name, Registry<?> registry) {
		return this.register(name, registry);
	}
	
	public Object removeRegistry(String name) {
		return this.unregister(name);
	}

}
