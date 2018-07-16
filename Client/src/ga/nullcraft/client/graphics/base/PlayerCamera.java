package ga.nullcraft.client.graphics.base;

import org.joml.Vector3f;

import ga.nullcraft.global.game.entity.EntityPlayer;

public class PlayerCamera implements ICamera {
	
	private final EntityPlayer player;
	boolean isLocked = true;
	
	public PlayerCamera(EntityPlayer player) {
		this.player = player;
	}
	
	@Override
	public Vector3f getPosition() {
		return new Vector3f(player.getX(), player.getY(), player.getZ());
	}
	
	@Override
	public void setPosition(float x, float y, float z) {
		player.setPosition(x, y, z);
	}
	
	@Override
	public void movePosition(float offsetX, float offsetY, float offsetZ) {
		player.movePosition(offsetX, offsetY, offsetZ);
		//System.out.println("Player moved to " + getPosition().x + ", " + getPosition().y + ", " + getPosition().z);
	}
	
	@Override
	public Vector3f getRotation() {
		return new Vector3f(player.getRotX(), player.getRotY(), player.getRotZ());
	}

	@Override
	public void setRotation(float x, float y, float z) {
		player.setRotation(x, y, z);
	}

	@Override
	public void moveRotation(float offsetX, float offsetY, float offsetZ) {
		if(!isLocked) {
			player.moveRotation(offsetX, offsetY, offsetZ);
		}
	}

	@Override
	public void setLock(boolean lock) {
		this.isLocked = lock;
	}
}
