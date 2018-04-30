package ga.nullcraft.client;

public class NuevGameLoop {
	
	private NullcraftClient game;
	private NuevWindow window;
	
	public NuevGameLoop(NullcraftClient game) {
		this.game = game;
		this.window = game.getWindow();
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
		game.input();
	}
	
	protected void updateGameState() {
		game.update();
	}
	
	protected void render() {
		game.render();
	}
}
