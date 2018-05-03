package ga.nullcraft.client.window;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public class NuevWindow extends GameWindow {

	public static final String DEFAULT_TITLE = "Nuev";
	
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 500;

	public NuevWindow() {
		super(DEFAULT_TITLE, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	public NuevWindow(int width, int height) {
		super(DEFAULT_TITLE, width, height);
	}

	public NuevWindow(String title, int width, int height) {
		super(title, width, height);
	}

	void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if (!GLFW.glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure GLFW
		GLFW.glfwDefaultWindowHints(); // optional, the current window hints are already the default
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE); // the window will stay hidden after creation
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE); // the window will be resizable
		
		//For OSX
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);

		// Create the window
		if(FULL_SCREEN) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			window = GLFW.glfwCreateWindow(screenSize.width, screenSize.height, "Nuev", GLFW.glfwGetPrimaryMonitor(), MemoryUtil.NULL);
		} else {
			window = GLFW.glfwCreateWindow(WIDTH, HEIGHT, "Nuev", MemoryUtil.NULL, MemoryUtil.NULL);
		}
		if (window == MemoryUtil.NULL)
			throw new RuntimeException("Failed to create the GLFW window");
		
		GLFW.glfwSetFramebufferSizeCallback(window, (window, width, height) -> {
			this.WIDTH = width;
			this.HEIGHT = height;
			this.setResized(true);
		});
		
		// Setup a key callback. It will be called every time a key is pressed, repeated
		// or released.
		GLFW.glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE)
				GLFW.glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
		});

		// Get the thread stack and push a new frame
		try (MemoryStack stack = MemoryStack.stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			GLFW.glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

			// Center the window
			GLFW.glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2,
					(vidmode.height() - pHeight.get(0)) / 2);
		} // the stack frame is popped automatically
		
		if(mouseLocked == true) {
			GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);
		}
		// Make the OpenGL context current
		GLFW.glfwMakeContextCurrent(window);
		// Enable v-sync
		GLFW.glfwSwapInterval(1);
		
		// Set icon
		/*try {
			InputStream is = getClass().getClassLoader().getResourceAsStream("icon.png");
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			int nRead;
			byte[] data = new byte[16384];

			while ((nRead = is.read(data, 0, data.length)) != -1) {
			  buffer.write(data, 0, nRead);
			}

			buffer.flush();
			
			GLFWImage image = GLFWImage.malloc();
			image.set(767, 768, ByteBuffer.wrap(buffer.toByteArray()));
			GLFWImage.Buffer images = GLFWImage.malloc(1);
			images.put(0, image);
			
			GLFW.glfwSetWindowIcon(window, images);
			
			images.free();
			image.free();
		} catch (IOException e) {
		}*/

		// Make the window visible
		GLFW.glfwShowWindow(window);
		
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();
		
		// Set the clear color
		GL11.glClearColor(78.0F/255, 208.0F/255, 170.0F/255, 0.0F);

	}

	void loop() {
		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		
		GLFW.glfwSwapBuffers(window); // swap the color buffers

		// Poll for window events. The key callback above will only be
		// invoked during this call.
		GLFW.glfwPollEvents();
	}
	
	void close() {
		// Free the window callbacks and destroy the window
		Callbacks.glfwFreeCallbacks(window);
		GLFW.glfwDestroyWindow(window);

		// Terminate GLFW and free the error callback
		GLFW.glfwTerminate();
		GLFW.glfwSetErrorCallback(null).free();
	}

	public boolean isKeyReleased(int key) {
		return GLFW.glfwGetKey(window, key) == GLFW.GLFW_RELEASE;
	}
	
	public boolean isKeyPressed(int key) {
		return GLFW.glfwGetKey(window, key) == GLFW.GLFW_PRESS;
	}
	
	public boolean isKeyRepeated(int key) {
		return GLFW.glfwGetKey(window, key) == GLFW.GLFW_REPEAT;
	}
	
	public boolean windowShouldClose() {
		return GLFW.glfwWindowShouldClose(window);
	}
	
	public long getWindowHandle() {
		return window;
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	
	public boolean isFullScreen() {
		return FULL_SCREEN;
	}

	public void setScreenMode(boolean isFullScreen) {
		FULL_SCREEN = isFullScreen;
		GLFW.glfwDestroyWindow(window);
		init();
	}
	
	public boolean isResized() {
		return isResized;
	}
	
	public void setResized(boolean isResized) {
		this.isResized = isResized;
	}
	
	public void clear() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); // clear the framebuffer
	}
	
	public void setClearColor(float r, float g, float b, float alpha) {
		GL11.glClearColor(r, g, b, alpha);
	}

	@Override
	public WindowState getWindowState() {
		return null;
	}

	@Override
	public WindowMode getWindowMode() {
		return null;
	}

	@Override
	public void setWindowState(WindowState windowState) {

	}

	@Override
	public void setWindowMode(WindowMode windowMode) {

	}

	@Override
	public long getHandle() {
		return 0;
	}
}
