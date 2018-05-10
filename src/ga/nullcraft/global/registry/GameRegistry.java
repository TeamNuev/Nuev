package ga.nullcraft.global.registry;

import ga.nullcraft.global.game.block.IBlock;
import ga.nullcraft.global.game.entity.IEntity;
import ga.nullcraft.global.game.item.IItem;

import java.util.HashMap;
import java.util.Map;

/**
 * Has information of items, blocks, or entities and so on
 * in all mods which uses same namespace.
 *
 * @author TNuev
 */
@Deprecated
public class GameRegistry implements IRegistry {

    Map<String, IItem> itemMap;
    Map<String, IBlock> blockMap;
    Map<String, IEntity> entityMap;

    private String namespace;

    public GameRegistry(String namespace){
        this.namespace = namespace;

        this.itemMap = new HashMap<>();
        this.blockMap = new HashMap<>();
        this.entityMap = new HashMap<>();
    }

    public void addItem(String name, IItem item){
        if (itemMap.containsKey(item))
            return;

        itemMap.put(name, item);
    }

    public void addBlock(String name, IBlock block){
        if (blockMap.containsKey(block))
            return;

        blockMap.put(name, block);
    }

    public void addEntity(String name, IEntity entity){
        if (blockMap.containsKey(entity))
            return;

        entityMap.put(name, entity);
    }

    public IItem getItem(String name){
        return itemMap.get(name);
    }

    public IBlock getBlock(String name){
        return blockMap.get(name);
    }

    public IEntity getEntity(String name){
        return entityMap.get(name);
    }

    public String getNamespace() {
        return namespace;
    }
}
