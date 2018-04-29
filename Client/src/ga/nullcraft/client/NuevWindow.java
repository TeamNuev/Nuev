package ga.nullcraft.client;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.nio.IntBuffer;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public class NuevWindow {
	
	private final int DEFAULT_WIDTH = 800;
	private final int DEFAULT_HEIGHT = 500;
	
	// The window handle
	private long window;
	private final int WIDTH;
	private final int HEIGHT;
	private boolean FULL_SCREEN;

	NuevWindow(int width, int height, boolean isFullScreen) {
		this.WIDTH = (width > 0) ? width : DEFAULT_WIDTH;
		this.HEIGHT = (height > 0) ? height : DEFAULT_HEIGHT;
		this.FULL_SCREEN = isFullScreen;
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

		// Make the OpenGL context current
		GLFW.glfwMakeContextCurrent(window);
		// Enable v-sync
		GLFW.glfwSwapInterval(1);

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
		// fk temp
		if (isKeyPressed(GLFW.GLFW_KEY_F11)) {
			setScreenMode(!FULL_SCREEN);
		}

		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); // clear the framebuffer

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
}
