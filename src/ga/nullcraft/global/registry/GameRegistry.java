package ga.nullcraft.global.registry;

/**
<<<<<<< HEAD
 * GameRegistry는 한 namespace를 사용 하는 모든
 * 모드의 아이템, 블록, 엔디티 들의 정보를 갖습니다.
 *
 * @author      storycraft
 */
public class GameRegistry implements IRegistry {

    private String namespace;

    public GameRegistry(String namespace){
        this.namespace = namespace;
    }

    public String getNamespace() {
        return namespace;
    }
}
