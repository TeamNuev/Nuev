package ga.nullcraft.client.graphics;

import org.lwjgl.opengl.GL20;

public class NuevShader {
	
	private final int programId;
	private int vertexShaderId;
	private int fragmentShaderId;
	
	public NuevShader() throws Exception {
		programId = GL20.glCreateProgram();
		if(programId == 0) {
			throw new Exception("Program failed");
		}
	}
	
	public void createVertexShader(String shaderCode) throws Exception {
		vertexShaderId = createShader(shaderCode, GL20.GL_VERTEX_SHADER);
	}
	
	public void createFragmentShader(String shaderCode) throws Exception {
		fragmentShaderId = createShader(shaderCode, GL20.GL_FRAGMENT_SHADER);
	}
	
	protected int createShader(String shaderCode, int shaderType) throws Exception {
		int shaderId = GL20.glCreateShader(shaderType);
		if(shaderId == 0) {
			throw new Exception("Shader creating failed");
		}
		GL20.glShaderSource(shaderId, shaderCode);
		GL20.glCompileShader(shaderId);
		if(GL20.glGetShaderi(shaderId, GL20.GL_COMPILE_STATUS) == 0) {
			throw new Exception("Shader compiling failed");
		}
		GL20.glAttachShader(programId, shaderId);
		return shaderId;
	}
	
	public void linkShader() throws Exception {
		GL20.glLinkProgram(programId);
		if(GL20.glGetProgrami(programId, GL20.GL_LINK_STATUS) == 0) {
			throw new Exception("Shader linking failed");
		}
		if(vertexShaderId != 0) {
			GL20.glDetachShader(programId, vertexShaderId);
		}
		if(fragmentShaderId != 0) {
			GL20.glDetachShader(programId, fragmentShaderId);
		}
	}
	
	public void bind() {
		GL20.glUseProgram(programId);
	}
	
	public void unbind() {
		GL20.glUseProgram(0);
	}
	
	public void cleanup() {
		unbind();
		if(programId != 0) {
			GL20.glDeleteProgram(programId);
		}
	}
}
