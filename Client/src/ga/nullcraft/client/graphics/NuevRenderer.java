package ga.nullcraft.client.graphics;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import ga.nullcraft.client.resource.ShaderLoader;

public class NuevRenderer {
	
    private static final float FOV = (float) Math.toRadians(60.0f);
    private static final float Z_NEAR = 0.01f;
    private static final float Z_FAR = 1000.f;
	private NuevShader shader;
	private Transformation transformation;
	
	public NuevRenderer() {
		transformation = new Transformation();
	}
	
	public void init(NuevWindow window) throws Exception {
		shader = new NuevShader();
		ShaderLoader loader = new ShaderLoader();
		shader.createVertexShader(loader.loadShader("vertex.vs"));
		shader.createFragmentShader(loader.loadShader("fragment.fs"));
		shader.linkShader();
		
		shader.createUniform("projectionMatrix");
		shader.createUniform("modelViewMatrix");
	}
	
	public void render(NuevWindow window, ICamera camera, NuevMeshItem[] items) {
		window.clear();
		if (window.isResized()) {
            GL11.glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }
        
		shader.bind();
		
		Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
		shader.setUniform("projectionMatrix", projectionMatrix);
		
		Matrix4f viewMatrix = transformation.getViewMatrix(camera);
		
		for(NuevMeshItem item : items) {
			Matrix4f modelViewMatrix = transformation.getModelViewMatrix(item, viewMatrix);
			shader.setUniform("modelViewMatrix", modelViewMatrix);
			item.getMesh().render();
		}
		
		shader.unbind();
	}
	
	public void cleanup() {
		if(shader != null) {
			shader.cleanup();
		}
		
	}
}
