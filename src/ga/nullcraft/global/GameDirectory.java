package ga.nullcraft.global;

import java.nio.file.Path;

import ga.nullcraft.client.storage.CacheStorage;
import ga.nullcraft.global.mod.IMod;
import ga.nullcraft.global.storage.ConfigStorage;
import ga.nullcraft.global.storage.ModStorage;
import ga.nullcraft.global.storage.WorldStorage;

public class GameDirectory implements IGameDirectory {

	protected Path path;

	public GameDirectory(Path path) {
		this.path = path;
	}

	public Path getPath() {
		return path;
	}

	public CacheStorage getCacheStorage() {
		return new CacheStorage(path.resolve(".data"));
	}

	@Override
	public WorldStorage getWorldStorage() {
		return new WorldStorage(path.resolve("saves"));
	}
	
	@Override
	public ModStorage getModStorage() {
		return new ModStorage(path.resolve("mods"));
	}

	@Override
	public ModStorage getFullModStorage() {
		return new ModStorage(path.resolve("mods/full"));
	}

	@Override
	public ModStorage getHalfModStorage() {
		return new ModStorage(path.resolve("mods/half"));
	}

	@Override
	public ConfigStorage getConfigStorage(IMod mod) {
		return new ConfigStorage(path.resolve("config"), mod);
	}
}
