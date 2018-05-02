package ga.nullcraft.global.mod.loader;

import ga.nullcraft.global.mod.WebMod;

/**
 * Loads mods.
 * 
 * @author TNuev
 *
 */
public class WebModLoader implements IWebModLoader {

	WebMod[] modList;
	
	public WebModLoader(WebMod[] modList) {
		this.modList = modList;
	}

	public void loadMods() {
		
	}
}
