package ga.nullcraft.client;

import ga.nullcraft.client.graphics.MouseInput;

public class NuevGameLoop implements Runnable {
	
	private final NullcraftClient game;
	private final NuevWindow window;
	private final Thread gameLoopThread;
	private final MouseInput mouseInput;
	
	public NuevGameLoop(NullcraftClient game) {
		gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");
		this.game = game;
		this.window = game.getWindow();
		mouseInput = new MouseInput();
	}
	
	public void start() {
		String osName = System.getProperty("os.name");
		if(osName.contains("mac")) {
			gameLoopThread.run();
		}
		else {
			gameLoopThread.start();
		}
	}
	
	@Override
	public void run() {
		try {
			init();
			gameLoop();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanup();
		}
	}
	
	protected void init() throws Exception {
		mouseInput.init(window);
		game.init();
	}
	
	protected void gameLoop() {
		double secsPerUpdate = 1000000000.0d / 30.0d;
		long previous = System.nanoTime();
		long steps = 0;
		while (!window.windowShouldClose()) {
			long loopStartTime = System.nanoTime();
			long elapsed = loopStartTime - previous;
			previous = loopStartTime;
			steps += elapsed;

			handleInput();

			while (steps >= secsPerUpdate) {
				updateGameState();
				steps -= secsPerUpdate;
			}
			
			render();
			window.loop();
			sync(loopStartTime);
		}
	}
	
	private void sync(long loopStartTime) {
		float loopSlot = 1f / 50;
		double endTime = loopStartTime + loopSlot; 
		while(System.nanoTime() < endTime) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {}
		}
	}
	
	protected void handleInput() {
		mouseInput.input(window);
		game.input(mouseInput);
	}
	
	protected void updateGameState() {
		game.update(mouseInput);
	}
	
	protected void render() {
		game.render();
	}
	
	protected void cleanup() {
		game.cleanup();
	}
}
