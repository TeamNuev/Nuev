package ga.nullcraft.global.game.world.chunk;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChunkProvider implements IChunkProvider {

	Map<BigInteger, Chunk> allChunkMap;
	List<Chunk> loadedChunks;
	
	ChunkProvider() {
		allChunkMap = new HashMap<BigInteger, Chunk>();
		loadedChunks = new ArrayList<Chunk>();
	}
	
	@Override
	public void loadChunk(BigInteger chunkX, BigInteger chunkY, BigInteger chunkZ) {
		
		
	}
	
	public BigInteger getMapIndex(BigInteger chunkX, BigInteger chunkY, BigInteger chunkZ) {
		BigInteger max = chunkX.max(chunkY).max(chunkZ);
		int i = 0;
		i += (chunkX.compareTo(BigInteger.ZERO) < 0) ? 4 : 0;
		i += (chunkY.compareTo(BigInteger.ZERO) < 0) ? 2 : 0;
		i += (chunkZ.compareTo(BigInteger.ZERO) < 0) ? 1 : 0;
		
		chunkX = chunkX.abs();
		chunkY = chunkY.abs();
		chunkZ = chunkZ.abs();
		
		BigInteger temp;
		if(chunkY.equals(max)) {
			BigInteger xzMax = chunkX.max(chunkZ);
			//max^3-max^2+xzMax^2-xzMax-chunkX+chunkZ+1
			temp =  max.pow(3).subtract(max.pow(2)).add(xzMax.pow(2)).subtract(xzMax).subtract(chunkX).add(chunkZ).add(BigInteger.ONE);
		} else {
			//max^3-3max^2+2max*chunkY+2max-chunkX-chunkY+chunkZ
			temp = max.pow(3).subtract(max.pow(2).multiply(new BigInteger("3"))).add(max.multiply(chunkY).multiply(new BigInteger("2"))).add(max.multiply(new BigInteger("2"))).subtract(chunkX).subtract(chunkY).add(chunkZ);
		}
		temp = temp.multiply(new BigInteger("8")).add(new BigInteger(Integer.toString(i)));
		return temp;
	}
}
