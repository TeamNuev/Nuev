package ga.nullcraft.global.game.world.chunk;

import ga.nullcraft.global.game.block.Block;

public class Chunk implements IChunk {

	Block[][][] blocks = new Block[16][16][16];
	
	public void placeBlock(Block block, int x, int y, int z) {
		blocks[x][y][z] = block;
	}
}
