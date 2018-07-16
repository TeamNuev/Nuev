package ga.nullcraft.client.resource;

import java.util.List;

import ga.nullcraft.client.graphics.basis.TextureLoader;
import ga.nullcraft.global.game.block.Block;
import ga.nullcraft.global.mod.loader.LocalFullModLoader;
import ga.nullcraft.global.storage.ModStorage;

public class ClientModLoader extends LocalFullModLoader {

	TextureLoader textureLoader;

	public ClientModLoader(ModStorage modStorage) {
		super(modStorage);
	}

	@Override
	public void loadMods() {
		super.loadMods();

	}

	protected void register(String registryName, Object object) {
		// Load block textures
		if (registryName.equals("blocks")) {
			if (object instanceof Block) {
				Block block = (Block) object;
				List<String[]> textures = block.getTextureNameList();
				for (String[] textureArray : textures) {
					for (String texture : textureArray) {
						
					}
				}
			} else {

			}
		}
	}

}
