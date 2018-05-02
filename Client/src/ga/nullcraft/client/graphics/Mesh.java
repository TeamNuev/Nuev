package ga.nullcraft.client.graphics;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

public class Mesh {
	
	private final int vaoId;
	private final int vboId;
	private final int vertexCount;
	
	public Mesh(float[] positions) {
		FloatBuffer verticesBuffer = null;
		try {
            verticesBuffer = MemoryUtil.memAllocFloat(positions.length);
            vertexCount = positions.length / 3;
            verticesBuffer.put(positions).flip();

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
	
	public int getVaoId() {
		return vaoId;
	}
	
	public int getVertexCount() {
		return vertexCount;
	}
	
	public void cleanup() {
	    GL20.glDisableVertexAttribArray(0);

	    GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	    GL15.glDeleteBuffers(vboId);

	    GL30.glBindVertexArray(0);
	    GL30.glDeleteVertexArrays(vaoId);
	}
}
