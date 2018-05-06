package ga.nullcraft.client.audio;

import ga.nullcraft.client.window.WindowManager; /**
 * main game audio system
 *
 * @author TNuev
 */
public class AudioManager implements Runnable {

    private WindowManager windowManager;

    public AudioManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    public WindowManager getWindowManager() {
        return windowManager;
    }

    @Override
    public void run() {

    }
}
