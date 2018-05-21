package ga.nullcraft.global.game.entity;

import ga.nullcraft.global.game.data.NullDataSet;
import ga.nullcraft.global.game.world.World;

public class EntityPlayer implements IEntity {
	
	private World world;
	
	private float posX;
	private float posY;
	private float posZ;
	private float rotX;
	private float rotY;
	private float rotZ;
	
	public EntityPlayer(World world, float spawnX, float spawnY, float spawnZ) {
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
		rotX = 0;
		rotY = 0;
		rotZ = 0;
	}
	
	public void movePosition(float offsetX, float offsetY, float offsetZ) {
        if ( offsetZ != 0 ) {
            posX += (float)Math.sin(Math.toRadians(rotY)) * -1.0f * offsetZ;
            posZ += (float)Math.cos(Math.toRadians(rotY)) * offsetZ;
        }
        if ( offsetX != 0) {
            posX += (float)Math.sin(Math.toRadians(rotY - 90)) * -1.0f * offsetX;
            posZ += (float)Math.cos(Math.toRadians(rotY - 90)) * offsetX;
        }
        posY += offsetY;
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
	
	public void setRotation(float x, float y, float z) {
		rotX = x;
		rotY = y;
		rotZ = z;
	}
	
	public void moveRotation(float offsetX, float offsetY, float offsetZ) {
		rotX += offsetX;
		rotY += offsetY;
		rotZ += offsetZ;
	}
	
	public float getRotX() {
		return rotX;
	}
	
	public float getRotY() {
		return rotY;
	}
	
	public float getRotZ() {
		return rotZ;
	}
	
	public World getWorld() {
		return world;
	}
}
