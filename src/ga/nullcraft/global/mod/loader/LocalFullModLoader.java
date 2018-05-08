package ga.nullcraft.global.mod.loader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import ga.nullcraft.global.mod.NullMod;
import ga.nullcraft.global.storage.ModStorage;

/**
 * Loads mods.
 * 
 * @author TNuev
 *
 */
public class LocalFullModLoader implements ILocalModLoader {

	ModStorage modStorage;
	URLClassLoader classLoader;
	List<NullMod> mods;
	
	public LocalFullModLoader(ModStorage modStorage) {
		this.modStorage = modStorage;
	}

	@Override
	public void loadMods() {
		mods = new ArrayList<NullMod>();
		
		File file = modStorage.getPath().toFile();
		if(!file.exists() || !file.isDirectory()) {
			file.mkdirs();
		}
		File[] files = file.listFiles();
		List<URL> urls = new ArrayList<URL>();
		List<String> mainClasses = new ArrayList<String>();
		for(File aFile : files) {
			try {
				JarFile jarFile = new JarFile(aFile);
				Manifest manifest = jarFile.getManifest();
				Attributes attribs = manifest.getMainAttributes();
				String mainClass = attribs.getValue("Main-Class");
				mainClasses.add(mainClass);
				
				urls.add(aFile.toURI().toURL());
				jarFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		classLoader = new URLClassLoader(urls.toArray(new URL[urls.size()]));
		
		addAllMods(mainClasses);
	}

	private void addAllMods(List<String> mainClasses) {
		for (String mainClass : mainClasses) {
			try {
				Class<?> cls = classLoader.loadClass(mainClass);
				if (NullMod.class.isAssignableFrom(cls)) {
					mods.add((NullMod) cls.newInstance());
					Method meth = cls.getMethod("main", String[].class);
					String[] params = null; // init params accordingly
					meth.invoke(null, (Object) params); // static method doesn't have an instance
				} else {
					System.err.println("Error: Main class should extend NullMod");
				}
			} catch (InstantiationException | ClassNotFoundException | NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
}
