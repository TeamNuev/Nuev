package ga.nullcraft.client.graphics.base;

import org.joml.Vector3f;

public interface ICamera {
	
	public Vector3f getPosition();
	
	public void setPosition(float x, float y, float z);
	
	public void movePosition(float offsetX, float offsetY, float offsetZ);
	
	public Vector3f getRotation();
	
	public void setRotation(float x, float y, float z);
	
	public void moveRotation(float offsetX, float offsetY, float offsetZ);

	public void setLock(boolean b);
}
