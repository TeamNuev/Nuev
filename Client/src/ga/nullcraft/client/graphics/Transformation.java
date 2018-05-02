package ga.nullcraft.client.graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transformation {
	
	private final Matrix4f projectionMatrix;
	private final Matrix4f modelViewMatrix;
	private final Matrix4f viewMatrix;
	
	public Transformation() {
		projectionMatrix = new Matrix4f();
		modelViewMatrix = new Matrix4f();
		viewMatrix = new Matrix4f();
	}
	
	public Matrix4f getProjectionMatrix(float fov, float width, float height, float znear, float zfar) {
		float aspectRatio = width / height;
		projectionMatrix.identity();
		projectionMatrix.perspective(fov, aspectRatio, znear, zfar);
		return projectionMatrix;
	}
	
	public Matrix4f getViewMatrix(ICamera camera) {
		Vector3f cameraPos = camera.getPosition();
		Vector3f rotation = camera.getRotation();
		
		viewMatrix.identity();
	    viewMatrix.rotate((float)Math.toRadians(rotation.x), new Vector3f(1, 0, 0)).rotate((float)Math.toRadians(rotation.y), new Vector3f(0, 1, 0));
		viewMatrix.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
		return viewMatrix;
	}
	
	public Matrix4f getModelViewMatrix(NuevMeshItem item, Matrix4f viewMatrix) {
		Vector3f rotation = item.getRotation();
	    modelViewMatrix.identity().translate(item.getPosition())
	    	.rotateX((float)Math.toRadians(-rotation.x))
	    	.rotateY((float)Math.toRadians(-rotation.y))
	    	.rotateZ((float)Math.toRadians(-rotation.z))
	    	.scale(item.getScale());
	    Matrix4f viewCurr = new Matrix4f(viewMatrix);
		return viewCurr.mul(modelViewMatrix);	
	}
}
