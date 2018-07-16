package ga.nullcraft.global.game.block;

import java.util.ArrayList;
import java.util.List;

public class Block implements IBlock {

	private String unlocalizedName;
	private List<String[]> textureNameList = new ArrayList<String[]>();
	
	Block(String unlocalizedName) {
		this.unlocalizedName = unlocalizedName;
	}
	
	public Block setTextureName(String... textureNames) {
		String[] textures = null;
		for(int i = 0; i < textureNames.length; i++) {
			if(i%6 == 0) {
				if(textures != null)
					textureNameList.add(textures);
				textures = new String[6];
			}
			textures[i%6] = textureNames[i%6];
		}
		return this;
	}
	
	public List<String[]> getTextureNameList() {
		return textureNameList;
	}
	
	public String getUnlocalizedName() {
		return unlocalizedName;
	}
}
