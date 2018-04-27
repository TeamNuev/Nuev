package ga.nullcraft.client.storage;

import ga.nullcraft.global.storage.Storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * AssetStorage에서는 주로 리소스 파일들을 저장 할수 있는 저장소 입니다
 * key 는 리소스 파일의 경로이며, 외부적으로는 key의 해시 값이 있을때
 * (해시값 1~2 자리)/(해시값) 경로로 저장되게 됩니다
 *
 * @see         Storage
 * @author      storycraft
 */
public class AssetStorage extends Storage<File> {

    private Path path;

    public AssetStorage(Path path){
        this.path = path;
    }

    /**
     * 게임의 asset 저장소에 name 이름의 파일을 저장합니다
     *
     * @param  name 저장할 파일의 경로를 포함한 이름
     * @return      성공시 true, 실패시 false 반환
     */
    @Override
    public boolean saveSync(File object, String name) throws IOException {
        return false;
    }

    /**
     * 게임의 asset 저장소에 name 이름의 파일을 로드 합니다
     *
     * @param  name 저장할 파일의 경로를 포함한 이름
     * @return      asset 파일을 반환. 없을시 null 반환
     */
    @Override
    public File getSync(String name) throws IOException {
        return null;
    }
}
