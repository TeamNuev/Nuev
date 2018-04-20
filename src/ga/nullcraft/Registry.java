package ga.nullcraft;

import java.util.List;

import ga.nullcraft.event.Event;
import ga.nullcraft.object.Block;
import ga.nullcraft.object.BlockWithData;
import ga.nullcraft.object.Entity;
import ga.nullcraft.object.GUI;
import ga.nullcraft.object.Item;

public class Registry {
	List<Item> itemRegistry;
	List<Block> blockRegistry;
	List<BlockWithData> dataBlockRegistry;
	List<Entity> entityRegistry;
	List<Event> eventRegistry;
	List<GUI> guiRegistry;
}
