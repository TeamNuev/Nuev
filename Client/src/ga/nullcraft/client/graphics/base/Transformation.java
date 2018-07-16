package ga.nullcraft.client.graphics.base;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transformation {
	
	private final Matrix4f projectionMatrix;
    private final Matrix4f modelMatrix;
	private final Matrix4f modelViewMatrix;
	private final Matrix4f viewMatrix;
    private final Matrix4f orthoMatrix;
    private final Matrix4f orthoModelMatrix;
	
	public Transformation() {
		projectionMatrix = new Matrix4f();
		modelMatrix = new Matrix4f();
		modelViewMatrix = new Matrix4f();
		viewMatrix = new Matrix4f();
        orthoMatrix = new Matrix4f();
        orthoModelMatrix = new Matrix4f();
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
	
    public Matrix4f buildModelViewMatrix(NuevMeshItem gameItem, Matrix4f viewMatrix) {
        Vector3f rotation = gameItem.getRotation();
        modelMatrix.identity().translate(gameItem.getPosition()).
                rotateX((float)Math.toRadians(-rotation.x)).
                rotateY((float)Math.toRadians(-rotation.y)).
                rotateZ((float)Math.toRadians(-rotation.z)).
                scale(gameItem.getScale());
        modelViewMatrix.set(viewMatrix);
        return modelViewMatrix.mul(modelMatrix);
    }
    
	public Matrix4f buildOrtoProjModelMatrix(NuevMeshItem gameItem, Matrix4f orthoMatrix) {
		Vector3f rotation = gameItem.getRotation();
		modelMatrix.identity().translate(gameItem.getPosition()).rotateX((float) Math.toRadians(-rotation.x))
				.rotateY((float) Math.toRadians(-rotation.y)).rotateZ((float) Math.toRadians(-rotation.z))
				.scale(gameItem.getScale());
		orthoModelMatrix.set(orthoMatrix);
		orthoModelMatrix.mul(modelMatrix);
		return orthoModelMatrix;
	}
	
	public final Matrix4f getOrthoProjectionMatrix(float left, float right, float bottom, float top) {
		orthoMatrix.identity();
		orthoMatrix.setOrtho2D(left, right, bottom, top);
		return orthoMatrix;
	}
}
