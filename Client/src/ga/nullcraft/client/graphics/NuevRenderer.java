package ga.nullcraft.client.graphics;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import ga.nullcraft.client.NuevWindow;
import ga.nullcraft.client.resource.ShaderLoader;

public class NuevRenderer {
	
    private static final float FOV = (float) Math.toRadians(60.0f);
    private static final float Z_NEAR = 0.01f;
    private static final float Z_FAR = 1000.f;
    private Matrix4f projectionMatrix;
    private Matrix4f viewMatrix;
	private NuevShader shader;
	
	public NuevRenderer() {
		
	}
	
	public void init(NuevWindow window) throws Exception {
		shader = new NuevShader();
		ShaderLoader loader = new ShaderLoader();
		shader.createVertexShader(loader.loadShader("vertex.vs"));
		shader.createFragmentShader(loader.loadShader("fragment.fs"));
		shader.linkShader();
		
		float aspectRatio = (float) window.getWidth() / (float) window.getHeight();
		projectionMatrix = new Matrix4f().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
		shader.createUniform("projectionMatrix");
	}
	
	public void render(NuevWindow window, Mesh mesh) {
		window.clear();
		if (window.isResized()) {
            GL11.glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }
        
		shader.bind();
		shader.setUniform("projectionMatrix", projectionMatrix);
		
		GL30.glBindVertexArray(mesh.getVaoId());
		GL20.glEnableVertexAttribArray(0);
		
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, mesh.getVertexCount());
		
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		
		shader.unbind();
	}
	
	public void cleanup() {
		if(shader != null) {
			shader.cleanup();
		}
		
	}
}
