package de.craftsarmy.hyperapi.threading;

import java.util.concurrent.ConcurrentLinkedQueue;

public class HypeRunnable implements Runnable {

    private final ConcurrentLinkedQueue<IHypeTask> hypeTasks = new ConcurrentLinkedQueue<>();
    private HypeThread hypeThread;

    @Override
    public void run() {
        while (true) {
            for(IHypeTask hypeTask : hypeTasks) {
                try {
                    hypeTask.run(this.hypeThread);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void register(HypeThread hypeThread, IHypeTask hypeTask) {
        if(this.hypeThread == null)
            this.hypeThread = hypeThread;
        if(!hypeTasks.contains(hypeTask))
            hypeTasks.add(hypeTask);
    }

    public void unregister(IHypeTask hypeTask) {
        hypeTasks.remove(hypeTask);
    }

}
