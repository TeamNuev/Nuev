package ga.nullcraft.global.game.world.chunk;

import java.math.BigInteger;

public interface IChunkProvider {
	
	public void loadChunk(BigInteger chunkX, BigInteger chunkY, BigInteger chunkZ);
}
