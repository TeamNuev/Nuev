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
	private NuevShader shader;
	private int vaoId;
	private int vboId;
	
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
		
        float[] vertices = new float[]{
                0.0f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f
        };

        FloatBuffer verticesBuffer = null;
        
        try {
            verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
            verticesBuffer.put(vertices).flip();

            vaoId = GL30.glGenVertexArrays();
            GL30.glBindVertexArray(vaoId);

            vboId = GL15.glGenBuffers();
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesBuffer, GL15.GL_STATIC_DRAW);
            GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);

            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
            GL30.glBindVertexArray(0);
        } finally {
            if (verticesBuffer != null) {
                 MemoryUtil.memFree(verticesBuffer);
            }
        }

	}
	
	public void render(NuevWindow window) {
		window.clear();
		if (window.isResized()) {
            GL11.glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }
        
		shader.bind();
		shader.setUniform("projectionMatrix", projectionMatrix);
		
		GL30.glBindVertexArray(vaoId);
		GL20.glEnableVertexAttribArray(0);
		
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);
		
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		
		shader.unbind();
	}
	
	public void cleanup() {
		if(shader != null) {
			shader.cleanup();
		}
	    GL20.glDisableVertexAttribArray(0);

	    GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	    GL15.glDeleteBuffers(vboId);

	    GL30.glBindVertexArray(0);
	    GL30.glDeleteVertexArrays(vaoId);
	}
}
