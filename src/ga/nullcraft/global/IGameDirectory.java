package ga.nullcraft.global;

import ga.nullcraft.global.mod.IMod;
import ga.nullcraft.global.storage.ConfigStorage;
import ga.nullcraft.global.storage.ModStorage;
import ga.nullcraft.global.storage.WorldStorage;

/**
 * NullcraftServer는 Nullcraft의 서버 추상 클래스 입니다.
 * getGameDirectory 메서드를 오버라이드하여 서버에서 사용할
 * GameDirectory를 지정해 줄수 있습니다
 *
 * @author      storycraft
 */
public interface IGameDirectory {
    ConfigStorage getConfigStorage(IMod mod);

    WorldStorage getWorldStorage();

    ModStorage getModStorage();
}

