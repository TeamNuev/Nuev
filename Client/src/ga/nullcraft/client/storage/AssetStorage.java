package ga.nullcraft.client.storage;

import ga.nullcraft.global.storage.Storage;

import java.io.*;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Mainly saves resource files.
 * key is the path of a resource file.
 * The file is saved in [first two letters of the hash value of key]/[the hash value of key] 
 *
 * @see Storage
 * @author TNuev
 */
public class AssetStorage extends Storage<byte[]> {

    private Path path;

    public AssetStorage(Path path){
        this.path = path;
    }

    /**
     * Saves a file in the game assets directory.
     *
     * @param name with a path of a file which is going to be saved.
     * @return true if succeed
     */
    @Override
    public boolean saveSync(byte[] data, String name) throws IOException {
        try {
            String hash = getHashsum(name);
            String prefix = hash.substring(0, 1);

            File file = path.resolve(prefix).resolve(hash).toFile();
            OutputStream writer = new FileOutputStream(file);

            writer.write(data);
            writer.close();
        } catch (NoSuchAlgorithmException e) {
            return false;
        }

        return true;
    }

    /**
     * Load a file in game assets directory.
     *
     * @param name with a path of a file which is going to be saved.
     * @return asset file. null if not exists.
     */
    @Override
    public byte[] getSync(String name) throws IOException {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            String hash = getHashsum(name);
            String prefix = hash.substring(0, 1);

            File file = path.resolve(prefix).resolve(hash).toFile();
            if (!file.exists())
                return null;

            FileInputStream input = new FileInputStream(file);

            int readed = 0;
            byte[] buffer = new byte[2048];
            while ((readed = input.read(buffer)) != -1)
                out.write(buffer, 0, readed);

            return out.toByteArray();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * Makes hash value from path.
     * 
     * @param path to be made into hash value.
     * @return hash value made from path.
     * @throws NoSuchAlgorithmException
     */
    private String getHashsum(String path) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(path.getBytes());

        byte[] rawHash = md5.digest();
        StringBuffer sb = new StringBuffer();
        for (byte data : rawHash)
            sb.append(Integer.toString((data & 0xff) + 0x100, 16).substring(1));

        return sb.toString();
    }
}
