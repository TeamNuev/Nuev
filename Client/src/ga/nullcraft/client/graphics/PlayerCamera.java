package ga.nullcraft.client.graphics;

import com.sun.javafx.geom.Vec3f;

import ga.nullcraft.global.game.entity.EntityPlayer;

public class PlayerCamera {
	
	private final EntityPlayer player;
	
	public PlayerCamera(EntityPlayer player) {
		this.player = player;
	}
	
	public Vec3f getPosition() {
		return new Vec3f(player.getX(), player.getY(), player.getZ());
	}
	
	public void setPositon(float x, float y, float z) {
		player.setPosition(x, y, z);
	}
	
	public void movePosition(float offsetX, float offsetY, float offsetZ) {
		player.movePosition(offsetX, offsetY, offsetZ);
		//System.out.println("Player moved to " + getPosition().x + ", " + getPosition().y + ", " + getPosition().z);
	}
}
