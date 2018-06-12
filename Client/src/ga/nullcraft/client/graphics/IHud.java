package ga.nullcraft.client.graphics;

public interface IHud {

	NuevMeshItem[] getMeshItems();
	
    default void cleanup() {
    	NuevMeshItem[] gameItems = getMeshItems();
        for (NuevMeshItem gameItem : gameItems) {
            gameItem.getMesh().cleanUp();
        }
    }
}
