package ga.nullcraft.client.graphics;

import ga.nullcraft.client.window.GameWindow;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLUtil;

import ga.nullcraft.client.resource.ShaderLoader;

public class NuevRenderer {
	
    private static final float FOV = (float) Math.toRadians(60.0f);
    private static final float Z_NEAR = 0.01f;
    private static final float Z_FAR = 1000.f;
	private NuevShader sceneShader;
	private Transformation transformation;
	private NuevShader hudShader;
	ShaderLoader loader;
	
	public NuevRenderer() {
		transformation = new Transformation();
		loader = new ShaderLoader();
	}
	
	public void init(GameWindow window) throws Exception {
		setUpSceneShader();
		setUpHudShader();
	}
	
	public void clear() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	public void renderScene(GameWindow window, ICamera camera, NuevMeshItem[] items) {
		clear();
		
		sceneShader.bind();
		
		Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
		sceneShader.setUniform("projectionMatrix", projectionMatrix);
		
		Matrix4f viewMatrix = transformation.getViewMatrix(camera);
		
		for(NuevMeshItem item : items) {
			Matrix4f modelViewMatrix = transformation.getModelViewMatrix(item, viewMatrix);
			sceneShader.setUniform("modelViewMatrix", modelViewMatrix);
			item.getMesh().render();
		}
		
		sceneShader.unbind();
	}
	
	public void renderHud(GameWindow window, IHud hud) {
		hudShader.bind();

        Matrix4f ortho = transformation.getOrthoProjectionMatrix(0, window.getWidth(), window.getHeight(), 0);
        for (NuevMeshItem gameItem : hud.getMeshItems()) {
            Mesh mesh = gameItem.getMesh();
            // Set ortohtaphic and model matrix for this HUD item
            Matrix4f projModelMatrix = transformation.buildOrtoProjModelMatrix(gameItem, ortho);
            hudShader.setUniform("projModelMatrix", projModelMatrix);

            // Render the mesh for this HUD item
            mesh.render();
        }

        hudShader.unbind();
	}
		
	private void setUpSceneShader() throws Exception {
		sceneShader = new NuevShader();
		sceneShader.createVertexShader(loader.loadShader("vertex.vs"));
		sceneShader.createFragmentShader(loader.loadShader("fragment.fs"));
		sceneShader.linkShader();
		
		sceneShader.createUniform("projectionMatrix");
		sceneShader.createUniform("modelViewMatrix");
	}
	
	private void setUpHudShader() throws Exception {
		hudShader = new NuevShader();
		hudShader.createVertexShader(loader.loadShader("vertex.vs"));
		hudShader.createFragmentShader(loader.loadShader("vertex.vs"));
		hudShader.linkShader();
		
		// Create uniforms for Ortographic-model projection matrix and base colour
		hudShader.createUniform("projModelMatrix");
		hudShader.createUniform("colour");
	}
	
	public void cleanUp() {
		if(sceneShader != null) {
			sceneShader.cleanUp();
		}
		if(hudShader != null) {
			hudShader.cleanUp();
		}
	}
}
