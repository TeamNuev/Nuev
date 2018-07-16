package ga.nullcraft.global.registry;

import java.util.Map;

/**
 * The base of registry.
 * 
 * @author TNuev
 *
 * @param <T> Something that is going to be registered.
 */
public abstract class RegistryBase<T> {

	private String namespace;
	private Map<String, T> registry;
	
	RegistryBase(String namespace) {
		setNamespace(namespace);
	}
	
	/**
	 * Registers something.
	 * 
	 * @param name A name of the object.
	 * @param object An object that you want to register.
	 * @return true if this is not cancelled.
	 */
	protected boolean register(String name, T object) {
		boolean isCancelled = false;//execute event(name, object)
		if(isCancelled) return false;
		registry.put(name, object);
		return true;
	}
	
	/**
	 * Unregisters something.
	 * 
	 * @param name A name of the object.
	 * @return object that you unregistered. Returns null if this is cancelled. 
	 */
	protected T unregister(String name) {
		T object = registry.get(name);
		boolean isCancelled = false;//execute event(name, object)
		if(isCancelled) return null;
		registry.remove(name);
		return object;
	}
	
	/**
	 * Sets namespace.
	 * 
	 * @param namespace
	 */
	private void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	/**
	 * 
	 * @return namespace
	 */
	public String getNamespace() {
		return namespace;
	}
	
	/**
	 * 
	 * @return registry
	 */
	public Map<String, T> getRegistry() {
		return registry;
	}
}
