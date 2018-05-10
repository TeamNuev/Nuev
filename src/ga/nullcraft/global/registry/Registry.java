package ga.nullcraft.global.registry;

/**
 * Registers something you want.
 * 
 * @author TNuev
 *
 * @param <T> A type that you want to register.
 */
public class Registry<T> extends RegistryBase<T> {

	public boolean register(String name, T object) {
		return super.register(name, object);
	}
	
	public T unregister(String name) {
		return super.unregister(name);
	}
}
