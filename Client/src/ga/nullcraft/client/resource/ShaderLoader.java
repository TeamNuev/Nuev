package ga.nullcraft.client.resource;

import java.io.InputStream;
import java.util.Scanner;

public class ShaderLoader {
	public String loadShader(String shaderName) throws Exception {
		String result;
		try(InputStream in = Class.forName(ShaderLoader.class.getName()).getResourceAsStream("/main/resources/shaders/" + shaderName);
				Scanner scanner = new Scanner(in, "UTF-8")) {
			result = scanner.useDelimiter("\\A").next();
			//System.out.println("Loaded " + shaderName);
		}
		return result;
	}
}
