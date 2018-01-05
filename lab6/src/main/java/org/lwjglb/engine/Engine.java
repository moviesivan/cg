package org.lwjglb.engine;

public class Engine implements Runnable {

    public static final int TARGET_FPS = 75;

    public static final int TARGET_UPS = 30;

    private final Window window;

    private final Thread LoopThread;

    private final Timer timer;

    private final ILogic Logic;

    private final MouseInput mouseInput;

    public Engine(String windowTitle, int width, int height, boolean vSync, ILogic Logic) throws Exception {
        LoopThread = new Thread(this, "GAME_LOOP_THREAD");
        window = new Window(windowTitle, width, height, vSync);
        mouseInput = new MouseInput();
        this.Logic = Logic;
        timer = new Timer();
    }

    public void start() {
        String osName = System.getProperty("os.name");
        if ( osName.contains("Mac") ) {
            LoopThread.run();
        } else {
            LoopThread.start();
        }
    }

    @Override
    public void run() {
        try {
            init();
            Loop();
        } catch (Exception excp) {
            excp.printStackTrace();
        } finally {
            cleanup();
        }
    }

    protected void init() throws Exception {
        window.init();
        timer.init();
        mouseInput.init(window);
        Logic.init(window);
    }

    protected void Loop() {
        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;

        boolean running = true;
        while (running && !window.windowShouldClose()) {
            elapsedTime = timer.getElapsedTime();
            accumulator += elapsedTime;

            input();

            while (accumulator >= interval) {
                update(interval);
                accumulator -= interval;
            }

            render();

            if ( !window.isvSync() ) {
                sync();
            }
        }
    }

    protected void cleanup() {
        Logic.cleanup();
    }
    
    private void sync() {
        float loopSlot = 1f / TARGET_FPS;
        double endTime = timer.getLastLoopTime() + loopSlot;
        while (timer.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {
            }
        }
    }

    protected void input() {
        mouseInput.input(window);
        Logic.input(window, mouseInput);
    }

    protected void update(float interval) {
        Logic.update(interval, mouseInput);
    }

    protected void render() {
        Logic.render(window);
        window.update();
    }
}
