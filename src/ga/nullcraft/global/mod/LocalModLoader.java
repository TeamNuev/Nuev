package ga.nullcraft.global.mod;

import java.io.File;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import ga.nullcraft.global.storage.ModStorage;

/**
 * Loads mods.
 * 
 * @author TNuev
 *
 */
public class LocalModLoader implements IModLoader {

	ModStorage modStorage;
	
	public LocalModLoader(ModStorage modStorage) {
		this.modStorage = modStorage;
	}

	public void loadMods() {
		File file = modStorage.getPath().toFile();
		if(!file.exists() || !file.isDirectory()) {
			file.mkdirs();
		}
		File[] files = file.listFiles();
		for(File aFile : files) {
			try {
				JarFile jarFile = new JarFile(aFile);
				Manifest manifest = jarFile.getManifest();
				Attributes attribs = manifest.getMainAttributes();
				String str = attribs.getValue("Main-Class");
				//Print main class
				System.out.println(str);
				jarFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
