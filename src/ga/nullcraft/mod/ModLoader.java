package ga.nullcraft.mod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import ga.nullcraft.Nullcraft;

public class ModLoader {
	
	public void loadJar(String str) throws IOException {
		ZipInputStream zip = new ZipInputStream(new FileInputStream(str), Charset.defaultCharset());
		while (true) {
			ZipEntry e = zip.getNextEntry();
			if (e == null)
				break;
			String name = e.getName();
			System.out.println(name);
		}
		zip.close();
	}
	
	List<URLClassLoader> cls = new ArrayList<URLClassLoader>();
	
	public void addClass(String path, String name) throws Exception {
		URLClassLoader classLoader = new URLClassLoader(new URL[] {new File(path).toURI().toURL()});
		cls.add(classLoader);
		Class<?> c = classLoader.loadClass(name);
		Method m = c.getMethod("test");
		m.invoke(c.newInstance());
	}
	
	public static void main(String[] args) throws Exception {
		new ModLoader().addClass(Nullcraft.rootDir, "Test");
	}
}
