package de.craftsarmy.hyperapi.threading;

public class HypeThread {

    protected static int count = 1;

    private final HypeRunnable hypeRunnable;
    private final String threadName;
    private long timeUntilDeath;

    protected Thread thread;

    public HypeThread(HypeRunnable hypeRunnable) {
        this(hypeRunnable, "HypeRuntime-Thread " + count, -1);
        count += 1;
    }

    public HypeThread(HypeRunnable hypeRunnable, String threadName) {
        this(hypeRunnable, threadName, -1);
    }

    public HypeThread(HypeRunnable hypeRunnable, long timeUntilDeath) {
        this(hypeRunnable, "HypeRuntime-Thread " + count, timeUntilDeath);
        count += 1;
    }

    public HypeThread(HypeRunnable hypeRunnable, String threadName, long timeUntilDeath) {
        this.hypeRunnable = hypeRunnable;
        this.threadName = threadName;
        this.timeUntilDeath = timeUntilDeath;
    }

    public void start() {
        thread = new Thread(hypeRunnable);
        if (timeUntilDeath >= 1)
            hypeRunnable.register(this, hypeThread -> {
                timeUntilDeath -= 1;
                if (timeUntilDeath <= 0)
                    hypeThread.stop();
            });
        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }

    public void registerTask(IHypeTask hypeTask) {
        hypeRunnable.register(this, hypeTask);
    }
    public void unregisterTask(IHypeTask hypeTask) {
        hypeRunnable.unregister(hypeTask);
    }
    public HypeRunnable getHypeRunnable() {
        return hypeRunnable;
    }
}
