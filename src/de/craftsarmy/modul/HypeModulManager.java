package de.craftsarmy.modul;

import java.util.concurrent.ConcurrentLinkedQueue;

public class HypeModulManager {

    private static final ConcurrentLinkedQueue<IHypeModul> enabled = new ConcurrentLinkedQueue<>();

    public static void enable(HypeModul hypeModul, HypeModul... hypeModuls) {
        if (!enabled.contains(hypeModul.getHypeModul())) {
            enabled.add(hypeModul.getHypeModul());
            hypeModul.getHypeModul().onEnable();
        }
        for (HypeModul modul : hypeModuls)
            if (!enabled.contains(modul.getHypeModul())) {
                enabled.add(modul.getHypeModul());
                modul.getHypeModul().onEnable();
            }
    }

    @Deprecated(since = "2.5")
    public static void enableRaw(IHypeModul hypeModul) {
        if (!enabled.contains(hypeModul)) {
            enabled.add(hypeModul);
            hypeModul.onEnable();
        }
    }

    public static void disable(HypeModul hypeModul) {
        enabled.remove(hypeModul.getHypeModul());
        hypeModul.getHypeModul().onDisable();
    }

    @Deprecated(since = "2.5")
    public static void disableRaw(IHypeModul hypeModul) {
        enabled.remove(hypeModul);
        hypeModul.onDisable();
    }

}
