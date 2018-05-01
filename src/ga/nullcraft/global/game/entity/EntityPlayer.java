package ga.nullcraft.global.game.entity;

import ga.nullcraft.global.game.data.NullDataSet;

public class EntityPlayer implements IEntity {
	
	private float posX;
	private float posY;
	private float posZ;
	
	public EntityPlayer(float spawnX, float spawnY, float spawnZ) {
		posX = spawnX;
		posY = spawnY;
		posZ = spawnZ;
	}

	@Override
	public NullDataSet getDataSet() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPosition(float x, float y, float z) {
		posX = x;
		posY = y;
		posZ = z;
	}
	
	public void movePosition(float offsetX, float offsetY, float offsetZ) {
		posX += offsetX;
		posY += offsetY;
		posZ += offsetZ;
	}
	
	public float getX() {
		return posX;
	}
	
	public float getY() {
		return posY;
	}
	
	public float getZ() {
		return posZ;
	}
}
