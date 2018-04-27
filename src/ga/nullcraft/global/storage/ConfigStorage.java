package ga.nullcraft.global.storage;

import ga.nullcraft.global.mod.IMod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigStorage extends Storage<File> {

    private Path path;
    private IMod mod;

    public ConfigStorage(Path path, IMod mod){
        this.path = path;
        this.mod = mod;
    }

    /**
     * 해당 모드 config 저장소 경로에 name 이름의 파일을 저장합니다
     *
     * @param  name 저장할 파일의 이름
     * @return      성공시 true, 실패시 false 반환
     */
    @Override
    public boolean saveSync(File object, String name) throws IOException {
        return false;
    }

    /**
     * 해당 모드 config 저장소 경로에서 name 이름의 파일을 불러옵니다
     *
     * @param  name 불러올 파일의 이름
     * @return      name 이름을 가진 파일. 없을시 null 반환
     */
    @Override
    public File getSync(String name) throws IOException {
        return null;
    }
}
